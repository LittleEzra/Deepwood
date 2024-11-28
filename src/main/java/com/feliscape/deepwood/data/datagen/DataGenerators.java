package com.feliscape.deepwood.data.datagen;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.data.datagen.language.DWLanguageProvider;
import com.feliscape.deepwood.data.datagen.loot.DWBlockLootTableProvider;
import com.feliscape.deepwood.data.datagen.model.DWBlockStateProvider;
import com.feliscape.deepwood.data.datagen.model.DWItemModelProvider;
import com.feliscape.deepwood.data.datagen.recipe.DWRecipeProvider;
import com.feliscape.deepwood.data.datagen.tag.DWBlockTagGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Deepwood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeServer()){
            DWGeneratedEntries generatedEntries = new DWGeneratedEntries(packOutput, lookupProvider);
            lookupProvider = generatedEntries.getRegistryProvider();
            generator.addProvider(true, generatedEntries);

            generator.addProvider(true, new DWBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new DWItemModelProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new DWRecipeProvider.Runner(packOutput, lookupProvider));

            generator.addProvider(true, new DWBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

            generator.addProvider(true, new DWLanguageProvider(packOutput, "en_us"));

            generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                    List.of(new LootTableProvider.SubProviderEntry(DWBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        }
    }
}
