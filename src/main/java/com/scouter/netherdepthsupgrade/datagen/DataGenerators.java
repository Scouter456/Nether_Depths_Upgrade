package com.scouter.netherdepthsupgrade.datagen;

import com.google.common.collect.Sets;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.Set;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent evt) {
        if (evt.includeServer())
            registerServerProviders(evt.getGenerator(), evt);

        if (evt.includeClient())
            registerClientProviders(evt.getGenerator(), evt);


    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent evt) {
        ExistingFileHelper helper = evt.getExistingFileHelper();
        generator.addProvider(new LootGenerator(generator));
        generator.addProvider(new BlockTagsGenerator(generator, helper));
    }

    private static void registerServerProviders(DataGenerator generator, GatherDataEvent evt) {
        ExistingFileHelper helper = evt.getExistingFileHelper();
        BlockTagsGenerator blockTags = new BlockTagsGenerator(generator, helper);
        Set<BlockStateGenerator> set = Sets.newHashSet();
        Consumer<BlockStateGenerator> consumer = set::add;
        generator.addProvider(new NDUBiomeTagsProvider(generator, helper));
        generator.addProvider(new EntityTags(generator, helper));
        generator.addProvider(new RecipeGenerator(generator));
        generator.addProvider(new BlockstateGenerator(generator, helper));
        generator.addProvider(new SoundsGenerator(generator, helper));
        generator.addProvider(new ItemTagsGenerator(generator, blockTags, helper));
        generator.addProvider(new ItemModelGenerator(generator, helper));
        generator.addProvider(new LanguageGenerator(generator));
        //generator.addProvider(new AdvancementGenerator(generator, helper));
    }
}
