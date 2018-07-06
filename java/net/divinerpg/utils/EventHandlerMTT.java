package net.divinerpg.utils;

import java.util.Iterator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EventHandlerMTT {

    private NBTTagCompound persistantData;

    @SubscribeEvent
    public void teleport(PlayerChangedDimensionEvent  event) {
        if (event.toDim==ConfigurationHelper.vethea) {
            EntityPlayer player = event.player;
            this.persistantData = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);

            this.persistantData.setTag("OverworldInv", player.inventory.writeToNBT(new NBTTagList()));
            player.getEntityData().setTag("PlayerPersisted", this.persistantData);
            player.inventory.clearInventory(null, -1);
            NBTTagList inv = this.persistantData.getTagList("VetheaInv", 10);
            player.inventory.readFromNBT(inv);
            player.inventoryContainer.detectAndSendChanges();
            ChunkCoordinates c = new ChunkCoordinates();
            c.posX = (int) player.posX + 2;
            c.posY = 18;
            c.posZ = (int) player.posZ - 2;
            player.setSpawnChunk(c, true, ConfigurationHelper.vethea);

        }
        if(event.fromDim==ConfigurationHelper.vethea) {
            EntityPlayer player = event.player;

            this.persistantData = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);

            this.persistantData.setTag("VetheaInv", player.inventory.writeToNBT(new NBTTagList()));
            player.getEntityData().setTag("PlayerPersisted", this.persistantData);
            player.inventory.clearInventory(null, -1);
            NBTTagList inv = this.persistantData.getTagList("OverworldInv", 10);
            player.inventory.readFromNBT(inv);
            player.inventoryContainer.detectAndSendChanges();
        }

    }
}

