package com.knightsheraldry.mixin;

import com.knightsheraldry.event.KeyInputHandler;
import com.knightsheraldry.items.custom.item.KHRangeWeapon;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.weaponutil.KHRangeWeaponUtil;
import com.knightsheraldry.util.weaponutil.KHWeaponUtil;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getUseAction(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        if (stack.getItem() instanceof KHWeapon)
            cir.setReturnValue(stack.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? UseAction.BLOCK : UseAction.NONE);
        if (stack.getItem() instanceof KHRangeWeapon khRangeWeapon)
            cir.setReturnValue(khRangeWeapon.useAction() == UseAction.BOW ? UseAction.BOW : UseAction.NONE);
    }

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getMaxUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof KHRangeWeapon khRangeWeapon)
            cir.setReturnValue(khRangeWeapon.maxUseTime());
    }

    @Inject(method = "usageTick", at = @At("HEAD"))
    public void knightsheraldry$usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks, CallbackInfo ci) {
        if (world.isClient || !(user instanceof PlayerEntity player) || !(stack.getItem() instanceof KHRangeWeapon khRangeWeapon)) return;
        if (khRangeWeapon.ammoRequirement() != null) return;
        int useTime = khRangeWeapon.maxUseTime() - remainingUseTicks;
        float crossbowPullProgress = KHRangeWeaponUtil.getCrossbowPullProgress(useTime, khRangeWeapon);

        if (khRangeWeapon.useAction() == UseAction.CROSSBOW && crossbowPullProgress >= 1.0F && !KHRangeWeaponUtil.getWeaponState(stack).isCharged()) {
            KHRangeWeaponUtil.getArrowFromInventory(player).ifPresent(arrowStack -> KHRangeWeaponUtil.loadAndPlayCrossbowSound(world, stack, player, arrowStack));
        }
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getItem() instanceof KHWeapon && !world.isClient) {
            if (user.isSneaking() && (itemStack.isIn(KHTags.WEAPONS_BLUDGEONING.getTag()) ||
                    itemStack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag()))) {
                itemStack.getOrCreateNbt().putBoolean("Bludgeoning", !itemStack.getOrCreateNbt().getBoolean("Bludgeoning"));
                cir.setReturnValue(TypedActionResult.success(itemStack));
            }
            user.setCurrentHand(hand);
            cir.setReturnValue(itemStack.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? TypedActionResult.consume(itemStack) : TypedActionResult.fail(itemStack));
        }

        if (itemStack.getItem() instanceof KHRangeWeapon khRangeWeapon && !world.isClient) {
            ItemStack offHandStack = user.getOffHandStack();
            if (khRangeWeapon.ammoRequirement() != null) {
                if (KHRangeWeaponUtil.getWeaponState(itemStack).isCharged()) {
                    if (khRangeWeapon.needsFlintAndSteel() && offHandStack.getItem() != Items.FLINT_AND_STEEL && !user.isCreative()) {
                        cir.setReturnValue(TypedActionResult.fail(itemStack));
                    }

                    user.setCurrentHand(hand);
                    KHRangeWeaponUtil.shootBullet(world, itemStack, khRangeWeapon, user);
                    KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(KHRangeWeaponUtil.getWeaponState(itemStack).isReloading(), false, true));

                    if (!user.getAbilities().creativeMode) {
                        if (khRangeWeapon.needsFlintAndSteel() && user instanceof ServerPlayerEntity serverPlayerEntity)
                            offHandStack.damage(1, null, serverPlayerEntity);
                    }

                    cir.setReturnValue(TypedActionResult.consume(itemStack));
                }
                cir.setReturnValue(TypedActionResult.fail(itemStack));
            }

            boolean hasArrow = KHRangeWeaponUtil.getArrowFromInventory(user).isPresent();

            if (khRangeWeapon.useAction() == UseAction.BOW && hasArrow) {
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(itemStack));
            }

            if (khRangeWeapon.useAction() == UseAction.CROSSBOW && hasArrow) {
                cir.setReturnValue(KHRangeWeaponUtil.handleCrossbowUse(world, user, hand, khRangeWeapon, itemStack));
            }

            cir.setReturnValue(TypedActionResult.fail(itemStack));
        }
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void knightsheraldry$onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        if (world.isClient || !(user instanceof PlayerEntity player) || !(stack.getItem() instanceof KHRangeWeapon khRangeWeapon)) return;
        int useTime = khRangeWeapon.maxUseTime() - remainingUseTicks;

        KHRangeWeaponUtil.getArrowFromInventory(player).ifPresent(arrowStack -> {
            float bowPullProgress = KHRangeWeaponUtil.getBowPullProgress(useTime);
            if (khRangeWeapon.useAction() == UseAction.BOW && bowPullProgress > 0.1f) {
                KHRangeWeaponUtil.shootArrow(world, stack, khRangeWeapon, player, arrowStack, bowPullProgress);
            }

            float crossbowPullProgress = KHRangeWeaponUtil.getCrossbowPullProgress(useTime, khRangeWeapon);
            if (khRangeWeapon.useAction() == UseAction.CROSSBOW && crossbowPullProgress < 1.0F) {
                KHRangeWeaponUtil.setWeaponState(stack, new KHRangeWeaponUtil.WeaponState(false,
                        KHRangeWeaponUtil.getWeaponState(stack).isCharged(), KHRangeWeaponUtil.getWeaponState(stack).isShooting()));
            }
        });
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    public void knightsheraldry$appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (stack.getItem() instanceof KHWeapon) {
            if (stack.isIn(KHTags.WEAPONS_BLUDGEONING.getTag()))
                tooltip.add(Text.translatable("tooltip.knightsheraldry.shift-right_click-bludgeoning"));
            if (stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag()))
                tooltip.add(Text.translatable("tooltip.knightsheraldry.shift-right_click-bludgeoning-piercing"));
            if (stack.isIn(KHTags.WEAPONS_HARVEST.getTag()))
                tooltip.add(Text.translatable("tooltip.knightsheraldry.right_click-replant"));
        }
        if (stack.getItem() instanceof KHRangeWeapon khRangeWeapon) {
            if (khRangeWeapon.ammoRequirement() != null)
                tooltip.add(Text.translatable("tooltip.knightsheraldry.need_to_hold", KeyInputHandler.reload.getBoundKeyLocalizedText()));
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = context.getStack();
        if (!(stack.getItem() instanceof KHWeapon)) return;
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if (!world.isClient && player != null) {
            Block block = state.getBlock();

            if (block instanceof CropBlock cropBlock && cropBlock.isMature(state) && world.breakBlock(pos, true, player)) {
                KHWeaponUtil.replantCrop(world, pos, cropBlock, player);
                stack.damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }

    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof KHWeapon khWeapon && attacker instanceof PlayerEntity playerEntity) {
            Vec3d playerPos = playerEntity.getPos();
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(KHWeaponUtil.getMaxDistance(khWeapon));
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), KHWeaponUtil.getMaxDistance(khWeapon) + 1))
                    .forEach(entity -> {
                        double distance = playerPos.distanceTo(target.getPos());
                        KHDamageCalculator.DamageType damageType = KHWeaponUtil.calculateDamageType(stack, khWeapon, ((PlayerAttackProperties) playerEntity).getComboCount());
                        float damage = KHDamageCalculator.getKHDamage(target, KHWeaponUtil.calculateDamage(khWeapon, distance, damageType.getIndex() - 4, damageType.getIndex()), damageType);

                        if (stack.isIn(KHTags.WEAPONS_DAMAGE_BEHIND.getTag()))
                            damage = KHWeaponUtil.adjustDamageForBackstab(target, playerPos, damage);

                        KHDamageCalculator.applyDamage(target, playerEntity, stack, damage);
                    });
            cir.setReturnValue(true);
        }
    }
}
