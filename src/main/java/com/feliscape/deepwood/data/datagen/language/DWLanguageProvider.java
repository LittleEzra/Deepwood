package com.feliscape.deepwood.data.datagen.language;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.registry.DeepwoodBlocks;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class DWLanguageProvider extends LanguageProvider {
    public DWLanguageProvider(PackOutput output, String locale) {
        super(output, Deepwood.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        this.addItem(DeepwoodItems.SPECTERLITE, "Specterlite");
        this.addItem(DeepwoodItems.SPECTERSCOPE, "Specterscope");
        this.addItem(DeepwoodItems.RAW_AMBOSS, "Raw Amboss");
        this.addItem(DeepwoodItems.AMBOSS_INGOT, "Amboss Ingot");
        this.addItem(DeepwoodItems.AMBOSS_NUGGET, "Amboss Nugget");
        this.addItem(DeepwoodItems.WRAITH_AMBOSS_INGOT, "Wraith Amboss Ingot");
        this.addItem(DeepwoodItems.OLD_BONES, "Old Bones");

        this.addBlockAndItem(DeepwoodBlocks.DEEP_SOIL, "Deep Soil");
        this.addBlockAndItem(DeepwoodBlocks.SPIRIT_GRASS_BLOCK, "Spirit Grass Block");
        this.addBlockAndItem(DeepwoodBlocks.AMBOSS_ORE, "Amboss Ore");
        this.addBlockAndItem(DeepwoodBlocks.AMBOSS_BLOCK, "Block of Amboss");
        this.addBlockAndItem(DeepwoodBlocks.RAW_AMBOSS_BLOCK, "Block of Raw Amboss");
        this.addBlockAndItem(DeepwoodBlocks.WRAITH_AMBOSS_BLOCK, "Wraith Amboss Block");

        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE, "Spiristone");
        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_STAIRS, "Spiristone Stairs");
        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_SLAB, "Spiristone Slab");
        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_WALL, "Spiristone Wall");

        this.addBlockAndItem(DeepwoodBlocks.COBBLED_SPIRISTONE, "Cobbled Spiristone");
        this.addBlockAndItem(DeepwoodBlocks.COBBLED_SPIRISTONE_STAIRS, "Cobbled Spiristone Stairs");
        this.addBlockAndItem(DeepwoodBlocks.COBBLED_SPIRISTONE_SLAB, "Cobbled Spiristone Slab");
        this.addBlockAndItem(DeepwoodBlocks.COBBLED_SPIRISTONE_WALL, "Cobbled Spiristone Wall");

        this.addBlockAndItem(DeepwoodBlocks.POLISHED_SPIRISTONE, "Polished Spiristone");
        this.addBlockAndItem(DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS, "Polished Spiristone Stairs");
        this.addBlockAndItem(DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB, "Polished Spiristone Slab");
        this.addBlockAndItem(DeepwoodBlocks.POLISHED_SPIRISTONE_WALL, "Polished Spiristone Wall");

        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_BRICKS, "Spiristone Bricks");
        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS, "Spiristone Brick Stairs");
        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_BRICK_SLAB, "Spiristone Brick Slab");
        this.addBlockAndItem(DeepwoodBlocks.SPIRISTONE_BRICK_WALL, "Spiristone Brick Wall");

        this.add("creative_tab.deepwood.base", "Deepwood");
    }

    public void addBlockAndItem(Supplier<? extends Block> key, String name) {
        this.addBlock(key, name);
        this.addItem(key.get()::asItem, name);
    }

    private void addItemTooltip(Supplier<? extends Item> key, String name) {
        add(key.get().getDescriptionId() + ".tooltip", name);
    }
    private void addMobEffect(Supplier<? extends MobEffect> key, String name) {
        add(key.get().getDescriptionId(), name);
    }
    private void addSubtitle(Supplier<SoundEvent> key, String name) {
        add("subtitle.{}.{}".formatted(Deepwood.MOD_ID, key.get().location().getPath()), name);
    }
}
