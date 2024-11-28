package com.feliscape.deepwood.content.worldgen.noise;

import com.feliscape.deepwood.Deepwood;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class DeepwoodNoiseRouterData {

    private static ResourceKey<DensityFunction> createKey(String location) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Deepwood.asResource(location));
    }

    public static void bootstrap(BootstrapContext<DensityFunction> context) {

    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> densityFunctions, ResourceKey<DensityFunction> key) {
        return new DensityFunctions.HolderHolder(densityFunctions.getOrThrow(key));
    }

    protected static NoiseRouter deepwood(
            HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParameters
    ) {
        DensityFunction temperature = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.TEMPERATURE));
        DensityFunction vegetation = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.VEGETATION));
        DensityFunction factorDensity = getFunction(densityFunctions, NoiseRouterData.FACTOR);
        DensityFunction depth = getFunction(densityFunctions, NoiseRouterData.DEPTH).squeeze();
        DensityFunction baseDensity = noiseGradientDensity(DensityFunctions.cache2d(factorDensity), depth);
        DensityFunction finalDensity = postProcess(baseDensity);

        return new NoiseRouter(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                temperature,
                vegetation,
                getFunction(densityFunctions, NoiseRouterData.CONTINENTS),
                getFunction(densityFunctions, NoiseRouterData.EROSION),
                depth,
                getFunction(densityFunctions, NoiseRouterData.RIDGES),
                slideDeepwood(DensityFunctions.add(baseDensity, DensityFunctions.constant(-0.703125)).clamp(-64.0, 64.0)),
                finalDensity,
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero()
        );
    }

    private static DensityFunction noiseGradientDensity(DensityFunction minFunction, DensityFunction maxFunction) {
        DensityFunction densityfunction = DensityFunctions.mul(maxFunction, minFunction);
        return DensityFunctions.mul(DensityFunctions.constant(4.0), densityfunction.quarterNegative());
    }

    private static DensityFunction slideDeepwood(DensityFunction densityFunction) {
        return slide(densityFunction, -64, 384, 80, 64, -0.078125, 0, 24, 0.1171875);
    }

    private static DensityFunction slide(
            DensityFunction input, int minY, int maxY, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_
    ) {
        DensityFunction densityfunction1 = DensityFunctions.yClampedGradient(minY + maxY - p_224447_, minY + maxY - p_224448_, 1.0, 0.0);
        DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, p_224449_, input);
        DensityFunction densityfunction2 = DensityFunctions.yClampedGradient(minY + p_224450_, minY + p_224451_, 0.0, 1.0);
        return DensityFunctions.lerp(densityfunction2, p_224452_, $$9);
    }


    private static DensityFunction postProcess(DensityFunction densityFunction) {
        DensityFunction densityfunction = DensityFunctions.blendDensity(densityFunction);
        return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64)).squeeze();
    }
}
