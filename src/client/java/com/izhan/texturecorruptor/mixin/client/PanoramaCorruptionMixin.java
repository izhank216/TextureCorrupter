package com.izhan.texturecorruptor.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CubeMap.class)
public class CubeMapCorruptionMixin {

    @Inject(
        method = "render",
        at = @At("HEAD")
    )
    private void applyPanoramaCorruption(Minecraft mc, float pitch, float yaw, float alpha, CallbackInfo ci) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(
            (float) Math.random(),
            (float) Math.random(),
            (float) Math.random(),
            alpha
        );
    }
}
