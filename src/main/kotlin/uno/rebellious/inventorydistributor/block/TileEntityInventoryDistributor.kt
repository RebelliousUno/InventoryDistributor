package uno.rebellious.InventoryDistributor.block

import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import org.apache.logging.log4j.Level
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler

import net.minecraftforge.items.wrapper.SidedInvWrapper

import uno.rebellious.InventoryDistributor.InventoryDistributor

class TileEntityInventoryDistributor : TileEntity(), ITickable, ISidedInventory {


    var invDistStacks: NonNullList<ItemStack> = NonNullList.withSize(5, ItemStack.EMPTY)
    val logger = InventoryDistributor.logger

    val handlerInput = SidedInvWrapper(this, EnumFacing.UP)
    val handlerOutput = SidedInvWrapper(this, EnumFacing.NORTH)

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true
        }
        return super.hasCapability(capability, facing)
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return super.getCapability(capability, facing)
        /*return if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.UP) {
                handlerInput as T
            } else {
                handlerOutput as T
            }
        } else
            super.getCapability(capability, facing)*/
    }

    override fun update() {
        if (world.isRemote) return
        EnumFacing.VALUES
                .filter{ it != EnumFacing.UP}
                .filter { isInventoryBlock(world.getBlockState(pos.offset(it)).block) }
                .filter { world.getTileEntity(pos.offset(it))?.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, it) ?: false}
                .map { world.getTileEntity(pos.offset(it))?.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, it)!!}
                .forEach {
                    val numSlots = it.slots
                    val slotIds = 0..(numSlots-1)
                    slotIds.forEach {
                        slotId -> logger?.log(Level.INFO, "$slotId: ${it.getStackInSlot(slotId).displayName}")
                    }
                }
    }

    private fun isInventoryBlock(checkBlock: Block): Boolean {
        return checkBlock is BlockContainer
    }

    override fun getStackInSlot(index: Int): ItemStack {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInventoryStackLimit(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSlotsForFace(side: EnumFacing?): IntArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openInventory(player: EntityPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setField(id: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeStackFromSlot(index: Int): ItemStack {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFieldCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getField(id: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasCustomName(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun decrStackSize(index: Int, count: Int): ItemStack {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSizeInventory(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isItemValidForSlot(index: Int, stack: ItemStack?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canInsertItem(index: Int, itemStackIn: ItemStack?, direction: EnumFacing?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isUsableByPlayer(player: EntityPlayer?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canExtractItem(index: Int, stack: ItemStack?, direction: EnumFacing?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeInventory(player: EntityPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}