package com.feliscape.deepwood.content.event;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.client.data.ClientSpecterscopeData;
import com.feliscape.deepwood.content.data.level.VeilSavedData;
import com.feliscape.deepwood.content.tags.DeepwoodDimensionTags;
import com.feliscape.deepwood.content.worldgen.dimension.DeepwoodDimensions;
import com.feliscape.deepwood.networking.ClientPayloadHandler;
import com.feliscape.deepwood.networking.payload.S2CVeilDataSyncPayload;
import com.feliscape.deepwood.registry.DeepwoodAttachmentTypes;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkDataEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.List;
import java.util.Optional;

public class Events {
    @EventBusSubscriber(modid = Deepwood.MOD_ID)
    public static class GameEvents {
        /*private static final RandomSource RANDOM = RandomSource.create();
        private static final PerlinNoise VEIL_NOISE = PerlinNoise.create(RANDOM, List.of(1));
        @SubscribeEvent
        public static void onChunkDataLoad(ChunkDataEvent.Load event){
            ChunkAccess chunk = event.getChunk();
            LevelAccessor level = event.getLevel();
            Deepwood.LOGGER.debug("Level == null?: " + (level == null));
            if (level == null || level.dimensionType().natural()){
                if (!chunk.hasData(DeepwoodAttachmentTypes.VEIL)){
                    //Deepwood.LOGGER.debug(Boolean.toString(chunk.getLevel() == null));
                    float f = (float) VEIL_NOISE.getValue(chunk.getPos().x / 2D, chunk.getPos().z / 2D, 0);
                    Deepwood.LOGGER.debug("X: %d, Y: %d, Perlin: %f, ".formatted(chunk.getPos().x, chunk.getPos().z, f));
                    f = Math.clamp((f + 1F) / 2F, 0F, 1F) ; // Normalize Noise
                    float veil = -Mth.square(f - 1f) + 1;
                    chunk.getData(DeepwoodAttachmentTypes.VEIL)
                            .setVeil(veil * 16F);
                }
            }
        }*/
        private static final RandomSource RANDOM = RandomSource.create();
        @SubscribeEvent
        public static void onChunkLoad(ChunkEvent.Load event){
            ChunkAccess chunk = event.getChunk();
            LevelAccessor level = event.getLevel();
            if (level instanceof ServerLevel serverLevel && level.dimensionType().natural()){
                //Deepwood.LOGGER.debug("Loading Chunk at X: %d, Z: %d. Level: %s".formatted(chunk.getPos().x, chunk.getPos().z, Boolean.toString(level == null)));
                if (!chunk.hasData(DeepwoodAttachmentTypes.VEIL)){
                    var data = VeilSavedData.get(serverLevel);
                    float f = (float) data.sampleNoise(chunk.getPos().x, chunk.getPos().z);
                    f = Math.clamp((f + 1F) / 2F, 0F, 1F);
                    //float veil = -Mth.square(f - 1f) + 1;
                    f = (float) Math.pow(f, 1.8);
                    chunk.getData(DeepwoodAttachmentTypes.VEIL)
                            .setVeil(f * 16F);
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Post event){
            ChunkAccess chunk = event.getEntity().level().getChunkAt(event.getEntity().blockPosition());
            if (chunk.hasData(DeepwoodAttachmentTypes.VEIL)){
                if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                    PacketDistributor.sendToPlayer(serverPlayer, new S2CVeilDataSyncPayload(
                            chunk.getData(DeepwoodAttachmentTypes.VEIL).getVeil()));
                }
            } else{
                float value = event.getEntity().level().dimensionTypeRegistration().is(DeepwoodDimensionTags.SPIRIT) ? 0.0F : 16.0F;
                if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                    PacketDistributor.sendToPlayer(serverPlayer, new S2CVeilDataSyncPayload(value));
                }
            }
        }
    }
    @EventBusSubscriber(modid = Deepwood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents{
        @SubscribeEvent
        public static void registerPayloads(final RegisterPayloadHandlersEvent event){
            final PayloadRegistrar registrar = event.registrar("1");
            registrar.playToClient(
                    S2CVeilDataSyncPayload.TYPE,
                    S2CVeilDataSyncPayload.STREAM_CODEC,
                    ClientPayloadHandler::handleVeilDataSync
            );
        }

    }
}
