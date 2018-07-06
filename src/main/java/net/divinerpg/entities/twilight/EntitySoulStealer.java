package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySoulStealer extends EntityDivineRPGMob {
	
	public EntitySoulStealer(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes();
	    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerHealth);
	    this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerDamage);
	    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerSpeed);
	    this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerFollowRange);
	}

	@Override
	public boolean attackEntityAsMob(Entity var1) {
		if(super.attackEntityAsMob(var1)) {
			if(var1 instanceof EntityLiving) {
				((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.confusion.id, 12 * 20, 0));
				((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.blindness.id, 12 * 20, 0));
			}
			return true;
		} else {
			return false;
		}
	}
 
	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.insect);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.mortumSoul;
	}

	@Override
	public String mobName() {
		return "Soul Stealer";
	}
}