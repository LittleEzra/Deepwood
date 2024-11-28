package com.feliscape.deepwood;

import com.feliscape.deepwood.client.renderer.item.SpecterscopeItemPropertyFunction;
import com.feliscape.deepwood.registry.DeepwoodAttachmentTypes;
import com.feliscape.deepwood.registry.DeepwoodBlocks;
import com.feliscape.deepwood.registry.DeepwoodCreativeModeTabs;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Deepwood.MOD_ID)
public class Deepwood
{
    public static final String MOD_ID = "deepwood";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Deepwood(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        DeepwoodBlocks.register(modEventBus);
        DeepwoodItems.register(modEventBus);
        DeepwoodCreativeModeTabs.register(modEventBus);

        DeepwoodAttachmentTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation asResource(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ItemProperties.register(DeepwoodItems.SPECTERSCOPE.get(), asResource("angle"), new SpecterscopeItemPropertyFunction());
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
}
