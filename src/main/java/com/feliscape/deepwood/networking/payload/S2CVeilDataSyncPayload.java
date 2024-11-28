package com.feliscape.deepwood.networking.payload;

import com.feliscape.deepwood.Deepwood;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

// Server-To-Client
public record S2CVeilDataSyncPayload(float veil) implements CustomPacketPayload {
    public static final Type<S2CVeilDataSyncPayload> TYPE
            = new Type<>(Deepwood.asResource("veil_data_sync"));

    public static final StreamCodec<ByteBuf, S2CVeilDataSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            S2CVeilDataSyncPayload::veil,
            S2CVeilDataSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
