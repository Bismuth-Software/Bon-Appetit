package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.bi83.bonappetit.core.BAItems.*;

public class BACreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BonAppetit.ID);

    public static final Supplier<CreativeModeTab> BA_TAB = CREATIVE_MODE_TAB.register("bonappetit_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(MACARON.get()))
            .title(Component.translatable("tab.bonappetit"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(BABlocks.DRYING_RACK);

                output.accept(PAPER_PLATE);
                output.accept(PIE_CRUST);
                output.accept(GLASS_PITCHER);
                output.accept(GLASS_COCKTAIL);

                output.accept(DOUGH);
                output.accept(WAFER);

                output.accept(CHERRIES);
                output.accept(GOLDEN_CHERRIES);

                output.accept(APPLE_SLICE);
                output.accept(GREEN_APPLE);
                output.accept(HONEY_APPLE);
                output.accept(CANDY_APPLE);
                output.accept(CARAMEL_APPLE);
                output.accept(APPLE_PIE);
                output.accept(APPLE_PIE_SLICE);
                output.accept(APPLE_CAKE_SLICE);
                output.accept(APPLE_JUICE);
                output.accept(APPLE_CIDER);
                output.accept(APPLEJACK);

                output.accept(GRAPEFRUIT);
                output.accept(GRAPEFRUIT_SLICE);

                output.accept(ORANGE);
                output.accept(ORANGE_SLICE);
                output.accept(CANDIED_ORANGE_PEELS);
                output.accept(ORANGE_CAKE_SLICE);
                output.accept(ORANGE_SORBET);
                output.accept(ORANGE_SHERBET);
                output.accept(ORANGE_PUDDING);
                output.accept(ORANGE_JUICE);

                output.accept(PUMPKIN_SLICE);
                output.accept(PUMPKIN_PIE_SLICE);

                output.accept(MANGO);

                output.accept(APRICOT);

                output.accept(PINEAPPLE);

                output.accept(BANANA);
                output.accept(DRIED_BANANA);
                output.accept(BANANA_BREAD);
                output.accept(BANANA_CAKE_SLICE);
                output.accept(BANANA_SMOOTHIE);

                output.accept(LEMON);
                output.accept(LEMON_SLICE);
                output.accept(LEMON_TART);
                output.accept(LEMON_MERINGUE_PIE);
                output.accept(LEMON_MERINGUE_PIE_SLICE);
                output.accept(LEMON_CAKE_SLICE);
                output.accept(LEMONADE);

                output.accept(LIME);
                output.accept(LIME_SLICE);
                output.accept(LIME_COOKIE);
                output.accept(LIME_POPSICLE);
                output.accept(DOUBLE_LIME_POPSICLE);
                output.accept(LIME_CAKE_SLICE);
                output.accept(LIMEADE);

                output.accept(KIWI);

                output.accept(PEAR);

                output.accept(GRAPES);
                output.accept(RAISINS);

                output.accept(PEACH);

                output.accept(DRAGON_FRUIT);

                output.accept(POMEGRANATE);
                output.accept(POMEGRANATE_SLICE);

                output.accept(COCONUT);
                output.accept(COCONUT_SLICE);

                output.accept(STRAWBERRIES);
                output.accept(GOLDEN_STRAWBERRIES);
                output.accept(WINGED_STRAWBERRY);
                output.accept(WINGED_GOLDEN_STRAWBERRY);

                output.accept(CRANBERRIES);

                output.accept(SALMONBERRIES);

                output.accept(BLUEBERRIES);

                output.accept(MULBERRIES);

                output.accept(RASPBERRIES);
                output.accept(BLACK_RASPBERRIES);

                output.accept(CORN);
                output.accept(CORN_ON_A_COB);
                output.accept(CORN_KERNELS);
                output.accept(POPCORN);

                output.accept(GREEN_TEA_LEAVES);
                output.accept(YELLOW_TEA_LEAVES);
                output.accept(BLACK_TEA_LEAVES);

                output.accept(COFFEE_BERRIES);
                output.accept(COFFEE_BEANS);
                output.accept(COFFEE);

                output.accept(CINNAMON_STICKS);

                output.accept(BROWNIE);
                output.accept(PLAIN_COOKIE);
                output.accept(SNICKERDOODLE);
                output.accept(ECLIPSE_COOKIE);
                output.accept(GOLDEN_COOKIE);
                output.accept(MACARON);
                output.accept(PANETTONE);
                output.accept(AMBROSIA_SALAD);
                output.accept(STRAWBERRY_BANANA_SMOOTHIE);
                output.accept(PINK_LEMONADE);
                output.accept(PINK_LADY);
                output.accept(BLUEBERRY_LIMEADE);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}