package com.github.gisellevonbingen.glowestplastic.common.datagen;

import com.github.gisellevonbingen.glowestplastic.common.GlowestPlastic;
import com.github.gisellevonbingen.glowestplastic.common.tag.GlowestPlasticTags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsTagGenerator extends ItemTagsProvider
{
	public ItemsTagGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, GlowestPlastic.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		this.copy(GlowestPlasticTags.Blocks.PLASTIC_BLOCKS_GLOWEST, GlowestPlasticTags.Items.PLASTIC_BLOCKS_GLOWEST);
		this.copy(GlowestPlasticTags.Blocks.PLASTIC_BLOCKS, GlowestPlasticTags.Items.PLASTIC_BLOCKS);

		this.copy(GlowestPlasticTags.Blocks.STAIRS, GlowestPlasticTags.Items.STAIRS);
		this.copy(GlowestPlasticTags.Blocks.PLASTIC_STAIRS_GLOWEST, GlowestPlasticTags.Items.PLASTIC_STAIRS_GLOWEST);

		this.copy(GlowestPlasticTags.Blocks.PLASTIC_SLABS_GLOWEST, GlowestPlasticTags.Items.PLASTIC_SLABS_GLOWEST);
		this.copy(GlowestPlasticTags.Blocks.SLABS, GlowestPlasticTags.Items.SLABS);
	}

}
