package com.scouter.netherdepthsupgrade.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class OriginsCompatGenerator implements DataProvider {
    private static final ResourceLocation POWER_ID =
            NetherDepthsUpgrade.prefix("blazeborn_lava_vision");

    private static final ResourceLocation BLAZEBORN_TAG =
            ResourceLocation.fromNamespaceAndPath("origins", "blazeborn");

    private final PackOutput.PathProvider powerPath;
    private final PackOutput.PathProvider powerTagPath;

    public OriginsCompatGenerator(PackOutput output) {
        this.powerPath = output.createPathProvider(
                PackOutput.Target.DATA_PACK,
                "origins/power"
        );

        this.powerTagPath = output.createPathProvider(
                PackOutput.Target.DATA_PACK,
                "tags/origins/power"
        );
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.allOf(
                DataProvider.saveStable(
                        output,
                        createPower(),
                        powerPath.json(POWER_ID)
                ),
                DataProvider.saveStable(
                        output,
                        createBlazebornTag(),
                        powerTagPath.json(BLAZEBORN_TAG)
                )
        );
    }

    private static JsonObject createPower() {
        JsonObject effect = new JsonObject();
        effect.addProperty(
                "effect",
                NetherDepthsUpgrade.prefix("lava_vision").toString()
        );
        effect.addProperty("amplifier", 0);
        effect.addProperty("show_particles", false);
        effect.addProperty("show_icon", false);

        JsonObject power = new JsonObject();
        power.addProperty("type", "origins:permanent_effect");
        power.addProperty("hidden", true);
        power.add("effect", effect);

        return power;
    }

    private static JsonObject createBlazebornTag() {
        JsonArray values = new JsonArray();
        values.add(POWER_ID.toString());

        JsonObject tag = new JsonObject();
        tag.addProperty("replace", false);
        tag.add("values", values);

        return tag;
    }

    @Override
    public String getName() {
        return "Nether Depths Upgrade Origins compatibility";
    }
}