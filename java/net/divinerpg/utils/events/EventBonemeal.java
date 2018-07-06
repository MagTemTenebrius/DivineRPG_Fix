package net.divinerpg.utils.events;

import net.divinerpg.blocks.iceika.BlockWinterberryBush;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBonemeal {

	@SubscribeEvent
	public void bonemealUsed(BonemealEvent event) {
		if(event.world.getBlock(event.x, event.y, event.z) == IceikaBlocks.winterberryBush) {
			((BlockWinterberryBush)IceikaBlocks.winterberryBush).updateTick(event.world, event.x, event.y, event.z, event.world.rand);
		}
	}
	
}
