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
    @Unique
    private static final ResourceLocation MINECRAFT_LOGO = ResourceLocation.withDefaultNamespace("textures/gui/title/minecraft.png");
    @Unique
    private static final ResourceLocation EDITION_LOGO = ResourceLocation.withDefaultNamespace("textures/gui/title/edition.png");

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(GuiGraphics guiGraphics, int i, float f, int j, CallbackInfo ci) {
        ci.cancel();

        int centerX = i / 2;
        float alpha = f;

        if (random.nextFloat() < 0.05f) {
            alpha *= (0.5f + random.nextFloat() * 0.5f);
        }

        int color = ((int) (alpha * 255.0F) << 24) | 0xFFFFFF;

        this.drawCorrupted(guiGraphics, MINECRAFT_LOGO, centerX - 128, j, 256, 44, 256, 64, color);
        this.drawCorrupted(guiGraphics, EDITION_LOGO, centerX - 64, j + 44, 128, 14, 128, 16, color);
    }

    @Unique
    private void drawCorrupted(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y, int width, int height, int texWidth, int texHeight, int color) {
        int slices = 10 + random.nextInt(10);
        int sliceHeight = Math.max(1, height / slices);

        for (int i = 0; i < slices; i++) {
            int currY = y + (i * sliceHeight);
            
            int wOffset = 0;
            int xOffset = 0;
            
            if (random.nextFloat() < 0.2f) {
                wOffset = random.nextInt(40) - 20;
                xOffset = random.nextInt(10) - 5;
            }

            if (random.nextFloat() < 0.02f) {
                wOffset = random.nextInt(200) - 100;
            }

            int finalWidth = width + wOffset;
            int finalX = x + xOffset - (wOffset / 2);

            guiGraphics.blit(texture, finalX, currY, 0, i * sliceHeight, finalWidth, sliceHeight, texWidth, texHeight, color);
        }
    }
}
