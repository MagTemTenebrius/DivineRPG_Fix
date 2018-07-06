package net.divinerpg.entities.base;

import java.util.Iterator;

import net.divinerpg.DivineRPG;
import net.divinerpg.entities.iceika.EntityWorkshopMerchant;
import net.divinerpg.entities.iceika.EntityWorkshopTinkerer;
import net.divinerpg.entities.vanilla.EntityJackOMan;
import net.divinerpg.entities.vethea.EntityTheHunger;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public abstract class EntityDivineRPGVillager extends EntityVillager {

    private int                randomTickDivider;
    private Village            villageObj;
    private String             lastBuyingPlayer;
    private EntityPlayer       buyingPlayer;
    private MerchantRecipeList buyingList;
    private int                timeUntilReset;
    private boolean            needsInitilization;
    private int                wealth;
    private String             buyersName;
    private float              buying;

    public EntityDivineRPGVillager(World var1) {
        super(var1);
        this.setSize(1.0F, 2.0F);
        this.randomTickDivider = 0;
        this.villageObj = null;
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(1, new EntityAITradePlayer(this));
        this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(5, new EntityAIWander(this, EntityStats.modVillagerSpeed));
        setProfession(1234);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.modVillagerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.modVillagerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.modVillagerFollowRange);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected String getLivingSound() {
        return null;
    }

    @Override
    protected String getDeathSound() {
        return null;
    }

    @Override
    protected String getHurtSound() {
        return null;
    }

    @Override
    public void setProfession(int i) {
        super.setProfession(12345);
    }

    @Override
    protected void updateAITick() {
        if (this.randomTickDivider-- <= 0) {
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);

            if (this.villageObj == null) {
                this.detachHome();
            } else {
                this.villageObj.setDefaultPlayerReputation(30);
            }
        }

        if (this.timeUntilReset > 0) {
            if (this.timeUntilReset <= 0) {
                if (this.buyingList.size() > 1) {
                    Iterator iterator = this.buyingList.iterator();
                    if (needsInitilization) {
                        while (iterator.hasNext()) {
                            MerchantRecipe merchantrecipe = (MerchantRecipe) iterator.next();

                            if (merchantrecipe.isRecipeDisabled()) {
                                merchantrecipe.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                            }
                        }
                    }

                    this.addDefaultEquipmentAndRecipies(75);
                    this.needsInitilization = false;

                    if (this.villageObj != null && this.lastBuyingPlayer != null) {
                        this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 30);
                    }
                }
                this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
            }
        }
        super.updateAITick();
    }

    @Override
    public boolean interact(EntityPlayer var1) {
        if (!this.worldObj.isRemote) {
            extraInteract(var1);
            var1.openGui(DivineRPG.instance, guiID(), this.worldObj, getEntityId(), 0, 0);
            return true;
        } else {
            return super.interact(var1);
        }
    }

    public abstract void extraInteract(EntityPlayer p);

    public abstract int guiID();

    public abstract void addRecipies(MerchantRecipeList list);

    @Override
    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        var1.setInteger("Profession", 1234);
        var1.setInteger("Riches", this.wealth);

        if (this.buyingList != null) {
            var1.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        this.setProfession(1234);
        this.wealth = var1.getInteger("Riches");

        if (var1.hasKey("Offers")) {
            NBTTagCompound var2 = var1.getCompoundTag("Offers");
            if (this instanceof EntityTheHunger || this instanceof EntityWorkshopTinkerer || this instanceof EntityWorkshopMerchant) this.buyingList = new InfiniteTradeList(var2);
            else this.buyingList = new MerchantRecipeList(var2);
        }
    }

    @Override
    public void useRecipe(MerchantRecipe var1) {
        var1.incrementToolUses();

        if (var1.hasSameIDsAs((MerchantRecipe) this.buyingList.get(this.buyingList.size() - 1))) {
            this.timeUntilReset = 40;
            this.needsInitilization = true;

            if (this.buyingPlayer != null) {
                this.buyersName = this.buyingPlayer.getCommandSenderName();
            } else {
                this.buyersName = null;
            }
        }

        if (var1.getItemToBuy().getItem() == Items.emerald) {
            this.wealth += var1.getItemToBuy().stackSize;
        }
    }

    public void func_110297_a_(ItemStack par1ItemStack) {}

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer var1) {
        if (this.buyingList == null) {
            this.addDefaultEquipmentAndRecipies(75);
        }
        return this.buyingList;
    }

    //@Override
    //public void verifySellingItem(ItemStack stack) {}

    private void addDefaultEquipmentAndRecipies(int par1) {
        if (this.buyingList != null) {
            this.buying = MathHelper.sqrt_float(this.buyingList.size()) * 0.2F;
        } else {
            this.buying = 0.0F;
        }

        MerchantRecipeList rec = new MerchantRecipeList();

        addRecipies(rec);

        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }

        for (int var3 = 0; var3 < par1 && var3 < rec.size(); ++var3) {
            this.buyingList.add((MerchantRecipe) rec.get(var3));
        }
    }

    public abstract String mobName();
}
