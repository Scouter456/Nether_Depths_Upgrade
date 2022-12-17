package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.LavaFishingRodItem;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.loot.NDULootTables;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class LavaFishingBobberEntity extends FishingHook {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    private static final EntityDataAccessor<Integer> DATA_HOOKED_ENTITY = SynchedEntityData.defineId(LavaFishingBobberEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_BITING = SynchedEntityData.defineId(LavaFishingBobberEntity.class, EntityDataSerializers.BOOLEAN);

    private final Random syncronizedRandom = new Random();
    private boolean bitingFish;
    private final int luck;
    private final int lureSpeed;
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


    private LavaFishingBobberEntity(EntityType<? extends LavaFishingBobberEntity> entityType, Level level, int luck, int lureSpeed) {
        super(entityType, level);
        this.luck = Math.max(0, luck);
        this.lureSpeed = Math.max(0, lureSpeed);
    }

    public LavaFishingBobberEntity(EntityType<? extends LavaFishingBobberEntity> entityType, Level level) {
        this((EntityType)entityType, level, 0, 0);
    }

    public LavaFishingBobberEntity(Player player, Level level, int luck, int lureSpeed) {
        this(NDUEntity.LAVA_BOBBER, level, luck, lureSpeed);
        this.setOwner(player);
        float f = player.getXRot();
        float g = player.getYRot();
        float h = Mth.cos(-g * 0.017453292F - 3.1415927F);
        float k = Mth.sin(-g * 0.017453292F - 3.1415927F);
        float l = -Mth.cos(-f * 0.017453292F);
        float m = Mth.sin(-f * 0.017453292F);
        double d = player.getX() - (double)k * 0.3;
        double e = player.getEyeY();
        double n = player.getZ() - (double)h * 0.3;
        this.moveTo(d, e, n, g, f);
        Vec3 vec3 = new Vec3((double)(-k), (double)Mth.clamp(-(m / l), -5.0F, 5.0F), (double)(-h));
        double o = vec3.length();
        vec3 = vec3.multiply(0.6 / o + this.random.triangle(0.5, 0.0103365), 0.6 / o + this.random.triangle(0.5, 0.0103365), 0.6 / o + this.random.triangle(0.5, 0.0103365));
        this.setDeltaMovement(vec3);
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 57.2957763671875));
        this.setXRot((float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * 57.2957763671875));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }




    @Nonnull
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        Entity entity = this.getOwner();
        return new ClientboundAddEntityPacket(this, entity == null ? this.getId() : entity.getId());
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_HOOKED_ENTITY, 0);
        this.getEntityData().define(DATA_BITING, false);
    }


    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_HOOKED_ENTITY.equals(pKey)) {
            int i = this.getEntityData().get(DATA_HOOKED_ENTITY);
            this.hookedEntity = i > 0 ? this.level.getEntity(i - 1) : null;
        }

        if (DATA_BITING.equals(pKey)) {
            this.bitingFish = this.getEntityData().get(DATA_BITING);
            if (this.bitingFish) {
                this.setDeltaMovement(this.getDeltaMovement().x, (double)(-0.4F * Mth.nextFloat(this.random, 0.6F, 1.0F)), this.getDeltaMovement().z);
            }
        }

        super.onSyncedDataUpdated(pKey);
    }

    private boolean shouldStopFishing(Player player) {
        ItemStack itemstack = player.getMainHandItem();
        ItemStack itemstack1 = player.getOffhandItem();
        boolean flag = itemstack.getItem() instanceof LavaFishingRodItem;
        boolean flag1 = itemstack1.getItem() instanceof LavaFishingRodItem;
        if (!player.isRemoved() && player.isAlive() && (flag || flag1) && (this.distanceToSqr(player) <= 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    public void tick() {
        this.syncronizedRandom.setSeed(this.getUUID().getLeastSignificantBits() ^ this.level.getGameTime());
        Player player = this.getPlayerOwner();
        if (player == null) {
            this.discard();
        } else if (this.level.isClientSide || !this.shouldStopFishing(player)) {
            if (this.onGround) {
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
            FluidState fluidstate = this.level.getFluidState(blockpos);
            if (fluidstate.is(FluidTags.LAVA)) {
                f = fluidstate.getHeight(this.level, blockpos);
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
                        if (!this.hookedEntity.isRemoved() && this.hookedEntity.level.dimension() == this.level.dimension()) {
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
                            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.1D * (double)this.syncronizedRandom.nextFloat() * (double)this.syncronizedRandom.nextFloat(), 0.0D));
                        }

                        if (!this.level.isClientSide) {
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
            if (this.currentState == LavaFishingBobberEntityState.FLYING && (this.onGround || this.horizontalCollision)) {
                this.setDeltaMovement(Vec3.ZERO);
            }

            double d1 = 0.92D;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.92D));
            this.reapplyPosition();
        }
    }

    private void checkCollision() {
        HitResult hitResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        this.onHit(hitResult);
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
        BlockState blockstate = this.level.getBlockState(p_37164_);
        if (!blockstate.isAir()) {
            FluidState fluidstate = blockstate.getFluidState();
            return fluidstate.is(FluidTags.LAVA) && fluidstate.isSource() && blockstate.getCollisionShape(this.level, p_37164_).isEmpty() ? FishLavaType.INSIDE_LAVA : FishLavaType.INVALID;
        } else {
            return FishLavaType.ABOVE_LAVA;
        }
    }

    private void catchingFish(BlockPos p_37146_) {
        ServerLevel serverlevel = (ServerLevel)this.level;
        int i = 1;
        BlockPos blockpos = p_37146_.above();
        if (this.random.nextFloat() < 0.25F && this.level.isRainingAt(blockpos)) {
            ++i;
        }

        if (this.random.nextFloat() < 0.5F && !this.level.canSeeSky(blockpos)) {
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
                BlockState blockstate = serverlevel.getBlockState(new BlockPos(d0, d1 - 1.0D, d2));
                if (serverlevel.getBlockState(new BlockPos((int)d0, (int)d1 - 1, (int)d2)).getMaterial() == net.minecraft.world.level.material.Material.LAVA) {
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
                BlockState blockstate1 = serverlevel.getBlockState(new BlockPos(d4, d5 - 1.0D, d6));
                if (serverlevel.getBlockState(new BlockPos(d4, d5 - 1.0D, d6)).getMaterial() == net.minecraft.world.level.material.Material.LAVA) {
                    serverlevel.sendParticles(ParticleTypes.LANDING_LAVA, d4, d5, d6, 2 + this.random.nextInt(2), (double)0.1F, 0.0D, (double)0.1F, 0.0D);
                }
            }

            if (this.timeUntilLured <= 0) {
                this.fishAngle = Mth.nextFloat(this.random, 0.0F, 360.0F);
                this.timeUntilHooked = Mth.nextInt(this.random, 20, 80);
            }
        } else {
            this.timeUntilLured = Mth.nextInt(this.random, 100, 600);
            this.timeUntilLured -= this.lureSpeed * 20 * 5;
        }

    }

    public int retrieve(ItemStack p_37157_) {
        Player player = this.getPlayerOwner();
        if (!this.level.isClientSide && player != null && !this.shouldStopFishing(player)) {
            int i = 0;
            if (this.hookedEntity!= null) {
                this.pullEntity(this.hookedEntity);
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)player, p_37157_, this, Collections.emptyList());
                this.level.broadcastEntityEvent(this, (byte)31);
                i = this.hookedEntity instanceof ItemEntity ? 3 : 5;
            } else if (this.nibble > 0) {
                LootContext.Builder builder = (new LootContext.Builder((ServerLevel)this.level)).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, p_37157_).withParameter(LootContextParams.THIS_ENTITY, this).withRandom(this.random).withLuck((float)this.luck + player.getLuck());
                LootTable loottable = null;
                double dd = (float) Math.floor(this.getBoundingBox().minY) + 1.0F;
                BlockState blockstate = this.level
                        .getBlockState(new BlockPos(this.position().x, dd - 1.0D, this.position().z));
                if(blockstate.is(Blocks.LAVA)) {
                    if (this.level.dimension() == Level.NETHER) {
                        loottable = Objects.requireNonNull(this.level.getServer()).getLootTables().get(NDULootTables.NETHER_FISHING);
                    } else {
                        loottable = Objects.requireNonNull(this.level.getServer()).getLootTables().get(NDULootTables.LAVA_FISHING);
                    }

                }else {
                    loottable = Objects.requireNonNull(this.level.getServer()).getLootTables().get(NDULootTables.FAILED_FISHING);
                }
                if(loottable == null){
                    this.discard();
                    return 0;
                    //return event.getRodDamage();
                }
                List<ItemStack> list = loottable.getRandomItems(builder.create(LootContextParamSets.FISHING));
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)player, p_37157_, this, list);
                Iterator var7 = list.iterator();
                for (ItemStack stack : list) {
                    Entity entity = null;
                    if (stack.getItem() == NDUItems.SEARING_COD) {
                        entity = NDUEntity.SEARING_COD.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.SOULSUCKER) {
                        entity = NDUEntity.SOULSUCKER.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.LAVA_PUFFERFISH) {
                        entity = NDUEntity.LAVA_PUFFERFISH.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.BONEFISH) {
                        entity = NDUEntity.BONEFISH.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.WITHER_BONEFISH) {
                        entity = NDUEntity.WITHER_BONEFISH.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.GLOWDINE) {
                        entity = NDUEntity.GLOWDINE.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.MAGMACUBEFISH) {
                        entity = NDUEntity.MAGMACUBEFISH.create(this.level);
                    }
                    if (stack.getItem() == NDUItems.OBSIDIANFISH) {
                        entity = NDUEntity.OBSIDIAN_FISH.create(this.level);
                    }

                    if (entity == null) {
                        ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY() + 1, this.getZ(), stack);
                        double d0 = player.position().x() - this.position().x();
                        double d1 = player.position().y() - (this.position().y() + 1);
                        double d2 = player.position().z() - this.position().z();
                        double d3 = 0.1D;
                        itementity.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
                        this.level.addFreshEntity(itementity);
                        player.level.addFreshEntity(new ExperienceOrb(player.level, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, player.level.random.nextInt(6) + 1));
                        //event.damageRodBy(event.getRodDamage());
                        break;
                    } else{
                        entity.moveTo(this.position().x(), this.position().y(), this.position().z(), this.xRotO, this.yRotO);
                        double dX = player.position().x() - this.position().x();
                        double dY = player.position().y() - this.position().y();
                        double dZ = player.position().z() - this.position().z();
                        double mult = 0.12;
                        entity.setDeltaMovement(dX * mult, dY * mult + Math.sqrt(Math.sqrt(dX * dX + dY * dY + dZ * dZ)) * 0.14D, dZ * mult);
                        this.level.addFreshEntity(entity);
                        ItemStack itemStack2 = (ItemStack)var7.next();
                        if (itemStack2.is(ItemTags.FISHES)) {
                            player.awardStat(Stats.FISH_CAUGHT, 1);
                        }
                        break;
                    }

                }


                i = 1;
            }

            if (this.onGround) {
                i = 2;
            }

            this.discard();
            return i;
        } else {
            return 0;
        }
    }

    public void onClientRemoval() {
        this.updateOwnerInfo((LavaFishingBobberEntity)null);
    }

    public void setOwner(@Nullable Entity pEntity) {
        super.setOwner(pEntity);
        this.updateOwnerInfo(this);
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
        return NDUEntity.LAVA_BOBBER;
    }


    protected void pullEntity(Entity entityPulled) {
        Entity entity = this.getOwner();
        if (entity != null) {
            Vec3 vec3 = (new Vec3(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ())).scale(0.1D);
            entityPulled.setDeltaMovement(entityPulled.getDeltaMovement().add(vec3));
        }
    }
/*
    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            buffer.writeUUID(player.getUUID());
        }
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {

    }
*/

    static enum LavaFishingBobberEntityState {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;
    }

    static enum FishLavaType {
        ABOVE_LAVA,
        INSIDE_LAVA,
        INVALID;
    }
}
