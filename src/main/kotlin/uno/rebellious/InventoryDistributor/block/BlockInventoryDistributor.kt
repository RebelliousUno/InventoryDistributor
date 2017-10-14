package uno.rebellious.InventoryDistributor.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import uno.rebellious.InventoryDistributor.InventoryDistributor

class BlockInventoryDistributor : Block(Material.CIRCUITS) {
    init {
        unlocalizedName = "distributor"
        setRegistryName(InventoryDistributor.MOD_ID, "distributor")
        setCreativeTab(CreativeTabs.MISC)
    }

    fun initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, ModelResourceLocation(registryName, "inventory"))
    }

}