package net.bi83.bonappetit.core.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class CaffeinatedEffect extends MobEffect {
    public CaffeinatedEffect() {super(MobEffectCategory.BENEFICIAL, 0xF5A332);}

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        return true;
    }
}