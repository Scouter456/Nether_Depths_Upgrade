package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.blocks.advancements.NDUAdvancementTriggers;
import com.scouter.netherdepthsupgrade.blocks.advancements.PlaceWetLavaSpongeTrigger;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.function.Consumer;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.getTranslation;
import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class AdvancementGenerator extends AdvancementProvider {
    public AdvancementGenerator(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        Advancement netherdepthsupgrade = Advancement.Builder.advancement().display(NDUItems.SOULSUCKER.get(), getTranslation("advancement.root", new Object[0]), getTranslation("advancement.root.desc", new Object[0]), prefix("textures/block/lava.png"), FrameType.TASK, false, true, false).addCriterion("entered_nether", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(Level.NETHER)).save(consumer, this.getNameId("main/root"));
        Advancement lavaFishing = getAdvancement(netherdepthsupgrade, (ItemLike)NDUItems.LAVA_FISHING_ROD.get(), "craft_lava_fishing_rod", FrameType.TASK, true, true, false).addCriterion("lava_fishing_rod", InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.LAVA_FISHING_ROD.get())).save(consumer, this.getNameId("main/craft_lava_fishing_rod"));
        Advancement abyssTrailblazer = getAdvancement(netherdepthsupgrade, (ItemLike) NDUItems.EYEBALL_FISH.get(), "get_eyeball_fish", FrameType.TASK, true, true, false).addCriterion("eyeball_fish",InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.EYEBALL_FISH.get())).save(consumer, this.getNameId("main/get_eyeball_fish"));

        getAdvancement(abyssTrailblazer, (ItemLike)NDUItems.WARPED_KELP.get(), "abyssal_flora_expert", FrameType.CHALLENGE, true, true, false)
                .addCriterion("warped_kelp", InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.WARPED_KELP.get()))
                .addCriterion("warped_seagrass", InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.WARPED_SEAGRASS.get()))
                .addCriterion("crimson_kelp", InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.CRIMSON_KELP.get()))
                .addCriterion("crimson_seagrass", InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.CRIMSON_SEAGRASS.get()))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(200))
                .save(consumer, this.getNameId("main/abyssal_flora_expert"));

        Advancement wetLavaSponge = getAdvancement(abyssTrailblazer, (ItemLike) NDUItems.WET_LAVA_SPONGE.get(), "get_wet_lava_sponge", FrameType.TASK, true, true, false).addCriterion("wet_lava_sponge",InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike)NDUItems.WET_LAVA_SPONGE.get())).save(consumer, this.getNameId("main/get_wet_lava_sponge"));
        getAdvancement(wetLavaSponge, (ItemLike) NDUItems.LAVA_SPONGE.get(), "turn_wet_lava_sponge", FrameType.TASK, true, true, false).addCriterion("turn_wet_lava_sponge", PlaceWetLavaSpongeTrigger.TriggerInstance.placedBlock(NDUBlocks.WET_LAVA_SPONGE.get())).save(consumer, this.getNameId("main/turn_wet_lava_sponge"));
        getAdvancement(lavaFishing, (ItemLike) NDUItems.LAVA_PUFFERFISH.get(), "lava_fishing_master", FrameType.CHALLENGE, true, true, false)
                .addCriterion("lava_pufferfish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.LAVA_PUFFERFISH.get().asItem()).build()))
                .addCriterion("searing_cod", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.SEARING_COD.get().asItem()).build()))
                .addCriterion("glowdine", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.GLOWDINE.get().asItem()).build()))
                .addCriterion("soulsucker", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.SOULSUCKER.get().asItem()).build()))
                .addCriterion("fortress_grouper", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.FORTRESS_GROUPER.get().asItem()).build()))
                .addCriterion("eyeball_fish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.EYEBALL_FISH.get().asItem()).build()))
                .addCriterion("obsidianfish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.OBSIDIANFISH.get().asItem()).build()))
                .addCriterion("bonefish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.BONEFISH.get().asItem()).build()))
                .addCriterion("wither_bonefish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.WITHER_BONEFISH.get().asItem()).build()))
                .addCriterion("magma_cube_fish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.MAGMACUBEFISH.get().asItem()).build()))
                .addCriterion("blazefish", FishingRodHookedTrigger.TriggerInstance.fishedItem(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.item().of(NDUItems.BLAZEFISH.get().asItem()).build()))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(200))
                .save(consumer, this.getNameId("main/lava_fishing_master"));

        Advancement lavaquarium = getAdvancement(netherdepthsupgrade, (ItemLike) NDUItems.LAVA_GLASS.get(), "place_lava_glass", FrameType.TASK, true, true, false).addCriterion("place_lava_glass",PlacedBlockTrigger.TriggerInstance.placedBlock(NDUBlocks.LAVA_GLASS.get())).save(consumer, this.getNameId("main/place_lava_glass"));
        getAdvancement(lavaquarium, (ItemLike) NDUItems.SOULSUCKER_BUCKET.get(), "filled_soulsucker_bucket", FrameType.TASK, true, true, false).addCriterion("filled_soulsucker_bucket",  FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(NDUItems.SOULSUCKER_BUCKET.get()).build())).save(consumer, this.getNameId("main/filled_soulsucker_bucket"));
        //getAdvancement(lavaquarium, (ItemLike) NDUItems.SOULSUCKER_BUCKET.get(), "filled_soulsucker_bucket", FrameType.TASK, true, true, false).addCriterion("filled_soulsucker_bucket",  FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(NDUItems.SOULSUCKER_BUCKET.get()).build())).save(consumer, this.getNameId("main/filled_soulsucker_bucket"));

    }

    protected static Advancement.Builder getAdvancement(Advancement parent, ItemLike display, String name, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display, getTranslation("advancement." + name, new Object[0]), getTranslation("advancement." + name + ".desc", new Object[0]), (ResourceLocation)null, frame, showToast, announceToChat, hidden);
    }

    private String getNameId(String id) {
        return "netherdepthsupgrade:" + id;
    }


    public String getName() {
        return NetherDepthsUpgrade.MODID + " Advancements";
    }
}
