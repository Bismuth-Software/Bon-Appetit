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
                output.accept(CHERRIES);
                output.accept(GOLDEN_CHERRIES);
                output.accept(ORANGE);
                output.accept(MANGO);
                output.accept(APRICOT);
                output.accept(PINEAPPLE);
                output.accept(LEMON);
                output.accept(LIME);
                output.accept(KIWI);
                output.accept(PEAR);
                output.accept(GRAPES);
                output.accept(PEACH);
                output.accept(DRAGON_FRUIT);

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

                output.accept(GREEN_TEA_LEAVES);
                output.accept(YELLOW_TEA_LEAVES);
                output.accept(BLACK_TEA_LEAVES);
                output.accept(COFFEE_BERRIES);
                output.accept(COFFEE_BEANS);
                output.accept(COFFEE);

                output.accept(MERINGUE);
                output.accept(MACARON);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}