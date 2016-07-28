package mrkickkiller.recipecost.dataset;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Mathieu on 25/07/2016.
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
