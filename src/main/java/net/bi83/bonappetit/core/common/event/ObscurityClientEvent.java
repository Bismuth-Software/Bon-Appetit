package net.bi83.bonappetit.core.common.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.BAEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = BonAppetit.ID, value = Dist.CLIENT)
public class ObscurityClientEvent {
    private static final ResourceLocation VIGNETTE_TEXTURE = ResourceLocation.withDefaultNamespace("textures/misc/vignette.png");
    private static float vignetteAlpha = 0.0f;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiLayerEvent.Post event) {
        if (event.getName().equals(VanillaGuiLayers.CAMERA_OVERLAYS)) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;

            boolean hidden = BAEvents.isHidden(mc.player);
            float pTicks = event.getPartialTick().getGameTimeDeltaPartialTick(true);

            if (hidden) {
                vignetteAlpha = Math.min(1.0f, vignetteAlpha + 0.02f * pTicks);
            } else {
                vignetteAlpha = Math.max(0.0f, vignetteAlpha - 0.05f * pTicks);
            }

            if (vignetteAlpha > 0) {
                GuiGraphics guiGraphics = event.getGuiGraphics();
                int width = mc.getWindow().getGuiScaledWidth();
                int height = mc.getWindow().getGuiScaledHeight();

                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();

                guiGraphics.setColor(0.0F, 0.02F, 0.08F, vignetteAlpha * 0.5f);
                guiGraphics.blit(VIGNETTE_TEXTURE, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);

                guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.disableBlend();
                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
            }
        }
    }
}