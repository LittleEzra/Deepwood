package com.feliscape.deepwood.content.tags;

import com.feliscape.deepwood.Deepwood;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.dimension.DimensionType;

public class DeepwoodDimensionTags {
    public static TagKey<DimensionType> SPIRIT = create("spirit");

    public static TagKey<DimensionType> create(String name){
        return TagKey.create(Registries.DIMENSION_TYPE, Deepwood.asResource(name));
    }
}
