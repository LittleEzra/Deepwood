package com.feliscape.deepwood.content.worldgen.noise;

import com.feliscape.deepwood.Deepwood;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class DeepwoodNoise {
    public static final ResourceKey<NormalNoise.NoiseParameters> VEIL = createKey("veil");


    private static ResourceKey<NormalNoise.NoiseParameters> createKey(String key) {
        return ResourceKey.create(Registries.NOISE, Deepwood.asResource(key));
    }

    public static void bootstrap(BootstrapContext<NormalNoise.NoiseParameters> context) {
        register(context, DeepwoodNoise.VEIL, -6, 1.0);
    }

    private static void register(
            BootstrapContext<NormalNoise.NoiseParameters> context,
            ResourceKey<NormalNoise.NoiseParameters> key,
            int firstOctave,
            double amplitude,
            double... otherAmplitudes
    ) {
        context.register(key, new NormalNoise.NoiseParameters(firstOctave, amplitude, otherAmplitudes));
    }

    public static DensityFunction createVeilDensityFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters){
        return DensityFunctions.noise(noiseParameters.getOrThrow(DeepwoodNoise.VEIL));
    }
}
