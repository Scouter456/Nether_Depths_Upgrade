package com.scouter.netherdepthsupgrade.sounds;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<SoundEvent> GLOWDINE_AMBIENT = SOUNDS.register("entity.netherdepthsupgrade.glowdine.ambient", ()-> new SoundEvent(prefix("entity.netherdepthsupgrade.glowdine.ambient")));
    public static final RegistryObject<SoundEvent> GLOWDINE_SQUIRT = SOUNDS.register("entity.netherdepthsupgrade.glowdine.squirt", ()-> new SoundEvent(prefix("entity.netherdepthsupgrade.glowdine.squirt")));
    public static final RegistryObject<SoundEvent> GLOWDINE_HURT = SOUNDS.register("entity.netherdepthsupgrade.glowdine.hurt", ()-> new SoundEvent(prefix("entity.netherdepthsupgrade.glowdine.hurt")));
    public static final RegistryObject<SoundEvent> GLOWDINE_DEATH = SOUNDS.register("entity.netherdepthsupgrade.glowdine.death", ()-> new SoundEvent(prefix("entity.netherdepthsupgrade.glowdine.death")));


}
