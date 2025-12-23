package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.util.SCDamageCalculator;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Lance extends SwordItem {
    private boolean charged = false;
    private final SCDamageCalculator.DamageType onlyDamageType;
    public Lance(float attackSpeed, Item.Properties properties, SCDamageCalculator.DamageType onlyDamageType) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, properties);
        this.onlyDamageType = onlyDamageType;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && entity instanceof Player player) {
            ItemStack mainHandStack = player.getMainHandItem();
            ItemStack offHandStack = player.getOffhandItem();
            Lance weapon = null;
            if (mainHandStack.getItem() instanceof Lance) {
                weapon = (Lance) mainHandStack.getItem();
            } else if (offHandStack.getItem() instanceof Lance) {
                weapon = (Lance) offHandStack.getItem();
            }
            if (weapon != null) {
                Entity targetedEntity = raycastEntity(player, getRange());
                boolean damageTamedEntities = KnightsHeraldry.getConfig().getDamageTamedEntities();
                if (damageTamedEntities && (targetedEntity instanceof TamableAnimal tamableAnimal
                        && tamableAnimal.isOwnedBy(player))) return;
                if (targetedEntity instanceof LivingEntity livingEntity && isCharged(stack)
                        && !player.getCooldowns().isOnCooldown(this)) {
                    double damage = SCDamageCalculator.getSCDamage(livingEntity, getLanceDamage() *
                            ((IEntityDataSaver) player).stoneycore$getPersistentData().getFloat("speedHistory") * 10,
                            this.onlyDamageType);

                    setCharged(stack, false);
                    if (livingEntity.isPassenger()) livingEntity.stopRiding();

                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(livingEntity.getUsedItemHand()));
                    targetedEntity.hurt(player.level().damageSources().playerAttack(player), (float) damage);
                    if (!player.isCreative()) player.getCooldowns().addCooldown(this, KnightsHeraldry.getConfig().getLanceCooldown() * 20);
                }
            }
        }
    }

    public double getRange() {
        return 4.0d;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    public float getLanceDamage() {
        return 7.0f;
    }

    public static Entity raycastEntity(Player player, double range) {
        Vec3 start = player.getEyePosition(1.0F);
        Vec3 direction = player.getViewVector(1.0F);
        Vec3 end = start.add(direction.scale(range));

        AABB box = player.getBoundingBox().expandTowards(direction.scale(range)).inflate(1.0D);

        Predicate<Entity> validEntity = (entity) ->
                !entity.isSpectator()
                        && entity.isAlive()
                        && entity != player
                        && (player.getVehicle() == null || entity != player.getVehicle());

        // collect all potential entities in the path
        List<Entity> candidates = player.level().getEntities(player, box, validEntity);

        for (Entity candidate : candidates) {
            AABB targetBox = candidate.getBoundingBox().inflate(0.3D);
            var result = targetBox.clip(start, end);
            if (result.isPresent()) {
                return candidate;
            }
        }

        return null;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("text.tooltip.knightsheraldry.right_click-to-charge"));
        list.add(Component.translatable("text.tooltip.knightsheraldry.on-charge"));
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!level.isClientSide() && user instanceof Player player && !player.getCooldowns().isOnCooldown(this)) {
            int i = this.getUseDuration(stack) - remainingUseTicks;
            doChargeProgress(i, stack, user);
            float f = (float)(stack.getUseDuration() - remainingUseTicks) / (float)40;
            if (f < 1.0F) {
                this.charged = false;
            }

            if (f >= 1.0F && !this.charged) {
                this.charged = true;
            }
        }
    }

    private static void doChargeProgress(int useTicks, ItemStack stack, LivingEntity user) {
        float f = (float)useTicks / (float)40;
        if (f >= 1.0F && !isCharged(stack) && user instanceof Player player) {
            setCharged(stack, true);
            player.displayClientMessage(Component.literal("Has been charged"), true);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (isCharged(itemStack)) {
            setCharged(itemStack, false);
            user.startUsingItem(hand);
            return InteractionResultHolder.success(itemStack);
        } else if (!isCharged(itemStack)) {
            this.charged = false;
            user.startUsingItem(hand);
        }
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 100;
    }

    public static boolean isCharged(ItemStack stack) {
        CompoundTag compoundTag = stack.getTag();
        return compoundTag != null && compoundTag.getBoolean("charged");
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        nbtCompound.putBoolean("charged", charged);
    }
}