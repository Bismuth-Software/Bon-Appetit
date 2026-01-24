package net.bi83.bonappetit;

import net.bi83.bonappetit.core.BABlockEntities;
import net.bi83.bonappetit.core.common.renderer.DryingRackRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BonAppetit.ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = BonAppetit.ID, value = Dist.CLIENT)
public class BonAppetitClient {
    public BonAppetitClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BABlockEntities.DRYING_RACK.get(), DryingRackRenderer::new);
    }
    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        BonAppetit.LOGGER.info("Bon Appetit successfully ran clientside");
    }
}
