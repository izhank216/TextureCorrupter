// this was edited from logo corruption mixin
package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PanoramaRenderer.class)
public class PanoramaCorruptionMixin {
    @ModifyConstant(
            method = "render",
            constant = @Constant(floatValue = 0.1F)
    )
    private float corruptPanoramaSpin(float constant) {
        return 50.0F;
    }
}
