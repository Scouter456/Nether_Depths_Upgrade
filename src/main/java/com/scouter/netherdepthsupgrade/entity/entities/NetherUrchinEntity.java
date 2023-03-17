package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import com.scouter.netherdepthsupgrade.entity.ai.FishSwimGoal;
import com.scouter.netherdepthsupgrade.entity.ai.LavaMoveGoal;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class NetherUrchinEntity extends AbstractLavaFish implements IAnimatable, IAnimationTickable {

    private AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    public LavaMoveGoal lavaMoveGoal;


    public NetherUrchinEntity(EntityType<? extends AbstractLavaFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInLava()) {
            this.moveRelative(0.01F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.3D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }
    @Override
    protected void registerGoals() {
        this.lavaMoveGoal = new LavaMoveGoal(this);
        //this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new LavaMoveGoal(this));
        this.lavaMoveGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.BONE_BLOCK_HIT;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.NETHER_URCHIN_BUCKET.get());
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("netherurchin.moving", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<NetherUrchinEntity> controller = new AnimationController<>(this, "controller",0, this::predicate);
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }
}
