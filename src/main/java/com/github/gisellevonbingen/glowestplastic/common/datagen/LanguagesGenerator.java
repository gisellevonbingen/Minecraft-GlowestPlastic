package com.github.gisellevonbingen.glowestplastic.common.datagen;

import java.util.Map;

import com.github.gisellevonbingen.glowestplastic.common.GlowestPlastic;
import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;

import mekanism.api.providers.IBlockProvider;
import mekanism.api.text.EnumColor;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguagesGenerator extends LanguageProvider
{
	public LanguagesGenerator(DataGenerator generator)
	{
		super(generator, GlowestPlastic.MODID, "en_us");
	}

	@Override
	protected void addTranslations()
	{
		this.addColoredBlocks(GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS, "Glowest Plastic Block");
		this.addColoredBlocks(GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS, "Glowest Plastic Stairs");
		this.addColoredBlocks(GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS, "Glowest Plastic Slab");
	}

	public void addColoredBlocks(Map<EnumColor, ? extends IBlockProvider> blocks, String suffix)
	{
		for (Map.Entry<EnumColor, ? extends IBlockProvider> entry : blocks.entrySet())
		{
			this.add(entry.getValue().getBlock(), entry.getKey().getEnglishName() + " " + suffix);
		}

	}

}
