package com.izhan.texturecorrupter.mixin;

import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(NativeImage.class)
public abstract class TextureCorruptionMixin {
    @Shadow private int width;
    @Shadow private int height;

    private final Random random = new Random();

    @Inject(method = "<init>(Lnet/minecraft/client/texture/NativeImage$Format;IIZJ)V", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        NativeImage img = (NativeImage)(Object)this;
        
        if (this.width <= 0 || this.height <= 0) return;

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (random.nextFloat() < 0.15f) {
                    int color = img.getColorArgb(x, y);
                    img.setColor(x, y, color ^ random.nextInt(0xFFFFFF));
                }
            }
        }

        if (random.nextFloat() < 0.05f) {
            this.width = Math.max(1, this.width + (random.nextInt(11) - 5));
            this.height = Math.max(1, this.height + (random.nextInt(11) - 5));
        }
    }
}
