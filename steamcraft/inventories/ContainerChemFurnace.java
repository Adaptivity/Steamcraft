package steamcraft.inventories;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import steamcraft.TileEntityChemFurnace;

public class ContainerChemFurnace extends Container
{
    public ContainerChemFurnace(InventoryPlayer inventoryplayer, TileEntityChemFurnace tileentitychemfurnace)
    {
        cookTime = 0;
        burnTime = 0;
        itemBurnTimea = 0;
        furnace = tileentitychemfurnace;
        addSlotToContainer(new Slot(tileentitychemfurnace, 0, 56, 17));
        addSlotToContainer(new Slot(tileentitychemfurnace, 1, 66, 53));
        addSlotToContainer(new Slot(tileentitychemfurnace, 3, 45, 53));
        addSlotToContainer(new SlotFurnace(inventoryplayer.player, tileentitychemfurnace, 2, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
            	addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
        	addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);
            if(cookTime != furnace.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, furnace.furnaceCookTime);
            }
            if(burnTime != furnace.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, furnace.furnaceBurnTime);
            }
            if(itemBurnTimea != furnace.currentItemBurnTimea)
            {
                icrafting.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTimea);
            } 
			if(itemBurnTimeb != furnace.currentItemBurnTimeb)
            {
                icrafting.sendProgressBarUpdate(this, 3, furnace.currentItemBurnTimeb);
            }
        }

        cookTime = furnace.furnaceCookTime;
        burnTime = furnace.furnaceBurnTime;
        itemBurnTimea = furnace.currentItemBurnTimea;
		itemBurnTimeb = furnace.currentItemBurnTimeb;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if(i == 0)
        {
            furnace.furnaceCookTime = j;
        }
        if(i == 1)
        {
            furnace.furnaceBurnTime = j;
        }
        if(i == 2)
        {
            furnace.currentItemBurnTimea = j;
        }
		if(i == 3)
        {
            furnace.currentItemBurnTimeb = j;
        }
    }
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return furnace.isUseableByPlayer(entityplayer);
    }
    @Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 2)
            {
            	mergeItemStack(itemstack1, 4, 40, true);
            } else
            if(i >= 4 && i < 31)
            {
            	mergeItemStack(itemstack1, 31, 40, false);
            } else
            if(i >= 31 && i < 40)
            {
            	mergeItemStack(itemstack1, 4, 31, false);
            } else
            {
            	mergeItemStack(itemstack1, 4, 40, false);
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }

    private TileEntityChemFurnace furnace;
    private int cookTime;
    private int burnTime;
    private int itemBurnTimea;
	private int itemBurnTimeb;
}