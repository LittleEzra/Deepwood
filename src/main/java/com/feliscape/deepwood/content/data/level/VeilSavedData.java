package com.feliscape.deepwood.content.data.level;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.worldgen.VeilGenerator;
import com.feliscape.deepwood.content.worldgen.noise.DeepwoodNoise;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.List;

public class VeilSavedData extends SavedData {
    private static final String NAME = "deepwood_veil";

    private long seed = -1;
    private RandomSource source;
    private PerlinNoise noise;

    public static VeilSavedData get(ServerLevel serverLevel) {
        var factory = new SavedData.Factory<>(
                () -> new VeilSavedData(serverLevel),
                VeilSavedData::createFrom
        );
        return serverLevel.getDataStorage().computeIfAbsent(factory, NAME);
    }

    private VeilSavedData(){

    }
    private VeilSavedData(ServerLevel level){
        this.seed = level.getSeed();
        createNoise();
    }

    public double sampleNoise(double x, double y){
        if (noise == null){
            Deepwood.LOGGER.error("Trying to sample noise, but noise is null!");
            return 0.0D;
        }
        return noise.getValue(x, y, 0);
    }

    private void createNoise(){
        if (this.seed < 0){
            Deepwood.LOGGER.warn("Tried to recreate noise with no seed");
        }
        if (source == null) createRandomSource();
        this.noise = PerlinNoise.create(source, -4, 1.0);
    }

    private void createRandomSource() {
        this.source = RandomSource.create(this.seed);
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag, HolderLookup.Provider provider) {
        compoundTag.putLong("Seed", seed);
        return compoundTag;
    }

    public VeilSavedData load(CompoundTag tag, HolderLookup.Provider provider){
        if (!tag.contains("Seed"))
            this.seed = RandomSupport.generateUniqueSeed();
        else this.seed = tag.getLong("Seed");
        this.createRandomSource();
        this.createNoise();
        return this;
    }
    public static VeilSavedData createFrom(CompoundTag tag, HolderLookup.Provider provider){
        VeilSavedData data = create();
        data.load(tag, provider);
        return data;
    }
    public static VeilSavedData create() {
        return new VeilSavedData();
    }

    @Override
    public boolean isDirty() {
        return true;
    }
}
