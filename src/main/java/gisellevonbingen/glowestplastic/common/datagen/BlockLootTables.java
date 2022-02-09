package gisellevonbingen.glowestplastic.common.datagen;

import java.util.Collection;
import java.util.stream.Collectors;

import com.google.common.base.Function;

import gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

public class BlockLootTables extends net.minecraft.data.loot.BlockLoot
{
	public BlockLootTables()
	{

	}

	@Override
	protected void addTables()
	{
		this.dropSelf(GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.values());
		this.dropSelf(GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS.values());
		this.add(GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS.values(), BlockLootTables::createSlabItemTable);
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
		return GlowestPlasticBlocks.BLOCKS.getAllBlocks().stream().map(IBlockProvider::getBlock).collect(Collectors.toList());
	}

}
