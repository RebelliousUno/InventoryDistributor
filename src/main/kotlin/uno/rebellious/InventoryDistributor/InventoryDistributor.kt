package uno.rebellious.InventoryDistributor

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import uno.rebellious.InventoryDistributor.proxy.CommonProxy

@Mod(modid = InventoryDistributor.MODID, name = InventoryDistributor.NAME, version = InventoryDistributor.VERSION, modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object InventoryDistributor {
    const val MODID = "inventorydistributor"
    const val NAME = "InventoryDistributor"
    const val VERSION = "0.0.1"

    @Mod.Instance
    var instance: InventoryDistributor? = null

    @SidedProxy(clientSide = "uno.rebellious.InventoryDistributor.proxy.ClientProxy", serverSide = "uno.rebellious.InventoryDistributor.proxy.ServerProxy")
    var proxy: CommonProxy? = null

    var logger: Logger? = null

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        proxy?.preInit(event)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy?.postInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy?.init(event)
    }


}