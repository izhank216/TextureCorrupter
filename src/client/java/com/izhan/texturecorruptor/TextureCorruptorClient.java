package com.izhan.texturecorruptor;

import net.fabricmc.api.ClientModInitializer;

import static com.mojang.text2speech.Narrator.LOGGER;


// Written by kizzy-june.
public class TextureCorruptorClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		LOGGER.warn("ur textures are now corrupted lmao");
	}
}