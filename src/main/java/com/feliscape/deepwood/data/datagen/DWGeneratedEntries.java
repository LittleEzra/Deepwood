package com.feliscape.deepwood.data.datagen;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.worldgen.biome.DeepwoodBiomes;
import com.feliscape.deepwood.content.worldgen.dimension.DeepwoodDimensions;
import com.feliscape.deepwood.content.worldgen.noise.DeepwoodNoise;
import com.feliscape.deepwood.content.worldgen.noise.DeepwoodNoiseGeneratorSettings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DWGeneratedEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            //.add(Registries.CONFIGURED_FEATURE, DeepwoodConfiguredFeatures::bootstrap)
            //.add(Registries.PLACED_FEATURE, DeepwoodPlacedFeatures::bootstrap)
            //.add(ForgeRegistries.Keys.BIOME_MODIFIERS, DeepwoodBiomeModifiers::bootstrap)
            .add(Registries.DIMENSION_TYPE, DeepwoodDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, DeepwoodDimensions::bootstrapStem)
            .add(Registries.BIOME, DeepwoodBiomes::bootstrap)
            .add(Registries.NOISE_SETTINGS, DeepwoodNoiseGeneratorSettings::bootstrap)
            //.add(Registries.DAMAGE_TYPE, DeepwoodDamageTypes::bootstrap)
            //.add(Registries.NOISE, DeepwoodNoise::bootstrap)
            ;
    public DWGeneratedEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Deepwood.MOD_ID));
    }
}
