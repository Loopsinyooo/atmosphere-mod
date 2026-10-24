package dev.alperen.atmosphere.render;

import dev.alperen.atmosphere.config.ConfigManager;

public final class CloudRenderer implements AutoCloseable {

    private final CloudMesh mesh = new CloudMesh();

    public void tick() {
        // Bulut simülasyonu henüz uygulanmadı.
    }

    public void render() {
        if (!ConfigManager.get().cloudsEnabled) {
            return;
        }

        mesh.render();
    }

    @Override
    public void close() {
        mesh.close();
    }
}
