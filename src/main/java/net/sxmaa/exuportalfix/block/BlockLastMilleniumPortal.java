package net.sxmaa.exuportalfix.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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
import net.sxmaa.exuportalfix.teleporter.TeleporterLastMillenium;
import net.sxmaa.exuportalfix.tileentity.TELastMilleniumPortal;

import java.util.Random;

public class BlockLastMilleniumPortal extends BlockContainer {
    public IIcon particle;

    public BlockLastMilleniumPortal()
    {
        super(Material.iron);
        setBlockName(Names.LAST_MILL_BLOCK);
        setBlockTextureName(ExtraUtilitiesPortalFix.MODID + ":" + Names.LAST_MILL_BLOCK);
        setCreativeTab(CreativeTabs.tabTransport);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1.5f);
        this.setResistance(12.0f);
        this.setStepSound(Block.soundTypePiston);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        super.registerBlockIcons(par1IIconRegister);
        particle = par1IIconRegister.registerIcon(ExtraUtilitiesPortalFix.MODID + ":particle_" + Names.LAST_MILL_BLOCK);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer splayer, int side, float subX, float subY, float subZ) {
        if (splayer instanceof EntityPlayerMP player) {
            if (player.dimension != Config.last_millenium_id) {
                player.setLocationAndAngles(x + 0.5D, player.posY, z + 0.5D, player.rotationYaw, player.rotationPitch);
                player.mcServer.getConfigurationManager().transferPlayerToDimension(
                    player,
                    Config.last_millenium_id,
                    new TeleporterLastMillenium(player.mcServer.worldServerForDimension(Config.last_millenium_id)));
            } else {
                player.mcServer.getConfigurationManager().transferPlayerToDimension(
                    player,
                    0,
                    new TeleporterLastMillenium(player.mcServer.worldServerForDimension(0)));
            }
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TELastMilleniumPortal();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random r) {
        double dx = MathHelper.clamp_double(0.5D + 0.2D * r.nextGaussian(), 0.0D, 1.0D);
        double dz = MathHelper.clamp_double(0.5D + 0.2D * r.nextGaussian(), 0.0D, 1.0D);
        ParticleHelper.addParticle((EntityFX)new ParticlePortal(world, x + dx, (y + 1), z + dz, 1.0F, 1.0F, 1.0F, this.particle));
    }
}
