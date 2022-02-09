package com.github.gisellevonbingen.glowestplastic.common.tag;

import com.github.gisellevonbingen.glowestplastic.common.GlowestPlastic;

import mekanism.additions.common.AdditionsTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class GlowestPlasticTags
{
	private static final String PLASTIC_BLOCKS_GLOWEST = getName(GlowestPlastic.MODID, "plastic_blocks/glowest");
	private static final String PLASTIC_STAIRS_GLOWEST = getName("forge", "stairs/plastic/glowest");
	private static final String PLASTIC_SLABS_GLOWEST = getName("forge", "slabs/plastic/glowest");

	public static String getName(String namespace, String path)
	{
		return new ResourceLocation(namespace, path).toString();
	}

	public static class Blocks
	{
		public static final INamedTag<Block> PLASTIC_BLOCKS_GLOWEST = BlockTags.bind(GlowestPlasticTags.PLASTIC_BLOCKS_GLOWEST);
		public static final INamedTag<Block> PLASTIC_BLOCKS = AdditionsTags.Blocks.PLASTIC_BLOCKS;

		public static final INamedTag<Block> STAIRS = BlockTags.STAIRS;
		public static final INamedTag<Block> PLASTIC_STAIRS_GLOWEST = BlockTags.bind(GlowestPlasticTags.PLASTIC_STAIRS_GLOWEST);

		public static final INamedTag<Block> SLABS = BlockTags.SLABS;
		public static final INamedTag<Block> PLASTIC_SLABS_GLOWEST = BlockTags.bind(GlowestPlasticTags.PLASTIC_SLABS_GLOWEST);
	}

	public static class Items
	{
		public static final INamedTag<Item> PLASTIC_BLOCKS_GLOWEST = ItemTags.bind(GlowestPlasticTags.PLASTIC_BLOCKS_GLOWEST);
		public static final INamedTag<Item> PLASTIC_BLOCKS = AdditionsTags.Items.PLASTIC_BLOCKS;

		public static final INamedTag<Item> STAIRS = ItemTags.STAIRS;
		public static final INamedTag<Item> PLASTIC_STAIRS_GLOWEST = ItemTags.bind(GlowestPlasticTags.PLASTIC_STAIRS_GLOWEST);

		public static final INamedTag<Item> SLABS = ItemTags.SLABS;
		public static final INamedTag<Item> PLASTIC_SLABS_GLOWEST = ItemTags.bind(GlowestPlasticTags.PLASTIC_SLABS_GLOWEST);
	}

}
