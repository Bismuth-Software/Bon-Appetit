package net.bi83.bonappetit.core;

import com.google.common.collect.ImmutableMap;
import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.common.template.BABottleDrinkItem;
import net.bi83.bonappetit.core.common.template.BACocktailDrinkItem;
import net.bi83.bonappetit.core.common.template.BAPitcherDrinkItem;
import net.bi83.bonappetit.core.content.item.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

public abstract class BAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BonAppetit.ID);
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}

    public static final int BRIEF_DURATION = 300;    //15s
    public static final int SHORT_DURATION = 600;    //30s
    public static final int MEDIUM_DURATION = 1200;    //60s
    public static final int LONG_DURATION = 3600;    //3m
    public static final int EXCESSIVE_DURATION = 6000;    //5m

    public static Supplier<Item> suppRegister(final String name, final Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static MobEffectInstance rooted(int duration) {return new MobEffectInstance(BAEffects.ROOTED, duration, 0, false, true);}

    public static final Supplier<Item> COOKING_POT = suppRegister("cooking_pot",
            () -> new CookingPotItem(BABlocks.COOKING_POT.get(), new Item.Properties().stacksTo(64)));

    // Serving Items
    public static final DeferredItem<Item> PAPER_PLATE = ITEMS.register("paper_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PIE_CRUST = ITEMS.register("pie_crust", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.4f).build())));
    public static final DeferredItem<Item> GLASS_PITCHER = ITEMS.register("glass_pitcher", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLASS_COCKTAIL = ITEMS.register("glass_cocktail", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DOUGH = ITEMS.register("dough", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));
    public static final DeferredItem<Item> WAFER = ITEMS.register("wafer", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));

    /*|* MOD FOODS *|*/
    //---------------//
    /* FRUITS */
    //Cherry
    public static final DeferredItem<Item> CHERRIES = ITEMS.register("cherries", () -> new Item(new Item.Properties().food(BANutrition.CHERRIES)));
    public static final DeferredItem<Item> GOLDEN_CHERRIES = ITEMS.register("golden_cherries", () -> new Item(new Item.Properties().food(BANutrition.GOLDEN_CHERRIES)));

    //Apple
    public static final DeferredItem<Item> APPLE_SLICE = ITEMS.register("apple_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));
    public static final DeferredItem<Item> GREEN_APPLE = ITEMS.register("green_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> HONEY_APPLE = ITEMS.register("honey_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.425f).build())));
    public static final DeferredItem<Item> CANDY_APPLE = ITEMS.register("candy_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.35f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0), 1.0F).build())));
    public static final DeferredItem<Item> CARAMEL_APPLE = ITEMS.register("caramel_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.375f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 150, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0), 1.0F).build())));
    public static final DeferredItem<Item> APPLE_PIE = ITEMS.register("apple_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.3f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 1.0F).build())));
    public static final DeferredItem<Item> APPLE_PIE_SLICE = ITEMS.register("apple_pie_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 0), 1.0F).build())));
    public static final DeferredItem<Item> APPLE_CAKE_SLICE = ITEMS.register("apple_cake_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.125f).build())));
    public static final DeferredItem<Item> APPLE_JUICE = ITEMS.register("apple_juice", () -> new BAPitcherDrinkItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.3f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 1.0F).build())));
    public static final DeferredItem<Item> APPLE_CIDER = ITEMS.register("apple_cider", () -> new BAPitcherDrinkItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.3f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 450, 1), 1.0F).build())));
    public static final DeferredItem<Item> APPLEJACK = ITEMS.register("applejack", () -> new BACocktailDrinkItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.275f)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1), 1.0F).build())));

    //Grapefruit
    public static final DeferredItem<Item> GRAPEFRUIT = ITEMS.register("grapefruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> GRAPEFRUIT_SLICE = ITEMS.register("grapefruit_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));

    //Orange
    public static final DeferredItem<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> GOLDEN_ORANGE = ITEMS.register("golden_orange", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.8f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 1200, 1), 1.0F).build())));
    public static final DeferredItem<Item> ORANGE_SLICE = ITEMS.register("orange_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));
    public static final DeferredItem<Item> CANDIED_ORANGE_PEELS = ITEMS.register("candied_orange_peels", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.295f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 100, 0), 0.8F).build())));
    public static final DeferredItem<Item> ORANGE_CAKE_SLICE = ITEMS.register("orange_cake_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.125f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 150, 0), 1.0F).build())));
    public static final DeferredItem<Item> ORANGE_SORBET = ITEMS.register("orange_sorbet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.2f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 250, 0), 1.0F).build())));
    public static final DeferredItem<Item> ORANGE_SHERBET = ITEMS.register("orange_sherbet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 300, 0), 1.0F).build())));
    public static final DeferredItem<Item> ORANGE_PUDDING = ITEMS.register("orange_pudding", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 300, 0), 1.0F).build())));
    public static final DeferredItem<Item> ORANGE_JUICE = ITEMS.register("orange_juice", () -> new BAPitcherDrinkItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.3f)
            .effect(new MobEffectInstance(BAEffects.CONCENTRATION, 600, 0), 1.0F).build())));

    //Pumpkin
    public static final DeferredItem<Item> PUMPKIN_SLICE = ITEMS.register("pumpkin_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));
    public static final DeferredItem<Item> PUMPKIN_PIE_SLICE = ITEMS.register("pumpkin_pie_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build())));

    //Mango
    public static final DeferredItem<Item> MANGO = ITEMS.register("mango", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));

    //Apricot -- MIGHT BE SCRAPPED
    public static final DeferredItem<Item> APRICOT = ITEMS.register("apricot", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));

    //Pineapple
    public static final DeferredItem<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));

    //Banana
    public static final DeferredItem<Item> BANANA = ITEMS.register("banana", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> DRIED_BANANA = ITEMS.register("dried_banana", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.35f).build())));
    public static final DeferredItem<Item> BANANA_BREAD = ITEMS.register("banana_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.65f).build())));
    public static final DeferredItem<Item> BANANA_CAKE_SLICE = ITEMS.register("banana_cake_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.125f).build())));
    public static final DeferredItem<Item> BANANA_SMOOTHIE = ITEMS.register("banana_smoothie", () -> new BABottleDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEdible().nutrition(4).saturationModifier(0.65f)
            .effect(new MobEffectInstance(BAEffects.AGILITY, 900, 0), 1.0F).build())));

    //Lemon
    public static final DeferredItem<Item> LEMON = ITEMS.register("lemon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> LEMON_SLICE = ITEMS.register("lemon_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));
    public static final DeferredItem<Item> LEMON_COOKIE = ITEMS.register("lemon_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).fast()
            .effect(new MobEffectInstance(BAEffects.RESONANCE, 100, 0), 0.8F).build())));
    public static final DeferredItem<Item> LEMON_TART = ITEMS.register("lemon_tart", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> LEMON_MERINGUE_PIE = ITEMS.register("lemon_meringue_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.35f).build())));
    public static final DeferredItem<Item> LEMON_MERINGUE_PIE_SLICE = ITEMS.register("lemon_meringue_pie_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.35f).build())));
    public static final DeferredItem<Item> LEMON_CAKE = ITEMS.register("lemon_cake", () -> new BlockItem(BABlocks.LEMON_CAKE.get(), new Item.Properties()));
    public static final DeferredItem<Item> LEMON_CAKE_SLICE = ITEMS.register("lemon_cake_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.125f).build())));
    public static final DeferredItem<Item> LEMONADE = ITEMS.register("lemonade", () -> new BAPitcherDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.45f).alwaysEdible()
            .effect(new MobEffectInstance(BAEffects.RESONANCE, 450, 0), 1.0F).build())));
    
    //Lime
    public static final DeferredItem<Item> LIME = ITEMS.register("lime", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> LIME_SLICE = ITEMS.register("lime_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).build())));
    public static final DeferredItem<Item> LIME_COOKIE = ITEMS.register("lime_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.175f).fast()
            .effect(new MobEffectInstance(BAEffects.DISSONANCE, 100, 0), 0.8F).build())));
    public static final DeferredItem<Item> LIME_POPSICLE = ITEMS.register("lime_popsicle", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.4f)
            .effect(new MobEffectInstance(BAEffects.DISSONANCE, 300, 0), 1.0F).build())));
    public static final DeferredItem<Item> DOUBLE_LIME_POPSICLE = ITEMS.register("double_lime_popsicle", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.425f)
            .effect(new MobEffectInstance(BAEffects.DISSONANCE, 600, 0), 1.0F).build())));
    public static final DeferredItem<Item> LIME_CAKE_SLICE = ITEMS.register("lime_cake_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.125f).build())));
    public static final DeferredItem<Item> LIMEADE = ITEMS.register("limeade", () -> new BAPitcherDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5f).alwaysEdible()
                .effect(new MobEffectInstance(BAEffects.DISSONANCE, 450, 0), 1.0F).build())));

    //Kiwi -- MIGHT BE SCRAPPED
    public static final DeferredItem<Item> KIWI = ITEMS.register("kiwi", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));

    //Pear -- MIGHT BE SCRAPPED
    public static final DeferredItem<Item> PEAR = ITEMS.register("pear", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));

    //Grape
    public static final DeferredItem<Item> GRAPES = ITEMS.register("grapes", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> RAISINS = ITEMS.register("raisins", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).fast().build())));

    //Peach
    public static final DeferredItem<Item> PEACH = ITEMS.register("peach", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));

    //Dragon Fruit
    public static final DeferredItem<Item> DRAGON_FRUIT = ITEMS.register("dragon_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> DRIED_DRAGON_FRUIT = ITEMS.register("dried_dragon_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.35f).build())));

    //Pomegranate
    public static final DeferredItem<Item> POMEGRANATE = ITEMS.register("pomegranate", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.075f).build())));
    public static final DeferredItem<Item> POMEGRANATE_SLICE = ITEMS.register("pomegranate_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));

    //Coconut
    public static final DeferredItem<Item> COCONUT = ITEMS.register("coconut", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COCONUT_SLICE = ITEMS.register("coconut_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build())));

    /* BERRIES */
    //Strawberry
    public static final DeferredItem<Item> STRAWBERRIES = ITEMS.register("strawberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> GOLDEN_STRAWBERRIES = ITEMS.register("golden_strawberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(1.075f).build())));
                //.effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1.0F).build())));
    public static final DeferredItem<Item> WINGED_STRAWBERRY = ITEMS.register("winged_strawberry", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5f).alwaysEdible().build())));
    public static final DeferredItem<Item> WINGED_GOLDEN_STRAWBERRY = ITEMS.register("winged_golden_strawberry", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(20).saturationModifier(1.0f).alwaysEdible().build())));
                //.effect(new MobEffectInstance(MobEffects.REGENERATION, 14400, 2), 1.0F).build())));

    //Cranberry
    public static final DeferredItem<Item> CRANBERRIES = ITEMS.register("cranberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build())));

    //Salmonberry
    public static final DeferredItem<Item> SALMONBERRIES = ITEMS.register("salmonberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build())));

    //Blueberry
    public static final DeferredItem<Item> BLUEBERRIES = ITEMS.register("blueberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));

    //Mulberry
    public static final DeferredItem<Item> MULBERRIES = ITEMS.register("mulberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));

    //Raspberry
    public static final DeferredItem<Item> RASPBERRIES = ITEMS.register("raspberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));
    public static final DeferredItem<Item> BLACK_RASPBERRIES = ITEMS.register("black_raspberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build())));

    /* GRAINS */
    //Corn
    public static final DeferredItem<Item> CORN = ITEMS.register("corn", () -> new CornItem(new Item.Properties().food(BANutrition.CORN)));
    public static final DeferredItem<Item> CORN_ON_A_COB = ITEMS.register("corn_on_a_cob", () -> new CornCobItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).build())));
    public static final DeferredItem<BlockItem> CORN_KERNELS = ITEMS.register("corn_kernels", () -> new ItemNameBlockItem(BABlocks.CORN_BASE.get(), new Item.Properties()));
    public static final DeferredItem<Item> POPCORN = ITEMS.register("popcorn", () -> new PopcornItem(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(2).saturationModifier(0.1f).build())));

    /* VEGETABLES */

    /* WHATEVER THE FUCK THESE ARE */
    //Tea
    public static final DeferredItem<Item> GREEN_TEA_LEAVES = ITEMS.register("green_tea_leaves", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> YELLOW_TEA_LEAVES = ITEMS.register("yellow_tea_leaves", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> BLACK_TEA_LEAVES = ITEMS.register("black_tea_leaves", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));

    //Coffee
    public static final DeferredItem<Item> COFFEE_CHERRIES = ITEMS.register("coffee_cherries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.75f).build())));
    public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.register("coffee_beans", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).build())));
    public static final DeferredItem<Item> COFFEE = ITEMS.register("coffee", () -> new BAPitcherDrinkItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.7f).alwaysEdible()
            .effect(new MobEffectInstance(BAEffects.CAFFEINATED, 600, 0), 1.0F).build())));

    /* SPICES */
    //Cinnamon
    public static final DeferredItem<Item> CINNAMON_STICKS = ITEMS.register("cinnamon_sticks", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CINNAMON_DUST = ITEMS.register("cinnamon_dust", () -> new Item(new Item.Properties()));

    /* MEALS */
    public static final DeferredItem<Item> CHOCOLATE_BAR = ITEMS.register("chocolate_bar", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> BROWNIE = ITEMS.register("brownie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build())));
    public static final DeferredItem<Item> PLAIN_COOKIE = ITEMS.register("plain_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast()
            .nutrition(2)
            .saturationModifier(0.025f).build())));
    public static final DeferredItem<Item> SNICKERDOODLE = ITEMS.register("snickerdoodle", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast()
            .nutrition(2)
            .saturationModifier(0.15f).build())));
    public static final DeferredItem<Item> ECLIPSE_COOKIE = ITEMS.register("eclipse_cookie", () -> new EclipseCookieItem(new Item.Properties().food(new FoodProperties.Builder().fast()
            .nutrition(2)
            .saturationModifier(0.2f).build())));
    public static final DeferredItem<Item> GOLDEN_COOKIE = ITEMS.register("golden_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast()
            .nutrition(4)
            .saturationModifier(0.8f).build())));
    public static final DeferredItem<Item> MACARON = ITEMS.register("macaron", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast()
            .nutrition(4)
            .saturationModifier(0.225f).build())));
    public static final DeferredItem<Item> PANETTONE = ITEMS.register("panettone", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.9f).build())));
    public static final DeferredItem<Item> AMBROSIA_SALAD = ITEMS.register("ambrosia_salad", () -> new Item(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEdible()
            .nutrition(9)
            .saturationModifier(0.9f).build())));
    public static final DeferredItem<Item> STRAWBERRY_BANANA_SMOOTHIE = ITEMS.register("strawberry_banana_smoothie", () -> new BABottleDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEdible()
            .nutrition(6)
            .saturationModifier(0.7f)
            .effect(new MobEffectInstance(BAEffects.AGILITY, 600, 0), 1.0F).build())));
    public static final DeferredItem<Item> PINK_LEMONADE = ITEMS.register("pink_lemonade", () -> new BAPitcherDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEdible()
            .nutrition(7)
            .saturationModifier(0.6f).build())));
    public static final DeferredItem<Item> PINK_LADY = ITEMS.register("pink_lady", () -> new BACocktailDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEdible()
            .nutrition(7)
            .saturationModifier(0.6f).build())));
    public static final DeferredItem<Item> BLUEBERRY_LIMEADE = ITEMS.register("blueberry_limeade", () -> new BAPitcherDrinkItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEdible()
            .nutrition(7)
            .saturationModifier(0.55f)
            .effect(new MobEffectInstance(BAEffects.DISSONANCE, 450, 0), 1.0F).build())));

    /* NON-FOODS */
    //public static final DeferredItem<DinnerwareBundleItem> DINNERWARE_BUNDLE = ITEMS.register("dinnerware_bundle", () -> new DinnerwareBundleItem(new Item.Properties()));

    /*|* VANILLA FOODS *|*/
    //-------------------//
    public static final Map<Item, FoodProperties> VANILLA_EFFECTS = (new ImmutableMap.Builder<Item, FoodProperties>())
            .put(Items.BEETROOT_SOUP, (new FoodProperties.Builder()).effect(() -> rooted(SHORT_DURATION), 1.0F).build())
            .build();
}