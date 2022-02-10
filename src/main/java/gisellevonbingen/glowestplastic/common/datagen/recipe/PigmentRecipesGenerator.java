package gisellevonbingen.glowestplastic.common.datagen.recipe;

import java.util.Map;
import java.util.function.Consumer;

import gisellevonbingen.glowestplastic.common.GlowestPlastic;
import gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import gisellevonbingen.glowestplastic.common.tag.GlowestPlasticTags;
import mekanism.api.chemical.pigment.Pigment;
import mekanism.api.chemical.pigment.PigmentStack;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToChemicalRecipeBuilder;
import mekanism.api.providers.IPigmentProvider;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import mekanism.api.recipes.inputs.chemical.PigmentStackIngredient;
import mekanism.api.text.EnumColor;
import mekanism.common.recipe.ingredient.IngredientWithout;
import mekanism.common.registration.impl.PigmentRegistryObject;
import mekanism.common.registries.MekanismPigments;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class PigmentRecipesGenerator extends RecipeProvider
{
	public static final long DYE_RATE = 256;
	public static final long PLASTIC_BLOCK_RATE = DYE_RATE * 3 / 16;
	public static final long PLASTIC_GLOW_BLOCK_RATE = PLASTIC_BLOCK_RATE * 7 / 8;
	public static final long PLASTIC_GLOW_STAIRS_RATE = PLASTIC_GLOW_BLOCK_RATE * 2 / 3;
	public static final long PLASTIC_GLOW_SLAB_RATE = PLASTIC_GLOW_BLOCK_RATE / 2;

	public PigmentRecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
	{
		this.addExtratingRecipes(consumer);
		this.addPaintingRecipes(consumer);
	}

	private void addExtratingRecipes(Consumer<IFinishedRecipe> consumer)
	{
		String basePath = "pigment_extracting/plastic/";

		for (Map.Entry<EnumColor, PigmentRegistryObject<Pigment>> entry : MekanismPigments.PIGMENT_COLOR_LOOKUP.entrySet())
		{
			EnumColor color = entry.getKey();
			IPigmentProvider pigment = entry.getValue();
			addExtractionRecipe(consumer, color, GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS, pigment, PLASTIC_GLOW_BLOCK_RATE, basePath + "glowest/");
			addExtractionRecipe(consumer, color, GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS, pigment, PLASTIC_GLOW_STAIRS_RATE, basePath + "stairs/glowest/");
			addExtractionRecipe(consumer, color, GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS, pigment, PLASTIC_GLOW_SLAB_RATE, basePath + "slab/glowest/");
		}

	}

	private void addPaintingRecipes(Consumer<IFinishedRecipe> consumer)
	{
		String basePath = "painting/plastic/";

		for (Map.Entry<EnumColor, PigmentRegistryObject<Pigment>> entry : MekanismPigments.PIGMENT_COLOR_LOOKUP.entrySet())
		{
			EnumColor color = entry.getKey();
			IPigmentProvider pigment = entry.getValue();
			addPaintingRecipe(consumer, color, GlowestPlasticTags.Items.PLASTIC_BLOCKS_GLOWEST, GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS, pigment, PLASTIC_GLOW_BLOCK_RATE, basePath + "glowest/");
			addPaintingRecipe(consumer, color, GlowestPlasticTags.Items.PLASTIC_STAIRS_GLOWEST, GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS, pigment, PLASTIC_GLOW_STAIRS_RATE, basePath + "stairs/glowest/");
			addPaintingRecipe(consumer, color, GlowestPlasticTags.Items.PLASTIC_SLABS_GLOWEST, GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS, pigment, PLASTIC_GLOW_SLAB_RATE, basePath + "slab/glowest/");
		}

	}

	public static void addExtractionRecipe(Consumer<IFinishedRecipe> consumer, EnumColor color, Map<EnumColor, ? extends IItemProvider> map, IPigmentProvider pigment, long rate, String basePath)
	{
		ItemStackIngredient ingredient = ItemStackIngredient.from(map.get(color));
		PigmentStack pigmentStack = pigment.getStack(rate);
		ResourceLocation id = new ResourceLocation(GlowestPlastic.MODID, basePath + color.getRegistryPrefix());

		ItemStackToChemicalRecipeBuilder.pigmentExtracting(ingredient, pigmentStack).build(consumer, id);
	}

	public static void addPaintingRecipe(Consumer<IFinishedRecipe> consumer, EnumColor color, ITag<Item> tag, Map<EnumColor, ? extends IItemProvider> map, IPigmentProvider pigment, long rate, String basePath)
	{
		Item result = map.get(color).asItem();
		ItemStackIngredient itemStackIngredient = ItemStackIngredient.from(IngredientWithout.create(tag, result));
		PigmentStackIngredient pigmentStackIngredient = PigmentStackIngredient.from(MekanismPigments.PIGMENT_COLOR_LOOKUP.get(color), DYE_RATE / 4);
		ResourceLocation id = new ResourceLocation(GlowestPlastic.MODID, basePath + color.getRegistryPrefix());

		ItemStackChemicalToItemStackRecipeBuilder.painting(itemStackIngredient, pigmentStackIngredient, new ItemStack(result)).build(consumer, id);
	}

}
