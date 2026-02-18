package net.bi83.bonappetit.core.common.datagen;

import net.bi83.bonappetit.BAConfig;
import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.BATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.bi83.bonappetit.core.BAItems.*;
import static net.minecraft.world.item.Items.*;

public class BARecipeProvider extends RecipeProvider implements IConditionBuilder {
    public BARecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {super(output, registries);}

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PITCHFORK.get(), 1).pattern(" ##").pattern(" S#").pattern("S  ")
                .define('#', IRON_BARS).define('S', STICK).unlockedBy("has_iron_bars", has(IRON_BARS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PAPER_PLATE.get(), 3).pattern("###")
                .define('#', PAPER).unlockedBy("has_paper", has(PAPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PIE_CRUST.get(), 2).pattern("W W").pattern(" W ")
                .define('W', WHEAT).unlockedBy("has_wheat", has(WHEAT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GLASS_PITCHER.get(), 5).pattern("# #").pattern("# #").pattern(" # ")
                .define('#', GLASS).unlockedBy("has_glass", has(GLASS)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GLASS_COCKTAIL.get(), 6).pattern("# #").pattern(" # ").pattern("###")
                .define('#', GLASS).unlockedBy("has_glass", has(GLASS)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GOLDEN_CHERRIES.get(), 1).pattern("GGG").pattern("G#G").pattern("GGG")
                .define('#', CHERRIES.get()).define('G', GOLD_INGOT).unlockedBy("has_gold_ingot", has(GOLD_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CAKE_SLICE.get(), 7).requires(CAKE)
                .unlockedBy("has_cake", has(CAKE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CAKE, 1).requires(CAKE_SLICE).requires(CAKE_SLICE).requires(CAKE_SLICE).requires(CAKE_SLICE).requires(CAKE_SLICE).requires(CAKE_SLICE).requires(CAKE_SLICE)
                .unlockedBy("has_cake", has(CAKE)).save(recipeOutput, "bonappetit:cake_from_cake_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, APPLE_SLICE.get(), 2).requires(APPLE).unlockedBy("has_apple", has(APPLE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CANDY_APPLE.get(), 1).pattern("T").pattern("#").pattern("S").define('#', APPLE).define('S', SUGAR).define('T', STICK).unlockedBy("has_apple", has(APPLE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, APPLE_PIE, 2).requires(APPLE).requires(APPLE).requires(APPLE).requires(SUGAR).requires(Tags.Items.EGGS).requires(PIE_CRUST)
                .unlockedBy("has_apple", has(APPLE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, APPLE_PIE_SLICE.get(), 4).requires(APPLE_PIE)
                .unlockedBy("has_apple_pie", has(APPLE_PIE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, APPLE_PIE, 1).requires(APPLE_PIE_SLICE).requires(APPLE_PIE_SLICE).requires(APPLE_PIE_SLICE).requires(APPLE_PIE_SLICE)
                .unlockedBy("has_apple_pie_slice", has(APPLE_PIE_SLICE)).save(recipeOutput, "bonappetit:apple_pie_from_apple_pie_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GRAPEFRUIT_SLICE.get(), 2).requires(GRAPEFRUIT)
                .unlockedBy("has_grapefruit", has(GRAPEFRUIT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GRAPEFRUIT_PIE, 2).requires(GRAPEFRUIT).requires(GRAPEFRUIT).requires(GRAPEFRUIT).requires(SUGAR).requires(Tags.Items.EGGS).requires(PIE_CRUST)
                .unlockedBy("has_grapefruit", has(GRAPEFRUIT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GRAPEFRUIT_PIE_SLICE.get(), 4).requires(GRAPEFRUIT_PIE)
                .unlockedBy("has_grapefruit_pie", has(GRAPEFRUIT_PIE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GRAPEFRUIT_PIE, 1).requires(GRAPEFRUIT_PIE_SLICE).requires(GRAPEFRUIT_PIE_SLICE).requires(GRAPEFRUIT_PIE_SLICE).requires(GRAPEFRUIT_PIE_SLICE)
                .unlockedBy("has_grapefruit_pie_slice", has(GRAPEFRUIT_PIE_SLICE)).save(recipeOutput, "bonappetit:grapefruit_pie_from_grapefruit_pie_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ORANGE_SLICE.get(), 2).requires(ORANGE)
                .unlockedBy("has_orange", has(ORANGE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ORANGE_PIE, 2).requires(ORANGE).requires(ORANGE).requires(ORANGE).requires(SUGAR).requires(Tags.Items.EGGS).requires(PIE_CRUST)
                .unlockedBy("has_orange", has(ORANGE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ORANGE_PIE_SLICE.get(), 4).requires(ORANGE_PIE)
                .unlockedBy("has_orange_pie", has(ORANGE_PIE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ORANGE_PIE, 1).requires(ORANGE_PIE_SLICE).requires(ORANGE_PIE_SLICE).requires(ORANGE_PIE_SLICE).requires(ORANGE_PIE_SLICE)
                .unlockedBy("has_orange_pie_slice", has(ORANGE_PIE_SLICE)).save(recipeOutput, "bonappetit:orange_pie_from_orange_pie_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MANGO_PIE, 2).requires(MANGO).requires(MANGO).requires(MANGO).requires(SUGAR).requires(Tags.Items.EGGS).requires(PIE_CRUST)
                .unlockedBy("has_mango", has(MANGO)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MANGO_PIE_SLICE.get(), 4).requires(MANGO_PIE)
                .unlockedBy("has_mango_pie", has(MANGO_PIE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MANGO_PIE, 1).requires(MANGO_PIE_SLICE).requires(MANGO_PIE_SLICE).requires(MANGO_PIE_SLICE).requires(MANGO_PIE_SLICE)
                .unlockedBy("has_mango_pie_slice", has(MANGO_PIE_SLICE)).save(recipeOutput, "bonappetit:mango_pie_from_mango_pie_slices");
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PUMPKIN_SLICE.get(), 9).requires(PUMPKIN)
                .unlockedBy("has_pumpkin", has(PUMPKIN)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PUMPKIN_PIE, 2).requires(PUMPKIN).requires(PUMPKIN).requires(PUMPKIN).requires(SUGAR).requires(Tags.Items.EGGS).requires(PIE_CRUST)
                .unlockedBy("has_pumpkin", has(PUMPKIN)).save(recipeOutput, "bonappetit:pumpkin_pie");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PUMPKIN_PIE_SLICE.get(), 4).requires(PUMPKIN_PIE)
                .unlockedBy("has_pumpkin_pie", has(PUMPKIN_PIE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PUMPKIN_PIE, 1).requires(PUMPKIN_PIE_SLICE).requires(PUMPKIN_PIE_SLICE).requires(PUMPKIN_PIE_SLICE).requires(PUMPKIN_PIE_SLICE)
                .unlockedBy("has_pumpkin_pie_slice", has(PUMPKIN_PIE_SLICE)).save(recipeOutput, "bonappetit:pumpkin_pie_from_pumpkin_pie_slices");
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LEMON_SLICE.get(), 2).requires(LEMON)
                .unlockedBy("has_lemon", has(LEMON)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LEMON_CAKE_SLICE.get(), 7).requires(LEMON_CAKE)
                .unlockedBy("has_lemon_cake", has(LEMON_CAKE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LEMON_CAKE, 1).requires(LEMON_CAKE_SLICE).requires(LEMON_CAKE_SLICE).requires(LEMON_CAKE_SLICE).requires(LEMON_CAKE_SLICE).requires(LEMON_CAKE_SLICE).requires(LEMON_CAKE_SLICE).requires(LEMON_CAKE_SLICE)
                .unlockedBy("has_lemon_cake", has(LEMON_CAKE)).save(recipeOutput, "bonappetit:lemon_cake_from_lemon_cake_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LIME_SLICE.get(), 2).requires(LIME)
                .unlockedBy("has_lime", has(LIME)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MELON_SLICE, 9).requires(MELON)
                .unlockedBy("has_melon", has(MELON)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, POMEGRANATE_SLICE.get(), 2).requires(POMEGRANATE)
                .unlockedBy("has_pomegranate", has(POMEGRANATE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, POMEGRANATE_SEEDS.get(), 1).requires(POMEGRANATE_SLICE)
                .unlockedBy("has_pomegranate", has(POMEGRANATE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DRAGON_FRUIT_SLICE.get(), 2).requires(DRAGON_FRUIT)
                .unlockedBy("has_dragon_fruit", has(DRAGON_FRUIT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DRAGON_FRUIT_PIE, 2).requires(DRAGON_FRUIT).requires(DRAGON_FRUIT).requires(DRAGON_FRUIT).requires(SUGAR).requires(Tags.Items.EGGS).requires(PIE_CRUST)
                .unlockedBy("has_dragon_fruit", has(DRAGON_FRUIT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DRAGON_FRUIT_PIE_SLICE.get(), 4).requires(DRAGON_FRUIT_PIE)
                .unlockedBy("has_dragon_fruit_pie", has(DRAGON_FRUIT_PIE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DRAGON_FRUIT_PIE, 1).requires(DRAGON_FRUIT_PIE_SLICE).requires(DRAGON_FRUIT_PIE_SLICE).requires(DRAGON_FRUIT_PIE_SLICE).requires(DRAGON_FRUIT_PIE_SLICE)
                .unlockedBy("has_dragon_fruit_pie_slice", has(DRAGON_FRUIT_PIE_SLICE)).save(recipeOutput, "bonappetit:dragon_fruit_pie_from_dragon_fruit_pie_slices");
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, COCONUT_SLICE.get(), 2).requires(COCONUT)
                .unlockedBy("has_coconut", has(COCONUT)).save(recipeOutput);

        //strawberry
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GOLDEN_STRAWBERRIES.get(), 1).pattern("GGG").pattern("G#G").pattern("GGG")
                .define('#', STRAWBERRIES.get()).define('G', GOLD_INGOT).unlockedBy("has_gold_ingot", has(GOLD_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, WINGED_STRAWBERRY.get(), 1).pattern("F#F")
                .define('#', STRAWBERRIES.get()).define('F', FEATHER).unlockedBy("has_strawberries", has(STRAWBERRIES)).save(recipeOutput);

        //corn
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CORN_KERNELS.get(), 1).requires(CORN)
                .unlockedBy("has_corn", has(CORN)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CORN_ON_A_COB.get(), 1).requires(STICK).requires(CORN)
                .unlockedBy("has_corn", has(CORN)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(CORN_KERNELS.get()), RecipeCategory.FOOD, POPCORN.get(), 0.25f, 200).unlockedBy("has_corn", has(CORN)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(CORN_KERNELS.get()), RecipeCategory.FOOD, POPCORN.get(), 0.25f, 100).unlockedBy("has_corn", has(CORN)).save(recipeOutput, "bonappetit:popcorn_from_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(CORN_KERNELS.get()), RecipeCategory.FOOD, POPCORN.get(), 0.25f, 600).unlockedBy("has_corn", has(CORN)).save(recipeOutput, "bonappetit:popcorn_from_campfire_cooking");

        //meals
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, JOCKEY_SANDWICH.get(), 1).requires(BREAD).requires(COOKED_CHICKEN).requires(JERKY).requires(POTATO).requires(BROWN_MUSHROOM)
                .unlockedBy("has_jerky", has(JERKY)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PANETTONE.get(), 1)
                .requires(MILK_BUCKET).requires(DOUGH).requires(SUGAR).requires(BATags.Items.CITRUS_FRUITS).requires(RAISINS).requires(RAISINS)
                .unlockedBy("has_corn", has(BATags.Items.CITRUS_FRUITS)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LEMONADE.get(), 1).requires(LEMON_SLICE).requires(LEMON_SLICE).requires(SUGAR).requires(GLASS_PITCHER).unlockedBy("has_lemon", has(LEMON)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PINK_LEMONADE.get(), 1).requires(LEMON_SLICE).requires(LEMON_SLICE).requires(CHERRIES).requires(SUGAR).requires(GLASS_PITCHER).unlockedBy("has_lemon", has(LEMON)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PINK_LEMONADE.get(), 1).requires(LEMONADE).requires(CHERRIES).unlockedBy("has_lemon", has(LEMON)).save(recipeOutput, "bonappetit:pink_lemonade_from_lemonade");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LIMEADE.get(), 1).requires(LIME_SLICE).requires(LIME_SLICE).requires(SUGAR).requires(GLASS_PITCHER).unlockedBy("has_lime", has(LIME)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PINK_LIMEADE.get(), 1).requires(LIME_SLICE).requires(LIME_SLICE).requires(POMEGRANATE_SLICE).requires(POMEGRANATE_SLICE).requires(POMEGRANATE_SLICE).requires(SUGAR).requires(GLASS_PITCHER).unlockedBy("has_lime", has(LIME)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PINK_LIMEADE.get(), 1).requires(LIMEADE).requires(POMEGRANATE_SLICE).requires(POMEGRANATE_SLICE).requires(POMEGRANATE_SLICE).unlockedBy("has_lime", has(LIME)).save(recipeOutput, "bonappetit:pink_limeade_from_limeade");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BLUEBERRY_LIMEADE.get(), 1).requires(LIME_SLICE).requires(LIME_SLICE).requires(BLUEBERRIES).requires(SUGAR).requires(GLASS_PITCHER).unlockedBy("has_lime", has(LIME)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BLUEBERRY_LIMEADE.get(), 1).requires(LIMEADE).requires(BLUEBERRIES).unlockedBy("has_lime", has(LIME)).save(recipeOutput, "bonappetit:blueberry_limeade_from_limeade");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DRAGON_FRUIT_LATTE.get(), 1).requires(DRAGON_FRUIT_SLICE).requires(DRAGON_FRUIT_SLICE).requires(Tags.Items.BUCKETS_MILK).requires(ROSE_BUSH).requires(GLASS_PITCHER).unlockedBy("has_dragon_fruit", has(DRAGON_FRUIT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, JEWELED_RICE_BOWL.get(), 1).requires(DRAGON_FRUIT_SLICE).requires(DRAGON_FRUIT_SLICE).requires(DRAGON_FRUIT_SLICE).requires(POMEGRANATE_SEEDS).requires(LIME).requires(ROASTED_ACORN).requires(RICE).requires(BOWL).unlockedBy("has_dragon_fruit", has(DRAGON_FRUIT)).save(recipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");}
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");}
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {for(ItemLike itemlike : pIngredients) {SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(recipeOutput, BonAppetit.ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));}}
}
