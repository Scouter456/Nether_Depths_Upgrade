package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(event.includeServer(), new RecipeGenerator(packOutput, lookupProvider));
        //generator.addProvider(event.includeServer(), new LootTableGenerator(packOutput));
        generator.addProvider(event.includeServer(), new LanguageGenerator(packOutput));
        generator.addProvider(event.includeClient(), new SBlockStateGenerator(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new FluidTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new EntityTagGenerator(packOutput, lookupProvider, existingFileHelper));
        //generator.addProvider(event.includeServer(), new StructureUpdater("structure", packOutput, existingFileHelper));
        generator.addProvider(true,new AdvancementProvider(packOutput, lookupProvider, existingFileHelper));





        BlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
        //generator.addProvider(event.includeServer(), new WorldGenProvider(packOutput, lookupProvider));

        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(packOutput, lookupProvider);

        CompletableFuture<HolderLookup.Provider> customLookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(event.includeServer(), datapackProvider);
        generator.addProvider(true,new EnchantmenTagGenerator(  packOutput, customLookupProvider, existingFileHelper));
        Map<PackType, Integer> packVersions = Arrays.stream(PackType.values())
                .collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion));

        InclusiveRange<Integer> supportedFormatsRange = new InclusiveRange<>(
                packVersions.values().stream().min(Integer::compareTo).orElse(1),
                packVersions.values().stream().max(Integer::compareTo).orElse(1)
        );

        generator.addProvider(true, new PackMetadataGenerator(packOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Resources for Nether Depths Upgrade"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.of(supportedFormatsRange)
        )));
        //new ForgeSherdDatagenSuite(event, Scalebound.MODID)

        //        .makeSherdSuite(SSherds.DRAGON, new Sherd(SItems.DRAGON_POTTERY_SHERD.get(), prefix("dragon_pottery_pattern")));
    }
}
