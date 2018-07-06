package net.divinerpg.utils.recipes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtractorRecipes {
	
    private static final ExtractorRecipes instanceBase = new ExtractorRecipes();
    private Map instanceList = new HashMap();
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metainstanceList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();
 
    public static final ExtractorRecipes instance() {
        return instanceBase;
    }

    private ExtractorRecipes() {
        this.addinstance(ArcanaBlocks.arcaniumOre, new ItemStack(ArcanaItems.arcanium), 0.0F);
    }
 
    public void addinstance(Block par1, ItemStack par2ItemStack, float par3) {
        this.instanceList.put(Integer.valueOf(Item.getIdFromItem(Item.getItemFromBlock(par1))), par2ItemStack);
        this.experienceList.put(Integer.valueOf(Item.getIdFromItem(par2ItemStack.getItem())), Float.valueOf(par3));
    }

    public Map getinstanceList() {
        return this.instanceList;
    }
    
    public void addinstance(int itemID, int metadata, ItemStack itemstack, float experience) {
        metainstanceList.put(Arrays.asList(itemID, metadata), itemstack);
        metaExperience.put(Arrays.asList(itemID, metadata), experience);
    }
 
    public ItemStack getSmeltingResult(ItemStack item)  {
        if (item == null)
            return null;
        ItemStack ret = metainstanceList.get(Arrays.asList(Item.getIdFromItem(item.getItem()), item.getItemDamage()));
        if (ret != null)
            return ret;
        return (ItemStack)instanceList.get(Integer.valueOf(Item.getIdFromItem(item.getItem())));
    }
 
    public float getExperience(ItemStack item) {
        if (item == null || item.getItem() == null)
            return 0;
        float ret = item.getItem().getSmeltingExperience(item);
        if(ret < 0 && metaExperience.containsKey(Arrays.asList(Item.getIdFromItem(item.getItem()), item.getItemDamage()))) 
            ret = metaExperience.get(Arrays.asList(Item.getIdFromItem(item.getItem()), item.getItemDamage()));
        
        if(ret < 0 && experienceList.containsKey(Item.getIdFromItem(item.getItem())));
            ret = (((Float) experienceList.get(Item.getIdFromItem(item.getItem()))).floatValue());
        return (ret < 0 ? 0 : ret);
    }
}
