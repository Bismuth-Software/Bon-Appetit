package net.bi83.bonappetit.core.common.datagen;

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
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PIE_CRUST.get(), 2).pattern("# #").pattern(" # ")
                .define('#', WHEAT).unlockedBy("has_wheat", has(WHEAT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GLASS_PITCHER.get(), 5).pattern("# #").pattern("# #").pattern(" # ")
                .define('#', GLASS).unlockedBy("has_glass", has(GLASS)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GLASS_COCKTAIL.get(), 6).pattern("# #").pattern(" # ").pattern("###")
                .define('#', GLASS).unlockedBy("has_glass", has(GLASS)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GOLDEN_CHERRIES.get(), 1).pattern("GGG").pattern("G#G").pattern("GGG")
                .define('#', CHERRIES.get()).define('G', GOLD_INGOT).unlockedBy("has_gold_ingot", has(GOLD_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, APPLE_SLICE.get(), 2).requires(APPLE).unlockedBy("has_apple", has(APPLE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CANDY_APPLE.get(), 1).pattern("T").pattern("#").pattern("S").define('#', APPLE).define('S', SUGAR).define('T', STICK).unlockedBy("has_apple", has(APPLE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, APPLE_PIE.get(), 2).pattern("SES").pattern("###").pattern("WPW")
                .define('#', APPLE).define('E', Tags.Items.EGGS).define('S', SUGAR).define('P', PIE_CRUST).define('W', WHEAT).unlockedBy("has_apple", has(APPLE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, APPLE_PIE_SLICE.get(), 4).requires(APPLE_PIE)
                .unlockedBy("has_apple_pie", has(APPLE_PIE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, APPLE_PIE.get(), 1).pattern("##").pattern("##")
                .define('#', APPLE_PIE_SLICE).unlockedBy("has_apple_pie_slice", has(APPLE_PIE_SLICE)).save(recipeOutput, "bonappetit:apple_pie_from_slices");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GRAPEFRUIT_SLICE.get(), 2).requires(GRAPEFRUIT)
                .unlockedBy("has_grapefruit", has(GRAPEFRUIT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ORANGE_SLICE.get(), 2).requires(ORANGE)
                .unlockedBy("has_orange", has(ORANGE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PUMPKIN_SLICE.get(), 9).requires(PUMPKIN)
                .unlockedBy("has_pumpkin", has(PUMPKIN)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PUMPKIN_PIE, 2).pattern("SES").pattern("###").pattern("WPW")
                .define('#', PUMPKIN).define('E', Tags.Items.EGGS).define('S', SUGAR).define('P', PIE_CRUST).define('W', WHEAT).unlockedBy("has_pumpkin", has(PUMPKIN)).save(recipeOutput, "bonappetit:pumpkin_pie");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PUMPKIN_PIE_SLICE.get(), 4).requires(PUMPKIN_PIE)
                .unlockedBy("has_pumpkin_pie", has(PUMPKIN_PIE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PUMPKIN_PIE, 1).pattern("##").pattern("##")
                .define('#', PUMPKIN_PIE_SLICE).unlockedBy("has_pumpkin_pie_slice", has(PUMPKIN_PIE_SLICE)).save(recipeOutput, "bonappetit:pumpkin_pie_from_slices");
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LEMON_SLICE.get(), 2).requires(LEMON)
                .unlockedBy("has_lemon", has(LEMON)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LIME_SLICE.get(), 2).requires(LIME)
                .unlockedBy("has_lime", has(LIME)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MELON_SLICE, 9).requires(MELON)
                .unlockedBy("has_melon", has(MELON)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, POMEGRANATE_SLICE.get(), 2).requires(POMEGRANATE)
                .unlockedBy("has_pomegranate", has(POMEGRANATE)).save(recipeOutput);

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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PANETTONE.get(), 1)
                .requires(MILK_BUCKET).requires(DOUGH).requires(SUGAR).requires(BATags.Items.CITRUS_FRUITS).requires(RAISINS).requires(RAISINS)
                .unlockedBy("has_corn", has(BATags.Items.CITRUS_FRUITS)).save(recipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");}
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");}
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {for(ItemLike itemlike : pIngredients) {SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(recipeOutput, BonAppetit.ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));}}
}
