package com.knightsheraldry.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WarSword extends SwordItem {
    public WarSword(ToolMaterial toolMaterial, float attackSpeed, Settings settings) {
        super(toolMaterial, 0, attackSpeed, settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity playerEntity) {
            Vec3d playerPos = playerEntity.getPos();
            double maxDistance = 3.5;
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(maxDistance);

            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                    entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), maxDistance + 1)).forEach(entity -> {
                double distance = playerPos.distanceTo(target.getPos());

                float damage = 0.0F;
                if (distance <= 1.0) {
                    damage = 1.0F;
                } else if (distance <= 2) {
                    damage = 8.0F;
                }  else if (distance <= 2.5) {
                    damage = 12.0F;
                }  else if (distance <= 3.5) {
                    damage = 8.0F;
                } else if (distance <= 4) {
                    damage = 4.0F;
                }

                playerEntity.sendMessage(Text.literal("Damage: " + damage));

                entity.damage(playerEntity.getWorld().getDamageSources().playerAttack(playerEntity), damage);
            });
        }
        return true;
    }
}