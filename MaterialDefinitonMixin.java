package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.model.geom.builders.MaterialDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// Code written by kizzy-june.

@Mixin(MaterialDefinition.class)
public class MaterialDefinitonMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static int modifyX(int x) {
        return x + 10;
    }
}
