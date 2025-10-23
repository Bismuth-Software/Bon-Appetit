package net.bi83.bonappetit.core.datagen;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static net.bi83.bonappetit.core.BAItems.*;

public class BAItemModelProvider extends ItemModelProvider {
    public BAItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {super(output, BonAppetit.ID, existingFileHelper);}

    @Override
    protected void registerModels() {
        basicItem(PAPER_PLATE.get());
        basicItem(PIE_CRUST.get());
        basicItem(GLASS_PITCHER.get());

        basicItem(CHERRIES.get());
        basicItem(GOLDEN_CHERRIES.get());
        basicItem(ORANGE.get());
        basicItem(MANGO.get());
        basicItem(APRICOT.get());
        basicItem(PINEAPPLE.get());
        basicItem(LEMON.get());
        basicItem(LEMON_SLICE.get());
        basicItem(LEMONADE.get());
        basicItem(LIME.get());
        basicItem(LIME_SLICE.get());
        basicItem(LIMEADE.get());
        basicItem(KIWI.get());
        basicItem(PEAR.get());
        basicItem(GRAPES.get());
        basicItem(PEACH.get());
        //basicItem(DRAGON_FRUIT.get());
        basicItem(POMEGRANATE.get());
        basicItem(POMEGRANATE_SLICE.get());

        basicItem(STRAWBERRIES.get());
        basicItem(GOLDEN_STRAWBERRIES.get());
        basicItem(WINGED_STRAWBERRY.get());
        basicItem(WINGED_GOLDEN_STRAWBERRY.get());
        basicItem(CRANBERRIES.get());
        basicItem(SALMONBERRIES.get());
        basicItem(BLUEBERRIES.get());
        basicItem(MULBERRIES.get());
        basicItem(RASPBERRIES.get());
        basicItem(BLACK_RASPBERRIES.get());

        basicItem(GREEN_TEA_LEAVES.get());
        basicItem(YELLOW_TEA_LEAVES.get());
        basicItem(BLACK_TEA_LEAVES.get());
        basicItem(COFFEE_BERRIES.get());
        basicItem(COFFEE_BEANS.get());
        basicItem(COFFEE.get());

        basicItem(MERINGUE.get());
        basicItem(MACARON.get());
        basicItem(PINK_LEMONADE.get());
    }
}