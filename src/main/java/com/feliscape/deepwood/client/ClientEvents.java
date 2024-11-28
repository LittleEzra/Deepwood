package com.feliscape.deepwood.client;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.client.data.ClientSpecterscopeData;
import com.feliscape.deepwood.client.hud.SpecterscopeGuiLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

public class ClientEvents {
    @EventBusSubscriber(modid = Deepwood.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
    public static class ClientGameEvents
    {
        @SubscribeEvent
        public static void onCustomizeDebugScreen(CustomizeGuiOverlayEvent.DebugText event)
        {
            event.getLeft().add(" ");
            event.getLeft().add("Veil: " + ClientSpecterscopeData.getVeil());
        }
    }
    @EventBusSubscriber(modid = Deepwood.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
        {

        }
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
        @SubscribeEvent
        public static void onRegisterMenuScreens(RegisterMenuScreensEvent event)
        {

        }

        @SubscribeEvent
        public static void registerGuiLayers(final RegisterGuiLayersEvent event)
        {
            event.registerAbove(VanillaGuiLayers.EXPERIENCE_BAR, SpecterscopeGuiLayer.ID, new SpecterscopeGuiLayer());
        }

        @SubscribeEvent
        public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
        {
            new DeepwoodClient(event);
        }
    }
}
