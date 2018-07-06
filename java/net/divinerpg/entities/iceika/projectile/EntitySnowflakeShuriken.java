package net.divinerpg.entities.iceika.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySnowflakeShuriken extends EntityThrowable {
	
    public EntitySnowflakeShuriken(World var1) {
        super(var1);
    }

    public EntitySnowflakeShuriken(World var1, EntityLivingBase var3) {
        super(var1, var3);
    }

    public EntitySnowflakeShuriken(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            byte var2 = 7;

            if (var1.entityHit instanceof EntityBlaze) {
                var2 = 7;
            }

            if (var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2)) {
                boolean var3 = true;
            }
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}