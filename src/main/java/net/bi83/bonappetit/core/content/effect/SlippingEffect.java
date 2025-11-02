package net.bi83.bonappetit.core.content.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/*\
 * temp
\*/
public class SlippingEffect extends MobEffect {
    public SlippingEffect(MobEffectCategory neutral, int i) {super(MobEffectCategory.BENEFICIAL, 0xF5A332);}

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}