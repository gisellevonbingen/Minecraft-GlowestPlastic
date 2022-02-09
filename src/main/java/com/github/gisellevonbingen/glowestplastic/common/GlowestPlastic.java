package com.github.gisellevonbingen.glowestplastic.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.gisellevonbingen.glowestplastic.client.ClientHandler;
import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import com.github.gisellevonbingen.glowestplastic.common.datagen.DataGenerators;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GlowestPlastic.MODID)
public class GlowestPlastic
{
	public static final String MODID = "glowestplastic";
	public static final Logger LOGGER = LogManager.getLogger();

	public GlowestPlastic()
	{
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::new);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(new DataGenerators());

		GlowestPlasticBlocks.register();
	}

}
