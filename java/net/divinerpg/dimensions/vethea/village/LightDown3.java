/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package net.divinerpg.dimensions.vethea.village;
import java.util.Random;

import net.divinerpg.utils.blocks.VetheaBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LightDown3 extends WorldGenerator
{
	protected int[] GetValidSpawnBlocks() {
		return new int[] {
		};
	}

	public LightDown3() { }

	public boolean generate(World world, Random rand, int i, int j, int k) {

		world.setBlock(i + 0, j + 7, k + 0, VetheaBlocks.darkEverstone);
		world.setBlock(i + 0, j + 7, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 0, j + 7, k + 2, VetheaBlocks.darkEverstone);
		world.setBlock(i + 1, j + 0, k + 1, VetheaBlocks.firelight);
		world.setBlock(i + 1, j + 1, k + 1, VetheaBlocks.firelight);
		world.setBlock(i + 1, j + 2, k + 1, VetheaBlocks.firelight);
		world.setBlock(i + 1, j + 3, k + 1, VetheaBlocks.firelight);
		world.setBlock(i + 1, j + 4, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 1, j + 5, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 1, j + 7, k + 0, VetheaBlocks.darkEverstone);
		world.setBlock(i + 1, j + 7, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 1, j + 7, k + 2, VetheaBlocks.darkEverstone);
		world.setBlock(i + 2, j + 5, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 2, j + 6, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 2, j + 7, k + 0, VetheaBlocks.darkEverstone);
		world.setBlock(i + 2, j + 7, k + 1, VetheaBlocks.darkEverstone);
		world.setBlock(i + 2, j + 7, k + 2, VetheaBlocks.darkEverstone);

		return true;
	}
}