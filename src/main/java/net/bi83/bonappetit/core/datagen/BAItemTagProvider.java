package net.bi83.bonappetit.core.datagen;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.bi83.bonappetit.core.BAItems.*;

public class BAItemTagProvider extends ItemTagsProvider {
    public BAItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {super(output, lookupProvider, blockTags, BonAppetit.ID, existingFileHelper);}

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(Tags.Items.FOODS)
                .add(CHERRIES.get())
                .add(GOLDEN_CHERRIES.get())
                .add(APPLE_SLICE.get())
                .add(GRAPEFRUIT.get())
                .add(GRAPEFRUIT_SLICE.get())
                .add(ORANGE.get())
                .add(ORANGE_SLICE.get())
                .add(MANGO.get())
                .add(APRICOT.get())
                .add(PINEAPPLE.get())
                .add(LEMON.get())
                .add(LEMON_SLICE.get())
                .add(LIME.get())
                .add(LIME_SLICE.get())
                .add(KIWI.get())
                .add(PEAR.get())
                .add(GRAPES.get())
                .add(PEACH.get())
                //.add(DRAGON_FRUIT.get())
                .add(POMEGRANATE.get())
                .add(POMEGRANATE_SLICE.get())
                    .add(STRAWBERRIES.get())
                    .add(GOLDEN_STRAWBERRIES.get())
                    .add(WINGED_STRAWBERRY.get())
                    .add(WINGED_GOLDEN_STRAWBERRY.get())
                    .add(CRANBERRIES.get())
                    .add(SALMONBERRIES.get())
                    .add(BLUEBERRIES.get())
                    .add(MULBERRIES.get())
                    .add(RASPBERRIES.get())
                    .add(BLACK_RASPBERRIES.get())
                        .add(GREEN_TEA_LEAVES.get())
                        .add(YELLOW_TEA_LEAVES.get())
                        .add(BLACK_TEA_LEAVES.get())
                        .add(COFFEE_BERRIES.get())
                        .add(COFFEE_BEANS.get())
                            .add(PIE_CRUST.get())
                            .add(MERINGUE.get())
                            .add(MACARON.get());

        this.tag(Tags.Items.FOODS_FRUIT)
                .add(CHERRIES.get())
                .add(GOLDEN_CHERRIES.get())
                .add(APPLE_SLICE.get())
                .add(GRAPEFRUIT.get())
                .add(GRAPEFRUIT_SLICE.get())
                .add(ORANGE.get())
                .add(ORANGE_SLICE.get())
                .add(MANGO.get())
                .add(APRICOT.get())
                .add(PINEAPPLE.get())
                .add(LEMON_SLICE.get())
                .add(LIME.get())
                .add(LIME_SLICE.get())
                .add(KIWI.get())
                .add(PEAR.get())
                .add(GRAPES.get())
                .add(PEACH.get())
                //.add(DRAGON_FRUIT.get())
                .add(POMEGRANATE.get())
                .add(POMEGRANATE_SLICE.get())
                    .add(STRAWBERRIES.get())
                    .add(GOLDEN_STRAWBERRIES.get())
                    .add(WINGED_STRAWBERRY.get())
                    .add(WINGED_GOLDEN_STRAWBERRY.get())
                    .add(CRANBERRIES.get())
                    .add(SALMONBERRIES.get())
                    .add(BLUEBERRIES.get())
                    .add(MULBERRIES.get())
                    .add(RASPBERRIES.get())
                    .add(BLACK_RASPBERRIES.get());
        this.tag(Tags.Items.FOODS_BERRY)
                .add(POMEGRANATE.get())
                .add(POMEGRANATE_SLICE.get())
                    .add(STRAWBERRIES.get())
                    .add(GOLDEN_STRAWBERRIES.get())
                    .add(WINGED_STRAWBERRY.get())
                    .add(WINGED_GOLDEN_STRAWBERRY.get())
                    .add(CRANBERRIES.get())
                    .add(SALMONBERRIES.get())
                    .add(BLUEBERRIES.get())
                    .add(MULBERRIES.get())
                    .add(RASPBERRIES.get())
                    .add(BLACK_RASPBERRIES.get())
                        .add(COFFEE_BERRIES.get());
        this.tag(Tags.Items.FOODS_CANDY)
                                .add(MERINGUE.get());
        this.tag(Tags.Items.FOODS_COOKIE)
                                .add(MACARON.get());
        this.tag(ItemTags.FOX_FOOD)
                    .add(STRAWBERRIES.get())
                    .add(GOLDEN_STRAWBERRIES.get())
                    .add(CRANBERRIES.get())
                    .add(SALMONBERRIES.get())
                    .add(BLUEBERRIES.get())
                    .add(MULBERRIES.get())
                    .add(RASPBERRIES.get())
                    .add(BLACK_RASPBERRIES.get())
                        .add(COFFEE_BERRIES.get());
        this.tag(Tags.Items.DRINKS)
                            .add(LEMONADE.get())
                            .add(LIMEADE.get())
                            .add(COFFEE.get())
                            .add(PINK_LEMONADE.get());

        this.tag(ItemTags.DYEABLE)
                                .add(MACARON.get());
    }
}