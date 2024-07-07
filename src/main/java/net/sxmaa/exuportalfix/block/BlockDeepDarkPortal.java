package net.sxmaa.exuportalfix.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.sxmaa.exuportalfix.Config;
import net.sxmaa.exuportalfix.ExtraUtilitiesPortalFix;
import net.sxmaa.exuportalfix.Names;
import net.sxmaa.exuportalfix.particle.ParticleHelper;
import net.sxmaa.exuportalfix.particle.ParticlePortal;
import net.sxmaa.exuportalfix.teleporter.TeleporterDeepDark;
import net.sxmaa.exuportalfix.tileentity.TEDeepDarkPortal;

import java.util.Random;

public class BlockDeepDarkPortal extends BlockContainer {
    public IIcon particle;

    public BlockDeepDarkPortal()
    {
        super(Material.rock);
        setBlockName(Names.DEEP_DARK_BLOCK);
        setBlockTextureName(ExtraUtilitiesPortalFix.MODID + ":" + Names.DEEP_DARK_BLOCK);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        super.registerBlockIcons(par1IIconRegister);
        particle = par1IIconRegister.registerIcon(ExtraUtilitiesPortalFix.MODID + ":particle_" + Names.DEEP_DARK_BLOCK);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer splayer, int side, float subX, float subY, float subZ) {
        if (splayer instanceof EntityPlayerMP player) {
            if (player.dimension != Config.deep_dark_id) {
                player.setLocationAndAngles(x + 0.5D, player.posY, z + 0.5D, player.rotationYaw, player.rotationPitch);
                player.mcServer.getConfigurationManager().transferPlayerToDimension(
                    player,
                    Config.deep_dark_id,
                    new TeleporterDeepDark(player.mcServer.worldServerForDimension(Config.deep_dark_id)));
            } else {
                player.mcServer.getConfigurationManager().transferPlayerToDimension(
                    player,
                    0,
                    new TeleporterDeepDark(player.mcServer.worldServerForDimension(0)));
            }
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEDeepDarkPortal();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random r) {
        double dx = MathHelper.clamp_double(0.5D + 0.2D * r.nextGaussian(), 0.0D, 1.0D);
        double dz = MathHelper.clamp_double(0.5D + 0.2D * r.nextGaussian(), 0.0D, 1.0D);
        ParticleHelper.addParticle((EntityFX)new ParticlePortal(world, x + dx, (y + 1), z + dz, 1.0F, 1.0F, 1.0F, this.particle));
    }

    @Override
    public String getLocalizedName() {
        return super.getLocalizedName();
    }
}
