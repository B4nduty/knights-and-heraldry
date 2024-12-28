package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class KHWeapons extends SwordItem {

    /**
     * Class Example:
     * <pre>
     * {@code
     * public class ExampleWeapon extends KHWeapons {
     *     public ExampleWeapon(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
     *         super(attackSpeed, settings, onlyDamageType);
     *     }
     *
     *     @Override
     *     public float[] getDefaultAttackDamageValues() {
     *         return new float[] {
     *                 0.0F, 7.0F, 10.5F, 7.0F, 3.5F, //Slashing
     *                 0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
     *                 0.0F, 5.0F, 7.5F, 5.0F, 2.5F //Bludgeoning
     *         };
     *     }
     *
     *     // Values cannot be higher or equal than its next value
     *     @Override
     *     public double[] getDefaultRadiusValues() {
     *         return new double[] {
     *                 1.0d, //1st Distance
     *                 2.3d, //2nd Distance
     *                 2.8d, //3rd Distance
     *                 3.1d, //4th Distance
     *                 4.0d  //5th Distance
     *         };
     *     }
     *
     *     // How many animations has your item
     *     @Override
     *     public int getAnimation() {
     *         return 3;
     *     }
     *
     *     // In which animation is Piercing Damage applied
     *     //
     *     // Item needs to have Tag KH_WEAPONS_PIERCING
     *     //
     *     // Max length of index is 2
     *     @Override
     *     public int[] getPiercingAnimation() {
     *         return new int[] {
     *                 2
     *         };
     *     }
     * }
     *</pre>
     */
    
    private final KHDamageCalculator.DamageType onlyDamageType;
    public KHWeapons(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
        this.onlyDamageType = onlyDamageType;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return stack.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? UseAction.BLOCK : UseAction.NONE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            if (user.isSneaking() && (itemStack.isIn(KHTags.WEAPONS_BLUDGEONING.getTag()) ||
                    itemStack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag()))) {
                itemStack.getOrCreateNbt().putBoolean("Bludgeoning", !itemStack.getOrCreateNbt().getBoolean("Bludgeoning"));
                return TypedActionResult.success(itemStack);
            }
        }
        user.setCurrentHand(hand);
        return itemStack.isIn(KHTags.WEAPONS_SHIELD.getTag()) ? TypedActionResult.consume(itemStack) : TypedActionResult.fail(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.isIn(KHTags.WEAPONS_BLUDGEONING.getTag()))
            tooltip.add(Text.translatable("tooltip.knightsheraldry.shift-right_click-bludgeoning"));
        if (stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag()))
            tooltip.add(Text.translatable("tooltip.knightsheraldry.shift-right_click-bludgeoning-piercing"));
        if (stack.isIn(KHTags.WEAPONS_HARVEST.getTag()))
            tooltip.add(Text.translatable("tooltip.knightsheraldry.right_click-replant"));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity playerEntity) {
            Vec3d playerPos = playerEntity.getPos();
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(getMaxDistance());
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), getMaxDistance() + 1))
                    .forEach(entity -> {
                        double distance = playerPos.distanceTo(target.getPos());
                        KHDamageCalculator.DamageType damageType = calculateDamageType(stack, ((PlayerAttackProperties) playerEntity).getComboCount());
                        float damage = KHDamageCalculator.getKHDamage(target, calculateDamage(distance, damageType.getIndex() - 4, damageType.getIndex()), damageType);

                        if (stack.isIn(KHTags.WEAPONS_DAMAGE_BEHIND.getTag()))
                            damage = adjustDamageForBackstab(target, playerPos, damage);

                        KHDamageCalculator.applyDamage(target, playerEntity, stack, damage);
                    });
            stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
        }
        return true;
    }

    public final KHDamageCalculator.DamageType calculateDamageType(ItemStack stack, int comboCount) {
        boolean bludgeoning = stack.getOrCreateNbt().getBoolean("Bludgeoning");
        if (stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag())) bludgeoning = !bludgeoning;

        boolean piercing = (stack.isIn(KHTags.WEAPONS_PIERCING.getTag()) && isComboCountPiercing(comboCount)) ||
                getOnlyDamageType() == KHDamageCalculator.DamageType.PIERCING;

        return (bludgeoning || getOnlyDamageType() == KHDamageCalculator.DamageType.BLUDGEONING) ?
                KHDamageCalculator.DamageType.BLUDGEONING :
                (piercing || stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag())) ?
                        KHDamageCalculator.DamageType.PIERCING :
                        KHDamageCalculator.DamageType.SLASHING;
    }

    private boolean isComboCountPiercing(int comboCount) {
        int[] piercingAnimations = getPiercingAnimation();
        validatePiercingValues();
        int animationLength = getAnimation();
        for (int piercingAnimation : piercingAnimations) {
            if (comboCount % animationLength == piercingAnimation - 1) return true;
        }
        return piercingAnimations.length == animationLength;
    }

    private float adjustDamageForBackstab(LivingEntity target, Vec3d playerPos, float damage) {
        boolean isBehind = target.getRotationVec(1.0F).normalize().dotProduct(playerPos.subtract(target.getPos()).normalize()) < -0.5;
        return isBehind ? damage * 2 : damage;
    }

    public final float getAttackDamage(int index) {
        float[] defaultDamageValues = getDefaultAttackDamageValues();
        return (index >= 0 && index < defaultDamageValues.length) ? defaultDamageValues[index] : 0.0F;
    }

    public abstract float[] getDefaultAttackDamageValues();

    public final double getMaxDistance() {
        return getRadius(4);
    }

    private double getRadius(int index) {
        double[] defaultRadiusValues = getDefaultRadiusValues();
        validateRadiusValues(defaultRadiusValues);
        return (index >= 0 && index < defaultRadiusValues.length) ? defaultRadiusValues[index] : 0.0d;
    }

    public abstract double[] getDefaultRadiusValues();

    private void validateRadiusValues(double[] radiusValues) {
        for (int i = 1; i < radiusValues.length; i++) {
            if (radiusValues[i - 1] > radiusValues[i]) {
                KnightsHeraldry.LOGGER.error("Critical error: Index {} is higher than index {}. Radius values: {}", i - 1, i, java.util.Arrays.toString(radiusValues));
                throw new IndexOutOfBoundsException("Index " + (i - 1) + " is higher than index " + i + ". Radius values: " + java.util.Arrays.toString(radiusValues));
            }
        }
    }

    private void validatePiercingValues() {
        if (getPiercingAnimation().length > 2) {
            KnightsHeraldry.LOGGER.error("Critical error: Piercing Animations Index is higher than 2");
            throw new IndexOutOfBoundsException("Critical error: Piercing Animations Index is higher than 2");
        }
    }

    public int[] getPiercingAnimation() {
        return new int[0];
    }

    public int getAnimation() {
        return 0;
    }

    public final float calculateDamage(double distance, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (distance < getRadius(i - startIndex) + 0.25F) {
                return getAttackDamage(i);
            }
        }
        return 0.0F;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        if (!world.isClient && player != null) {
            ItemStack stack = context.getStack();
            Block block = state.getBlock();

            if (block instanceof CropBlock cropBlock && cropBlock.isMature(state) && world.breakBlock(pos, true, player)) {
                replantCrop(world, pos, cropBlock, player);
                stack.damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    private void replantCrop(World world, BlockPos pos, CropBlock cropBlock, PlayerEntity player) {
        ItemStack seedStack = new ItemStack(cropBlock.asItem());
        if (!seedStack.isEmpty()) {
            world.setBlockState(pos, cropBlock.getDefaultState(), Block.NOTIFY_ALL);
            world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
            if (player.getAbilities().creativeMode) {
                return;
            }
            seedStack.decrement(1);
        }
    }

    public final KHDamageCalculator.DamageType getOnlyDamageType() {
        return this.onlyDamageType;
    }
}