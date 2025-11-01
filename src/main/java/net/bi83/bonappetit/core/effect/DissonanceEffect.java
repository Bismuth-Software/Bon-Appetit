package net.bi83.bonappetit.core.effect;

import com.mojang.datafixers.util.Either;
import net.bi83.bonappetit.core.BAEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*\
 * Dissonance creates a lingering cloud with all your negative effects. If you have none, it will wait for you to get one. Once you get one, the Dissonance effect will be cleared. (Do note, *you* will still get the harmful effects.)
 * It also only gives you a small portion of the effect duration. The cloud lasts for 5 seconds at amplifier 0, and adds 2.5 seconds of lifespan for every amplifier
\*/
public class DissonanceEffect extends MobEffect implements Holder<MobEffect> {
    public DissonanceEffect(MobEffectCategory neutral, int i) {super(MobEffectCategory.NEUTRAL, 0xE8FED8);}

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        Level level = entity.level();
        if (level.isClientSide) return true;
        float radius = 3.0f + amplifier;

        boolean hasOtherHarmful = false;
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            MobEffect effect = instance.getEffect().value();
            if (effect.getCategory() == MobEffectCategory.HARMFUL && effect != BAEffects.RESONANCE.get() && effect != BAEffects.DISSONANCE.get()) {hasOtherHarmful = true; break;}
        }
        if (!hasOtherHarmful) return true;

        AreaEffectCloud cloud = new AreaEffectCloud(level, entity.getX(), entity.getY(), entity.getZ());
        cloud.setRadius(radius);
        cloud.setDuration(100 + (amplifier * 50)); //5s + 2.5s*amplifier
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick(-0.01f);
        cloud.setOwner(entity);
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            Holder<MobEffect> holder = instance.getEffect();
            MobEffect effect = holder.value();

            if (effect.getCategory() != MobEffectCategory.HARMFUL) continue;

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

    @Override public MobEffect value() {return null;}
    @Override public boolean isBound() {return false;}
    @Override public boolean is(ResourceLocation resourceLocation) {return false;}
    @Override public boolean is(ResourceKey<MobEffect> resourceKey) {return false;}
    @Override public boolean is(Predicate<ResourceKey<MobEffect>> predicate) {return false;}
    @Override public boolean is(TagKey<MobEffect> tagKey) {return false;}
    @Override public boolean is(Holder<MobEffect> holder) {return false;}
    @Override public Stream<TagKey<MobEffect>> tags() {return Stream.empty();}
    @Override public Either<ResourceKey<MobEffect>, MobEffect> unwrap() {return null;}
    @Override public Optional<ResourceKey<MobEffect>> unwrapKey() {return Optional.empty();}
    @Override public Holder.Kind kind() {return null;}
    @Override public boolean canSerializeIn(HolderOwner<MobEffect> holderOwner) {return false;}
}