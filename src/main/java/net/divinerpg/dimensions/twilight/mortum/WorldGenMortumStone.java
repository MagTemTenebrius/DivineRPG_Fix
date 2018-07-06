package net.divinerpg.dimensions.twilight.mortum;

import java.util.Random;

import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMortumStone extends WorldGenerator {
	
    private int numberOfBlocks;

    public WorldGenMortumStone(int var2) {
        this.numberOfBlocks = var2;
    }

    @Override
    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        float var6 = var2.nextFloat() * (float)Math.PI;
        double var7 = var3 + 8 + MathHelper.sin(var6) * this.numberOfBlocks / 8.0F;
        double var9 = var3 + 8 - MathHelper.sin(var6) * this.numberOfBlocks / 8.0F;
        double var11 = var5 + 8 + MathHelper.cos(var6) * this.numberOfBlocks / 8.0F;
        double var13 = var5 + 8 - MathHelper.cos(var6) * this.numberOfBlocks / 8.0F;
        double var15 = var4 + var2.nextInt(3) - 2;
        double var17 = var4 + var2.nextInt(3) - 2;

        for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19) {
            double var20 = var7 + (var9 - var7) * var19 / this.numberOfBlocks;
            double var22 = var15 + (var17 - var15) * var19 / this.numberOfBlocks;
            double var24 = var11 + (var13 - var11) * var19 / this.numberOfBlocks;
            double var26 = var2.nextDouble() * this.numberOfBlocks / 16.0D;
            double var28 = (MathHelper.sin(var19 * (float)Math.PI / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (MathHelper.sin(var19 * (float)Math.PI / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

            for (int var38 = var32; var38 <= var35; ++var38) {
                double var39 = (var38 + 0.5D - var20) / (var28 / 2.0D);

                if (var39 * var39 < 1.0D) {
                    for (int var41 = var33; var41 <= var36; ++var41) {
                        double var42 = (var41 + 0.5D - var22) / (var30 / 2.0D);

                        if (var39 * var39 + var42 * var42 < 1.0D) {
                            for (int var44 = var34; var44 <= var37; ++var44) {
                                double var45 = (var44 + 0.5D - var24) / (var28 / 2.0D);

                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && var1.getBlock(var38, var41, var44) == TwilightBlocks.mortumGrass) {
                                    var1.setBlock(var38, var41, var44, TwilightBlocks.twilightStone);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}