package net.bi83.bonappetit.core.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class HearttrickleEffect extends MobEffect {
    public HearttrickleEffect() {super(MobEffectCategory.BENEFICIAL, 0x8B1F39);}

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        float healAmount = 1.0F * (amplifier + 1); entity.heal(healAmount);
        return true;
    }
}