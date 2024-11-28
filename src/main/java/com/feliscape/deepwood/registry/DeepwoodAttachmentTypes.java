package com.feliscape.deepwood.registry;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.data.attachment.VeilChunkData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class DeepwoodAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Deepwood.MOD_ID);

    public static final Supplier<AttachmentType<VeilChunkData>> VEIL =
            ATTACHMENT_TYPES.register("veil", () -> AttachmentType.serializable(VeilChunkData::new).build());

    public static void register(IEventBus eventBus){
        ATTACHMENT_TYPES.register(eventBus);
    }
}
