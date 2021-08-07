package com.github.gisellevonbingen.glowestplastic.datagen;

import com.github.gisellevonbingen.glowestplastic.util.ExistingFileHelperExtensions;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer())
		{
			BlocksTagGenerator blocksTagGenerator = new BlocksTagGenerator(generator, existingFileHelper);
			generator.addProvider(blocksTagGenerator);
			generator.addProvider(new ItemsTagGenerator(generator, blocksTagGenerator, existingFileHelper));
			generator.addProvider(new RecipesGenerator(generator));
			generator.addProvider(new LootTablesGenerator(generator));
			generator.addProvider(new LanguagesGenerator(generator));
		}

		if (event.includeClient())
		{
			ExistingFileHelperExtensions.setEnabled(existingFileHelper, false);
			generator.addProvider(new BlocksModelGenerator(generator, existingFileHelper));
			generator.addProvider(new BlocksStateGenerator(generator, existingFileHelper));
			generator.addProvider(new ItemsModelGenerator(generator, existingFileHelper));
		}

	}

}
