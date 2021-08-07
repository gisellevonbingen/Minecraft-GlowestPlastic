package com.github.gisellevonbingen.glowestplastic.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;

import mekanism.api.text.EnumColor;
import mekanism.client.ClientRegistrationUtil;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientHandler
{
	public ClientHandler()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(this);
	}

	@SubscribeEvent
	public void registerBlockColors(ColorHandlerEvent.Item event)
	{
		List<Map<EnumColor, ? extends BlockRegistryObject<?, ?>>> list = new ArrayList<>();
		list.add(GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS);
		list.add(GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS);
		list.add(GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS);

		this.registerBlockColorHandles(event.getBlockColors(), event.getItemColors(), list);
	}

	private void registerBlockColorHandles(BlockColors blockColors, ItemColors itemColors, List<Map<EnumColor, ? extends BlockRegistryObject<?, ?>>> blocks)
	{
		for (Map<EnumColor, ? extends BlockRegistryObject<?, ?>> blockMap : blocks)
		{
			for (BlockRegistryObject<?, ?> block : blockMap.values())
			{
				ClientRegistrationUtil.registerIColoredBlockHandler(blockColors, itemColors, block);
			}

		}

	}

}
