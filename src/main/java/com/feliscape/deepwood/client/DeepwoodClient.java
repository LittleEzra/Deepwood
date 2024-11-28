package com.feliscape.deepwood.client;

import com.feliscape.deepwood.Deepwood;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

public class DeepwoodClient {
    private static DeepwoodClient instance;



    public DeepwoodClient(RegisterClientReloadListenersEvent event){
        instance = this;
        Deepwood.LOGGER.info("DeepwoodClient instantiated");
    }

    public static DeepwoodClient getInstance() {
        return instance;
    }
}
