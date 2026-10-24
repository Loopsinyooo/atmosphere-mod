package dev.alperen.atmosphere.render;

public final class CloudMesh implements AutoCloseable {

    public void render() {
        /*
         * Minecraft 1.21.11 GPU çizimi henüz uygulanmadı.
         *
         * Mevcut projede de geometri üreten veya bulut çizen
         * bir uygulama bulunmuyordu.
         *
         * Bu nedenle vanilla bulutlar iptal edilmez.
         */
    }

    @Override
    public void close() {
        // Henüz oluşturulmuş GPU kaynağı yok.
    }
}
