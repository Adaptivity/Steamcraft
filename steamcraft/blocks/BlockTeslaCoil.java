package steamcraft.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.RedstoneUpdateInfo;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import steamcraft.Steamcraft;

public class BlockTeslaCoil extends BlockRedstoneTorch
{
	private boolean torchActive;
	public BlockTeslaCoil(int i, boolean flag)
    {
        super(i,flag);
        torchActive = flag;
    }

    @Override
    public int tickRate(World world)
    {
        return 1;
    }
    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
		world.notifyBlocksOfNeighborChange(i, j, k, blockID);
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
    }

    @Override
	public void breakBlock(World world, int i, int j, int k, int par5, int par6)
    {
    	int l2 = world.getBlockMetadata(i, j, k);
        int f = 0;
        int f1 = 0;
		int f2 = 0;
        if(l2 == 3)
        {
			 f = 0;
            f1 = 1;
			f2 = 0;
        } else
        if(l2 == 2)
        {
            f = -1;
           f1 = 0;
		   f2 = 0;
        } else
        if(l2 == 1)
        {
            f = 1;
           f1 = 0;
		   f2 = 0;
        } else
        if(l2 == 4)
        {
            f = 0;
            f1 = -1;
			f2 = 0;
        } else
        {
             f = 0;
            f1 = 0;
			f2 = 1;
        }

		int nn = 0;
		while (nn <= 8) {
		nn=nn+1;
		int t1 = (i + f * nn);
		int t2 = (k + f1 * nn);
		int t3 = (j + f2 * nn);

           if((world.getBlockId(t1, t3, t2) == Steamcraft.teslaReceiver.blockID || world.getBlockId(t1, t3, t2) == Steamcraft.teslaReceiverActive.blockID) && nn >= 1) {
            world.setBlockMetadataWithNotify(t1, t3, t2, 1, 2);

            world.notifyBlocksOfNeighborChange(t1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1 - 1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1 + 1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3, t2 - 1, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3, t2 + 1, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3 - 1, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3 + 1, t2, blockID);
			}
			if((world.getBlockId(t1, t3, t2) == Steamcraft.wirelessLampIdle.blockID || world.getBlockId(t1, t3, t2) == Steamcraft.wirelessLampActive.blockID) && nn >= 1) {
			world.notifyBlocksOfNeighborChange(t1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1 - 1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1 + 1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3, t2 - 1, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3, t2 + 1, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3 - 1, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3 + 1, t2, blockID);
			}
			}
        if(torchActive)
        {
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
            world.notifyBlocksOfNeighborChange(i - 1, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i + 1, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j, k - 1, blockID);
            world.notifyBlocksOfNeighborChange(i, j, k + 1, blockID);
        }
    }
	
    @Override
    public void updateTick(World world, int i, int j, int k, Random random)
    {
    	boolean flag = this.isIndirectlyPowered(world, i, j, k);
        List list = (List)redstoneUpdateInfoCache.get(world);

        while (list != null && !list.isEmpty() && world.getTotalWorldTime() - ((RedstoneUpdateInfo)list.get(0)).updateTime > 60L)
        {
            list.remove(0);
        }
        byte nnum = 1;

        if(torchActive)
		{
    	  nnum = 2;
		}
		else	
		{
			nnum = 1;
		}
		
        int l2 = world.getBlockMetadata(i, j, k);
        int f = 0;
        int f1 = 0;
		int f2 = 0;
        if(l2 == 3)
        {
			 f = 0;
            f1 = 1;
			f2 = 0;
        } else
        if(l2 == 2)
        {
            f = -1;
           f1 = 0;
		   f2 = 0;
        } else
        if(l2 == 1)
        {
            f = 1;
           f1 = 0;
		   f2 = 0;
        } else
        if(l2 == 4)
        {
            f = 0;
            f1 = -1;
			f2 = 0;
        } else
        {
             f = 0;
            f1 = 0;
			f2 = 1;
        }

		int nn = 0;
		while (nn <= 8) {
		nn=nn+1;
		int t1 = (i + f * nn);
		int t2 = (k + f1 * nn);
		int t3 = (j + f2 * nn);

       if((world.getBlockId(t1, t3, t2) == Steamcraft.teslaReceiver.blockID || world.getBlockId(t1, t3, t2) == Steamcraft.teslaReceiverActive.blockID) && nn >= 1) {
    	   world.setBlockMetadataWithNotify(t1, t3, t2, nnum, 2);
	        world.notifyBlocksOfNeighborChange(t1, t3, t2, blockID);
	        world.notifyBlocksOfNeighborChange(t1 - 1, t3, t2, blockID);
	        world.notifyBlocksOfNeighborChange(t1 + 1, t3, t2, blockID);
	        world.notifyBlocksOfNeighborChange(t1, t3, t2 - 1, blockID);
	        world.notifyBlocksOfNeighborChange(t1, t3, t2 + 1, blockID);
	        world.notifyBlocksOfNeighborChange(t1, t3 - 1, t2, blockID);
	        world.notifyBlocksOfNeighborChange(t1, t3 + 1, t2, blockID);
       }
	   	if((world.getBlockId(t1, t3, t2) == Steamcraft.wirelessLampIdle.blockID || world.getBlockId(t1, t3, t2) == Steamcraft.wirelessLampActive.blockID) && nn >= 1) {
			world.notifyBlocksOfNeighborChange(t1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1 - 1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1 + 1, t3, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3, t2 - 1, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3, t2 + 1, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3 - 1, t2, blockID);
            world.notifyBlocksOfNeighborChange(t1, t3 + 1, t2, blockID);
			}
		}
	
        if(!torchActive)
        {
            if(flag)
            {
                world.setBlock(i, j, k, Steamcraft.torchTeslaActive.blockID, world.getBlockMetadata(i, j, k), 2);
            }
        } 
        else if(!flag && !checkForBurnout(world, i, j, k, false))
        {
            world.setBlock(i, j, k, Steamcraft.torchTeslaIdle.blockID, world.getBlockMetadata(i, j, k), 2);
        }
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
    }
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        super.onNeighborBlockChange(world, i, j, k, l);
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
    }
    @Override
    public int idDropped(int i, Random random, int j)
    {
        return Steamcraft.torchTeslaIdle.blockID;
    }
    @Override
    public boolean canProvidePower()
    {
        return false;
    }
}
