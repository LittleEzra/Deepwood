package com.feliscape.deepwood.content.worldgen.noise;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.worldgen.biome.DeepwoodSurfaceRules;
import com.feliscape.deepwood.registry.DeepwoodBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class DeepwoodNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> DEEPWOOD = ResourceKey.create(
            Registries.NOISE_SETTINGS, Deepwood.asResource("deepwood")
    );

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        context.register(DEEPWOOD, deepwood(context));
    }

    public static NoiseGeneratorSettings deepwood(BootstrapContext<?> context) {
        return new NoiseGeneratorSettings(
                DeepwoodNoiseSettings.DEEPWOOD_NOISE_SETTINGS,
                DeepwoodBlocks.SPIRISTONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                DeepwoodNoiseRouterData.deepwood(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE)),
                DeepwoodSurfaceRules.deepwood(),
                new OverworldBiomeBuilder().spawnTarget(),
                63,
                false,
                true,
                true,
                false
        );
    }
}
