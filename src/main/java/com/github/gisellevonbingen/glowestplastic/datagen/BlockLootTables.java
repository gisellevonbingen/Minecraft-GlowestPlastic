package com.github.gisellevonbingen.glowestplastic.datagen;

import java.util.Collection;
import java.util.stream.Collectors;

import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import com.google.common.base.Function;

import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;

public class BlockLootTables extends net.minecraft.data.loot.BlockLootTables
{
	public BlockLootTables()
	{

	}

	@Override
	protected void addTables()
	{
		this.dropSelf(GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.values());
		this.dropSelf(GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS.values());
		this.add(GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS.values(), b -> BlockLootTables.createSlabItemTable(b));
	}

	public <B extends Block, I extends Item> void dropSelf(Collection<BlockRegistryObject<B, I>> blocks)
	{
		for (BlockRegistryObject<?, ?> block : blocks)
		{
			this.dropSelf(block.getBlock());
		}

	}

	public <B extends Block, I extends Item> void add(Collection<BlockRegistryObject<B, I>> blocks, Function<Block, LootTable.Builder> function)
	{
		for (BlockRegistryObject<?, ?> block : blocks)
		{
			this.add(block.getBlock(), function);
		}

	}

	@Override
	protected Iterable<Block> getKnownBlocks()
	{
		return GlowestPlasticBlocks.BLOCKS.getAllBlocks().stream().map(b -> b.getBlock()).collect(Collectors.toList());
	}

}
