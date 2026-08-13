package com.scouter.netherdepthsupgrade.enchantments;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.datacomponents.NDUDataComponents;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUEnchantments {
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    //public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(BuiltInRegistries.ENCHANTMENTS, NetherDepthsUpgrade.MODID);
    //public static final RegistryObject<Enchantment> HELL_STRIDER = ENCHANTMENT.register("hell_strider", () -> new HellStriderEnchantment(Enchantment.Rarity.RARE, ARMOR_SLOTS));



    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final ResourceKey<Enchantment> HELL_STRIDER = key("hell_strider");




    public static void bootstrap(BootstrapContext<Enchantment> bootstrapContext) {
        HolderGetter<DamageType> holderGetter = bootstrapContext.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> holderGetter2 = bootstrapContext.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> holderGetter3 = bootstrapContext.lookup(Registries.ITEM);
        HolderGetter<Block> holderGetter4 = bootstrapContext.lookup(Registries.BLOCK);
        register(bootstrapContext, HELL_STRIDER, Enchantment.enchantment(Enchantment.definition(holderGetter3.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), 10, 2, Enchantment.dynamicCost(1, 11), Enchantment.dynamicCost(12, 11), 1, new EquipmentSlotGroup[]{EquipmentSlotGroup.ARMOR})).exclusiveWith(holderGetter2.getOrThrow(EnchantmentTags.BOOTS_EXCLUSIVE)).withEffect(NDUDataComponents.HAS_HELL_STRIDER));

    }

    private static void register(BootstrapContext<Enchantment> bootstrapContext, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        bootstrapContext.register(resourceKey, builder.build(resourceKey.location()));
    }

    private static ResourceKey<Enchantment> key(String string) {
        return ResourceKey.create(Registries.ENCHANTMENT, prefix(string));
    }

    public static void ENCHANTMENTS()
    {
        LOGGER.info("Registering Enchantments for " + NetherDepthsUpgrade.MODID);
    }
}
