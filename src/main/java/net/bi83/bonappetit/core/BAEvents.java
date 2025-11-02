package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.content.entity.goal.BeeMoveToFruitBushGoal;
import net.bi83.bonappetit.core.content.entity.goal.BeePollinateFruitGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@Mod(value = BonAppetit.ID) @EventBusSubscriber(modid = BonAppetit.ID)
public class BAEvents {
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
            if (passenger instanceof LivingEntity entity && entity.hasEffect(BAEffects.SLIPPING)) {
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