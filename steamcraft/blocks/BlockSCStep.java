package steamcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStep;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.mod_Steamcraft;

public class BlockSCStep extends BlockStep
{
    public BlockSCStep(int i, boolean flag)
    {
        super(i, flag);
        setLightOpacity(0);
    }
    @Override
    public Icon getIcon(int i, int j)
    {
        if(j == 0)
        {
            return i > 1 ? 5 : 6;
        }
        if(j == 1)
        {
            if(i == 0)
            {
                return 208;
            }
            return i != 1 ? 192 : 176;
        }
        if(j == 2)
        {
            return 4;
        }
        if(j == 3)
        {
            return 16;
        }
        if(j == 4)
        {
            return Block.brick.blockIndexInTexture;
        }
        if(j == 5)
        {
            return Block.stoneBrick.blockIndexInTexture;
        }
		if(j == 6){
			return mod_Steamcraft.RoofTilesTex;
		} else
        {
            return 6;
        }
    }
    @Override
    public boolean isOpaqueCube()
    {
        return isDoubleSlab;
    }
    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        if(this != Block.stairSingle)
        {
            super.onBlockAdded(world, i, j, k);
        }
        int l = world.getBlockId(i, j - 1, k);
        int i1 = world.getBlockMetadata(i, j, k);
        int j1 = world.getBlockMetadata(i, j - 1, k);
        if(i1 != j1)
        {
            return;
        }
        if(l == stairSingle.blockID)
        {
            world.setBlockToAir(i, j, k);
            world.setBlockAndMetadataWithNotify(i, j - 1, k, Block.stairDouble.blockID, i1);
        }
    }
    @Override
    public int idDropped(int i, Random random, int j)
    {
        return Block.stairSingle.blockID;
    }
    @Override
    public int quantityDropped(Random random)
    {
        return !isDoubleSlab ? 1 : 2;
    }
    @Override
	public int damageDropped(int i)
    {
        return i;
    }
    @Override
    public boolean renderAsNormalBlock()
    {
        return isDoubleSlab;
    }
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(this != Block.stairSingle)
        {
            super.shouldSideBeRendered(iblockaccess, i, j, k, l);
        }
        if(l == 1)
        {
            return true;
        }
        if(!super.shouldSideBeRendered(iblockaccess, i, j, k, l))
        {
            return false;
        }
        if(l == 0)
        {
            return true;
        } else
        {
            return iblockaccess.getBlockId(i, j, k) != blockID;
        }
    }
}