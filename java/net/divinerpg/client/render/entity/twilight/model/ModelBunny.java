package net.divinerpg.client.render.entity.twilight.model;

import net.divinerpg.entities.twilight.EntityBunny;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBunny extends ModelBase
{
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer tail;

    public ModelBunny()
    {
        textureWidth = 64;
        textureHeight = 32;

        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(-3F, -3F, -2F, 6, 6, 4);
        Head.setRotationPoint(-1F, 16.5F, -7F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 18, 14);
        Body.addBox(-4F, -2F, -3F, 6, 9, 6);
        Body.setRotationPoint(0F, 17F, -3F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 1.570796F, 0F, 0F);
        Leg1 = new ModelRenderer(this, 0, 18);
        Leg1.addBox(-1F, 0F, -1F, 2, 5, 2);
        Leg1.setRotationPoint(-2.5F, 19F, 3F);
        Leg1.setTextureSize(64, 32);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new ModelRenderer(this, 0, 18);
        Leg2.addBox(-1F, 0F, -1F, 2, 5, 2);
        Leg2.setRotationPoint(0.5F, 19F, 3F);
        Leg2.setTextureSize(64, 32);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Leg3 = new ModelRenderer(this, 0, 18);
        Leg3.addBox(-1F, 0F, -1F, 2, 5, 2);
        Leg3.setRotationPoint(-2.5F, 19F, -4F);
        Leg3.setTextureSize(64, 32);
        Leg3.mirror = true;
        setRotation(Leg3, 0F, 0F, 0F);
        Leg4 = new ModelRenderer(this, 0, 18);
        Leg4.addBox(-1F, 0F, -1F, 2, 5, 2);
        Leg4.setRotationPoint(0.5F, 19F, -4F);
        Leg4.setTextureSize(64, 32);
        Leg4.mirror = true;
        setRotation(Leg4, 0F, 0F, 0F);
        Ear1 = new ModelRenderer(this, 16, 14);
        Ear1.addBox(-3F, -5F, 0F, 2, 2, 1);
        Ear1.setRotationPoint(-1F, 16.5F, -7F);
        Ear1.setTextureSize(64, 32);
        Ear1.mirror = true;
        setRotation(Ear1, 0F, 0F, 0F);
        Ear2 = new ModelRenderer(this, 16, 14);
        Ear2.addBox(1F, -5F, 0F, 2, 2, 1);
        Ear2.setRotationPoint(-1F, 16.5F, -7F);
        Ear2.setTextureSize(64, 32);
        Ear2.mirror = true;
        setRotation(Ear2, 0F, 0F, 0F);
        tail = new ModelRenderer(this, 6, 0);
        tail.addBox(0F, -5F, 8F, 2, 2, 1);
        tail.setRotationPoint(-2F, 16F, 4F);
        tail.setTextureSize(64, 32);
        tail.mirror = true;
        setRotation(tail, 0F, 0F, 0F);
        Body.addChild(tail);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Head.render(f5);
        Body.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
        Ear1.render(f5);
        Ear2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        if ((e instanceof EntityBunny && ((EntityBunny) e).isSitting())) {
            Body.rotateAngleX = 0.95993f;
            Leg1.rotateAngleX = Leg2.rotateAngleX = -1.5708f;
            Leg3.rotateAngleX = Leg4.rotateAngleX = -0.2818f;
            Leg1.rotationPointY = Leg2.rotationPointY = 23;
            Leg1.rotationPointZ = Leg2.rotationPointZ = 2;
        } else {
            Leg1.rotationPointY = Leg2.rotationPointY = 19;
            Leg1.rotationPointZ = Leg2.rotationPointZ = 3;
            this.Body.rotateAngleX = ((float) Math.PI / 2F);
            this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
            this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
            this.Leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
            this.Leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        }
        this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.Ear1.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Ear1.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.Ear2.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Ear2.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.tail.rotateAngleX = ((float) Math.PI / 2F);
    }
}
