package dev.alperen.atmosphere;

import dev.alperen.atmosphere.config.ConfigManager;
import dev.alperen.atmosphere.render.CloudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.world.ClientWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AtmosphereClient implements ClientModInitializer {

    public static final String MOD_ID = "atmosphere";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final CloudRenderer clouds = new CloudRenderer();
    private ClientWorld activeWorld;

    @Override
    public void onInitializeClient() {
        ConfigManager.load();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != activeWorld) {
                clouds.close();
                activeWorld = client.world;
            }

            if (client.world == null || client.player == null || client.isPaused()) {
                return;
            }

            clouds.tick();
        });

        WorldRenderEvents.LAST.register(context -> {
            if (context.world() == activeWorld) {
                clouds.render(context);
            }
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            clouds.close();
            activeWorld = null;
        });

        LOGGER.info("[{}] Initialized on Java {}.", MOD_ID, Runtime.version());
    }
}
