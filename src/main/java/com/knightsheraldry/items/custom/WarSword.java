package com.knightsheraldry.items.custom;

import com.knightsheraldry.util.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class WarSword extends SwordItem {
    public WarSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (stack.getOrCreateNbt().getBoolean("stamina_blocked")) {
            return UseAction.NONE;
        }
        return UseAction.BLOCK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        boolean isStaminaBlocked = ((IEntityDataSaver) user).bsroleplay$getPersistentData().getBoolean("stamina_blocked");
        itemStack.getOrCreateNbt().putBoolean("stamina_blocked", isStaminaBlocked);

        if (isStaminaBlocked) {
            return TypedActionResult.fail(itemStack);
        }

        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    // @Override
    // public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    //     ItemStack itemStack = user.getStackInHand(hand);
    //     ItemStack offHandStack = user.getOffHandStack();
    //     if (!world.isClient) {
    //         if (((itemStack.getItem() == ModItems.WARSWORD && offHandStack.getItem() == ModItems.SMITHING_HAMMER) ||
    //                 (offHandStack.getItem() == ModItems.WARSWORD && itemStack.getItem() == ModItems.SMITHING_HAMMER))
    //                  && user.isSneaking()) {
    //             int currentVariant = itemStack.getOrCreateNbt().getInt("CustomModelData");
    //             int newVariant = (currentVariant + 1) % MAX_TEXTURE_VARIANTS;

    //             itemStack.getOrCreateNbt().putInt("CustomModelData", newVariant);

    //            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND,
    //                     SoundCategory.BLOCKS, 1f, 1f);
    //            return TypedActionResult.success(itemStack);
    //         }
    //    }
    //     return TypedActionResult.fail(itemStack);
    // }
}
