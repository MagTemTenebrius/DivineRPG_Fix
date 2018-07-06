package net.divinerpg.items.vethea;

import java.util.List;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVetheanDisk extends ItemMod {
	
    public int damage;
    
    public ItemVetheanDisk(int par1, String name) {
    	super(name);
		this.maxStackSize = 1;
        this.damage = par1;
        this.setCreativeTab(DivineRPGTabs.vethea);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedDam(damage));
        list.add("Returns to sender");
        list.add(TooltipLocalizer.vethean());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        if (!par3.capabilities.isCreativeMode) {
            --par1.stackSize;
        }

        par2.playSoundAtEntity(par3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2.isRemote) {
            shoot(par1, par2, par3);
        }

        return par1;
    }

    protected void shoot(ItemStack stack, World par2, EntityPlayer par3){}
}