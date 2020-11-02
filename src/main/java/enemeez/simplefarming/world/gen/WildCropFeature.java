package enemeez.simplefarming.world.gen;

import java.util.Random;

import com.mojang.serialization.Codec;

import enemeez.simplefarming.config.GenConfig;
import enemeez.simplefarming.init.ModBlocks;
import enemeez.simplefarming.util.WorldGenHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WildCropFeature extends Feature<NoFeatureConfig> {
	public WildCropFeature(Codec<NoFeatureConfig> configFactory) {
		super(configFactory);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator p_241855_2_, Random random, BlockPos pos,
			NoFeatureConfig config) {
		if (random.nextInt(GenConfig.wild_crop_chance.get()) != 0)
			return false;
		int rolls = random.nextInt(5) + 1;
		for (int i = 0; i < rolls; i++) {
			if (i == 0) {
				int initial = random.nextInt(2) + 1;
				int offset = random.nextInt(2) + 1;
				if (WorldGenHelper.checkConditions(world, pos.north(initial).west(offset)))
					generatePlant(world, pos.north(initial).west(offset), random);
			}
			if (i == 1) {
				int initial = random.nextInt(2) + 1;
				int offset = random.nextInt(2) + 1;
				if (WorldGenHelper.checkConditions(world, pos.south(initial).east(offset)))
					generatePlant(world, pos.south(initial).east(offset), random);
			}
			if (i == 2) {
				int initial = random.nextInt(2) + 1;
				int offset = random.nextInt(2) + 1;
				if (WorldGenHelper.checkConditions(world, pos.north(initial).east(offset)))
					generatePlant(world, pos.north(initial).east(offset), random);
			} else {
				int initial = random.nextInt(2) + 1;
				int offset = random.nextInt(2) + 1;
				if (WorldGenHelper.checkConditions(world, pos.south(initial).west(offset)))
					generatePlant(world, pos.south(initial).west(offset), random);
			}
		}
		return true;
	}

	public static void generatePlant(IWorld world, BlockPos pos, Random random) {
		world.setBlockState(pos, ModBlocks.wild_crop.getDefaultState(), 2);
	}

}