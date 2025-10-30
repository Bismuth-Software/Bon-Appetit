package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.effect.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BAEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, BonAppetit.ID);

    public static final DeferredHolder<MobEffect, HearttrickleEffect> HEARTTRICKLE = EFFECTS.register("hearttrickle", HearttrickleEffect::new);
    public static final DeferredHolder<MobEffect, ReflectionEffect> REFLECTION = EFFECTS.register("reflection", ReflectionEffect::new);
    public static final DeferredHolder<MobEffect, ConcentrationEffect> CONCENTRATION = EFFECTS.register("concentration", ConcentrationEffect::new);
    public static final DeferredHolder<MobEffect, ResonanceEffect> RESONANCE = EFFECTS.register("resonance", ResonanceEffect::new);
    public static final DeferredHolder<MobEffect, DissonanceEffect> DISSONANCE = EFFECTS.register("dissonance", DissonanceEffect::new);

    public static final DeferredHolder<MobEffect, CaffeinatedEffect> CAFFEINATED = EFFECTS.register("caffeinated", CaffeinatedEffect::new);
}