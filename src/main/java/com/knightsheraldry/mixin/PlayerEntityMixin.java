package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.items.custom.item.khweapon.Lance;
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

    @Unique
    private static final int STAMINA_COST_ON_SHIELD_DAMAGE = 20;
    @Unique
    private static final float SHIELD_DISABLE_CHANCE_BASE = 0.25F;
    @Unique
    private static final float SHIELD_DISABLE_CHANCE_SPRINT_BONUS = 0.75F;
    @Unique
    private static final int SHIELD_COOLDOWN_TICKS = 100;
    @Unique
    private static final int VANILLA_SHIELD_COOLDOWN_TICKS = 60;

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
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        if (mainHandStack.getItem() instanceof KHWeapon && playerEntity.getActiveItem().isIn(KHTags.WEAPONS_SHIELD.getTag())) {
            if (!playerEntity.getWorld().isClient) {
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(playerEntity.getActiveItem().getItem()));
            }
            ci.cancel();

            if (KnightsHeraldry.getConfig().getBlocking()) {
                int stamina = knightsheraldry$getPersistentData().getInt("stamina_int");
                StaminaData.removeStamina(this, Math.min(stamina, STAMINA_COST_ON_SHIELD_DAMAGE));
            }
        }
    }

    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    public void knightsHeraldry$disableShield(boolean sprinting, CallbackInfo ci) {
        ItemStack activeItem = playerEntity.getActiveItem();
        World world = playerEntity.getWorld();

        float disableChance = SHIELD_DISABLE_CHANCE_BASE + (float) EnchantmentHelper.getEfficiency(playerEntity) * 0.05F;
        if (sprinting) {
            disableChance += SHIELD_DISABLE_CHANCE_SPRINT_BONUS;
        }

        if (playerEntity.getRandom().nextFloat() < disableChance) {
            if (!playerEntity.isCreative()) {
                int cooldownTicks = activeItem.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? SHIELD_COOLDOWN_TICKS : VANILLA_SHIELD_COOLDOWN_TICKS;
                playerEntity.getItemCooldownManager().set(activeItem.getItem(), cooldownTicks);
            }
            playerEntity.clearActiveItem();
            world.sendEntityStatus(playerEntity, (byte) 30);
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void knightsheraldry$onTick(CallbackInfo ci) {
        ItemStack lanceStack = getLanceStack(playerEntity);
        if (lanceStack != null && lanceStack.getNbt() != null && lanceStack.getNbt().getBoolean("kh_charged")
                && playerEntity instanceof ServerPlayerEntity serverPlayer) {

            NbtCompound nbt = knightsheraldry$getPersistentData();
            int nonSprintingTicks = nbt.getInt("nonSprintingTicks");

            BlockPos previousBlockPos = BlockPos.fromLong(nbt.getLong("previousBlockPos"));
            BlockPos currentBlockPos = serverPlayer.getBlockPos();

            boolean staying = currentBlockPos.equals(previousBlockPos);

            float velocity = calculateVelocity(serverPlayer, nonSprintingTicks, staying);

            PlayerVelocity.updatePreviousBlockPos(this, currentBlockPos.asLong());
            PlayerVelocity.updateSpeedHistory(this, velocity);
            PlayerVelocity.updateNonSprintingTicks(this, nonSprintingTicks);
        }
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$onAttack(Entity target, CallbackInfo ci) {
        if (target.isAttackable()) {
            ItemStack itemStack = playerEntity.getMainHandStack();
            if (isVanillaWeapon(itemStack) && KnightsHeraldry.getConfig().getVanillaWeaponsDamage0()) {
                handleVanillaWeaponAttack(target);
                ci.cancel();
            }
        }
    }

    @Unique
    private ItemStack getLanceStack(PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        return mainHandStack.getItem() instanceof Lance ? mainHandStack :
                offHandStack.getItem() instanceof Lance ? offHandStack : null;
    }

    @Unique
    private float calculateVelocity(ServerPlayerEntity player, int nonSprintingTicks, boolean staying) {
        float velocity = player.getMovementSpeed();

        if (player.isSprinting()) {
            velocity *= 1.3f;
            knightsheraldry$getPersistentData().putInt("nonSprintingTicks", 0);
        } else {
            nonSprintingTicks++;
            if (nonSprintingTicks >= 3) {
                if (nonSprintingTicks >= 5 && staying) {
                    velocity *= 0.1f;
                    knightsheraldry$getPersistentData().putInt("nonSprintingTicks", 0);
                } else if (player.isSneaking()) {
                    velocity *= 0.3f;
                    knightsheraldry$getPersistentData().putInt("nonSprintingTicks", 0);
                } else if (player.hasVehicle()) {
                    velocity = getVehicleVelocity(player);
                    knightsheraldry$getPersistentData().putInt("nonSprintingTicks", 0);
                }
            }
        }

        return velocity;
    }

    @Unique
    private float getVehicleVelocity(ServerPlayerEntity player) {
        Entity vehicle = player.getVehicle();
        if (vehicle instanceof MinecartEntity minecart) {
            return (float) minecart.getVelocity().length();
        } else if (vehicle instanceof BoatEntity boat) {
            return (float) boat.getVelocity().length();
        } else if (vehicle instanceof AbstractHorseEntity horse) {
            return (float) horse.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        }
        return player.getMovementSpeed();
    }

    @Unique
    private boolean isVanillaWeapon(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item instanceof SwordItem || item instanceof AxeItem ||
                item instanceof ShovelItem || item instanceof HoeItem;
    }

    @Unique
    private void handleVanillaWeaponAttack(Entity target) {
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
    }
}