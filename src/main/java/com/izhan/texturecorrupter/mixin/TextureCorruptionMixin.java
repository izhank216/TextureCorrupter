package com.izhan.texturecorrupter.mixin;

import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(NativeImage.class)
public abstract class TextureCorruptionMixin {
    @Shadow private int width;
    @Shadow private int height;

    private static final Random RND = new Random();

    @ModifyVariable(method = "<init>(Lnet/minecraft/client/texture/NativeImage$Format;IIZJ)V", at = @At("HEAD"), ordinal = 0)
    private static int modifyWidth(int w) {
        return w > 0 && RND.nextFloat() < 0.1f ? w + (RND.nextInt(17) - 8) : w;
    }

    @ModifyVariable(method = "<init>(Lnet/minecraft/client/texture/NativeImage$Format;IIZJ)V", at = @At("HEAD"), ordinal = 1)
    private static int modifyHeight(int h) {
        return h > 0 && RND.nextFloat() < 0.1f ? h + (RND.nextInt(17) - 8) : h;
    }

    @Inject(method = "<init>(Lnet/minecraft/client/texture/NativeImage$Format;IIZJ)V", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        NativeImage img = (NativeImage)(Object)this;
        int w = this.width;
        int h = this.height;
        if (w <= 0 || h <= 0) return;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (RND.nextFloat() < 0.20f) {
                    img.setColor(x, y, img.getColor(x, y) ^ RND.nextInt(0xFFFFFF));
                }
            }
        }
    }
}
