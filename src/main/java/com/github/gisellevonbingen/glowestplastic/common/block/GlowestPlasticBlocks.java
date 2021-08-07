package com.github.gisellevonbingen.glowestplastic.common.block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.github.gisellevonbingen.glowestplastic.GlowestPlastic;
import com.google.common.base.Function;

import mekanism.additions.common.block.plastic.BlockPlastic;
import mekanism.additions.common.block.plastic.BlockPlasticSlab;
import mekanism.additions.common.block.plastic.BlockPlasticStairs;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.item.block.ItemBlockColoredName;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GlowestPlasticBlocks
{
	private static final int lightLevel = 15;

	public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(GlowestPlastic.MODID);

	public static final Map<EnumColor, BlockRegistryObject<BlockPlastic, ItemBlockColoredName>> PLASTIC_GLOWEST_BLOCKS = new HashMap<>();
	public static final Map<EnumColor, BlockRegistryObject<BlockPlasticStairs, ItemBlockColoredName>> PLASTIC_GLOWEST_STAIRS = new HashMap<>();
	public static final Map<EnumColor, BlockRegistryObject<BlockPlasticSlab, ItemBlockColoredName>> PLASTIC_GLOWEST_SLABS = new HashMap<>();

	public static void register()
	{
		register(BLOCKS);
	}

	public static void register(BlockDeferredRegister register)
	{
		register.register(FMLJavaModLoadingContext.get().getModEventBus());

		for (EnumColor color : EnumColor.values())
		{
			BlockRegistryObject<BlockPlastic, ItemBlockColoredName> baseBlock = registerGlowPlastic(color);
			PLASTIC_GLOWEST_BLOCKS.put(color, baseBlock);
			PLASTIC_GLOWEST_STAIRS.put(color, registerGlowPlasticStairs(baseBlock, color));
			PLASTIC_GLOWEST_SLABS.put(color, registerGlowPlasticSlab(color));
		}

	}

	public static BlockRegistryObject<BlockPlasticSlab, ItemBlockColoredName> registerPlasticSlab(EnumColor color, String blockTypeSuffix, UnaryOperator<AbstractBlock.Properties> propertyModifier)
	{
		return registerColoredBlock(c -> new BlockPlasticSlab(c, propertyModifier), blockTypeSuffix, color);
	}

	public static BlockRegistryObject<BlockPlasticSlab, ItemBlockColoredName> registerGlowPlasticSlab(EnumColor color)
	{
		return registerPlasticSlab(color, "_plastic_glow_slab", properties -> properties.lightLevel(state -> lightLevel));
	}

	public static BlockRegistryObject<BlockPlasticStairs, ItemBlockColoredName> registerPlasticStairs(IBlockProvider baseBlock, EnumColor color, String blockTypeSuffix, UnaryOperator<AbstractBlock.Properties> propertyModifier)
	{
		return registerColoredBlock(c -> new BlockPlasticStairs(baseBlock, c, propertyModifier), blockTypeSuffix, color);
	}

	public static BlockRegistryObject<BlockPlasticStairs, ItemBlockColoredName> registerGlowPlasticStairs(IBlockProvider baseBlock, EnumColor color)
	{
		return registerPlasticStairs(baseBlock, color, "_plastic_glow_stairs", properties -> properties.lightLevel(state -> lightLevel));
	}

	public static BlockRegistryObject<BlockPlastic, ItemBlockColoredName> registerGlowPlastic(EnumColor color)
	{
		return registerPlastic(color, "_plastic_glowest", properties -> properties.strength(5.0F, 6.0F).lightLevel(state -> lightLevel));
	}

	public static BlockRegistryObject<BlockPlastic, ItemBlockColoredName> registerPlastic(EnumColor color, String blockTypeSuffix, UnaryOperator<AbstractBlock.Properties> propertyModifier)
	{
		return registerColoredBlock(c -> new BlockPlastic(c, propertyModifier), blockTypeSuffix, color);
	}

	public static <BLOCK extends Block & mekanism.common.block.interfaces.IColoredBlock> BlockRegistryObject<BLOCK, ItemBlockColoredName> registerColoredBlock(Function<EnumColor, BLOCK> blockCreator, String blockTypeSuffix, EnumColor color)
	{
		return BLOCKS.register(color.getRegistryPrefix() + blockTypeSuffix, () -> blockCreator.apply(color), x$0 -> new ItemBlockColoredName(x$0));
	}

}
