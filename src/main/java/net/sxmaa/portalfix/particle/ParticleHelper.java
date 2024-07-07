package net.sxmaa.portalfix.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public class ParticleHelper implements IResourceManagerReloadListener {

    @SideOnly(Side.CLIENT)
    private static EffectRenderer effectRenderer;

    @SideOnly(Side.CLIENT)
    public static void addParticle(EntityFX particle) {
        effectRenderer.addEffect(particle);
    }

    public void onResourceManagerReload(IResourceManager manager) {
        effectRenderer = (Minecraft.getMinecraft()).effectRenderer;
        registerTextures(manager);
    }

    private static void registerTextures(IResourceManager manager) {}
}
