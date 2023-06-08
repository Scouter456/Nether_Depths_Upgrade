package com.scouter.netherdepthsupgrade.modcompat;
/**
 * The following code falls under GNU Lesser General Public License v3.0
 *
 * @Author TelepathicGrunt
 * Taken from https://github.com/TelepathicGrunt/Bumblezone/blob/1.19.2-Forge/src/main/java/com/telepathicgrunt/the_bumblezone/modcompat/ModChecker.java
 */

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.fabricmc.loader.api.FabricLoader;

public class ModChecker {


    public static boolean farmersDelightPresent = false;
    public static boolean biomesYoullGoPresent = false;
    public static void setupModCompatPreInit(){
        String modid = "";

        try{

            modid = "farmersdelight";
            //loadModCompat(modid, () -> FarmersDelightCompat.setupCompat());

            modid = "byg";
            loadModCompat(modid, () -> BiomesYoullGoCompat.setupCompatPreInit());

        }
        catch (Throwable e) {
            printErrorToLogs("classloading " + modid + " pre init, mod compat done afterwards broke");
            e.printStackTrace();
        }
    }
    public static void setupModCompatCommonSetup(){
        String modid = "";

        try{
        }
        catch (Throwable e) {
            printErrorToLogs("classloading " + modid + " common setup, mod compat done afterwards broke");
            e.printStackTrace();
        }

    }
    private static void loadModCompat(String modid, Runnable runnable){
        try{
            if(FabricLoader.getInstance().isModLoaded(modid)){
                runnable.run();
            }
        }

        catch (Throwable e){
            printErrorToLogs(modid);
            e.printStackTrace();

        }
    }

    private static void printErrorToLogs(String currentModID) {
        NetherDepthsUpgrade.LOGGER.error("""
		  ------------------------------------------------NOTICE-------------------------------------------------------------------------
		  
		  ERROR: Something broke when trying to add mod compatibility with %s. Please let The Nether Depths Upgrade developer know about this!
		  
		  ------------------------------------------------NOTICE-------------------------------------------------------------------------
		""".formatted(currentModID));
    }
}
