package com.feliscape.deepwood.data.datagen.model;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.item.SpecterscopeItem;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class DWItemModelProvider extends ItemModelProvider {
    public DWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Deepwood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(DeepwoodItems.SPECTERLITE);
        createSpecterscope(DeepwoodItems.SPECTERSCOPE);
        simpleItem(DeepwoodItems.RAW_AMBOSS);
        simpleItem(DeepwoodItems.AMBOSS_INGOT);
        simpleItem(DeepwoodItems.AMBOSS_NUGGET);
        simpleItem(DeepwoodItems.WRAITH_AMBOSS_INGOT);
        simpleItem(DeepwoodItems.OLD_BONES);

    }
    private ItemModelBuilder simpleItem(DeferredItem<?> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                Deepwood.asResource("item/" + item.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(DeferredItem<?> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                Deepwood.asResource("item/" + item.getId().getPath()));
    }
    private ItemModelBuilder candyCaneItem(DeferredItem<?> item){
        return withExistingParent(item.getId().getPath(),
                Deepwood.asResource("item/candy_cane_template")).texture("layer0",
                Deepwood.asResource("item/" + item.getId().getPath()));
    }
    private ItemModelBuilder rotatedHandheldItem(DeferredItem<?> item){
        return withExistingParent(item.getId().getPath(),
                Deepwood.asResource("item/rotated_handheld")).texture("layer0",
                Deepwood.asResource("item/" + item.getId().getPath()));
    }

    /*public void manualBlockItem(DeferredBlock<?> block) {
        this.withExistingParent(WinterShine.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + NeoForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(DeferredBlock<?> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + NeoForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  WinterShine.asResource("block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  WinterShine.asResource("block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  WinterShine.asResource("block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }*/

    private ItemModelBuilder blockItemSprite(DeferredBlock<?> item) { // Uses a block instead of item (Example: Doors)
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                Deepwood.asResource("item/" + item.getId().getPath()));
    }
    private ItemModelBuilder generatedBlockItem(DeferredBlock<?> item) { // Uses the texture from textures/block (Example: Saplings)
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                Deepwood.asResource("block/" + item.getId().getPath()));
    }
    private ItemModelBuilder torchItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                Deepwood.asResource("block/" + item.getId().getPath()));
    }


    private ItemModelBuilder createSpecterscope(DeferredItem<? extends SpecterscopeItem> item){
        float step = 1F / 16F;
        ResourceLocation location = Deepwood.asResource("angle");

        ItemModelBuilder builder = withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                        Deepwood.asResource("item/" + item.getId().getPath() + "_1"));

        for (int i = 1; i < 17; i++){
            ModelFile file = withExistingParent(item.getId().getPath() + "_" + (i + 1),
                    ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                    Deepwood.asResource("item/" + item.getId().getPath() + "_" + (i + 1)));
            builder.override().predicate(location, step * i).model(file).end();
        }
        return builder;
    }
}
