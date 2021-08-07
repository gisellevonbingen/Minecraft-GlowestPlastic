package com.github.gisellevonbingen.glowestplastic.datagen;

import com.github.gisellevonbingen.glowestplastic.GlowestPlastic;
import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import com.github.gisellevonbingen.glowestplastic.common.tag.GlowestPlasticTags;

import mekanism.api.text.EnumColor;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlocksTagGenerator extends BlockTagsProvider
{
	public BlocksTagGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, GlowestPlastic.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		this.tagPlasictGlowestBlocks();
		this.tagPlasticGlowestStairs();
		this.tagPlasticGlowestSlabs();
	}

	public void tagPlasictGlowestBlocks()
	{
		for (EnumColor color : EnumColor.values())
		{
			Block block = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).getBlock();
			this.tag(GlowestPlasticTags.Blocks.PLASTIC_BLOCKS_GLOWEST).add(block);
			this.tag(GlowestPlasticTags.Blocks.PLASTIC_BLOCKS).add(block);
		}

	}

	public void tagPlasticGlowestStairs()
	{
		for (EnumColor color : EnumColor.values())
		{
			Block block = GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS.get(color).getBlock();
			this.tag(GlowestPlasticTags.Blocks.STAIRS).add(block);
			this.tag(GlowestPlasticTags.Blocks.PLASTIC_STAIRS_GLOWEST).add(block);
		}

	}

	public void tagPlasticGlowestSlabs()
	{
		for (EnumColor color : EnumColor.values())
		{
			Block block = GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS.get(color).getBlock();
			this.tag(GlowestPlasticTags.Blocks.PLASTIC_SLABS_GLOWEST).add(block);
			this.tag(GlowestPlasticTags.Blocks.SLABS).add(block);
		}

	}

}
