package com.feliscape.deepwood.registry;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.item.SpecterscopeItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeepwoodItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Deepwood.MOD_ID);

    public static final DeferredItem<Item> SPECTERLITE = ITEMS.registerItem("specterlite", Item::new);
    public static final DeferredItem<SpecterscopeItem> SPECTERSCOPE = ITEMS.registerItem("specterscope", SpecterscopeItem::new);
    public static final DeferredItem<Item> RAW_AMBOSS = ITEMS.registerItem("raw_amboss", Item::new);
    public static final DeferredItem<Item> AMBOSS_INGOT = ITEMS.registerItem("amboss_ingot", Item::new);
    public static final DeferredItem<Item> AMBOSS_NUGGET = ITEMS.registerItem("amboss_nugget", Item::new);

    public static final DeferredItem<Item> WRAITH_AMBOSS_INGOT = ITEMS.registerItem("wraith_amboss_ingot", Item::new);

    public static final DeferredItem<Item> OLD_BONES = ITEMS.registerItem("old_bones", Item::new);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
