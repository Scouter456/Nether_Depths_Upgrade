package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.config.NetherDepthsUpgradeConfig;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.LavaFishingRodItem;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.loot.NDULootTables;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LavaFishingBobberEntity extends FishingHook {
    private static final EntityDataAccessor<Integer> DATA_HOOKED_ENTITY = SynchedEntityData.defineId(LavaFishingBobberEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_BITING = SynchedEntityData.defineId(LavaFishingBobberEntity.class, EntityDataSerializers.BOOLEAN);
    /*
     * This nested holder is initialized only when a fish is actually retrieved.
     * That prevents DeferredHolder#get() from being called during early registry setup.
     */
    private static final class LiveFishTypes {
        private static final Map<Item, EntityType<?>> VALUES = Map.ofEntries(
                Map.entry(NDUItems.SEARING_COD.get(), NDUEntity.SEARING_COD.get()),
                Map.entry(NDUItems.SOULSUCKER.get(), NDUEntity.SOULSUCKER.get()),
                Map.entry(NDUItems.LAVA_PUFFERFISH.get(), NDUEntity.LAVA_PUFFERFISH.get()),
                Map.entry(NDUItems.BONEFISH.get(), NDUEntity.BONEFISH.get()),
                Map.entry(NDUItems.WITHER_BONEFISH.get(), NDUEntity.WITHER_BONEFISH.get()),
                Map.entry(NDUItems.GLOWDINE.get(), NDUEntity.GLOWDINE.get()),
                Map.entry(NDUItems.MAGMACUBEFISH.get(), NDUEntity.MAGMACUBEFISH.get()),
                Map.entry(NDUItems.OBSIDIANFISH.get(), NDUEntity.OBSIDIAN_FISH.get()),
                Map.entry(NDUItems.BLAZEFISH.get(), NDUEntity.BLAZEFISH.get()),
                Map.entry(NDUItems.EYEBALL_FISH.get(), NDUEntity.EYEBALL_FISH.get()),
                Map.entry(NDUItems.FORTRESS_GROUPER.get(), NDUEntity.FORTRESS_GROUPER.get())
        );
    }
    private final RandomSource synchronizedRandom = RandomSource.create();
    private boolean bitingFish;
    private int luck;
    private int lureSpeed;
    private int life;
    private int nibble;
    private int timeUntilLured;
    private int timeUntilHooked;
    private int outOfLavaTime;
    private float fishAngle;
    private boolean openLava = true;
    @Nullable
    private Entity hookedEntity;

    private LavaFishingBobberEntityState currentState = LavaFishingBobberEntityState.FLYING;

    public LavaFishingBobberEntity(EntityType<? extends LavaFishingBobberEntity> entityType, Level level) {
        super(entityType, level);
    }

    public LavaFishingBobberEntity(Player p_37106_, Level level, int luck, int lureSpeed) {
        super(p_37106_, level, luck, lureSpeed);
        this.luck = Math.max(0, luck);
        this.lureSpeed = Math.max(0, lureSpeed);
    }


    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_HOOKED_ENTITY.equals(pKey)) {
            int i = this.getEntityData().get(DATA_HOOKED_ENTITY);
            this.hookedEntity = i > 0 ? this.level().getEntity(i - 1) : null;
        }

        if (DATA_BITING.equals(pKey)) {
            this.bitingFish = this.getEntityData().get(DATA_BITING);
            if (this.bitingFish) {
                this.setDeltaMovement(this.getDeltaMovement().x, (double)(-0.4F * Mth.nextFloat(this.random, 0.6F, 1.0F)), this.getDeltaMovement().z);
            }
        }

        super.onSyncedDataUpdated(pKey);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_HOOKED_ENTITY, 0);
        pBuilder.define(DATA_BITING, false);
    }



    private boolean shouldStopFishing(Player player) {
        ItemStack itemstack = player.getMainHandItem();
        ItemStack itemstack1 = player.getOffhandItem();
        boolean flag = itemstack.getItem() instanceof LavaFishingRodItem;
        boolean flag1 = itemstack1.getItem() instanceof LavaFishingRodItem;
        if (!player.isRemoved() && player.isAlive() && (flag || flag1) && !(this.distanceToSqr(player) > 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    public void tick() {
        this.synchronizedRandom.setSeed(this.getUUID().getLeastSignificantBits() ^ this.level().getGameTime());
        Player player = this.getPlayerOwner();
        if (player == null) {
            this.discard();
        } else if (this.level().isClientSide || !this.shouldStopFishing(player)) {
            if (this.onGround()) {
                ++this.life;
                if (this.life >= 1200) {
                    this.discard();
                    return;
                }
            } else {
                this.life = 0;
            }

            float f = 0.0F;
            BlockPos blockpos = this.blockPosition();
            FluidState fluidstate = this.level().getFluidState(blockpos);
            if (fluidstate.is(FluidTags.LAVA)) {
                f = fluidstate.getHeight(this.level(), blockpos);
            }

            boolean flag = f > 0.0F;
            if (this.currentState ==  LavaFishingBobberEntityState.FLYING) {
                if (this.hookedEntity != null) {
                    this.setDeltaMovement(Vec3.ZERO);
                    this.currentState =  LavaFishingBobberEntityState.HOOKED_IN_ENTITY;
                    return;
                }

                if (flag) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.3D, 0.2D, 0.3D));
                    this.currentState =  LavaFishingBobberEntityState.BOBBING;
                    return;
                }

                this.checkCollision();
            } else {
                if (this.currentState ==  LavaFishingBobberEntityState.HOOKED_IN_ENTITY) {
                    if (this.hookedEntity != null) {
                        if (!this.hookedEntity.isRemoved() && this.hookedEntity.level().dimension() == this.level().dimension()) {
                            this.setPos(this.hookedEntity.getX(), this.hookedEntity.getY(0.8D), this.hookedEntity.getZ());
                        } else {
                            this.setHookedEntity((Entity)null);
                            this.currentState =  LavaFishingBobberEntityState.FLYING;
                        }
                    }

                    return;
                }

                if (this.currentState ==  LavaFishingBobberEntityState.BOBBING) {
                    Vec3 vec3 = this.getDeltaMovement();
                    double d0 = this.getY() + vec3.y - (double)blockpos.getY() - (double)f;
                    if (Math.abs(d0) < 0.01D) {
                        d0 += Math.signum(d0) * 0.1D;
                    }

                    this.setDeltaMovement(vec3.x * 0.9D, vec3.y - d0 * (double)this.random.nextFloat() * 0.2D, vec3.z * 0.9D);
                    if (this.nibble <= 0 && this.timeUntilHooked <= 0) {
                        this.openLava = true;
                    } else {
                        this.openLava = this.openLava && this.outOfLavaTime < 10 && this.calculateOpenLava(blockpos);
                    }

                    if (flag) {
                        this.outOfLavaTime = Math.max(0, this.outOfLavaTime - 1);
                        if (this.bitingFish) {
                            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.1D * (double)this.synchronizedRandom.nextFloat() * (double)this.synchronizedRandom.nextFloat(), 0.0D));
                        }

                        if (!this.level().isClientSide) {
                            this.catchingFish(blockpos);
                        }
                    } else {
                        this.outOfLavaTime = Math.min(10, this.outOfLavaTime + 1);
                    }
                }
            }

            if (!fluidstate.is(FluidTags.LAVA)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            this.updateRotation();
            if (this.currentState == LavaFishingBobberEntityState.FLYING && (this.onGround() || this.horizontalCollision)) {
                this.setDeltaMovement(Vec3.ZERO);
            }

            double d1 = 0.92D;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.92D));
            this.reapplyPosition();
        }
    }

    private void checkCollision() {
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() == HitResult.Type.MISS || !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) this.onHit(hitresult);
    }

    protected boolean canHitEntity(Entity p_37135_) {
        return super.canHitEntity(p_37135_) || p_37135_.isAlive() && p_37135_ instanceof ItemEntity;
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            this.setHookedEntity(pResult.getEntity());
        }

    }

    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.setDeltaMovement(this.getDeltaMovement().normalize().scale(pResult.distanceTo(this)));
    }

    private boolean calculateOpenLava(BlockPos p_37159_) {
        FishLavaType fishinghook$fishlavatype = FishLavaType.INVALID;

        for(int i = -1; i <= 2; ++i) {
            FishLavaType fishinghook$fishlavatype1 = this.getOpenLavaTypeForArea(p_37159_.offset(-2, i, -2), p_37159_.offset(2, i, 2));
            switch(fishinghook$fishlavatype1) {
                case INVALID:
                    return false;
                case ABOVE_LAVA:
                    if (fishinghook$fishlavatype == FishLavaType.INVALID) {
                        return false;
                    }
                    break;
                case INSIDE_LAVA:
                    if (fishinghook$fishlavatype == FishLavaType.ABOVE_LAVA) {
                        return false;
                    }
            }

            fishinghook$fishlavatype = fishinghook$fishlavatype1;
        }
        return true;
    }

    private FishLavaType getOpenLavaTypeForArea(BlockPos p_37148_, BlockPos p_37149_) {
        return BlockPos.betweenClosedStream(p_37148_, p_37149_).map(this::getOpenLavaTypeForBlock).reduce((p_37139_, p_37140_) -> {
            return p_37139_ == p_37140_ ? p_37139_ : FishLavaType.INVALID;
        }).orElse(FishLavaType.INVALID);
    }

    private FishLavaType getOpenLavaTypeForBlock(BlockPos p_37164_) {
        BlockState blockstate = this.level().getBlockState(p_37164_);
        if (!blockstate.isAir()) {
            FluidState fluidstate = blockstate.getFluidState();
            return fluidstate.is(FluidTags.LAVA) && fluidstate.isSource() && blockstate.getCollisionShape(this.level(), p_37164_).isEmpty() ? FishLavaType.INSIDE_LAVA : FishLavaType.INVALID;
        } else {
            return FishLavaType.ABOVE_LAVA;
        }
    }

    private void catchingFish(BlockPos p_37146_) {
        ServerLevel serverlevel = (ServerLevel)this.level();
        int i = 1;
        BlockPos blockpos = p_37146_.above();
        if (this.random.nextFloat() < 0.25F && this.level().isRainingAt(blockpos)) {
            ++i;
        }

        if (this.random.nextFloat() < 0.5F && !this.level().canSeeSky(blockpos)) {
            --i;
        }

        if (this.nibble > 0) {
            --this.nibble;
            if (this.nibble <= 0) {
                this.timeUntilLured = 0;
                this.timeUntilHooked = 0;
                this.getEntityData().set(DATA_BITING, false);
            }
        } else if (this.timeUntilHooked > 0) {
            this.timeUntilHooked -= i;
            if (this.timeUntilHooked > 0) {
                this.fishAngle += (float)(this.random.nextGaussian() * 4.0D);
                float f = this.fishAngle * ((float)Math.PI / 180F);
                float f1 = Mth.sin(f);
                float f2 = Mth.cos(f);
                double d0 = this.getX() + (double)(f1 * (float)this.timeUntilHooked * 0.1F);
                double d1 = (double)((float)Mth.floor(this.getY()) + 1.0F);
                double d2 = this.getZ() + (double)(f2 * (float)this.timeUntilHooked * 0.1F);
                BlockState blockstate = serverlevel.getBlockState(BlockPos.containing(d0, d1 - 1.0D, d2));
                if (blockstate.is(Blocks.LAVA)) {
                    if (this.random.nextFloat() < 0.15F) {
                        serverlevel.sendParticles(ParticleTypes.CRIT, d0, d1 - (double)0.1F, d2, 1, (double)f1, 0.1D, (double)f2, 0.0D);
                    }

                    float f3 = f1 * 0.04F;
                    float f4 = f2 * 0.04F;
                    serverlevel.sendParticles(ParticleTypes.SMOKE, d0, d1, d2, 0, (double)f4, 0.01D, (double)(-f3), 1.0D);
                    serverlevel.sendParticles(ParticleTypes.SMOKE, d0, d1, d2, 0, (double)(-f4), 0.01D, (double)f3, 1.0D);
                }
            } else {
                this.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                double d3 = this.getY() + 0.5D;
                serverlevel.sendParticles(ParticleTypes.SMOKE, this.getX(), d3, this.getZ(), (int)(1.0F + this.getBbWidth() * 20.0F), (double)this.getBbWidth(), 0.0D, (double)this.getBbWidth(), (double)0.2F);
                this.nibble = Mth.nextInt(this.random, 20, 40);
                this.getEntityData().set(DATA_BITING, true);
            }
        } else if (this.timeUntilLured > 0) {
            this.timeUntilLured -= i;
            float f5 = 0.15F;
            if (this.timeUntilLured < 20) {
                f5 += (float)(20 - this.timeUntilLured) * 0.05F;
            } else if (this.timeUntilLured < 40) {
                f5 += (float)(40 - this.timeUntilLured) * 0.02F;
            } else if (this.timeUntilLured < 60) {
                f5 += (float)(60 - this.timeUntilLured) * 0.01F;
            }

            if (this.random.nextFloat() < f5) {
                float f6 = Mth.nextFloat(this.random, 0.0F, 360.0F) * ((float)Math.PI / 180F);
                float f7 = Mth.nextFloat(this.random, 25.0F, 60.0F);
                double d4 = this.getX() + (double)(Mth.sin(f6) * f7) * 0.1D;
                double d5 = (double)((float)Mth.floor(this.getY()) + 1.0F);
                double d6 = this.getZ() + (double)(Mth.cos(f6) * f7) * 0.1D;
                BlockState blockstate1 = serverlevel.getBlockState(BlockPos.containing(d4, d5 - 1.0D, d6));
                if (blockstate1.is(Blocks.LAVA)) {
                    serverlevel.sendParticles(ParticleTypes.LANDING_LAVA, d4, d5, d6, 2 + this.random.nextInt(2), (double)0.1F, 0.0D, (double)0.1F, 0.0D);
                }
            }

            if (this.timeUntilLured <= 0) {
                this.fishAngle = Mth.nextFloat(this.random, 0.0F, 360.0F);
                this.timeUntilHooked = Mth.nextInt(this.random, 20, 80);
            }
        } else {
            this.timeUntilLured = Mth.nextInt(this.random, 100, 600);
            int lureReduction = this.lureSpeed;
            this.timeUntilLured = Math.max(1, timeUntilLured - lureReduction);
        }

    }

    public int retrieve(ItemStack rod) {
        Player player = this.getPlayerOwner();

        if (this.level().isClientSide || player == null || this.shouldStopFishing(player)) {
            return 0;
        }

        int rodDamage = 0;
        ItemFishedEvent fishingEvent = null;

        if (this.hookedEntity != null) {
            this.pullEntity(this.hookedEntity);

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger(serverPlayer, rod, this, Collections.emptyList());
            }

            this.level().broadcastEntityEvent(this, (byte) 31);
            rodDamage = this.hookedEntity instanceof ItemEntity ? 3 : 5;
        } else if (this.nibble > 0) {
            ServerLevel serverLevel = (ServerLevel) this.level();

            LootParams lootParams = new LootParams.Builder(serverLevel)
                    .withParameter(LootContextParams.ORIGIN, this.position())
                    .withParameter(LootContextParams.TOOL, rod)
                    .withParameter(LootContextParams.THIS_ENTITY, this)
                    .withParameter(LootContextParams.ATTACKING_ENTITY, player)
                    .withLuck(this.luck + player.getLuck())
                    .create(LootContextParamSets.FISHING);

            LootTable lootTable = this.getFishingLootTable(serverLevel);
            List<ItemStack> generatedDrops = lootTable.getRandomItems(lootParams);

            fishingEvent = new ItemFishedEvent(generatedDrops, this.onGround() ? 2 : 1, this);

            NeoForge.EVENT_BUS.post(fishingEvent);

            if (fishingEvent.isCanceled()) {
                this.discard();
                return fishingEvent.getRodDamage();
            }

            List<ItemStack> drops = fishingEvent.getDrops();

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger(
                        serverPlayer,
                        rod,
                        this,
                        drops
                );
            }

            for (ItemStack stack : drops) {
                this.spawnFishingDrop(player, stack);
            }

            if (!drops.isEmpty()) {
                serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, this.random.nextInt(6) + 1));
            }

            int caughtFish = drops.stream().filter(stack -> stack.is(ItemTags.FISHES)).mapToInt(ItemStack::getCount).sum();

            if (caughtFish > 0) {
                player.awardStat(Stats.FISH_CAUGHT, caughtFish);
            }

            rodDamage = fishingEvent.getRodDamage();
        }

        if (fishingEvent == null && this.onGround()) {
            rodDamage = 2;
        }

        this.discard();
        return rodDamage;
    }

    private LootTable getFishingLootTable(ServerLevel level) {
        if (!level.getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
            return level.getServer()
                    .reloadableRegistries()
                    .getLootTable(NDULootTables.FAILED_FISHING);
        }

        if (level.dimension().equals(Level.NETHER)) {
            return level.getServer()
                    .reloadableRegistries()
                    .getLootTable(NDULootTables.NETHER_FISHING);
        }

        return level.getServer()
                .reloadableRegistries()
                .getLootTable(NDULootTables.LAVA_FISHING);
    }

    private void spawnFishingDrop(Player player, ItemStack stack) {
        EntityType<?> fishType = NetherDepthsUpgradeConfig.FISH_ENTITIES.get() ? LiveFishTypes.VALUES.get(stack.getItem()) : null;
        if (fishType == null) {
            this.spawnItemDrop(player, stack.copy());
            return;
        }

        /*
         * A stack of three fish must produce three entities, rather than silently
         * discarding two of them.
         */
        for (int count = 0; count < stack.getCount(); count++) {
            Entity fish = fishType.create(this.level());

            if (fish == null) {
                this.spawnItemDrop(player, stack.copyWithCount(1));
                continue;
            }

            fish.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());

            fish.setDeltaMovement(this.getRetrievalVelocity(player, this.getY(), 0.12D, 0.14D));

            this.level().addFreshEntity(fish);
        }
    }

    private void spawnItemDrop(Player player, ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(this.level(), this.getX(), this.getY() + 1.0D, this.getZ(), stack) {
            @Override
            public boolean displayFireAnimation() {
                return false;
            }

            @Override
            public void lavaHurt() {
                // Fishing loot must survive while travelling out of the lava.
            }
        };

        itemEntity.setDeltaMovement(this.getRetrievalVelocity(player, this.getY() + 1.0D, 0.1D, 0.08D)
        );

        this.level().addFreshEntity(itemEntity);
    }

    private Vec3 getRetrievalVelocity(Player player, double sourceY, double horizontalScale, double liftScale
    ) {
        double xDifference = player.getX() - this.getX();
        double yDifference = player.getY() - sourceY;
        double zDifference = player.getZ() - this.getZ();

        double distanceSquared = xDifference * xDifference + yDifference * yDifference + zDifference * zDifference;

        return new Vec3(xDifference * horizontalScale, yDifference * horizontalScale + Math.sqrt(Math.sqrt(distanceSquared)) * liftScale, zDifference * horizontalScale);
    }


    public void onClientRemoval() {
        this.updateOwnerInfo((LavaFishingBobberEntity)null);
    }

    public void setOwner(@Nullable Entity pEntity) {
        super.setOwner(pEntity);
        this.updateOwnerInfo(this);
    }

    /**
     * Checks if the entity is in range to render.
     */
    @Override
    public boolean shouldRenderAtSqrDistance(double distanceSqr) {
        return distanceSqr < 64.0D * 64.0D;
    }

    public void remove(RemovalReason pReason) {
        this.updateOwnerInfo((LavaFishingBobberEntity)null);
        super.remove(pReason);
    }

    private void updateOwnerInfo(@Nullable LavaFishingBobberEntity p_150148_) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            player.fishing = p_150148_;
        }

    }

    private void setHookedEntity(@Nullable Entity entity) {
        this.hookedEntity = entity;
        this.getEntityData().set(DATA_HOOKED_ENTITY, entity == null ? 0 : entity.getId() + 1);
    }

    @Nonnull
    @Override
    public EntityType<?> getType() {
        return NDUEntity.LAVA_BOBBER.get();
    }


    protected void pullEntity(Entity entityPulled) {
        Entity entity = this.getOwner();
        if (entity != null) {
            Vec3 vec3 = (new Vec3(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ())).scale(0.1D);
            entityPulled.setDeltaMovement(entityPulled.getDeltaMovement().add(vec3));
        }
    }

    private enum LavaFishingBobberEntityState {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;
    }

    private enum FishLavaType {
        ABOVE_LAVA,
        INSIDE_LAVA,
        INVALID;
    }
}
