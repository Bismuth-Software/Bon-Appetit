package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = BonAppetit.ID) @EventBusSubscriber(modid = BonAppetit.ID)
public class BAEvents {
    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((p_329705_, p_329706_) -> {
                    event.register((stack, index) -> index == 0 ? DyedItemColor.getOrDefault(stack, -1) : -1, BAItems.MACARON);

                    return 0xFFFFFFFF;
                },
                BAItems.MACARON.value());
    }
}