package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityHerbomancer extends VetheaMob {
	
    private int spawnTick;

    public EntityHerbomancer(World var1) {
        super(var1);
        addAttackingAI();
        this.spawnTick = 40;
    }

    @Override
    public int getSpawnLayer() {
    	return 2;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.herbomancerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.herbomancerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.herbomancerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.herbomancerFollowRange);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.spawnTick == 0 && !this.worldObj.isRemote) {
            EntityHerbomancerMinion var2 = new EntityHerbomancerMinion(this.worldObj);
            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(var2);
            this.worldObj.spawnParticle("reddust", var2.posX, var2.posY + 0.5D, var2.posZ, this.rand.nextGaussian() * 2.0D - 1.0D, this.rand.nextGaussian() * 2.0D - 1.0D, this.rand.nextGaussian() * 2.0D - 1.0D);
            this.worldObj.playSoundAtEntity(var2, "" /*Sound.KarotSummon*/, 10.0F, 1.0F);
            this.spawnTick = 40;
        }

        this.spawnTick--;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.herbomancer.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.herbomancerHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return getHurtSound();
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

	@Override
	public String mobName() {
		return "Herbomancer";
	}
}