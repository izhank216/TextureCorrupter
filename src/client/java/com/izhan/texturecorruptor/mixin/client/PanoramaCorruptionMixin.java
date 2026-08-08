// this was edited from logo corruption mixin
package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.renderer.CubeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CubeMap.class)
public class PanoramaCorruptionMixin {
    @ModifyConstant(
            method = "render",
            constant = @Constant(floatValue = 85.0F)
    )
    private float corruptPanoramaFOV(float constant) {
        return 360.0F;
    }

    @ModifyConstant(
            method = "render",
            constant = @Constant(floatValue = 10.0F)
    )
    private float corruptPanoramaDistance(float constant) {
        return -50.0F;
    }
}
