package uno.rebellious.InventoryDistributor.block

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object Blocks {
    val inventoryDistributor: BlockInventoryDistributor = BlockInventoryDistributor()

    @SideOnly(Side.CLIENT)
    fun initModels() {
        inventoryDistributor.initModel()
    }
}