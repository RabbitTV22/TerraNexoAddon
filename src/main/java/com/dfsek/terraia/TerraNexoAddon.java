package com.dfsek.terraia;

import com.dfsek.terra.addons.manifest.api.AddonInitializer;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.api.Platform;
import com.dfsek.terra.api.addon.BaseAddon;
import com.dfsek.terra.api.event.events.config.pack.ConfigPackPreLoadEvent;
import com.dfsek.terra.api.event.functional.FunctionalEventHandler;
import com.dfsek.terra.api.inject.annotations.Inject;
import com.dfsek.terra.api.registry.exception.DuplicateEntryException;

import org.slf4j.Logger;

public class TerraNexoAddon implements AddonInitializer {
    @Inject
    private Platform platform;
    @Inject
    private BaseAddon addon;
    @Inject
    private Logger logger;

    @Override
    public void initialize() {
        platform.getEventManager()
            .getHandler(FunctionalEventHandler.class)
            .register(addon, ConfigPackPreLoadEvent.class)
            .then(event -> {
                try {
                    event.getPack().getCheckedRegistry(FunctionBuilder.class).register(addon.key("nexoBlock"), new NexoFunctionBuilder());
                } catch(DuplicateEntryException e) {
                    logger.error("Failed to register Nexo function!", e);
                    return;
                }

                logger.info("Registered Nexo function!");
            })
            .priority(90);
    }
}
