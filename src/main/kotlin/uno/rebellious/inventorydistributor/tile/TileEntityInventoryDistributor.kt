package uno.rebellious.inventorydistributor.tile

import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraft.util.NonNullList
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import org.apache.logging.log4j.Level
import uno.rebellious.inventorydistributor.InventoryDistributor
import uno.rebellious.inventorydistributor.handler.InventoryHandler

class TileEntityInventoryDistributor : TileEntity(), ITickable {
    var invDistStacks: NonNullList<ItemStack> = NonNullList.withSize(5, ItemStack.EMPTY)
    val logger = InventoryDistributor.logger

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true
        }
        return super.hasCapability(capability, facing)
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            InventoryHandler(this) as T
        } else super.getCapability(capability, facing)
    }

    override fun update() {
        if (world.isRemote) return
        if (!hasItems()) return
        EnumFacing.VALUES
                .filter { it != EnumFacing.UP }
                .filter { isInventoryBlock(world.getBlockState(pos.offset(it)).block) }
                .filter { world.getTileEntity(pos.offset(it))?.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, it) == true }
                .map { world.getTileEntity(pos.offset(it))?.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, it)!! }
                .forEach {
                    spreadItemsIntoBlock(it)
                }
    }

    private fun spreadItemsIntoBlock(handler: IItemHandler) {

        val numSlots = handler.slots
        val slotIds = 0..(numSlots - 1)
        slotIds.forEach { slotId ->
            logger?.log(Level.INFO, "$slotId: ${handler.getStackInSlot(slotId).displayName}")
        }
    }

    private fun isInventoryBlock(checkBlock: Block): Boolean {
        return checkBlock is BlockContainer
    }

    private fun hasItems(): Boolean {
        return invDistStacks.count { !it.isEmpty } > 0
    }
}