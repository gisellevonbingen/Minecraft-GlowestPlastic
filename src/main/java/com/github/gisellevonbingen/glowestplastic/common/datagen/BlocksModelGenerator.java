package com.github.gisellevonbingen.glowestplastic.common.datagen;

import com.github.gisellevonbingen.glowestplastic.common.GlowestPlastic;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlocksModelGenerator extends BlockModelProvider
{
	public BlocksModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, GlowestPlastic.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{

	}

}
