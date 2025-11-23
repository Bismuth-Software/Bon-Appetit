package net.bi83.bonappetit;

import com.mojang.logging.LogUtils;
import net.bi83.bonappetit.core.*;
import net.bi83.bonappetit.core.common.event.ConcentrationEvent;
import net.bi83.bonappetit.core.common.event.ReflectionEvent;
import net.bi83.bonappetit.core.content.blockentity.CopperTankEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.slf4j.Logger;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod(BonAppetit.ID)
public class BonAppetit {
    public static final String ID = "bonappetit";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static ResourceLocation asResource(String path) {return ResourceLocation.fromNamespaceAndPath(ID, path);}
    public static void queueServerWork(int tick, Runnable action) {if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {workQueue.add(new AbstractMap.SimpleEntry(action, tick));}}
    public BonAppetit(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::modifyComponents);
        NeoForgeMod.enableMilkFluid();

        BABlocks.register(modEventBus);
        BABlockEntities.register(modEventBus);
        BAItems.register(modEventBus);
        BACreativeTabs.register(modEventBus);

        BAEffects.EFFECTS.register(modEventBus);

        NeoForge.EVENT_BUS.register(ReflectionEvent.class);
        NeoForge.EVENT_BUS.register(ConcentrationEvent.class);
        modEventBus.addListener((RegisterCapabilitiesEvent event) -> {event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, BABlockEntities.COPPER_TANK.get(), (be, side) -> {if (be instanceof CopperTankEntity tank) {return tank.getTank();}return null;});});
        modContainer.registerConfig(ModConfig.Type.COMMON, BonAppetitConfig.SPEC);
    }

    @SubscribeEvent
    public void modifyComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.COOKIE, builder -> builder.set(DataComponents.FOOD, new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).fast().build()));
        event.modifyMatching(item -> item.hasCraftingRemainingItem(), builder -> builder.remove(DataComponents.BUCKET_ENTITY_DATA));
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Bon Appetit successfully loaded");
/*
        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));}

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));*/
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Bon Appetit successfully ran serverside");
    }
}