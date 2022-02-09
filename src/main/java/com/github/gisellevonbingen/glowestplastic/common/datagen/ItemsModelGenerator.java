package com.github.gisellevonbingen.glowestplastic.common.datagen;

import java.util.Map;

import com.github.gisellevonbingen.glowestplastic.common.GlowestPlastic;
import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;

import mekanism.additions.common.MekanismAdditions;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsModelGenerator extends ItemModelProvider
{
	public ItemsModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, GlowestPlastic.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		this.withParent(GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS, "block/plastic/glow");
		this.withParent(GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS, "block/plastic/glow_stairs");
		this.withParent(GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS, "block/plastic/glow_slab");
	}

	private void withParent(Map<?, ? extends IItemProvider> items, String modelName)
	{
		ResourceLocation parentName = new ResourceLocation(MekanismAdditions.MODID, modelName);
		ModelFile parent = this.getExistingFile(parentName);

		for (IItemProvider item : items.values())
		{
			this.getBuilder(item.asItem().getRegistryName().toString()).parent(parent);
		}

	}

}
