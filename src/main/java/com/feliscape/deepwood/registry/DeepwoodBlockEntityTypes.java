package com.feliscape.deepwood.registry;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.block.entity.ArcFurnaceBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class DeepwoodBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Deepwood.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ArcFurnaceBlockEntity>> CONFECTIONERY_OVEN =
            BLOCK_ENTITY_TYPES.register("confectionery_oven", () -> new BlockEntityType<>(ArcFurnaceBlockEntity::new, Set.of(DeepwoodBlocks.ARC_FURNACE.get())));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
