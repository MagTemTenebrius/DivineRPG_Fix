package net.divinerpg.items.base;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Set;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemShickaxe extends ItemTool {
   protected String name;

   public ItemShickaxe(ToolMaterial toolMaterial, String name) {
      super(0.0F, toolMaterial, (Set)null);
      this.setCreativeTab(DivineRPGTabs.tools);
      this.setTextureName("divinerpg:" + name);
      this.setUnlocalizedName(name);
      GameRegistry.registerItem(this, name);
      LangRegistry.addItem(this);
   }

   public boolean canHarvestBlock(Block block, ItemStack stack) {
      return block != Blocks.bedrock;
   }

   public boolean func_150897_b(Block block) {
      return this.isEfficient(block);
   }

   public float func_150893_a(ItemStack stack, Block block) {
      return super.toolMaterial.getEfficiencyOnProperMaterial();
   }

   protected boolean isEfficient(Block block) {
      return block == Blocks.obsidian ? super.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? super.toolMaterial.getHarvestLevel() >= 3 : (block.getMaterial() == Material.iron ? super.toolMaterial.getHarvestLevel() >= 3 : block.getMaterial() == Material.anvil)) : super.toolMaterial.getHarvestLevel() >= 3) : super.toolMaterial.getHarvestLevel() >= 3) : super.toolMaterial.getHarvestLevel() >= 3) : super.toolMaterial.getHarvestLevel() >= 3) : super.toolMaterial.getHarvestLevel() >= 3) : super.toolMaterial.getHarvestLevel() >= 3);
   }

   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
      if (!player.canPlayerEdit(x, y, z, par7, stack)) {
         return false;
      } else {
         UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
         if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
         } else if (event.getResult() == Result.ALLOW) {
            stack.damageItem(1, player);
            return true;
         } else {
            Block block = world.getBlock(x, y, z);
            if (par7 == 0 || !world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) || block != Blocks.grass && block != Blocks.dirt) {
               return false;
            } else {
               Block block1 = Blocks.farmland;
               world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.frequency * 0.8F);
               if (world.isRemote) {
                  return true;
               } else {
                  world.setBlock(x, y, z, block1);
                  stack.damageItem(1, player);
                  return true;
               }
            }
         }
      }
   }

   public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
      infoList.add(TooltipLocalizer.efficiency((double)super.toolMaterial.getEfficiencyOnProperMaterial()));
      if (item.getMaxDamage() != -1) {
         infoList.add(TooltipLocalizer.usesRemaining(item.getMaxDamage() - item.getItemDamage()));
      } else {
         infoList.add(TooltipLocalizer.infiniteUses());
      }

   }
}
