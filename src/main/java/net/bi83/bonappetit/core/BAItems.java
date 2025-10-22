package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.item.CoffeeItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BonAppetit.ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> CHERRIES = ITEMS.register("cherries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> GOLDEN_CHERRIES = ITEMS.register("golden_cherries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.85f)
                .effect(new MobEffectInstance(BAEffects.HEARTTRICKLE, 100, 1), 1.0F)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 150, 0), 1.0F).build())));
    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> MANGO = ITEMS.register("mango", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> APRICOT = ITEMS.register("apricot", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> LEMON = ITEMS.register("lemon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> LEMON_SLICE = ITEMS.register("lemon_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));
    public static final DeferredItem<Item> LIME = ITEMS.register("lime", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> LIME_SLICE = ITEMS.register("lime_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));
    public static final DeferredItem<Item> KIWI = ITEMS.register("kiwi", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> PEAR = ITEMS.register("pear", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> GRAPES = ITEMS.register("grapes", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> PEACH = ITEMS.register("peach", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> DRAGON_FRUIT = ITEMS.register("dragon_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> POMEGRANATE = ITEMS.register("pomegranate", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.075f)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 0.9F).build())));
    public static final DeferredItem<Item> POMEGRANATE_SLICE = ITEMS.register("pomegranate_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));

    public static final DeferredItem<Item> STRAWBERRIES = ITEMS.register("strawberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> GOLDEN_STRAWBERRIES = ITEMS.register("golden_strawberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(1.075f)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1.0F).build())));
    public static final DeferredItem<Item> WINGED_STRAWBERRY = ITEMS.register("winged_strawberry", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5f).alwaysEdible().build())));
    public static final DeferredItem<Item> WINGED_GOLDEN_STRAWBERRY = ITEMS.register("winged_golden_strawberry", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(20).saturationModifier(1.0f).alwaysEdible()
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 14400, 2), 1.0F).build())));
    public static final DeferredItem<Item> CRANBERRIES = ITEMS.register("cranberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> SALMONBERRIES = ITEMS.register("salmonberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> BLUEBERRIES = ITEMS.register("blueberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> MULBERRIES = ITEMS.register("mulberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> RASPBERRIES = ITEMS.register("raspberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> BLACK_RASPBERRIES = ITEMS.register("black_raspberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));

    public static final DeferredItem<Item> GREEN_TEA_LEAVES = ITEMS.register("green_tea_leaves", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> YELLOW_TEA_LEAVES = ITEMS.register("yellow_tea_leaves", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> BLACK_TEA_LEAVES = ITEMS.register("black_tea_leaves", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> COFFEE_BERRIES = ITEMS.register("coffee_berries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.register("coffee_beans", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).build())));
    public static final DeferredItem<CoffeeItem> COFFEE = ITEMS.register("coffee", () -> new CoffeeItem(new Item.Properties().food(new FoodProperties.Builder()
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0), 1.0F)
            .nutrition(0)
            .saturationModifier(0.7f).build())));

    public static final DeferredItem<Item> PIE_CRUST = ITEMS.register("pie_crust", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.4f).build())));
    public static final DeferredItem<Item> MERINGUE = ITEMS.register("meringue", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(0)
            .saturationModifier(0.15f).build())));
    public static final DeferredItem<Item> MACARON = ITEMS.register("macaron", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.225f).build())));
}