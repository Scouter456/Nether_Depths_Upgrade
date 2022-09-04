package com.scouter.netherdepthsupgrade.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class SoundsGenerator extends SoundDefinitionsProvider {

    protected SoundsGenerator(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MODID, helper);
    }

    @Override
    public void registerSounds() {
        /*this.add(MFSounds.MUSHROOM_WALK.get(), SoundDefinition.definition()
                .subtitle("walking_mushroom.walk")
                .with(SoundDefinitionsProvider
                        .sound(prefix( "mushroom_walk"))
                        .volume(0.5d)
                        .attenuationDistance(17)));
*/
        //this.add(MFSounds.MUSHROOM_WALK.get(), SoundDefinition.definition().with(SoundDefinitionsProvider.sound(prefix("mushroom_walk")).attenuationDistance(16)));
    }

    @Override
    public String getName()
    {
        return "Nether Depths Upgrade Sound Generator";
    }
}
