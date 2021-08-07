package com.github.gisellevonbingen.glowestplastic.datagen;

import java.util.function.Consumer;

import com.github.gisellevonbingen.glowestplastic.GlowestPlastic;
import com.github.gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import com.github.gisellevonbingen.glowestplastic.common.tag.GlowestPlasticTags;

import mekanism.additions.common.MekanismAdditions;
import mekanism.api.text.EnumColor;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class RecipesGenerator extends RecipeProvider
{
	public RecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
	{
		for (EnumColor color : EnumColor.values())
		{
			if (color.hasDyeName() == false)
			{
				continue;
			}

			this.buildGlowestBlocks(color, consumer, "plastic/glowest/", "_plastic_glow");
			this.buildGlowestStairs(color, consumer, "plastic/stairs/glowest/");
			this.buildGlowestSlab(color, consumer, "plastic/slab/glowest/");
		}

	}

	public <B extends Block, I extends BlockItem> void buildGlowestSlab(EnumColor color, Consumer<IFinishedRecipe> consumer, String namePrefix)
	{
		BlockItem block = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).getItem();
		BlockItem slab = GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS.get(color).getItem();
		this.buildRecolor(namePrefix, GlowestPlasticTags.Items.PLASTIC_SLABS_GLOWEST, slab, color, consumer);

		ShapedRecipeBuilder shapeBuilder = new ShapedRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix()));
		shapeBuilder.addPattern("###").addKey('#', Ingredient.of(block));
		shapeBuilder.setOutput(slab, 6);
		consumer.accept(shapeBuilder.getResult());
	}

	public <B extends Block, I extends BlockItem> void buildGlowestStairs(EnumColor color, Consumer<IFinishedRecipe> consumer, String namePrefix)
	{
		BlockItem block = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).getItem();
		BlockItem stairs = GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS.get(color).getItem();
		this.buildRecolor(namePrefix, GlowestPlasticTags.Items.PLASTIC_STAIRS_GLOWEST, stairs, color, consumer);

		ShapedRecipeBuilder shapeBuilder = new ShapedRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix()));
		shapeBuilder.addPattern("#  ", "## ", "###").addKey('#', Ingredient.of(block));
		shapeBuilder.setOutput(stairs, 4);
		consumer.accept(shapeBuilder.getResult());
	}

	@SuppressWarnings("deprecation")
	public <B extends Block, I extends BlockItem> void buildGlowestBlocks(EnumColor color, Consumer<IFinishedRecipe> consumer, String namePrefix, String glowNameSuffix)
	{
		BlockItem glow = (BlockItem) Registry.ITEM.get(new ResourceLocation(MekanismAdditions.MODID, color.getRegistryPrefix() + glowNameSuffix));
		BlockItem glowest = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).getItem();
		this.buildRecolor(namePrefix, GlowestPlasticTags.Items.PLASTIC_BLOCKS_GLOWEST, glowest, color, consumer);

		int moreGlowCount = 3;
		ShapelessRecipeBuilder moreGlowBuilder = new ShapelessRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix()));
		moreGlowBuilder.add(Ingredient.of(glow), moreGlowCount).add(Ingredient.of(Items.GLOWSTONE_DUST));
		moreGlowBuilder.setOutput(glowest, moreGlowCount);
		consumer.accept(moreGlowBuilder.getResult());
	}

	public void buildRecolor(String namePrefix, ITag<Item> tagInput, BlockItem itemOutput, EnumColor color, Consumer<IFinishedRecipe> consumer)
	{
		ShapedRecipeBuilder builder = new ShapedRecipeBuilder(this.getRecipeName(namePrefix + "recolor/" + color.getRegistryPrefix()));
		builder.addPattern(" # ", "#D#", " # ");
		builder.addKey('#', Ingredient.of(tagInput));
		builder.addKey('D', Ingredient.of(color.getDyeTag()));
		builder.setOutput(itemOutput, 4);
		consumer.accept(builder.getResult());
	}

	public ResourceLocation getRecipeName(String name)
	{
		return new ResourceLocation(GlowestPlastic.MODID, name.toLowerCase());
	}

}
