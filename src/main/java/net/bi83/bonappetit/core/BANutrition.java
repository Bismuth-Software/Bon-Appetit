package net.bi83.bonappetit.core;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class BANutrition {
    public static final FoodProperties CORN = (new FoodProperties.Builder())
            .nutrition(2).saturationModifier(0.2F).build();
    public static final FoodProperties CHERRIES = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties GOLDEN_CHERRIES = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.85F)
            .effect(() -> new MobEffectInstance(BAEffects.HEARTTRICKLE, 1200, 1), 1F).build();
}