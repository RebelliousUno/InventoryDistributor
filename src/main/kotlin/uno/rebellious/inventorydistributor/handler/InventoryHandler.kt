package uno.rebellious.inventorydistributor.handler

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemHandlerHelper
import uno.rebellious.inventorydistributor.tile.TileEntityInventoryDistributor

class InventoryHandler(val distributor: TileEntityInventoryDistributor) : IItemHandler {

    var invDistStacks = distributor.invDistStacks

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        if (stack.isEmpty) return ItemStack.EMPTY
        val stackInSlot = invDistStacks[slot]

        if (!stackInSlot.isEmpty) {
            if (!ItemHandlerHelper.canItemStacksStack(stack, stackInSlot)) {
                return stack
            }

            var spaceInSlot = Math.min(stack.maxStackSize, getSlotLimit(slot)) - stackInSlot.count
            if (spaceInSlot >= stack.count) {
                if (!simulate)
                    stackInSlot.grow(stack.count)
                distributor.markDirty()
                return ItemStack.EMPTY
            }
            if (spaceInSlot < stack.count) {
                val stackToAdd = stack.copy()
                var newStack = stackToAdd.splitStack(spaceInSlot)
                if (!simulate) {
                    stackInSlot.grow(newStack.count)
                }
                distributor.markDirty()
                return stackToAdd
            }
        }
        if (!simulate)
            invDistStacks[slot] = stack.copy()
        distributor.markDirty()
        return ItemStack.EMPTY
    }

    override fun getStackInSlot(slot: Int): ItemStack {
        return invDistStacks[slot]
    }

    override fun getSlotLimit(slot: Int): Int {
        return 64
    }

    override fun getSlots(): Int {
        return invDistStacks.size
    }

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}