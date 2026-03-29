package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LogoRenderer.class)
public class LogoCorruptionMixin {

    @Unique
    private final Random random = new Random();

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(GuiGraphics guiGraphics, int i, float f, int j, CallbackInfo ci) {
        ci.cancel();

        int k = i / 2 - 128;
        float l = f;

        if (random.nextFloat() < 0.1f) {
            l *= (0.7f + random.nextFloat() * 0.5f);
        }

        int color = ((int) (l * 255.0F) << 24) | 0xFFFFFF;

        this.drawCorruptedTexture(guiGraphics, k, j, 0, 0, 256, 44, 256, 64, ResourceLocation.parse("minecraft:textures/gui/title/minecraft.png"), color);
        this.drawCorruptedTexture(guiGraphics, k + 64, j + 44, 0, 0, 128, 14, 128, 16, ResourceLocation.parse("minecraft:textures/gui/title/edition.png"), color);
        
        this.renderStaticNoise(guiGraphics, k, j, 256, 60, l);
    }

    @Unique
    private void drawCorruptedTexture(GuiGraphics guiGraphics, int x, int y, int u, int v, int width, int height, int texWidth, int texHeight, ResourceLocation texture, int color) {
        int rows = 16;
        int rowHeight = Math.max(1, height / rows);

        for (int i = 0; i < rows; i++) {
            int xOffset = 0;
            float rand = random.nextFloat();
            
            if (rand < 0.12f) {
                xOffset = random.nextInt(10) - 5;
            } else if (rand < 0.015f) {
                xOffset = random.nextInt(60) - 30;
            }

            int vOffset = i * rowHeight;
            if (vOffset < height) {
                int currentHeight = Math.min(rowHeight, height - vOffset);
                guiGraphics.blit(texture, x + xOffset, y + vOffset, (float) u, (float) (v + vOffset), width, currentHeight, texWidth, texHeight, color);
            }
        }
    }

    @Unique
    private void renderStaticNoise(GuiGraphics guiGraphics, int x, int y, int width, int height, float alpha) {
        if (random.nextFloat() < 0.4f) {
            int count = random.nextInt(15);
            for (int i = 0; i < count; i++) {
                int px = x + random.nextInt(width);
                int py = y + random.nextInt(height);
                int pw = random.nextInt(30);
                int ph = 1;
                int noiseAlpha = (int) (random.nextFloat() * 180 * alpha);
                guiGraphics.fill(px, py, px + pw, py + ph, (noiseAlpha << 24) | 0xFFFFFF);
            }
        }
    }
}
