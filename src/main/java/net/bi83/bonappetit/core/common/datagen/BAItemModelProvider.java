package net.bi83.bonappetit.core.common.datagen;

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
        basicItem(GLASS_COCKTAIL.get());

        basicItem(CHERRIES.get());
        basicItem(GOLDEN_CHERRIES.get());
        
        basicItem(APPLE_SLICE.get());
        basicItem(GREEN_APPLE.get());
        basicItem(HONEY_APPLE.get());
        basicItem(CARAMEL_APPLE.get());
        basicItem(CANDY_APPLE.get());
        basicItem(APPLE_PIE.get());
        basicItem(APPLE_PIE_SLICE.get());
        basicItem(APPLE_CAKE_SLICE.get());
        basicItem(APPLE_JUICE.get());
        basicItem(APPLE_CIDER.get());
        basicItem(APPLEJACK.get());
        
        basicItem(GRAPEFRUIT.get());
        basicItem(GRAPEFRUIT_SLICE.get());
        
        basicItem(ORANGE.get());
        basicItem(ORANGE_SLICE.get());
        basicItem(CANDIED_ORANGE_PEELS.get());
        basicItem(ORANGE_CAKE_SLICE.get());
        basicItem(ORANGE_SORBET.get());
        basicItem(ORANGE_SHERBET.get());
        basicItem(ORANGE_PUDDING.get());
        basicItem(ORANGE_JUICE.get());
        
        basicItem(PUMPKIN_SLICE.get());
        
        basicItem(MANGO.get());
        
        basicItem(APRICOT.get());
        
        basicItem(PINEAPPLE.get());

        basicItem(BANANA.get());
        basicItem(DRIED_BANANA.get());
        basicItem(BANANA_BREAD.get());
        basicItem(BANANA_CAKE_SLICE.get());
        basicItem(BANANA_SMOOTHIE.get());

        basicItem(LEMON.get());
        basicItem(LEMON_SLICE.get());
        basicItem(LEMON_TART.get());
        basicItem(LEMON_MERINGUE_PIE.get());
        basicItem(LEMON_MERINGUE_PIE_SLICE.get());
        basicItem(LEMON_CAKE_SLICE.get());
        basicItem(LEMONADE.get());

        basicItem(LIME.get());
        basicItem(LIME_SLICE.get());
        basicItem(LIME_COOKIE.get());
        basicItem(LIME_POPSICLE.get());
        basicItem(DOUBLE_LIME_POPSICLE.get());
        basicItem(LIME_CAKE_SLICE.get());
        basicItem(LIMEADE.get());
        
        basicItem(KIWI.get());
        
        basicItem(PEAR.get());
        
        basicItem(GRAPES.get());
        
        basicItem(PEACH.get());
        
        basicItem(DRAGON_FRUIT.get());
        
        basicItem(POMEGRANATE.get());
        basicItem(POMEGRANATE_SLICE.get());
        
        basicItem(COCONUT.get());
        basicItem(COCONUT_SLICE.get());

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

        basicItem(CORN.get());
        basicItem(CORN_ON_A_COB.get());
        basicItem(CORN_KERNELS.get());
        basicItem(POPCORN.get());

        basicItem(GREEN_TEA_LEAVES.get());
        basicItem(YELLOW_TEA_LEAVES.get());
        basicItem(BLACK_TEA_LEAVES.get());
        
        basicItem(COFFEE_BERRIES.get());
        basicItem(COFFEE_BEANS.get());
        basicItem(COFFEE.get());

        handheldItem(CINNAMON_STICKS.get());

        basicItem(PLAIN_COOKIE.get());
        basicItem(SNICKERDOODLE.get());
        basicItem(GOLDEN_COOKIE.get());
        basicItem(MACARON.get());
        basicItem(AMBROSIA_SALAD.get());
        basicItem(STRAWBERRY_BANANA_SMOOTHIE.get());
        basicItem(PINK_LEMONADE.get());
        basicItem(PINK_LADY.get());
        basicItem(BERRY_LIMEADE.get());
    }
}