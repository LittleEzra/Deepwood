package com.feliscape.deepwood.content.data.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class VeilChunkData implements INBTSerializable<CompoundTag> {
    private float veil;

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("Veil", veil);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.veil = nbt.getFloat("Veil");
    }

    public void setVeil(float veil) {
        this.veil = veil;
    }

    public float getVeil() {
        return veil;
    }
}
