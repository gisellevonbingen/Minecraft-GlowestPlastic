package com.github.gisellevonbingen.glowestplastic.datagen;

import com.github.gisellevonbingen.glowestplastic.GlowestPlastic;
import com.github.gisellevonbingen.glowestplastic.util.ExistingFileHelperExtensions;

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
		boolean enable = this.existingFileHelper.isEnabled();

		try
		{
			ExistingFileHelperExtensions.setEnabled(this.existingFileHelper, false);
			this.onRegisterModels();
		}
		finally
		{
			ExistingFileHelperExtensions.setEnabled(this.existingFileHelper, enable);
		}

	}

	private void onRegisterModels()
	{

	}

}
