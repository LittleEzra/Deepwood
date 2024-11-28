package com.feliscape.deepwood.networking;

import com.feliscape.deepwood.client.data.ClientSpecterscopeData;
import com.feliscape.deepwood.networking.payload.S2CVeilDataSyncPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
    public static void handleVeilDataSync(final S2CVeilDataSyncPayload data, final IPayloadContext context){
        ClientSpecterscopeData.setVeil(data.veil());
    }
}

