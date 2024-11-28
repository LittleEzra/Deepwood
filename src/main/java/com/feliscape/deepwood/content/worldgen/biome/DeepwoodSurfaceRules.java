package com.feliscape.deepwood.content.worldgen.biome;

import com.feliscape.deepwood.registry.DeepwoodBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class DeepwoodSurfaceRules {
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource SPIRISTONE = makeStateRule(DeepwoodBlocks.SPIRISTONE.get());
    private static final SurfaceRules.RuleSource SPIRIT_GRASS = makeStateRule(DeepwoodBlocks.SPIRIT_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DEEP_SOIL = makeStateRule(DeepwoodBlocks.DEEP_SOIL.get());

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource deepwood(){

        SurfaceRules.ConditionSource underwaterCondition = SurfaceRules.waterBlockCheck(0, 0);
        SurfaceRules.RuleSource placeGrassOrSoil = SurfaceRules.sequence(SurfaceRules.ifTrue(underwaterCondition, SPIRIT_GRASS), DEEP_SOIL);

        var placeSoilForTestBiome = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(DeepwoodBiomes.TEST), placeGrassOrSoil));

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        builder.add(placeSoilForTestBiome);

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}
