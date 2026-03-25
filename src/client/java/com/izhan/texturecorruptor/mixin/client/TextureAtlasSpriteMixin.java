package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Code written by kizzy-june.

@Mixin(TextureAtlasSprite.class)
public class TextureAtlasSpriteMixin {
    @Inject(method = "getU", at = @At("TAIL"), cancellable = true)
    private void corrupt_textures(CallbackInfoReturnable<Float> ci) {
        ci.setReturnValue(ci.getReturnValue() + 0.1F);
    }
    @Inject(method = "getV", at = @At("TAIL"), cancellable = true)
    private void corrupt_textures2(CallbackInfoReturnable<Float> ci) {
        ci.setReturnValue(ci.getReturnValue() - 0.1F);
    }
    @ModifyVariable(
            method = "<init>",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private static int corrupt_textureAtlas(int originalValue) {
        int modifiedValue = originalValue;
        return modifiedValue * 2;
    }
}