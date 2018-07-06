package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySkythernFiend extends EntityDivineRPGMob {
	
	public EntitySkythernFiend(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes();
	    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernFiendHealth);
	    this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernFiendDamage);
	    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernFiendSpeed);
	    this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernFiendFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected String getLivingSound()
	{
		return Sounds.getSoundName(Sounds.insect);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.skythernSoul;
	}

	@Override
	public String mobName() {
		return "Skythern Fiend";
	}
}