package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.content.entity.goal.BeeMoveToFruitBushGoal;
import net.bi83.bonappetit.core.content.entity.goal.BeePollinateFruitGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

@Mod(value = BonAppetit.ID) @EventBusSubscriber(modid = BonAppetit.ID)
public class BAEvents {
    private static final List<CherryEcho> ECHO_QUEUE = new ArrayList<>();
    private static final Map<UUID, Integer> MELLOW_STILL_TICKS = new HashMap<>();
    private static final Map<UUID, Integer> TWIN_STRIKE_COOLDOWN = new HashMap<>();

    private record CherryEcho(LivingEntity attacker, LivingEntity victim, float damage, int timer) {}

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;
        UUID uuid = player.getUUID();

        // --- MELLOW TRACKER ---
        if (player.hasEffect(BAEffects.MELLOW)) {
            boolean isStill = player.getDeltaMovement().horizontalDistanceSqr() < 0.001;
            if (isStill && player.onGround()) {
                int ticks = MELLOW_STILL_TICKS.merge(uuid, 1, Integer::sum);
                if (ticks % 20 == 0) {
                    ((ServerLevel)player.level()).sendParticles(ParticleTypes.END_ROD,
                            player.getX(), player.getY() + 0.1, player.getZ(), 1, 0.2, 0, 0.2, 0.01);
                }
            } else {
                MELLOW_STILL_TICKS.put(uuid, 0);
            }
        } else {
            MELLOW_STILL_TICKS.remove(uuid);
        }

