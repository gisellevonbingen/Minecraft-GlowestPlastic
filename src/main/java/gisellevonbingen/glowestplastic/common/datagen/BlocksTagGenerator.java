package gisellevonbingen.glowestplastic.common.datagen;

import gisellevonbingen.glowestplastic.common.GlowestPlastic;
import gisellevonbingen.glowestplastic.common.block.GlowestPlasticBlocks;
import gisellevonbingen.glowestplastic.common.tag.GlowestPlasticTags;
import mekanism.additions.common.AdditionsTags;
import mekanism.api.text.EnumColor;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlocksTagGenerator extends BlockTagsProvider
{
	public BlocksTagGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, GlowestPlastic.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		this.tagPlasictGlowestBlocks();
		this.tagPlasticGlowestStairs();
		this.tagPlasticGlowestSlabs();
	}

	public void tagPlasictGlowestBlocks()
	{
		Named<Block> tag = GlowestPlasticTags.Blocks.PLASTIC_BLOCKS_GLOWEST;
		
		for (EnumColor color : EnumColor.values())
		{
			Block block = GlowestPlasticBlocks.PLASTIC_GLOWEST_BLOCKS.get(color).getBlock();
			this.tag(tag).add(block);
		}

		this.tag(AdditionsTags.Blocks.PLASTIC_BLOCKS).addTag(tag);
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(tag);
	}

	public void tagPlasticGlowestStairs()
	{
		Named<Block> tag = GlowestPlasticTags.Blocks.PLASTIC_STAIRS_GLOWEST;
		
		for (EnumColor color : EnumColor.values())
		{
			Block block = GlowestPlasticBlocks.PLASTIC_GLOWEST_STAIRS.get(color).getBlock();
			this.tag(tag).add(block);
		}

		this.tag(BlockTags.STAIRS).addTag(tag);
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(tag);
	}

	public void tagPlasticGlowestSlabs()
	{
		Named<Block> tag = GlowestPlasticTags.Blocks.PLASTIC_SLABS_GLOWEST;
		
		for (EnumColor color : EnumColor.values())
		{
			Block block = GlowestPlasticBlocks.PLASTIC_GLOWEST_SLABS.get(color).getBlock();
			this.tag(tag).add(block);
		}

		this.tag(BlockTags.SLABS).addTag(tag);
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(tag);
	}

}
