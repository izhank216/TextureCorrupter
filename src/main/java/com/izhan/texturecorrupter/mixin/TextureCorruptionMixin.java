package com.izhan.texturecorrupter.mixin;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(SpriteContents.class)
public abstract class TextureCorruptionMixin {
    @Shadow @Final @Mutable private int width;
    @Shadow @Final @Mutable private int height;
    @Shadow @Final private NativeImage image;

    private final Random random = new Random();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        if (this.width <= 0 || this.height <= 0) return;

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (random.nextFloat() < 0.15f) {
                    int color = image.getColorArgb(x, y);
                    image.setColorArgb(x, y, color ^ random.nextInt(0xFFFFFF));
                }
            }
        }

        if (random.nextFloat() < 0.05f) {
            this.width = Math.max(1, this.width + (random.nextInt(11) - 5));
            this.height = Math.max(1, this.height + (random.nextInt(11) - 5));
        }
    }
}
