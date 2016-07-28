package mrkickkiller.recipecost.dataset;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by MrKickkiller on 25/07/2016.
 *
 * Start of the data tree where the recipe will be explored to find the raw resources.
 */
public class DataTree {

    private ItemNode root;

    public DataTree(ItemStack toBeFound){
        root =  new ItemNode(toBeFound, 1);
    }

    public List<ItemStack> getCost(){
        return root.getMaterialCost();
    }
}
