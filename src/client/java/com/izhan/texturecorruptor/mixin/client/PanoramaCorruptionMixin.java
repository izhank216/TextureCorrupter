// this was edited from logo corruption mixin
package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PanoramaRenderer.class)
public class PanoramaCorruptionMixin {
    @ModifyConstant(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIZ)V",
            constant = @Constant(intValue = 16)
    )
    private int corruptPanorama1(int constant) {
        return constant * 3;
    }

    @ModifyConstant(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIZ)V",
            constant = @Constant(intValue = 128)
    )
    private int corruptPanorama2(int constant2) {
        return 20;
    }
}
