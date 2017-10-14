package uno.rebellious.InventoryDistributor.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import org.apache.logging.log4j.Level
import uno.rebellious.InventoryDistributor.InventoryDistributor

class BlockInventoryDistributor : Block(Material.CIRCUITS) {
    init {
        unlocalizedName = "distributor"
        setRegistryName("distributor")
        setCreativeTab(CreativeTabs.MISC)
        InventoryDistributor.logger?.log(Level.INFO, "Init Registry Name Is "+registryName)
    }

    fun initModel() {
        InventoryDistributor.logger?.log(Level.INFO, "initModel Registry Name Is "+registryName)
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, ModelResourceLocation(registryName, "inventory"))
    }

}