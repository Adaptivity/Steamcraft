package steamcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class ItemHandler extends DataHandler<Item> {
	private Item item = null;
    private String oreName;

	public ItemHandler(Item it, String... names) {
        super(names[0]);
		this.item = it;
		if (item != null) {
			item.setUnlocalizedName(names[0]).setTextureName(names[1]).setCreativeTab(Steamcraft.steamTab);//finalizing the item
            setOutput(1, 0);
            if (names.length > 2) {
                oreName = names[2];
			}
		}
	}

    @Override
    public void register(boolean asInternal) {
        if (item != null) {
            super.register(asInternal);
            GameRegistry.registerItem(item, registryName);//registering...
            if (oreName != null) {
                OreDictionary.registerOre(oreName, item);
            }
        }
    }

    @Override
    public DataHandler addSmelt(ItemStack stack, float xp) {
        FurnaceRecipes.smelting().func_151396_a(get(), stack, xp);
        return this;
    }

    @Override
    public DataHandler addSmelt(ItemStack stack, int meta, float xp) {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(get(), 1, meta), stack, xp);
        return this;
    }

    public Item get() {
		return item;
	}

    public Item getItem(){
        return item;
    }

	@Override
	public String getName() {
		if (item != null) {
			return item.getUnlocalizedName();
		}
		return null;
	}

    public ItemHandler setTool(String tool, int level) {
		if (tool.equals("drill")) {
			HandlerRegistry.addDrill(item);
		}
        item.setHarvestLevel(tool, level);
		return this;
	}
}
