package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
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
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SwordItem.class)
public class SwordItemMixin extends Item {
    public SwordItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (stack.getItem() instanceof KHWeapon)
            return stack.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? UseAction.BLOCK : UseAction.NONE;
        return super.getUseAction(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getItem() instanceof KHWeapon && !world.isClient) {
            if (user.isSneaking() && (itemStack.isIn(KHTags.WEAPONS_BLUDGEONING.getTag()) ||
                    itemStack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag()))) {
                itemStack.getOrCreateNbt().putBoolean("Bludgeoning", !itemStack.getOrCreateNbt().getBoolean("Bludgeoning"));
                return TypedActionResult.success(itemStack);
            }
            user.setCurrentHand(hand);
            return itemStack.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? TypedActionResult.consume(itemStack) : TypedActionResult.fail(itemStack);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.isIn(KHTags.WEAPONS_BLUDGEONING.getTag()))
            tooltip.add(Text.translatable("tooltip.knightsheraldry.shift-right_click-bludgeoning"));
        if (stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag()))
            tooltip.add(Text.translatable("tooltip.knightsheraldry.shift-right_click-bludgeoning-piercing"));
        if (stack.isIn(KHTags.WEAPONS_HARVEST.getTag()))
            tooltip.add(Text.translatable("tooltip.knightsheraldry.right_click-replant"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        if (!(stack.getItem() instanceof KHWeapon)) return ActionResult.PASS;
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if (!world.isClient && player != null) {
            Block block = state.getBlock();

            if (block instanceof CropBlock cropBlock && cropBlock.isMature(state) && world.breakBlock(pos, true, player)) {
                KHWeaponUtil.replantCrop(world, pos, cropBlock, player);
                stack.damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
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
