package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.bi83.bonappetit.core.BABlocks.*;
import static net.bi83.bonappetit.core.BAItems.*;

public class BACreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BonAppetit.ID);

    public static final Supplier<CreativeModeTab> BA_TAB = CREATIVE_MODE_TAB.register("bonappetit_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(MACARON.get()))
            .title(Component.translatable("tab.bonappetit"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(MACARON);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}