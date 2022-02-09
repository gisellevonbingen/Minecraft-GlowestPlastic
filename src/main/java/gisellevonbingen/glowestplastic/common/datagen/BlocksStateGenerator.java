package gisellevonbingen.glowestplastic.common.datagen;

import java.util.Map;

import gisellevonbingen.glowestplastic.common.GlowestPlastic;
import gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import mekanism.additions.common.MekanismAdditions;
import mekanism.additions.common.block.plastic.BlockPlasticStairs;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlocksStateGenerator extends BlockStateProvider
{
	public BlocksStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper)
	{
		super(gen, GlowestPlastic.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels()
	{
		this.coloredBlocks(GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS, "block/plastic/glow");
		this.coloredStairs(GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS, "block/plastic/", "glow_");
		this.coloredSlabs(GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS, "block/plastic/", "glow_", "glow");
	}

	public void coloredStairs(Map<?, ? extends BlockRegistryObject<? extends BlockPlasticStairs, ?>> stairs, String prefix, String existingPrefix)
	{
		BlockModelProvider models = this.models();
		ModelFile stairsModel = models.getExistingFile(this.parentLoc(prefix + existingPrefix + "stairs"));
		ModelFile stairsInner = models.getExistingFile(this.parentLoc(prefix + existingPrefix + "stairs_inner"));
		ModelFile stairsOuter = models.getExistingFile(this.parentLoc(prefix + existingPrefix + "stairs_outer"));

		for (BlockRegistryObject<? extends BlockPlasticStairs, ?> stair : stairs.values())
		{
			BlockPlasticStairs block = stair.getBlock();
			this.getVariantBuilder(block).forAllStatesExcept(state ->
			{
				Direction facing = state.getValue(StairsBlock.FACING);
				Half half = state.getValue(StairsBlock.HALF);
				StairsShape shape = state.getValue(StairsBlock.SHAPE);
				int yRot = (int) facing.getClockWise().toYRot();

				if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT)
				{
					yRot += 270;
				}

				if (shape != StairsShape.STRAIGHT && half == Half.TOP)
				{
					yRot += 90;
				}

				yRot %= 360;
				boolean uvlock = yRot != 0 || half == Half.TOP;
				return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT ? stairsModel : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter).rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(uvlock).build();
			}, StairsBlock.WATERLOGGED, block.getFluidLoggedProperty());
		}

	}

	public void coloredSlabs(Map<?, ? extends IBlockProvider> slabs, String prefix, String existingPrefix, String doubleType)
	{
		BlockModelProvider models = this.models();
		ConfiguredModel bottomModel = new ConfiguredModel(models.getExistingFile(this.parentLoc(prefix + existingPrefix + "slab")));
		ConfiguredModel topModel = new ConfiguredModel(models.getExistingFile(this.parentLoc(prefix + existingPrefix + "slab_top")));
		ConfiguredModel doubleModel = new ConfiguredModel(models.getExistingFile(this.parentLoc(prefix + doubleType)));

		for (IBlockProvider slab : slabs.values())
		{
			this.getVariantBuilder(slab.getBlock()).partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(bottomModel).partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(topModel).partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(doubleModel);
		}

	}

	public void coloredBlocks(Map<?, ? extends IBlockProvider> blocks, String modelName)
	{
		ConfiguredModel model = new ConfiguredModel(this.models().getExistingFile(this.parentLoc(modelName)));

		for (IBlockProvider block : blocks.values())
		{
			this.getVariantBuilder(block.getBlock()).partialState().addModels(model);
		}

	}

	private ResourceLocation parentLoc(String modelName)
	{
		return new ResourceLocation(MekanismAdditions.MODID, modelName);
	}

}
