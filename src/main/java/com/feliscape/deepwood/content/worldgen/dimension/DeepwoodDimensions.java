package com.feliscape.deepwood.content.worldgen.dimension;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.worldgen.biome.DeepwoodBiomes;
import com.feliscape.deepwood.content.worldgen.noise.DeepwoodNoiseGeneratorSettings;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class DeepwoodDimensions {
    public static final ResourceKey<LevelStem> DEEPWOOD_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Deepwood.asResource("deepwood"));
    public static final ResourceKey<Level> DEEPWOOD_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Deepwood.asResource("deepwood"));
    public static final ResourceKey<DimensionType> DEEPWOOD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            Deepwood.asResource("deepwood"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(DEEPWOOD_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        /*NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(DeepwoodBiomes.TEST)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));*/

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                /*Pair.of(Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)),
                                Pair.of(Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST)),
                                Pair.of(Climate.parameters(0.3F, 0.6F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.OCEAN)),
                                Pair.of(Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.DARK_FOREST)),*/
                                Pair.of(Climate.parameters(0.2F, 0.6F, 0.0F, 0.1F, 0.0F, 0.2F, 0.0F),
                                        biomeRegistry.getOrThrow(DeepwoodBiomes.TEST))

                        ))),
                noiseGenSettings.getOrThrow(DeepwoodNoiseGeneratorSettings.DEEPWOOD));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(DEEPWOOD_TYPE), noiseBasedChunkGenerator);

        context.register(DEEPWOOD_KEY, stem);
    }
}
