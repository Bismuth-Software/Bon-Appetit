package net.bi83.bonappetit.core.effect;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.BAEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@Mod(value = BonAppetit.ID) @EventBusSubscriber(modid = BonAppetit.ID)
public class RicochetEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onReflectProjectile(ProjectileImpactEvent impact) {
        final Entity entity = impact.getEntity();
        if (//impact.getProjectile().getType().is(BATags.EntityTypes.RICOCHET_IMMUNE) &&
                impact.getRayTraceResult() instanceof EntityHitResult result && result.getEntity() instanceof LivingEntity victim && victim.hasEffect(BAEffects.RICOCHET.get()))
        {
            if (victim.level() instanceof ServerLevel server) {
                impact.getProjectile().deflect(ProjectileDeflection.MOMENTUM_DEFLECT, victim, impact.getEntity(), true);
                impact.setCanceled(true);
                server.playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.ALLAY_DEATH, SoundSource.NEUTRAL, 0.8F, 1.1F);
            }
        }
    }
}
