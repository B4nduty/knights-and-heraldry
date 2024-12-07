package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.entity.custom.KHArrowEntity;
import com.knightsheraldry.entity.custom.KHBulletEntity;
import com.knightsheraldry.event.KeyInputHandler;
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
    public KHRangeWeapons(Settings settings, RangeWeaponConfig config) {
        super(settings);
        this.config = config;
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
            if (isCharged(itemStack)) {
                if (config.needsFlintAndSteel() && offHandStack.getItem() != Items.FLINT_AND_STEEL && !user.isCreative()) {
                    return TypedActionResult.fail(itemStack);
                }

                user.setCurrentHand(hand);
                shootBullet(world, itemStack, user);
                setShooting(itemStack, true);
                setCharged(itemStack, false);

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

        if (config.useAction() == UseAction.CROSSBOW && crossbowPullProgress >= 1.0F && !isCharged(stack)) {
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
        arrowEntity.setDamageAmount(config.damageSettings().damage());
        arrowEntity.setDamageType(config.damageSettings().damageType());

        arrowEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * config.damageSettings().velocity(), 1.0F);

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

        bulletEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, config.damageSettings().velocity(), 1.0F);

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
        float f = (float)useTicks / (config.rechargeTime() * 20);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (config.ammoRequirement() != null)
            tooltip.add(Text.translatable("tooltip.knightsheraldry.need_to_hold", KeyInputHandler.reload.getBoundKeyLocalizedText()));
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
}