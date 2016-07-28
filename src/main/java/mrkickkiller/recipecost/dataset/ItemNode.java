package mrkickkiller.recipecost.dataset;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mrkickkiller.recipecost.RecipeCostPlugin;
import mrkickkiller.recipecost.exception.EarlyRuntimeGrabException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrKickkiller on 25/07/2016.
 */
class ItemNode {

    private ItemStack content;
    private Map<ItemStack, Integer> nodeCount = new HashMap<ItemStack, Integer>();
    private List<ItemNode> nodes = new ArrayList<ItemNode>();
    private int amount;
    private int amountCrafted = 0;

    ItemNode(ItemStack content, int amount){
        this.content = content;
        this.amount = amount;

        try {
            IJeiRuntime run = RecipeCostPlugin.getRuntime();
            IRecipeRegistry registry = run.getRecipeRegistry();

            // TODO: Sort out the proper recipe categories and what to do in case of multiple recipes.
            List<IRecipeCategory> craftingCategorie = registry.getRecipeCategories(new ArrayList<String>(){{add("minecraft.crafting");}});

            // Todo: This needs serious clean up
            List<IRecipe> recipes = new ArrayList<IRecipe>();
            List<Object> fetchedRecipies = registry.getRecipesWithOutput(craftingCategorie.get(0), content);
            for (Object result : fetchedRecipies) {
                recipes.add((IRecipe) result);
            }

            // Todo: Generalise for every type of crafting recipe.
            for (IRecipe indivRecipe : recipes){
                if (indivRecipe instanceof ShapedOreRecipe){
                    ShapedOreRecipe inter = (ShapedOreRecipe) indivRecipe;
                    amountCrafted = inter.getRecipeOutput().stackSize;
                    System.out.println(amountCrafted);
                    for (Object item: inter.getInput()) {
                        if (item == null){
                            continue;
                        }
                        ItemStack craftingItem = (ItemStack) ((List) item).get(0);
                        if (nodeCount.containsKey(craftingItem)){
                            nodeCount.put(craftingItem, nodeCount.get(craftingItem) + 1);
                        }else {
                            nodeCount.put(craftingItem, 1);
                        }
                    }
                }
                for (ItemStack stack: nodeCount.keySet()){
                    nodes.add(new ItemNode(stack, nodeCount.get(stack)));
                }
            }

        } catch (EarlyRuntimeGrabException e) {
            System.err.println("Exception occured during building of datatree");
            e.printStackTrace();
        }
    }


    List<ItemStack> getMaterialCost(){

        if (nodes.size() == 0){ // No recipe found or blacklisted
            return new ArrayList<ItemStack>() {{
                for (int i = 0; i < amount; i++){
                    add(content);
                }
            }};
        }
        List<ItemStack> cost = null;
        for (ItemNode node : nodes){
            List<ItemStack> temp = node.getMaterialCost();
            if (cost == null){
                cost = temp;
            }else {
                cost.addAll(temp);
            }
        }
        return cost;
    }
}
