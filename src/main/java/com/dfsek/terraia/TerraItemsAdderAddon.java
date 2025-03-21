package com.dfsek.terraia;

import com.dfsek.terra.addons.manifest.api.AddonInitializer;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.api.Platform;
import com.dfsek.terra.api.addon.BaseAddon;
import com.dfsek.terra.api.event.events.config.pack.ConfigPackPreLoadEvent;
import com.dfsek.terra.api.event.functional.FunctionalEventHandler;
import com.dfsek.terra.api.inject.annotations.Inject;
import com.dfsek.terra.api.registry.exception.DuplicateEntryException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TerraItemsAdderAddon implements AddonInitializer {
    @Inject
    private Platform platform;
    @Inject
    private BaseAddon addon;
    @Inject
    private Logger logger;

    @Override
    public void initialize() {
        logger.info("Registering events...");
        platform.getEventManager()
            .getHandler(FunctionalEventHandler.class)
            .register(addon, ConfigPackPreLoadEvent.class)
            .then(event -> {
                try {
                    event.getPack().getCheckedRegistry(FunctionBuilder.class).register(addon.key("itemsAdderBlock"), new ItemsAdderFunctionBuilder());
                } catch(DuplicateEntryException e) {
                    logger.log(Level.SEVERE, "Failed to inject ItemsAdder function!", e);
                    return;
                }

                logger.info("Injected ItemsAdder function!");
            });
        logger.info("Done.");
    }
}
