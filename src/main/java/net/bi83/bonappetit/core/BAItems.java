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
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus); }

    public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.register("coffee_beans", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.75f).build())));
    public static final DeferredItem<CoffeeItem> COFFEE = ITEMS.register("coffee", () -> new CoffeeItem(new Item.Properties().food(new FoodProperties.Builder()
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0), 1.0F)
            .nutrition(0)
            .saturationModifier(0.7f).build())));
    public static final DeferredItem<Item> MERENGUE = ITEMS.register("merengue", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.05f).build())));
    public static final DeferredItem<Item> MACARON = ITEMS.register("macaron", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.225f).build())));
}