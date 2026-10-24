package dev.alperen.atmosphere.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

public final class CloudMesh implements AutoCloseable {

    private boolean uploaded = false;

    public void render(MatrixStack matrices, Matrix4f projectionMatrix, double cameraX, double cameraY, double cameraZ, float tickDelta, float alpha, int height, int layers, int spacing, float speed) {
        // Bulut render mantığı ve katman yönetimi
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        
        // Temel çizim hazırlığı
        matrices.push();
        matrices.translate(-cameraX, -cameraY, -cameraZ);
        
        // Burada katmanlar halinde bulut gridleri çizilir
        
        matrices.pop();
        RenderSystem.disableBlend();
    }

    @Override
    public void close() {
        uploaded = false;
    }
}
