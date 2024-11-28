package com.feliscape.deepwood.client.renderer.item;

import com.feliscape.deepwood.client.data.ClientSpecterscopeData;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.jetbrains.annotations.Nullable;

public class SpecterscopeItemPropertyFunction implements ClampedItemPropertyFunction {
    public SpecterscopeItemPropertyFunction(){

    }

    @Override
    public float unclampedCall(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity livingEntity, int seed) {
        Entity entity = (Entity)(livingEntity != null ? livingEntity : stack.getEntityRepresentation());
        if (entity == null) {
            return 0.0F;
        } else {
            level = this.tryFetchLevelIfMissing(entity, level);
            return level == null ? 0.0F : this.getRotation(stack, level, seed, entity);
        }
    }

    @Nullable
    private ClientLevel tryFetchLevelIfMissing(Entity entity, @javax.annotation.Nullable ClientLevel level) {
        return level == null && entity.level() instanceof ClientLevel ? (ClientLevel)entity.level() : level;
    }

    private float getRotation(ItemStack stack, ClientLevel level, int seed, Entity entity) {
        if (level.dimensionType().natural()){
            return 1F - ClientSpecterscopeData.getVeil() / 16F;
        } else{ // TODO: Make it go wild in Deepwood
            return 0.0F;
        }
    }
}
