package gisellevonbingen.glowestplastic.common.datagen;

import java.util.HashSet;

import gisellevonbingen.glowestplastic.common.datagen.recipe.CraftingRecipesGenerator;
import gisellevonbingen.glowestplastic.common.datagen.recipe.PigmentRecipesGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = new ExistingFileHelper(new HashSet<>(), new HashSet<>(), false, null, null);

		if (event.includeServer())
		{
			BlocksTagGenerator blocksTagGenerator = new BlocksTagGenerator(generator, existingFileHelper);
			generator.addProvider(blocksTagGenerator);
			generator.addProvider(new ItemsTagGenerator(generator, blocksTagGenerator, existingFileHelper));
			generator.addProvider(new CraftingRecipesGenerator(generator));
			generator.addProvider(new PigmentRecipesGenerator(generator));
			generator.addProvider(new LootTablesGenerator(generator));
			generator.addProvider(new LanguagesGenerator(generator));
		}

		if (event.includeClient())
		{
			generator.addProvider(new BlocksStateGenerator(generator, existingFileHelper));
			generator.addProvider(new ItemsModelGenerator(generator, existingFileHelper));
		}

	}

}
