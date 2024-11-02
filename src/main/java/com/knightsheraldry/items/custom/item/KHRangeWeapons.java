package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.entity.custom.KHArrowEntity;
import com.knightsheraldry.entity.custom.KHBulletEntity;
import com.knightsheraldry.util.KHDamageCalculator;
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
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class KHRangeWeapons extends Item {
    private final KHDamageCalculator.DamageType damageType;
    private final int maxUseTime;
    private final float damage;
    private final double blockRange;
    private final UseAction useAction;
    private final int rechargeTime;
    private final int amountFirstItem;
    private final Item firstItem;
    private final Item firstItem2nOption;
    private final int amountSecondItem;
    private final Item secondItem;
    private final Item secondItem2nOption;
    private final int amountThirdItem;
    private final Item thirdItem;
    private final Item thirdItem2nOption;
    private final boolean needsFlintAndSteel;
    private final int cooldown;
    private final SoundEvent[] soundEvents;

    private final Random random = new Random();

    /**
     * <p><b>Warning:</b>
     * This class is made for use of KnightsHeraldry, you can use it, but it isn't made to use by external people.
     * It is a class made only to reduce files and storage space.
     */
    public KHRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage, double blockRange,
                          UseAction useAction, SoundEvent... soundEvents) {
        super(settings);
        this.damageType = damageType;
        this.maxUseTime = maxUseTime;
        this.damage = damage;
        this.blockRange = blockRange;
        this.useAction = useAction;
        this.rechargeTime = 0;
        this.amountFirstItem = 0;
        this.firstItem = null;
        this.firstItem2nOption = null;
        this.amountSecondItem = 0;
        this.secondItem = null;
        this.secondItem2nOption = null;
        this.amountThirdItem = 0;
        this.thirdItem = null;
        this.thirdItem2nOption = null;
        this.needsFlintAndSteel = false;
        this.cooldown = 0;
        this.soundEvents = soundEvents;
    }

    public KHRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage, double blockRange,
                          UseAction useAction, int rechargeTime, SoundEvent... soundEvents) {
        super(settings);
        this.damageType = damageType;
        this.maxUseTime = maxUseTime;
        this.damage = damage;
        this.blockRange = blockRange;
        this.useAction = useAction;
        this.rechargeTime = Math.max(0, rechargeTime);
        this.amountFirstItem = 0;
        this.firstItem = null;
        this.firstItem2nOption = null;
        this.amountSecondItem = 0;
        this.secondItem = null;
        this.secondItem2nOption = null;
        this.amountThirdItem = 0;
        this.thirdItem = null;
        this.thirdItem2nOption = null;
        this.needsFlintAndSteel = false;
        this.cooldown = 0;
        this.soundEvents = soundEvents;
    }

    public KHRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage, double blockRange,
                          UseAction useAction, int rechargeTime, int amountFirstItem, Item firstItem, Item firstItem2nOption, int amountSecondItem, Item secondItem,
                          Item secondItem2nOption, int amountThirdItem, Item thirdItem, Item thirdItem2nOption, boolean needsFlintAndSteel, int cooldown, SoundEvent... soundEvents) {
        super(settings);
        this.damageType = damageType;
        this.maxUseTime = maxUseTime;
        this.damage = damage;
        this.blockRange = blockRange;
        this.useAction = useAction;
        this.rechargeTime = Math.max(0, rechargeTime);
        this.amountFirstItem = amountFirstItem;
        this.firstItem = Objects.requireNonNull(firstItem);
        this.firstItem2nOption = firstItem2nOption;
        this.amountSecondItem = amountSecondItem;
        this.secondItem = secondItem;
        this.secondItem2nOption = secondItem2nOption;
        this.amountThirdItem = amountThirdItem;
        this.thirdItem = thirdItem;
        this.thirdItem2nOption = thirdItem2nOption;
        this.needsFlintAndSteel = needsFlintAndSteel;
        this.cooldown = cooldown;
        this.soundEvents = soundEvents;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return useAction == UseAction.BOW ? useAction : UseAction.NONE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack offHandStack = user.getOffHandStack();
        if (firstItem != null) {
            if (isCharged(itemStack)) {
                if (isNeededFlintAndSteel() && offHandStack.getItem() != Items.FLINT_AND_STEEL) {
                    return TypedActionResult.fail(itemStack);
                }

                user.setCurrentHand(hand);
                shootBullet(world, itemStack, user);
                setShooting(itemStack, true);
                setCharged(itemStack, false);

                if (!user.getAbilities().creativeMode) {
                    user.getItemCooldownManager().set(this, this.cooldown * 20);
                    if (isNeededFlintAndSteel() && user instanceof ServerPlayerEntity serverPlayerEntity)
                        offHandStack.damage(1, null, serverPlayerEntity);
                }

                return TypedActionResult.consume(itemStack);
            }
            return TypedActionResult.fail(itemStack);
        }

        boolean hasArrow = getArrowFromInventory(user).isPresent();

        if (useAction == UseAction.BOW && hasArrow) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }

        if (useAction == UseAction.CROSSBOW && hasArrow) {
            return handleCrossbowUse(world, user, hand, itemStack);
        }

        return TypedActionResult.fail(itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return this.maxUseTime;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) return;
        if (firstItem != null) return;
        int useTime = getMaxUseTime(stack) - remainingUseTicks;
        float crossbowPullProgress = getCrossbowPullProgress(useTime);

        if (useAction == UseAction.CROSSBOW && crossbowPullProgress >= 1.0F && !isCharged(stack)) {
            getArrowFromInventory(player).ifPresent(arrowStack -> {
                loadAndPlayCrossbowSound(world, stack, player, arrowStack);
            });
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) return;
        int useTime = getMaxUseTime(stack) - remainingUseTicks;

        getArrowFromInventory(player).ifPresent(arrowStack -> {
            float bowPullProgress = getBowPullProgress(useTime);
            if (useAction == UseAction.BOW && bowPullProgress > 0.1f) {
                shootArrow(world, stack, player, arrowStack, bowPullProgress);
            }

            float crossbowPullProgress = getCrossbowPullProgress(useTime);
            if (useAction == UseAction.CROSSBOW && crossbowPullProgress < 1.0F) {
                setReload(stack, false);
            }
        });
    }

    private TypedActionResult<ItemStack> handleCrossbowUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack) {
        Optional<ItemStack> arrowStackOpt = getArrowFromInventory(user);
        if (arrowStackOpt.isPresent() && arrowStackOpt.get().getItem() instanceof KHArrow khArrow) {
            KHArrowEntity arrowEntity = (KHArrowEntity) khArrow.createArrowEntity(user, world);

            if (isCharged(itemStack)) {
                setCharged(itemStack, false);
                setShooting(itemStack, true);
                unloadProjectiles(itemStack, arrowEntity);
                shootArrow(world, itemStack, user, khArrow.getDefaultStack(), 1f);
                return TypedActionResult.consume(itemStack);
            } else if (!isLoaded(itemStack, arrowEntity)) {
                setShooting(itemStack, false);
                setReload(itemStack, true);
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
        arrowEntity.setDamageAmount(this.damage);
        arrowEntity.setDamageType(this.damageType);

        float velocityMultiplier = (float) blockRange / 32f;
        arrowEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * velocityMultiplier, 1.0F);

        if (player.isCreative()) {
            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            player.getInventory().removeStack(getArrowSlot(player), 1);
        }

        world.spawnEntity(arrowEntity);
        SoundEvent selectedSound = soundEvents.length > 0 ? soundEvents[random.nextInt(soundEvents.length)] : null;
        if (selectedSound != null) world.playSoundFromEntity(null, arrowEntity, selectedSound, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    private void shootBullet(World world, ItemStack stack, PlayerEntity player) {
        KHBulletEntity bulletEntity = new KHBulletEntity(player, world);
        bulletEntity.setDamageAmount(this.damage);
        bulletEntity.setDamageType(this.damageType);

        float velocityMultiplier = (float) blockRange / 32f;
        bulletEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, velocityMultiplier, 1.0F);


        world.spawnEntity(bulletEntity);
        SoundEvent selectedSound = soundEvents.length > 0 ? soundEvents[random.nextInt(soundEvents.length)] : null;
        if (selectedSound != null) world.playSound(null, player.getBlockPos(), selectedSound,
                    SoundCategory.PLAYERS, 1f, 1f);

        if (!player.isCreative()) {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }
    }

    private void loadAndPlayCrossbowSound(World world, ItemStack stack, PlayerEntity player, ItemStack arrowStack) {
        KHArrowEntity arrowEntity = (KHArrowEntity) ((KHArrow) arrowStack.getItem()).createArrowEntity(player, world);
        loadProjectiles(stack, arrowEntity);
        setReload(stack, false);
        setCharged(stack, true);

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

    public static float getBowPullProgress(int useTicks) {
        float f = useTicks / 20.0F;
        return Math.min((f * f + f * 2.0F) / 3.0F, 1.0F);
    }

    private float getCrossbowPullProgress(int useTicks) {
        float f = (float)useTicks / (getRechargeTime() * 20);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public final int getAmountFirstItem() {
        return this.amountFirstItem;
    }

    public final Item getFirstItem() {
        return this.firstItem;
    }

    public final Item getFirstItem2nOption() {
        return this.firstItem2nOption;
    }

    public final int getAmountSecondItem() {
        return this.amountSecondItem;
    }

    public final Item getSecondItem() {
        return this.secondItem;
    }

    public final Item getSecondItem2nOption() {
        return this.secondItem2nOption;
    }

    public final int getAmountThirdItem() {
        return this.amountThirdItem;
    }

    public final Item getThirdItem() {
        return this.thirdItem;
    }

    public final Item getThirdItem2nOption() {
        return this.thirdItem2nOption;
    }

    public final boolean isNeededFlintAndSteel() {
        return this.needsFlintAndSteel;
    }

    public final int getRechargeTime() {
        return this.rechargeTime;
    }

    private void setBooleanTag(ItemStack stack, String key, boolean value) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean(key, value);
    }

    private boolean getBooleanTag(ItemStack stack, String key) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.getBoolean(key);
    }

    public final boolean isReloading(ItemStack stack) {
        return getBooleanTag(stack, "Reload");
    }

    public final void setReload(ItemStack stack, boolean reload) {
        setBooleanTag(stack, "Reload", reload);
    }

    public final boolean isCharged(ItemStack stack) {
        return getBooleanTag(stack, "Charged");
    }

    public final void setCharged(ItemStack stack, boolean charged) {
        setBooleanTag(stack, "Charged", charged);
    }

    public final boolean isShooting(ItemStack stack) {
        return getBooleanTag(stack, "Shoot");
    }

    public final void setShooting(ItemStack stack, boolean shoot) {
        setBooleanTag(stack, "Shoot", shoot);
    }

    public final void loadProjectiles(ItemStack itemStack, KHArrowEntity arrowEntity) {
        itemStack.getOrCreateNbt().putInt(arrowEntity.getEntityName(), 1);
    }

    public final void unloadProjectiles(ItemStack itemStack, KHArrowEntity arrowEntity) {
        itemStack.getOrCreateNbt().putInt(arrowEntity.getEntityName(), 0);
    }

    public final boolean isLoaded(ItemStack stack, KHArrowEntity arrowEntity) {
        return stack.getNbt() != null && stack.getNbt().getInt(arrowEntity.getEntityName()) == 1;
    }

    public final float getDamage() {
        return this.damage;
    }

    public final double getBlockRange() {
        return this.blockRange;
    }
}