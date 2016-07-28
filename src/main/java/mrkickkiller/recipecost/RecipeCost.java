package mrkickkiller.recipecost;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = RecipeCost.MOD_ID,
        name = RecipeCost.MOD_NAME,
        version = RecipeCost.VERSION
)
public class RecipeCost {

    public static final String MOD_ID = "RecipeCost";
    public static final String MOD_NAME = "RecipeCost";
    public static final String VERSION = "1.0-SNAPSHOT";

    @EventHandler
    public void init(FMLInitializationEvent event){
    }

}
