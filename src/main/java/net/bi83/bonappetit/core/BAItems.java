package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BonAppetit.ID);
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus); }

    public static final DeferredItem<Item> MACARON = ITEMS.register("macaron", () -> new Item(new Item.Properties()));
}