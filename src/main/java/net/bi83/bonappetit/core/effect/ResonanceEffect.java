package net.bi83.bonappetit.core.effect;

import net.bi83.bonappetit.core.BAEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

/*\
 * Resonance creates a lingering cloud with all your positive effects. If you have none, it will wait for you to get one. Once you get one, the Resonance effect will be cleared.
 * It also only gives you a small portion of the effect duration. The cloud lasts for 5 seconds at amplifier 0, and adds 2.5 seconds of lifespan for every amplifier
\*/
public class ResonanceEffect extends MobEffect {
    public ResonanceEffect() {super(MobEffectCategory.NEUTRAL, 0xFCF5CA);}

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        Level level = entity.level();
        if (level.isClientSide) return true;
        float radius = 2.0f + amplifier;

        boolean hasOtherBeneficial = false;
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            MobEffect effect = instance.getEffect().value();
            if (effect.getCategory() == MobEffectCategory.BENEFICIAL && effect != BAEffects.RESONANCE.get() && effect != BAEffects.DISSONANCE.get()) {hasOtherBeneficial = true; break;}
        }
        if (!hasOtherBeneficial) return true;

        AreaEffectCloud cloud = new AreaEffectCloud(level, entity.getX(), entity.getY(), entity.getZ());
        cloud.setRadius(radius);
        cloud.setDuration(100 + (amplifier * 50)); //5s + 2.5s*amplifier
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick(-0.01f);
        cloud.setOwner(entity);
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            Holder<MobEffect> holder = instance.getEffect();
            MobEffect effect = holder.value();

            if (effect.getCategory() != MobEffectCategory.BENEFICIAL) continue;

            int grantDuration = Math.min(instance.getDuration(), 600 + (amplifier * 200)); //30s + 10s*amplifier
            if (grantDuration < 10) continue; //prevents instant effects from being transmitted

            cloud.addEffect(new MobEffectInstance(instance.getEffect(), grantDuration, instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon()
            ));
        }
        level.addFreshEntity(cloud);
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            if (instance.getEffect().value() == this) {
                entity.removeEffect(instance.getEffect());
                break;
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}