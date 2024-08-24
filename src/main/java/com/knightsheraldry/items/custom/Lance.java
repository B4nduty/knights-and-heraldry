package com.knightsheraldry.items.custom;

import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.util.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Lance extends SwordItem {
    private boolean charged = false;
    public Lance(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient && entity instanceof PlayerEntity player) {
            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            Lance weapon = null;
            if (mainHandStack.getItem() instanceof Lance) {
                weapon = (Lance) mainHandStack.getItem();
            } else if (offHandStack.getItem() instanceof Lance) {
                weapon = (Lance) offHandStack.getItem();
            }
            if (weapon != null) {
                Entity targetedEntity = raycastEntity(player, getRange());

                if (targetedEntity instanceof LivingEntity livingEntity && isCharged(stack)
                        && player.getItemCooldownManager().isCoolingDown(this)) {
                    float damage = getLanceDamage() * ((IEntityDataSaver) player)
                            .knightsheraldry$getPersistentData().getFloat("speedHistory") * 10;

                    setCharged(stack, false);
                    if (livingEntity.hasVehicle()) livingEntity.stopRiding();

                    player.getItemCooldownManager().set(this, 600);
                    stack.damage(1, player, p -> p.sendToolBreakStatus(livingEntity.getActiveHand()));
                    applyDamage(livingEntity, player, stack, damage);
                }
            }
        }
    }

    public double getRange() {
        return 4.0d;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public float getLanceDamage() {
        return 7.0f;
    }

    private void applyDamage(LivingEntity target, PlayerEntity playerEntity, ItemStack stack, float damage) {
        if (stack.isIn(ModTags.Items.KH_WEAPONS_IGNORES_ARMOR)) {
            target.setHealth(target.getHealth() - damage);
        } else {
            playerEntity.sendMessage(Text.literal("Damage: " + damage));
            target.damage(playerEntity.getWorld().getDamageSources().playerAttack(playerEntity), damage);
        }
        if (target.getHealth() <= 0.0F) {
            target.onDeath(playerEntity.getDamageSources().playerAttack(playerEntity));
        }
    }

    public static Entity raycastEntity(PlayerEntity player, double range) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d end = start.add(direction.multiply(range));

        // Define a bounding box that extends in the direction the player is looking
        Box box = player.getBoundingBox().stretch(direction.multiply(range)).expand(1.0, 1.0, 1.0);

        // Predicate to filter valid entities
        Predicate<Entity> validEntity = (entity) -> !entity.isSpectator() && entity.isAlive() && entity != player;

        // Perform the raycast
        EntityHitResult entityHitResult = ProjectileUtil.raycast(player, start, end, box, validEntity, range * range);

        return entityHitResult != null ? entityHitResult.getEntity() : null;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.knightsheraldry.right_click-to-charge"));
        tooltip.add(Text.translatable("tooltip.knightsheraldry.on-charge"));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity player && !player.getItemCooldownManager().isCoolingDown(this)) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            doChargeProgress(i, stack, user, world);
            float f = (float)(stack.getMaxUseTime() - remainingUseTicks) / (float)40;
            if (f < 1.0F) {
                this.charged = false;
            }

            if (f >= 1.0F && !this.charged) {
                this.charged = true;
            }
        }
    }

    private static void doChargeProgress(int useTicks, ItemStack stack, LivingEntity user, World world) {
        float f = (float)useTicks / (float)40;
        if (f >= 1.0F && !isCharged(stack) && user instanceof PlayerEntity player) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0F,
                    1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            setCharged(stack, true);
            player.sendMessage(Text.literal("Has been charged"), true);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (isCharged(itemStack)) {
            setCharged(itemStack, false);
            user.setCurrentHand(hand);
            return TypedActionResult.success(itemStack);
        } else if (!isCharged(itemStack)) {
            this.charged = false;
            user.setCurrentHand(hand);
        }
        return TypedActionResult.success(itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 100;
    }

    public static boolean isCharged(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean("Charged");
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("Charged", charged);
    }
}