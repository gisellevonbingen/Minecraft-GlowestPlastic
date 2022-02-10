package gisellevonbingen.glowestplastic.common.datagen.recipe;

import java.util.function.Consumer;

import gisellevonbingen.glowestplastic.common.GlowestPlastic;
import gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import gisellevonbingen.glowestplastic.common.tag.GlowestPlasticTags;
import mekanism.additions.common.MekanismAdditions;
import mekanism.api.text.EnumColor;
import mekanism.common.recipe.ingredient.IngredientWithout;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class CraftingRecipesGenerator extends RecipeProvider
{
	public CraftingRecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
	{
		for (EnumColor color : EnumColor.values())
		{
			if (color.getDyeColor() == null)
			{
				continue;
			}

			this.buildGlowestBlocks(color, consumer, "plastic/glowest/");
			this.buildGlowestStairs(color, consumer, "plastic/stairs/glowest/");
			this.buildGlowestSlab(color, consumer, "plastic/slab/glowest/");
		}

	}

	public <B extends Block, I extends BlockItem> void buildGlowestSlab(EnumColor color, Consumer<FinishedRecipe> consumer, String namePrefix)
	{
		BlockItem block = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).asItem();
		BlockItem slab = GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS.get(color).asItem();
		this.buildRecolor(namePrefix, GlowestPlasticTags.Items.PLASTIC_SLABS_GLOWEST, slab, color, consumer);

		ShapedRecipeBuilder shapeBuilder = new ShapedRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix()));
		shapeBuilder.addPattern("###").addKey('#', Ingredient.of(block));
		shapeBuilder.setOutput(slab, 6);
		consumer.accept(shapeBuilder.getResult());
	}

	public <B extends Block, I extends BlockItem> void buildGlowestStairs(EnumColor color, Consumer<FinishedRecipe> consumer, String namePrefix)
	{
		BlockItem block = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).asItem();
		BlockItem stairs = GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS.get(color).asItem();
		this.buildRecolor(namePrefix, GlowestPlasticTags.Items.PLASTIC_STAIRS_GLOWEST, stairs, color, consumer);

		ShapedRecipeBuilder shapeBuilder = new ShapedRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix()));
		shapeBuilder.addPattern("#  ", "## ", "###").addKey('#', Ingredient.of(block));
		shapeBuilder.setOutput(stairs, 4);
		consumer.accept(shapeBuilder.getResult());
	}

	public <B extends Block, I extends BlockItem> void buildGlowestBlocks(EnumColor color, Consumer<FinishedRecipe> consumer, String namePrefix)
	{
		Ingredient dustGlowStone = Ingredient.of(Tags.Items.DUSTS_GLOWSTONE);
		BlockItem normal = (BlockItem) ForgeRegistries.ITEMS.getValue(new ResourceLocation(MekanismAdditions.MODID, color.getRegistryPrefix() + "_plastic"));
		BlockItem glow = (BlockItem) ForgeRegistries.ITEMS.getValue(new ResourceLocation(MekanismAdditions.MODID, color.getRegistryPrefix() + "_plastic_glow"));
		BlockItem glowest = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).asItem();
		this.buildRecolor(namePrefix, GlowestPlasticTags.Items.PLASTIC_BLOCKS_GLOWEST, glowest, color, consumer);

		int normalCount = 3;
		ShapelessRecipeBuilder glowestBuilder = new ShapelessRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix() + "_from_normal"));
		glowestBuilder.add(Ingredient.of(normal), normalCount).add(dustGlowStone, 2);
		glowestBuilder.setOutput(glowest, normalCount);
		consumer.accept(glowestBuilder.getResult());

		int moreGlowCount = 3;
		ShapelessRecipeBuilder moreGlowBuilder = new ShapelessRecipeBuilder(this.getRecipeName(namePrefix + color.getRegistryPrefix() + "_from_glow"));
		moreGlowBuilder.add(Ingredient.of(glow), moreGlowCount).add(dustGlowStone);
		moreGlowBuilder.setOutput(glowest, moreGlowCount);
		consumer.accept(moreGlowBuilder.getResult());
	}

	public void buildRecolor(String namePrefix, Tag<Item> tagInput, BlockItem itemOutput, EnumColor color, Consumer<FinishedRecipe> consumer)
	{
		ShapedRecipeBuilder builder = new ShapedRecipeBuilder(this.getRecipeName(namePrefix + "recolor/" + color.getRegistryPrefix()));
		builder.addPattern(" # ", "#D#", " # ");
		builder.addKey('#', IngredientWithout.create(tagInput, itemOutput));
		builder.addKey('D', Ingredient.of(color.getDyeColor().getTag()));
		builder.setOutput(itemOutput, 4);
		consumer.accept(builder.getResult());
	}

	public ResourceLocation getRecipeName(String name)
	{
		return new ResourceLocation(GlowestPlastic.MODID, name.toLowerCase());
	}

}
