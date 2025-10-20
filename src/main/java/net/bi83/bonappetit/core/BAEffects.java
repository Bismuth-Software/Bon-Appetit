package net.bi83.bonappetit.core;

import net.bi83.bonappetit.core.effect.HearttrickleEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BAEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, "bonappetit");

    public static final DeferredHolder<MobEffect, HearttrickleEffect> HEARTTRICKLE = EFFECTS.register("hearttrickle", HearttrickleEffect::new);
}