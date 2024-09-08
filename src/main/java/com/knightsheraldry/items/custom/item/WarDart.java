package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.WarDartEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarDart extends KHWeapons {
    public WarDart(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 9.0F, 13.5F, 9.0F, 4.5F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                3.4d, //1st Distance
                3.5d, //2nd Distance
                3.7d, //3rd Distance
                3.8d, //4th Distance
                4.0d //5th Distance
        };
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        super.use(world, user, hand);
        ItemStack itemStack = user.getStackInHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= 10 && !world.isClient) {
                stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
                WarDartEntity wardartEntity = new WarDartEntity(playerEntity, world, stack);
                wardartEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2.5F, 1.0F);
                if (playerEntity.getAbilities().creativeMode) {
                    wardartEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }

                world.spawnEntity(wardartEntity);
                world.playSoundFromEntity(null, wardartEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!playerEntity.getAbilities().creativeMode) {
                    playerEntity.getInventory().removeOne(stack);
                    playerEntity.getItemCooldownManager().set(this, KnightsHeraldry.config().wardartCooldown() * 20);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.knightsheraldry.throw-to-pin"));
    }
}