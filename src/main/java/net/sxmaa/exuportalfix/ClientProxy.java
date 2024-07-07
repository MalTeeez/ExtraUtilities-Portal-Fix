package net.sxmaa.exuportalfix;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.Minecraft;
import net.sxmaa.exuportalfix.particle.ParticleHelper;

public class ClientProxy extends CommonProxy {

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.

    @Override
    public void init(FMLInitializationEvent event) {
        ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager())
            .registerReloadListener((IResourceManagerReloadListener)new ParticleHelper());
    }
}
