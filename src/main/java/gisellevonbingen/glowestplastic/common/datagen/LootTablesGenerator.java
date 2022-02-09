package gisellevonbingen.glowestplastic.common.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import gisellevonbingen.glowestplastic.common.GlowestPlastic;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

public class LootTablesGenerator extends LootTableProvider
{
	public LootTablesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	public String getName()
	{
		return super.getName() + ":" + GlowestPlastic.MODID;
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables()
	{
		List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> list = new ArrayList<>();
		list.add(Pair.of(BlockLootTables::new, LootParameterSets.BLOCK));

		return list;
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> p_validate_1_, ValidationTracker p_validate_2_)
	{

	}

}
