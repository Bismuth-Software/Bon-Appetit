package net.bi83.bonappetit;

import net.bi83.bonappetit.core.BABlockEntities;
import net.bi83.bonappetit.core.BAEntities;
import net.bi83.bonappetit.core.BAMenuTypes;
import net.bi83.bonappetit.core.BAModelLayers;
import net.bi83.bonappetit.core.common.recipe.RecipeCategories;
import net.bi83.bonappetit.core.common.renderer.DryingRackRenderer;
import net.bi83.bonappetit.core.content.blockentity.CookingPotScreen;
import net.bi83.bonappetit.core.content.entity.DragonShardModel;
import net.bi83.bonappetit.core.content.entity.DragonShardRenderer;
import net.bi83.bonappetit.core.content.entity.PitchforkModel;
import net.bi83.bonappetit.core.content.entity.ThrownPitchforkRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BonAppetit.ID, dist = Dist.CLIENT) @EventBusSubscriber(modid = BonAppetit.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BonAppetitClient {
    public BonAppetitClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BABlockEntities.DRYING_RACK.get(), DryingRackRenderer::new);
        event.registerEntityRenderer(BAEntities.PITCHFORK.get(), ThrownPitchforkRenderer::new);
        event.registerEntityRenderer(BAEntities.DRAGON_SHARD.get(), DragonShardRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BAModelLayers.PITCHFORK, PitchforkModel::createLayer);
        event.registerLayerDefinition(BAModelLayers.DRAGON_SHARD, DragonShardModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(BAMenuTypes.COOKING_POT.get(), CookingPotScreen::new);
    }

    @SubscribeEvent
    public static void registerRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
        RecipeCategories.init(event);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        BonAppetit.LOGGER.info("Bon Appetit successfully ran clientside");
    }
}