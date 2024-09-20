package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.KHWeapons;
import com.knightsheraldry.items.custom.item.Lance;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.ModTags;
import com.knightsheraldry.util.PlayerVelocity;
import com.knightsheraldry.util.StaminaData;
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
    private NbtCompound persistentData;

    @Override
    public NbtCompound knightsheraldry$getPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo ci) {
        if (persistentData != null) {
            nbt.put("knightsheraldry.data", persistentData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("knightsheraldry.data", 10)) {
            persistentData = nbt.getCompound("knightsheraldry.data");
        }
    }

    @Inject(method = "damageShield", at = @At("HEAD"), cancellable = true)
    private void knightsHeraldry$onDamageShield(float amount, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getMainHandStack().isIn(ModTags.Items.KH_WEAPONS)) {
            if (player.getActiveItem().isIn(ModTags.Items.KH_WEAPONS_SHIELD)) {
                if (!player.getWorld().isClient) {
                    player.incrementStat(Stats.USED.getOrCreateStat(player.getActiveItem().getItem()));
                }
                ci.cancel();
            }

            int stamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("stamina_int");
            int staminaCost = 20;

            if (KnightsHeraldry.config().getBlocking()) {
                StaminaData.removeStamina((IEntityDataSaver) player, Math.min(stamina, staminaCost));
            }
        }
    }

    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    public void knightsHeraldry$disableShield(boolean sprinting, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack activeItem = player.getActiveItem();
        World world = player.getWorld();

        float f = 0.25F + (float) EnchantmentHelper.getEfficiency(player) * 0.05F;
        if (sprinting) {
            f += 0.75F;
        }

        if (player.getRandom().nextFloat() < f) {
            if (activeItem.isIn(ModTags.Items.KH_WEAPONS_SHIELD)) {
                player.getItemCooldownManager().set(activeItem.getItem(), 100);
            } else {
                player.getItemCooldownManager().set(Items.SHIELD, 60);
            }
            player.clearActiveItem();
            world.sendEntityStatus(player, (byte) 30);
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        ItemStack weapon = null;

        if (mainHandStack.getItem() instanceof Lance) weapon = mainHandStack;
        else if (offHandStack.getItem() instanceof Lance) weapon = offHandStack;

        if (weapon != null && weapon.getNbt() != null && weapon.getNbt().getBoolean("Charged")
                && player instanceof ServerPlayerEntity serverPlayerEntity) {

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
    public void onAttack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (target.isAttackable()) {
            ItemStack itemStack = player.getMainHandStack();
            boolean isWeapon = itemStack.getItem() instanceof SwordItem ||
                    itemStack.getItem() instanceof AxeItem ||
                    itemStack.getItem() instanceof ShovelItem ||
                    itemStack.getItem() instanceof HoeItem;

            if (isWeapon && !(itemStack.getItem() instanceof KHWeapons) && KnightsHeraldry.config().getVanillaWeaponsDamage0()) {
                float attackDamage = 0.0F;
                float attackStrength = player.getAttackCooldownProgress(0.5F);

                if (attackStrength > 0.9F) {
                    player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                }

                boolean damageApplied = target.damage(player.getDamageSources().playerAttack(player), attackDamage);

                if (damageApplied) {
                    player.addExhaustion(0.1F);
                }
                ci.cancel();
            }
        }
    }
}