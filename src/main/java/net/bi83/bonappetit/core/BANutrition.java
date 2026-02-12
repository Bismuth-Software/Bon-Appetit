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
            .effect(() -> new MobEffectInstance(BAEffects.TWIN_STRIKE, 1200, 1), 1F).build();

    public static final FoodProperties JERKY = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.1F).build();

    public static final FoodProperties CHOCOLATE_BAR = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.3F)
            .effect(() -> new MobEffectInstance(BAEffects.VIGOR, 200, 0), 1F).build();
    public static final FoodProperties BROWNIE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.3F)
            .effect(() -> new MobEffectInstance(BAEffects.VIGOR, 300, 0), 1F).build();

    public static final FoodProperties JOCKEY_SANDWICH = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.8F).build();
            //.effect(() -> new MobEffectInstance(BAEffects.VIGOR, 300, 0), 1F).build();
}