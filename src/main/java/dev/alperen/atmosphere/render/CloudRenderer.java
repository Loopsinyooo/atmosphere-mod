package dev.alperen.atmosphere.render;

import dev.alperen.atmosphere.config.AtmosphereConfig;
import dev.alperen.atmosphere.config.ConfigManager;
import net.minecraft.client.render.WorldRenderContext;

public final class CloudRenderer implements AutoCloseable {

    private final CloudMesh mesh = new CloudMesh();

    public void tick() {
        // Her oyun tick'inde bulutların animasyonu ve güncellemeleri burada işlenir.
    }

    public void render(WorldRenderContext context) {
        AtmosphereConfig config = ConfigManager.get();
        if (!config.cloudsEnabled) {
            return;
        }

        // Bulutların ekrana çizdirilmesi
        mesh.render(
                context.matrixStack(),
                context.projectionMatrix(),
                context.camera().getPos().x,
                context.camera().getPos().y,
                context.camera().getPos().z,
                context.tickDelta(),
                config.cloudOpacityPercent / 100.0f,
                config.cloudHeight,
                config.cloudLayers,
                config.cloudLayerSpacing,
                config.cloudSpeedPercent / 100.0f
        );
    }

    @Override
    public void close() {
        mesh.close();
    }
}