        // --- TWIN STRIKE COOLDOWN ENGINE ---
        if (TWIN_STRIKE_COOLDOWN.containsKey(uuid)) {
            int time = TWIN_STRIKE_COOLDOWN.get(uuid);
            if (time > 0) TWIN_STRIKE_COOLDOWN.put(uuid, time - 1);
        }
    }

    @SubscribeEvent
    public static void handleFruitCombat(LivingIncomingDamageEvent event) {
        if (event.getEntity().level().isClientSide) return;

        LivingEntity victim = event.getEntity();
        float damage = event.getAmount();

        // 1. MANGO: INSATIABLE
        MobEffectInstance mangoEffect = victim.getEffect(BAEffects.INSATIABLE);
        if (mangoEffect != null && victim instanceof Player player) {
            int mangoAmplifier = mangoEffect.getAmplifier();
            if (player.getFoodData().getFoodLevel() >= 6) {
                event.setAmount(damage * (0.7f - (mangoAmplifier * 0.1f)));
                player.getFoodData().addExhaustion(1.0f);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.5f, 1.3f);
            }
        }

        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            UUID attackerUUID = attacker.getUUID();

            // 2. CHERRY: TWIN STRIKE (Cooldown + Charge Check)
            MobEffectInstance cherryEffect = attacker.getEffect(BAEffects.TWIN_STRIKE);
            if (cherryEffect != null && !event.getSource().is(BADamageTypes.TWIN_HIT)) {
                int cherryAmplifier = cherryEffect.getAmplifier();

                float charge = (attacker instanceof Player p) ? p.getAttackStrengthScale(0.5f) : 1.0f;
                int cooldown = TWIN_STRIKE_COOLDOWN.getOrDefault(attackerUUID, 0);

                if (charge >= 0.9f && cooldown <= 0) {
                    ECHO_QUEUE.add(new CherryEcho(attacker, victim, ((damage * 0.35f) + (cherryAmplifier * 0.1f)), 5));
                    TWIN_STRIKE_COOLDOWN.put(attackerUUID, 5);

                    attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                            SoundEvents.CHERRY_SAPLING_PLACE, SoundSource.PLAYERS, 0.8f, 0.5f);
                }
            }

            // 3. PEAR: MELLOW
            MobEffectInstance pearEffect = attacker.getEffect(BAEffects.MELLOW);
            if (pearEffect != null && attacker instanceof Player player) {
                int pearAmplifier = pearEffect.getAmplifier();
                int stillTicks = MELLOW_STILL_TICKS.getOrDefault(attackerUUID, 0);
                if (stillTicks > 20) {
                    float bonus = Math.min((float)stillTicks / 20f, (5f + pearAmplifier * 2.5f));
                    event.setAmount(damage + bonus);
                    MELLOW_STILL_TICKS.put(attackerUUID, 0);
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.6f, 0.5f);
                }
            }

            // 4. POMEGRANATE: PROLIFERATE
            MobEffectInstance pomegranateEffect = attacker.getEffect(BAEffects.PROLIFERATE);
            if (pomegranateEffect != null) {
                int pomegranateAmplifier = pomegranateEffect.getAmplifier();
                MobEffectInstance seeded = victim.getEffect(BAEffects.SEEDED);
                int stacks = (seeded == null) ? 0 : seeded.getAmplifier() + 1;

                if (stacks >= 3) {
                    victim.removeEffect(BAEffects.SEEDED);
                    victim.hurt(victim.level().damageSources().magic(), 4.0f + (pomegranateAmplifier * 1.5f));
                    victim.level().playSound(null, victim.getX(), victim.getY(), victim.getZ(),
                            SoundEvents.CHERRY_WOOD_BREAK, SoundSource.HOSTILE, 1.5f, 0.8f);
                    if (victim.level() instanceof ServerLevel sl) {
                        sl.sendParticles(ParticleTypes.CRIMSON_SPORE, victim.getX(), victim.getY() + 1, victim.getZ(), 30, 0.3, 0.3, 0.3, 0.1);
                        sl.sendParticles(ParticleTypes.EXPLOSION, victim.getX(), victim.getY() + 1, victim.getZ(), 1, 0, 0, 0, 0);
                    }
                } else {
                    victim.addEffect(new MobEffectInstance(BAEffects.SEEDED, 100, stacks));
                    victim.level().playSound(null, victim.getX(), victim.getY(), victim.getZ(),
                            SoundEvents.CHERRY_SAPLING_STEP, SoundSource.PLAYERS, 0.5f, 1.2f);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (ECHO_QUEUE.isEmpty()) return;
        for (int i = ECHO_QUEUE.size() - 1; i >= 0; i--) {
            CherryEcho echo = ECHO_QUEUE.get(i);
            if (echo.timer <= 0) {
                if (echo.victim.isAlive() && !echo.victim.isRemoved()) {
                    echo.victim.invulnerableTime = 0;
                    echo.victim.hurtDuration = 0;
                    echo.victim.hurt(echo.victim.level().damageSources().source(BADamageTypes.TWIN_HIT, echo.attacker), echo.damage);
                    echo.victim.level().playSound(null, echo.victim.getX(), echo.victim.getY(), echo.victim.getZ(),
                            SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.6f, 1.3f);
                    if (echo.victim.level() instanceof ServerLevel sl) {
                        sl.sendParticles(ParticleTypes.SWEEP_ATTACK, echo.victim.getX(), echo.victim.getY() + 1, echo.victim.getZ(), 1, 0, 0, 0, 0);
                    }
                }
                ECHO_QUEUE.remove(i);
            } else {
                ECHO_QUEUE.set(i, new CherryEcho(echo.attacker, echo.victim, echo.damage, echo.timer - 1));
            }
        }
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, BAItems.GOLDEN_STRAWBERRIES.get(), Potions.REGENERATION);
    }

    @SubscribeEvent
    public static void vanillaFoodEffects(net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        Item food = event.getItem().getItem();
        FoodProperties vanillaFoodChanges = BAItems.VANILLA_EFFECTS.get(food);
        if (vanillaFoodChanges != null) {
            for (FoodProperties.PossibleEffect effect : vanillaFoodChanges.effects()) {
                entity.addEffect(effect.effect());
            }
        }
    }

    @SubscribeEvent
    public static void addTooltipToVanillaFoods(ItemTooltipEvent event) {
        Item food = event.getItemStack().getItem();
        FoodProperties vanillaFoodChanges = BAItems.VANILLA_EFFECTS.get(food);
        if (vanillaFoodChanges != null) {
            List<Component> tooltip = event.getToolTip();
            for (FoodProperties.PossibleEffect effect : vanillaFoodChanges.effects()) {
                MobEffectInstance effectInstance = effect.effect();
                MutableComponent effectText = Component.translatable(effectInstance.getDescriptionId());
                Player player = event.getEntity();
                if (effectInstance.getDuration() > 20) {
                    effectText = Component.translatable("potion.withDuration", effectText, MobEffectUtil.formatDuration(effectInstance, 1, player == null ? 20 : player.level().tickRateManager().tickrate()));
                }
                tooltip.add(effectText.withStyle(effectInstance.getEffect().value().getCategory().getTooltipFormatting()));
            }
        }
    }
  /*  @SubscribeEvent
    public static void addTooltipToModdedFoods(ItemTooltipEvent event) {
        Item food = event.getItemStack().getItem();
        FoodProperties vanillaFoodChanges = BAItems.ITEMS.get(food);
        if (vanillaFoodChanges != null) {
            List<Component> tooltip = event.getToolTip();
            for (FoodProperties.PossibleEffect effect : vanillaFoodChanges.effects()) {
                MobEffectInstance effectInstance = effect.effect();
                MutableComponent effectText = Component.translatable(effectInstance.getDescriptionId());
                Player player = event.getEntity();
                if (effectInstance.getDuration() > 20) {
                    effectText = Component.translatable("potion.withDuration", effectText, MobEffectUtil.formatDuration(effectInstance, 1, player == null ? 20 : player.level().tickRateManager().tickrate()));
                }
                tooltip.add(effectText.withStyle(effectInstance.getEffect().value().getCategory().getTooltipFormatting()));
            }
        }
    }*/

    public static float getBoatFriction(Boat boat, float v) {
        for (var passenger : boat.getPassengers()) {
            if (passenger instanceof LivingEntity entity && entity.hasEffect(BAEffects.AGILITY)) {
                return Math.max(0.98f, v);
            }
        }
        return v;
    }

    @SubscribeEvent
    public static void onBeeJoin(EntityJoinLevelEvent join) {
        if (join.getEntity() instanceof Bee bee) {
            bee.getGoalSelector().addGoal(3, new BeePollinateFruitGoal(bee));
            bee.getGoalSelector().addGoal(4, new BeeMoveToFruitBushGoal(bee));
        }
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((p_329705_, p_329706_) -> {
            event.register((stack, index) -> index == 0 ? DyedItemColor.getOrDefault(stack, -1) : -1, BAItems.MACARON);
            return 0xFFFFFFFF;
        }, BAItems.MACARON.value());
    }
}