package uno.rebellious.inventorydistributor.proxy

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import org.apache.logging.log4j.Level
import uno.rebellious.inventorydistributor.InventoryDistributor
import uno.rebellious.inventorydistributor.block.Blocks
import uno.rebellious.inventorydistributor.config.GeneralConfig
import uno.rebellious.inventorydistributor.tile.TileEntityInventoryDistributor
import java.io.File

@Mod.EventBusSubscriber
open class CommonProxy {
    companion object {

        var config: Configuration? = null

        @JvmStatic
        @SubscribeEvent
        fun registerBlocks(event: RegistryEvent.Register<Block>) {
            InventoryDistributor.logger?.log(Level.INFO, "Registering Blocks")
            event.registry.register(Blocks.inventoryDistributor)
            GameRegistry.registerTileEntity(TileEntityInventoryDistributor::class.java, InventoryDistributor.MODID + ":distributor")
        }

        @JvmStatic
        @SubscribeEvent
        fun registerItems(event: RegistryEvent.Register<Item>) {
            InventoryDistributor.logger?.log(Level.INFO, "Registering Items")
            val invDistBlock = ItemBlock(Blocks.inventoryDistributor)
                    .setRegistryName(Blocks.inventoryDistributor.registryName)
            event.registry.register(invDistBlock)
        }
    }

    fun preInit(event: FMLPreInitializationEvent) {
        val configDir = event.modConfigurationDirectory
        config = Configuration(File(configDir.path, "inventorydistributor.cfg"))
        try {
            config!!.load()
            GeneralConfig.readConfig(config!!)
        } catch (exception: Exception) {
            InventoryDistributor.logger?.log(Level.ERROR, "Error loading config file", exception)
        } finally {
            if (config!!.hasChanged()) {
                config!!.save()
            }
        }
    }

    fun init(event: FMLInitializationEvent) {}

    fun postInit(event: FMLPostInitializationEvent) {
        if (config?.hasChanged()!!) config?.save()
    }
}

@Mod.EventBusSubscriber
class ServerProxy : CommonProxy()

@Mod.EventBusSubscriber
class ClientProxy : CommonProxy() {
    companion object {

        @JvmStatic
        @SubscribeEvent
        fun registerModels(event: ModelRegistryEvent) {
            Blocks.inventoryDistributor.initModel()
        }
    }
}