package com.feliscape.deepwood.content.worldgen.biome;

import com.feliscape.deepwood.Deepwood;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class DeepwoodBiomes {
    public static final ResourceKey<Biome> TEST = ResourceKey.create(Registries.BIOME,
            Deepwood.asResource("test"));

    public static void bootstrap(BootstrapContext<Biome> context){
        context.register(TEST, testBiome(context));

    }

    private static Biome testBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.MISC, new MobSpawnSettings.SpawnerData(ModEntityTypes.TWILIGHT_JELLYFISH.get(), 2, 1, 3));

        //BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        // !! WE NEED TO FOLLOW THE SAME ORDER AS VANILLA BIOMES FOR THE BiomeDefaultFeatures !!
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_FLOWER_FOREST);

        //BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PINE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.6f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(            0x4b70b9)
                        .waterFogColor(         0x272753)
                        .skyColor(              0x648fdd)
                        .grassColorOverride(    0x73bbe8)
                        .foliageColorOverride(  0x3c536d)
                        .fogColor(              0x9ab6ea)
                        //.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic(ModSounds.BAR_BRAWL.getHolder().get()))
                        .build())
                .build();
    }
}
