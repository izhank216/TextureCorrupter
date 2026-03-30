package com.izhan.texturecorruptor.mixin.client;

import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

// Code by Izhan, fixed by kizzy-june.

@Mixin(LogoRenderer.class)
public class LogoCorruptionMixin {
    @ModifyConstant(
            method = "renderLogo(Lnet/minecraft/client/gui/GuiGraphics;IFI)V",
            constant = @Constant(intValue = 64)
    )
    private int corrupt1(int constant) {
        return constant * 3;
    }
    @ModifyConstant(
            method = "renderLogo(Lnet/minecraft/client/gui/GuiGraphics;IFI)V",
            constant = @Constant(intValue = 128)
    )
    private int corrupt2(int constant2) {
        return 20;
    }
}

