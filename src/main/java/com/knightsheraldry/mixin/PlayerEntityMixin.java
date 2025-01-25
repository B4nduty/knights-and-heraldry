package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.items.custom.item.Lance;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.util.playerdata.PlayerVelocity;
import com.knightsheraldry.util.playerdata.StaminaData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IEntityDataSaver {
    @Unique
    private final PlayerEntity playerEntity = (PlayerEntity) (Object) this;

    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound knightsheraldry$getPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void knightsheraldry$injectWriteMethod(NbtCompound nbt, CallbackInfo ci) {
        if (persistentData != null) {
            nbt.put("knightsheraldry.data", persistentData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void knightsheraldry$injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("knightsheraldry.data", 10)) {
            persistentData = nbt.getCompound("knightsheraldry.data");
        }
    }

    @Inject(method = "damageShield", at = @At("HEAD"), cancellable = true)
    private void knightsHeraldry$onDamageShield(float amount, CallbackInfo ci) {
        if (playerEntity.getMainHandStack().getItem() instanceof KHWeapon) {
            if (playerEntity.getActiveItem().isIn(KHTags.WEAPONS_SHIELD.getTag())) {
                if (!playerEntity.getWorld().isClient) {
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(playerEntity.getActiveItem().getItem()));
                }
                ci.cancel();
            }

            int stamina = ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getInt("stamina_int");
            int staminaCost = 20;

            if (KnightsHeraldry.config().getBlocking()) {
                StaminaData.removeStamina((IEntityDataSaver) playerEntity, Math.min(stamina, staminaCost));
            }
        }
    }

    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    public void knightsHeraldry$disableShield(boolean sprinting, CallbackInfo ci) {
        ItemStack activeItem = playerEntity.getActiveItem();
        World world = playerEntity.getWorld();

        float f = 0.25F + (float) EnchantmentHelper.getEfficiency(playerEntity) * 0.05F;
        if (sprinting) {
            f += 0.75F;
        }

        if (playerEntity.getRandom().nextFloat() < f) {
            if (!playerEntity.isCreative()) {
                if (activeItem.isIn(KHTags.WEAPONS_SHIELD.getTag())) {
                    playerEntity.getItemCooldownManager().set(activeItem.getItem(), 100);
                } else {
                    playerEntity.getItemCooldownManager().set(Items.SHIELD, 60);
                }
            }
            playerEntity.clearActiveItem();
            world.sendEntityStatus(playerEntity, (byte) 30);
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void knightsheraldry$onTick(CallbackInfo ci) {
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        ItemStack offHandStack = playerEntity.getOffHandStack();
        ItemStack weapon = null;

        if (mainHandStack.getItem() instanceof Lance) weapon = mainHandStack;
        else if (offHandStack.getItem() instanceof Lance) weapon = offHandStack;

        if (weapon != null && weapon.getNbt() != null && weapon.getNbt().getBoolean("Charged")
                && playerEntity instanceof ServerPlayerEntity serverPlayerEntity) {

            NbtCompound nbt = ((IEntityDataSaver) serverPlayerEntity).knightsheraldry$getPersistentData();
            int nonSprintingTicks = nbt.getInt("nonSprintingTicks");

            BlockPos previousBlockPos = BlockPos.fromLong(nbt.getLong("previousBlockPos"));
            BlockPos currentBlockPos = serverPlayerEntity.getBlockPos();

            boolean staying = currentBlockPos.equals(previousBlockPos);

            float velocity = serverPlayerEntity.getMovementSpeed();

            if (serverPlayerEntity.isSprinting()) {
                velocity *= 1.3f;
                nonSprintingTicks = 0;
            } else {
                nonSprintingTicks++;
                if (nonSprintingTicks >= 3) {
                    if (nonSprintingTicks >= 5 && staying) {
                        velocity *= 0.1f;
                        nonSprintingTicks = 0;
                    } else if (serverPlayerEntity.isSneaking()) {
                        velocity *= 0.3f;
                        nonSprintingTicks = 0;
                    } else if (serverPlayerEntity.hasVehicle()) {
                        if (serverPlayerEntity.getVehicle() instanceof MinecartEntity minecartEntity)
                            velocity = (float) minecartEntity.getVelocity().length();
                        else if (serverPlayerEntity.getVehicle() instanceof BoatEntity boatEntity)
                            velocity = (float) boatEntity.getVelocity().length();
                        else if (serverPlayerEntity.getVehicle() instanceof AbstractHorseEntity abstractHorseEntity)
                            velocity = (float) abstractHorseEntity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    }
                    PlayerVelocity.updatePreviousBlockPos((IEntityDataSaver) serverPlayerEntity, currentBlockPos.asLong());
                } else velocity = nbt.getFloat("speedHistory");
            }

            PlayerVelocity.updateSpeedHistory((IEntityDataSaver) serverPlayerEntity, velocity);
            PlayerVelocity.updateNonSprintingTicks((IEntityDataSaver) serverPlayerEntity, nonSprintingTicks);
        }
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$onAttack(Entity target, CallbackInfo ci) {
        if (target.isAttackable()) {
            ItemStack itemStack = playerEntity.getMainHandStack();
            boolean isWeapon = itemStack.getItem() instanceof SwordItem ||
                    itemStack.getItem() instanceof AxeItem ||
                    itemStack.getItem() instanceof ShovelItem ||
                    itemStack.getItem() instanceof HoeItem;

            if (isWeapon && !(itemStack.getItem() instanceof KHWeapon) && KnightsHeraldry.config().getVanillaWeaponsDamage0()) {
                float attackDamage = 0.0F;
                float attackStrength = playerEntity.getAttackCooldownProgress(0.5F);

                if (attackStrength > 0.9F) {
                    playerEntity.getWorld().playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                            SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, playerEntity.getSoundCategory(), 1.0F, 1.0F);
                }

                boolean damageApplied = target.damage(playerEntity.getDamageSources().playerAttack(playerEntity), attackDamage);

                if (damageApplied) {
                    playerEntity.addExhaustion(0.1F);
                }
                ci.cancel();
            }
        }
    }
}