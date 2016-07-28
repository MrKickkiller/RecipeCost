package mrkickkiller.recipecost;

import mezz.jei.api.*;
import mrkickkiller.recipecost.dataset.DataTree;
import mrkickkiller.recipecost.exception.EarlyRuntimeGrabException;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by MrKickkiller on 24/07/2016.
 */

@JEIPlugin
public class RecipeCostPlugin extends BlankModPlugin {

    private static IJeiRuntime runtime = null;

    @Override
    public void register(@Nonnull IModRegistry registry) {

        IItemRegistry itemRegistry = registry.getItemRegistry();
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guihelper = jeiHelpers.getGuiHelper();

    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime iJeiRuntime) {
        System.out.println("Runtime HAPPENING");
        runtime = iJeiRuntime;
        List<ItemStack> itemsNeeded = new DataTree(new ItemStack(Blocks.PISTON)).getCost();
        System.out.println(itemsNeeded);
    }

    public static IJeiRuntime getRuntime() throws EarlyRuntimeGrabException {
        if (runtime == null){
            throw new EarlyRuntimeGrabException("Attempt at using the JEIRuntime before it has been given to the plugin: \t" + RecipeCostPlugin.toStatusString());
        }
        return runtime;
    }

    public static String toStatusString() {
        return "RecipeCostPlugin{" + RecipeCostPlugin.runtime == null? "runtime : null" : runtime.toString() + "}";
    }
}
