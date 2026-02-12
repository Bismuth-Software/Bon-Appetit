package net.bi83.bonappetit.core.content.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/*\
 * temp
\*/
public class FlakEffect extends MobEffect {
    public FlakEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xD80073);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }
}