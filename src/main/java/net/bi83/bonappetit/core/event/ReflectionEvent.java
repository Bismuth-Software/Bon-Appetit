package net.bi83.bonappetit.core.event;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@Mod(value = BonAppetit.ID) @EventBusSubscriber(modid = BonAppetit.ID)
public class ReflectionEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onReflectProjectile(ProjectileImpactEvent impact) {
        HitResult hit = impact.getRayTraceResult();
        if (hit != null && hit.getType() == HitResult.Type.ENTITY
                && hit instanceof EntityHitResult result
                && result.getEntity() instanceof LivingEntity victim
                && !victim.level().isClientSide)
        {
            boolean hasReflection = false; MobEffectInstance reflectionInstance = null;
            for (var effectInstance : victim.getActiveEffects()) {
                if (effectInstance.getEffect().getRegisteredName().equals("bonappetit:reflection")) {
                    reflectionInstance = effectInstance;
                    break;
                }
            }

            if (reflectionInstance != null && victim.level() instanceof ServerLevel server) {
                float chance = Math.min(0.4f + 0.35f * reflectionInstance.getAmplifier(), 1.0f); float roll = server.random.nextFloat();
                if (roll >= chance) return;

                Vec3 motion = impact.getProjectile().getDeltaMovement();
                Vec3 newMotion = new Vec3(-motion.x, -motion.y, -motion.z).add(0, -0.03, 0);
                impact.getProjectile().setDeltaMovement(newMotion);
                if (!impact.getProjectile().level().isClientSide() && impact.getProjectile().level() instanceof ServerLevel) {
                    server.getChunkSource().broadcastAndSend(
                            impact.getProjectile(),
                            new ClientboundSetEntityMotionPacket(impact.getProjectile().getId(), newMotion));
                }
                impact.setCanceled(true);
                server.playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.SHIELD_BLOCK, SoundSource.NEUTRAL, 0.8F, 1.1F);
            }
        }
    }
}