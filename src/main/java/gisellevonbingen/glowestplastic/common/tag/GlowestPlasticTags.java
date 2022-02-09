package gisellevonbingen.glowestplastic.common.tag;

import gisellevonbingen.glowestplastic.common.GlowestPlastic;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class GlowestPlasticTags
{
	private static final String PLASTIC_BLOCKS_GLOWEST = new ResourceLocation(GlowestPlastic.MODID, "plastic_blocks/glowest").toString();
	private static final String PLASTIC_STAIRS_GLOWEST = new ResourceLocation("forge", "stairs/plastic/glowest").toString();
	private static final String PLASTIC_SLABS_GLOWEST = new ResourceLocation("forge", "slabs/plastic/glowest").toString();

	public static class Blocks
	{
		public static final INamedTag<Block> PLASTIC_BLOCKS_GLOWEST = BlockTags.bind(GlowestPlasticTags.PLASTIC_BLOCKS_GLOWEST);
		public static final INamedTag<Block> PLASTIC_STAIRS_GLOWEST = BlockTags.bind(GlowestPlasticTags.PLASTIC_STAIRS_GLOWEST);
		public static final INamedTag<Block> PLASTIC_SLABS_GLOWEST = BlockTags.bind(GlowestPlasticTags.PLASTIC_SLABS_GLOWEST);
	}

	public static class Items
	{
		public static final INamedTag<Item> PLASTIC_BLOCKS_GLOWEST = ItemTags.bind(GlowestPlasticTags.PLASTIC_BLOCKS_GLOWEST);
		public static final INamedTag<Item> PLASTIC_STAIRS_GLOWEST = ItemTags.bind(GlowestPlasticTags.PLASTIC_STAIRS_GLOWEST);
		public static final INamedTag<Item> PLASTIC_SLABS_GLOWEST = ItemTags.bind(GlowestPlasticTags.PLASTIC_SLABS_GLOWEST);
	}

}
