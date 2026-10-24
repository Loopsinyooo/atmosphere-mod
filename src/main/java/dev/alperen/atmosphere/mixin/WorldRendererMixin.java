package dev.alperen.atmosphere.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void onRenderClouds(CallbackInfo ci) {
        // Özel bulut render sistemimizin varsayılan bulutların yerini alması için burayı kullanıyoruz.
        // İlerleyen adımlarda detaylandıracağız.
    }
}
