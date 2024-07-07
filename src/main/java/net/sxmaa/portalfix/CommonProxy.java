package net.sxmaa.portalfix;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.sxmaa.portalfix.block.BlockDeepDarkPortal;
import net.sxmaa.portalfix.block.BlockLastMilleniumPortal;
import net.sxmaa.portalfix.tileentity.TEDeepDarkPortal;
import net.sxmaa.portalfix.tileentity.TELastMilleniumPortal;

public class CommonProxy {

    public Block deepDarkPortal;
    public Block lastMilleniumPortal;

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        deepDarkPortal = new BlockDeepDarkPortal();
        lastMilleniumPortal = new BlockLastMilleniumPortal();
        GameRegistry.registerTileEntity(TEDeepDarkPortal.class, "portalfix.deep_dark_portal");
        GameRegistry.registerTileEntity(TELastMilleniumPortal.class, "portalfix.last_milenium_portal");

        ExtraUtilitiesPortalFix.LOG.info("Adding fixed Extra Utilities portal blocks..");
        ExtraUtilitiesPortalFix.LOG.info("Deep Dark dimension has id {}", String.valueOf(Config.deep_dark_id));
        ExtraUtilitiesPortalFix.LOG.info("Last Millenium dimension has id {}", String.valueOf(Config.last_millenium_id));
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}

}
