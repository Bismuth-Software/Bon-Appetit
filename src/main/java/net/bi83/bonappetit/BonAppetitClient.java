package net.bi83.bonappetit;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BonAppetit.ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = BonAppetit.ID, value = Dist.CLIENT)
public class BonAppetitClient {
    public BonAppetitClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        BonAppetit.LOGGER.info("Bon Appetit successfully ran clientside");
    }
}
