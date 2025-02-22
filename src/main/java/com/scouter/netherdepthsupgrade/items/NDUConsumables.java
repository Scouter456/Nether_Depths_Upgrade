package com.scouter.netherdepthsupgrade.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class NDUConsumables {
    public static Consumable.Builder defaultFood() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }


    public static final Consumable LAVA_PUFFERFISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.WITHER, 400, 0)
                            ),0.8F
                    )
            )
            .build();
    public static final Consumable OBSIDIANFISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 0)
                            ),0.8F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 500, 0)
                            ),0.5F
                    )
            )
            .build();


    public static final Consumable SEARING_COD = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0)
                            ),0.5F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.CONFUSION, 500, 0)
                            ),0.8F
                    )
            )
            .build();

    public static final Consumable BONEFISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.CONFUSION, 400, 0)
                            ),0.5F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.BLINDNESS, 500, 0)
                            ),0.9F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0)
                            ),0.8F
                    )
            )
            .build();

    public static final Consumable WITHER_BONEFISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.WITHER, 1000, 0)
                            ),1.0F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.BLINDNESS, 500, 0)
                            ),0.6F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.WEAKNESS, 5000, 0)
                            ),0.5F
                    )
            )
            .build();

    public static final Consumable BLAZEFISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0)
                            ),1.0F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800, 0)
                            ),0.5F
                    )
            )
            .build();

    public static final Consumable MAGMACUBEFISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0)
                            ),0.5F
                    )
            )
            .build();
    public static final Consumable GLOWDINE = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.GLOWING, 1000, 0)
                            ),0.9F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0)
                            ),0.7F
                    )
            ).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 1)
                            ),0.5F
                    )
            )
            .build();

    public static final Consumable FORTRESS_GROUPER = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.ABSORPTION, 400, 0)
                            ),0.8F
                    )
            )
            .build();

    public static final Consumable EYEBALL_FISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0)
                            ),0.8F
                    )
            )
            .build();
}
