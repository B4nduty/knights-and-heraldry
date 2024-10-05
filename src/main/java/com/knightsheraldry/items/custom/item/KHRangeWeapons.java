package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.entity.custom.KHArrowEntity;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class KHRangeWeapons extends Item {
    private final KHDamageCalculator.DamageType damageType;
    private final float damage;
    private final double blockRange;

    public KHRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, float damage, double blockRange) {
        super(settings.maxCount(1));
        this.damageType = damageType;
        this.damage = damage;
        this.blockRange = blockRange;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (hasArrowInInventory(user) || user.getAbilities().creativeMode) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity playerEntity)) return;

        int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
        float pullProgress = getPullProgress(useTime);

        if (pullProgress > 0.1f && (playerEntity.getAbilities().creativeMode || hasArrowInInventory(playerEntity))) {
            stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(user.getActiveHand()));

            ItemStack arrowStack = getArrowFromInventory(playerEntity);
            if (arrowStack != null && arrowStack.getItem() instanceof KHArrow khArrow) {
                Entity khArrowEntity = khArrow.createArrowEntity(playerEntity, world);

                if (khArrowEntity instanceof KHArrowEntity arrowEntity) {
                    arrowEntity.setDamageAmount(this.damage);
                    arrowEntity.setDamageType(this.damageType);

                    float velocityMultiplier = (float) (this.blockRange / 18.0d);
                    arrowEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, pullProgress * velocityMultiplier, 1.0F);

                    if (playerEntity.getAbilities().creativeMode) {
                        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }

                    world.spawnEntity(arrowEntity);
                    world.playSoundFromEntity(null, arrowEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

                    if (!playerEntity.getAbilities().creativeMode) {
                        playerEntity.getInventory().removeStack(getArrowSlot(playerEntity), 1);
                    }
                }
            }
        }
    }

    private boolean hasArrowInInventory(PlayerEntity playerEntity) {
        return getArrowFromInventory(playerEntity) != null;
    }

    private ItemStack getArrowFromInventory(PlayerEntity playerEntity) {
        return playerEntity.getInventory().main.stream()
                .filter(stack -> stack.getItem() instanceof KHArrow)
                .findFirst().orElse(null);
    }

    private int getArrowSlot(PlayerEntity playerEntity) {
        for (int slot = 0; slot < playerEntity.getInventory().size(); slot++) {
            if (playerEntity.getInventory().getStack(slot).getItem() instanceof KHArrow) {
                return slot;
            }
        }
        return -1;
    }

    public static float getPullProgress(int useTicks) {
        float f = (float) useTicks / 20.0F;
        return Math.min((f * f + f * 2.0F) / 3.0F, 1.0F);
    }
}