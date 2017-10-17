package uno.rebellious.inventorydistributor


import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemHandlerHelper
import uno.rebellious.inventorydistributor.block.TileEntityInventoryDistributor

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

            if (spaceInSlot)



            if ( m < stack.count) {
                var remainStack = stack.copy()
                return if (!simulate) {
                    var newStack = remainStack.splitStack(m)
                    invDistStacks[slot].grow(newStack.count)
                    remainStack
                } else {
                    remainStack.shrink(m)
                    remainStack
                }
            } else {
                if (!simulate)
                    invDistStacks[slot].grow(stack.count)
                return ItemStack.EMPTY
            }
        } else {

        }
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