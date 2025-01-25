package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.entity.custom.KHArrowEntity;
import com.knightsheraldry.entity.custom.KHBulletEntity;
import com.knightsheraldry.event.KeyInputHandler;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.RangeWeaponConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class KHRangeWeapons extends Item {
    public final RangeWeaponConfig config;
    private final Random random = new Random();

    /**
     * <p><b>Warning:</b>
     * This class is made for use of KnightsHeraldry, you can use it, but it isn't made to use by external people.
     * It is a class made only to reduce files and storage space.
     */
    public KHRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage,
                          float speed, RangeWeaponConfig.AmmoRequirement ammoRequirement, UseAction useAction,
                          int rechargeTime, boolean needsFlintAndSteel, SoundEvent... soundEvents) {
        super(settings);
        this.config = new RangeWeaponConfig(
                new RangeWeaponConfig.DamageSettings(damageType, maxUseTime, damage, speed),
                ammoRequirement,
                new RangeWeaponConfig.SoundSettings(soundEvents),
                useAction, rechargeTime, needsFlintAndSteel);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return config.useAction() == UseAction.BOW ? config.useAction() : UseAction.NONE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack offHandStack = user.getOffHandStack();
        if (config.ammoRequirement() != null) {
            if (getWeaponState(itemStack).isCharged()) {
                if (config.needsFlintAndSteel() && offHandStack.getItem() != Items.FLINT_AND_STEEL && !user.isCreative()) {
                    return TypedActionResult.fail(itemStack);
                }

                user.setCurrentHand(hand);
                shootBullet(world, itemStack, user);
                setWeaponState(itemStack, new WeaponState(getWeaponState(itemStack).isReloading(), false, true));

                if (!user.getAbilities().creativeMode) {
                    if (config.needsFlintAndSteel() && user instanceof ServerPlayerEntity serverPlayerEntity)
                        offHandStack.damage(1, null, serverPlayerEntity);
                }

                return TypedActionResult.consume(itemStack);
            }
            return TypedActionResult.fail(itemStack);
        }

        boolean hasArrow = getArrowFromInventory(user).isPresent();

        if (config.useAction() == UseAction.BOW && hasArrow) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }

        if (config.useAction() == UseAction.CROSSBOW && hasArrow) {
            return handleCrossbowUse(world, user, hand, itemStack);
        }

        return TypedActionResult.fail(itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return config.damageSettings().maxUseTime();
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) return;
        if (config.ammoRequirement() != null) return;
        int useTime = getMaxUseTime(stack) - remainingUseTicks;
        float crossbowPullProgress = getCrossbowPullProgress(useTime);

        if (config.useAction() == UseAction.CROSSBOW && crossbowPullProgress >= 1.0F && !getWeaponState(stack).isCharged()) {
            getArrowFromInventory(player).ifPresent(arrowStack -> loadAndPlayCrossbowSound(world, stack, player, arrowStack));
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) return;
        int useTime = getMaxUseTime(stack) - remainingUseTicks;

        getArrowFromInventory(player).ifPresent(arrowStack -> {
            float bowPullProgress = getBowPullProgress(useTime);
            if (config.useAction() == UseAction.BOW && bowPullProgress > 0.1f) {
                shootArrow(world, stack, player, arrowStack, bowPullProgress);
            }

            float crossbowPullProgress = getCrossbowPullProgress(useTime);
            if (config.useAction() == UseAction.CROSSBOW && crossbowPullProgress < 1.0F) {
                setWeaponState(stack, new WeaponState(false, getWeaponState(stack).isCharged(), getWeaponState(stack).isShooting()));
            }
        });
    }

    private TypedActionResult<ItemStack> handleCrossbowUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack) {
        Optional<ItemStack> arrowStackOpt = getArrowFromInventory(user);
        if (arrowStackOpt.isPresent() && arrowStackOpt.get().getItem() instanceof KHArrow khArrow) {
            KHArrowEntity arrowEntity = (KHArrowEntity) khArrow.createArrowEntity(user, world);

            if (getWeaponState(itemStack).isCharged()) {
                setWeaponState(itemStack, new WeaponState(getWeaponState(itemStack).isReloading(), false, true));
                Projectiles.fromNbt(itemStack.getOrCreateNbt(), arrowEntity).unloadProjectile();
                shootArrow(world, itemStack, user, khArrow.getDefaultStack(), 1f);
                return TypedActionResult.consume(itemStack);
            } else if (Projectiles.fromNbt(itemStack.getOrCreateNbt(), arrowEntity).getArrowCount() < 1) {
                setWeaponState(itemStack, new WeaponState(true, getWeaponState(itemStack).isCharged(), false));
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
        return TypedActionResult.fail(itemStack);
    }

    private void shootArrow(World world, ItemStack stack, PlayerEntity player, ItemStack arrowStack, float pullProgress) {
        KHArrowEntity arrowEntity = (KHArrowEntity) ((KHArrow) arrowStack.getItem()).createArrowEntity(player, world);
        arrowEntity.setDamageAmount(config.damageSettings().damage());
        arrowEntity.setDamageType(config.damageSettings().damageType());

        arrowEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * config.damageSettings().speed(), 1.0F);

        if (player.isCreative()) {
            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        } else {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            player.getInventory().removeStack(getArrowSlot(player), 1);
        }

        world.spawnEntity(arrowEntity);
        SoundEvent selectedSound = config.soundSettings().soundEvents().length > 0 ? config.soundSettings().soundEvents()[random.nextInt(config.soundSettings().soundEvents().length)] : null;
        if (selectedSound != null) world.playSoundFromEntity(null, arrowEntity, selectedSound, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    private void shootBullet(World world, ItemStack stack, PlayerEntity player) {
        KHBulletEntity bulletEntity = new KHBulletEntity(player, world);
        bulletEntity.setDamageAmount(config.damageSettings().damage());
        bulletEntity.setDamageType(config.damageSettings().damageType());

        bulletEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, config.damageSettings().speed(), 1.0F);

        world.spawnEntity(bulletEntity);
        SoundEvent selectedSound = config.soundSettings().soundEvents().length > 0 ? config.soundSettings().soundEvents()[random.nextInt(config.soundSettings().soundEvents().length)] : null;
        if (selectedSound != null) world.playSound(null, player.getBlockPos(), selectedSound,
                    SoundCategory.PLAYERS, 1f, 1f);

        if (!player.isCreative()) {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }
    }

    private void loadAndPlayCrossbowSound(World world, ItemStack stack, PlayerEntity player, ItemStack arrowStack) {
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

    private Optional<ItemStack> getArrowFromInventory(PlayerEntity player) {
        return player.getInventory().main.stream()
                .filter(stack -> stack.getItem() instanceof KHArrow)
                .findFirst();
    }

    private int getArrowSlot(PlayerEntity player) {
        return player.getInventory().main.stream()
                .filter(stack -> stack.getItem() instanceof KHArrow)
                .map(player.getInventory().main::indexOf)
                .findFirst().orElse(-1);
    }

    private float getBowPullProgress(int useTicks) {
        float progress = useTicks / 20.0F;
        return Math.min((progress * progress + progress * 2.0F) / 3.0F, 1.0F);
    }

    private float getCrossbowPullProgress(int useTicks) {
        return Math.min((float) useTicks / config.rechargeTime(), 1.0F);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (config.ammoRequirement() != null)
            tooltip.add(Text.translatable("tooltip.knightsheraldry.need_to_hold", KeyInputHandler.reload.getBoundKeyLocalizedText()));
    }

    public WeaponState getWeaponState(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return WeaponState.fromNbt(nbt);
    }

    public void setWeaponState(ItemStack stack, WeaponState state) {
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
                    nbt.getBoolean("Reload"),
                    nbt.getBoolean("Charged"),
                    nbt.getBoolean("Shoot")
            );
        }

        public void applyToNbt(NbtCompound nbt) {
            nbt.putBoolean("Reload", isReloading);
            nbt.putBoolean("Charged", isCharged);
            nbt.putBoolean("Shoot", isShooting);
        }
    }
}