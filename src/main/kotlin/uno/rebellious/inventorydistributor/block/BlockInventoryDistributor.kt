package uno.rebellious.inventorydistributor.block

import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import org.apache.logging.log4j.Level
import uno.rebellious.inventorydistributor.InventoryDistributor

class BlockInventoryDistributor : Block(Material.CIRCUITS), ITileEntityProvider {
    init {
        unlocalizedName = "distributor"
        setRegistryName("distributor")
        setCreativeTab(CreativeTabs.MISC)
        InventoryDistributor.logger?.log(Level.INFO, "Init Registry Name Is "+registryName)
    }

    override fun createNewTileEntity(worldIn: World?, meta: Int): TileEntity? {
        InventoryDistributor.logger?.log(Level.INFO, "Creating New Tile Entity")
        return TileEntityInventoryDistributor()
    }

    fun initModel() {
        InventoryDistributor.logger?.log(Level.INFO, "initModel Registry Name Is "+registryName)
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, ModelResourceLocation(registryName, "inventory"))
    }

}