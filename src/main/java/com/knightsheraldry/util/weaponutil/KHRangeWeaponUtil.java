package com.knightsheraldry.util.weaponutil;

import com.knightsheraldry.entity.custom.KHArrowEntity;
import com.knightsheraldry.entity.custom.KHBulletEntity;
import com.knightsheraldry.items.custom.item.KHArrow;
import com.knightsheraldry.items.custom.item.KHRangeWeapon;
import com.knightsheraldry.particle.ModParticles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class KHRangeWeaponUtil {
    private static final Random random = new Random();

    private KHRangeWeaponUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static TypedActionResult<ItemStack> handleCrossbowUse(World world, PlayerEntity user, Hand hand, KHRangeWeapon khRangeWeapon, ItemStack itemStack) {
        if (world == null || user == null || khRangeWeapon == null || itemStack == null) {
            return TypedActionResult.fail(ItemStack.EMPTY);
        }

        Optional<ItemStack> arrowStackOpt = getArrowFromInventory(user);
        if (arrowStackOpt.isPresent() && arrowStackOpt.get().getItem() instanceof KHArrow khArrow) {
            KHArrowEntity arrowEntity = (KHArrowEntity) khArrow.createArrowEntity(user, world);
            WeaponState weaponState = getWeaponState(itemStack);
            NbtCompound nbt = itemStack.getOrCreateNbt();
            Projectiles projectiles = Projectiles.fromNbt(nbt, arrowEntity);

            if (weaponState.isCharged()) {
                setWeaponState(itemStack, new WeaponState(weaponState.isReloading(), false, true));
                projectiles.unloadProjectile();
                shootArrow(world, itemStack, khRangeWeapon, user, khArrow.getDefaultStack(), 1f);
                return TypedActionResult.consume(itemStack);
            } else if (projectiles.getArrowCount() < 1) {
                setWeaponState(itemStack, new WeaponState(true, false, false));
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
        return TypedActionResult.fail(itemStack);
    }

    public static void shootArrow(World world, ItemStack stack, KHRangeWeapon khRangeWeapon, PlayerEntity player, ItemStack arrowStack, float pullProgress) {
        KHArrowEntity arrowEntity = (KHArrowEntity) ((KHArrow) arrowStack.getItem()).createArrowEntity(player, world);
        arrowEntity.setDamageAmount(khRangeWeapon.baseDamage());
        arrowEntity.setDamageType(khRangeWeapon.getDamageType());

        arrowEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * khRangeWeapon.speed(), 1.0F);

        if (player.isCreative()) {
            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        } else {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            player.getInventory().removeStack(getArrowSlot(player), 1);
        }

        world.spawnEntity(arrowEntity);
        int soundEventsLength = khRangeWeapon.soundEvents().length;
        SoundEvent selectedSound = soundEventsLength > 0 ? khRangeWeapon.soundEvents()[random.nextInt(soundEventsLength)] : null;
        if (selectedSound != null) world.playSoundFromEntity(null, arrowEntity, selectedSound, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    public static void shootBullet(World world, ItemStack stack, KHRangeWeapon khRangeWeapon, PlayerEntity player) {
        KHBulletEntity bulletEntity = new KHBulletEntity(player, world);
        bulletEntity.setDamageAmount(khRangeWeapon.baseDamage());
        bulletEntity.setDamageType(khRangeWeapon.getDamageType());

        bulletEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, khRangeWeapon.speed(), 1.0F);

        world.spawnEntity(bulletEntity);
        int soundEventsLength = khRangeWeapon.soundEvents().length;
        SoundEvent selectedSound = soundEventsLength > 0 ? khRangeWeapon.soundEvents()[random.nextInt(soundEventsLength)] : null;
        if (selectedSound != null) world.playSound(null, player.getBlockPos(), selectedSound,
                SoundCategory.PLAYERS, 1f, 1f);

        if (!player.isCreative()) {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }

        if (world instanceof ServerWorld serverWorld) {
            spawnSmokeTrail(serverWorld, player, player.getActiveHand());
            spawnFlashTrail(serverWorld, player, player.getActiveHand());
        }
    }

    private static void spawnSmokeTrail(ServerWorld world, PlayerEntity player, Hand hand) {
        Vec3d handPos = getHandPosition(player, hand);
        Vec3d lookDir = player.getRotationVec(1.0F);

        List<Vec3d> trailPositions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            trailPositions.add(handPos.add(lookDir.multiply(i)));
        }

        for (Vec3d pos : trailPositions) {
            world.spawnParticles(ModParticles.MUZZLES_SMOKE_PARTICLE, pos.x, pos.y, pos.z, 100,
                    0.2, 0.1, 0.2, 0.0005f);
        }
    }

    private static void spawnFlashTrail(ServerWorld world, PlayerEntity player, Hand hand) {
        Vec3d handPos = getHandPosition(player, hand);
        Vec3d lookDir = player.getRotationVec(1.0F);

        List<Vec3d> trailPositions = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            trailPositions.add(handPos.add(lookDir.multiply(i)));
        }

        for (Vec3d pos : trailPositions) {
            world.spawnParticles(ModParticles.MUZZLES_FLASH_PARTICLE, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0.1);
        }
    }

    private static Vec3d getHandPosition(PlayerEntity player, Hand hand) {
        boolean isMainHand = hand == Hand.MAIN_HAND;

        double xOffset = isMainHand ? 0.1 : -0.1;
        double yOffset = 1.5;
        double zOffset = 1.5;

        Vec3d basePos = player.getPos().add(0, yOffset, 0);
        Vec3d sideOffset = player.getRotationVec(1.0F).crossProduct(new Vec3d(0, 1, 0)).multiply(xOffset);

        return basePos.add(sideOffset).add(player.getRotationVec(1.0F).multiply(zOffset));
    }

    public static void loadAndPlayCrossbowSound(World world, ItemStack stack, PlayerEntity player, ItemStack arrowStack) {
        KHArrowEntity arrowEntity = (KHArrowEntity) ((KHArrow) arrowStack.getItem()).createArrowEntity(player, world);
        Projectiles.fromNbt(stack.getOrCreateNbt(), arrowEntity).loadProjectile();
        setWeaponState(stack, new WeaponState(false, true, getWeaponState(stack).isShooting()));

        SoundCategory soundCategory = player instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ITEM_CROSSBOW_LOADING_END, soundCategory, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        if (!player.getAbilities().creativeMode) {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }
    }

    public static Optional<ItemStack> getArrowFromInventory(PlayerEntity player) {
        return player.getInventory().main.stream()
                .filter(stack -> stack.getItem() instanceof KHArrow)
                .findFirst();
    }

    private static int getArrowSlot(PlayerEntity player) {
        return player.getInventory().main.stream()
                .filter(stack -> stack.getItem() instanceof KHArrow)
                .map(player.getInventory().main::indexOf)
                .findFirst().orElse(-1);
    }

    public static float getBowPullProgress(int useTicks) {
        float progress = useTicks / 20.0F;
        return Math.min((progress * progress + progress * 2.0F) / 3.0F, 1.0F);
    }

    public static float getCrossbowPullProgress(int useTicks, KHRangeWeapon khRangeWeapon) {
        return Math.min((float) useTicks / (khRangeWeapon.rechargeTime() * 20), 1.0F);
    }

    public static WeaponState getWeaponState(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return WeaponState.fromNbt(nbt);
    }

    public static void setWeaponState(ItemStack stack, WeaponState state) {
        NbtCompound nbt = stack.getOrCreateNbt();
        state.applyToNbt(nbt);
    }

    public record Projectiles(KHArrowEntity khArrowEntity, int arrowCount) {

        public static Projectiles fromNbt(NbtCompound nbt, KHArrowEntity khArrowEntity) {
            int count = nbt.contains(khArrowEntity.getEntityName()) ? nbt.getInt(khArrowEntity.getEntityName()) : 0;
            return new Projectiles(khArrowEntity, count);
        }

        public void loadProjectile() {
            new Projectiles(khArrowEntity, arrowCount + 1);
        }

        public void unloadProjectile() {
            new Projectiles(khArrowEntity, Math.max(arrowCount - 1, 0));
        }

        public int getArrowCount() {
            return arrowCount;
        }
    }

    public record WeaponState(boolean isReloading, boolean isCharged, boolean isShooting) {
        public static WeaponState fromNbt(NbtCompound nbt) {
            return new WeaponState(
                    nbt.getBoolean("kh_reload"),
                    nbt.getBoolean("kh_charged"),
                    nbt.getBoolean("kh_shoot")
            );
        }

        public void applyToNbt(NbtCompound nbt) {
            nbt.putBoolean("kh_reload", isReloading);
            nbt.putBoolean("kh_charged", isCharged);
            nbt.putBoolean("kh_shoot", isShooting);
        }
    }
}
