package net.bi83.bonappetit;

import com.mojang.logging.LogUtils;
import net.bi83.bonappetit.core.BABlocks;
import net.bi83.bonappetit.core.BACreativeTabs;
import net.bi83.bonappetit.core.BAEffects;
import net.bi83.bonappetit.core.BAItems;
import net.bi83.bonappetit.core.common.event.ReflectionEvent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(BonAppetit.ID)
public class BonAppetit {
    public static final String ID = "bonappetit";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BonAppetit(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        BABlocks.register(modEventBus);
        BAItems.register(modEventBus);
        BACreativeTabs.register(modEventBus);

        BAEffects.EFFECTS.register(modEventBus);

        NeoForge.EVENT_BUS.register(ReflectionEvent.class);
        modContainer.registerConfig(ModConfig.Type.COMMON, BonAppetitConfig.SPEC);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Bon Appetit successfully loaded");
/*
        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));*/
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Bon Appetit successfully ran serverside");
    }
}
