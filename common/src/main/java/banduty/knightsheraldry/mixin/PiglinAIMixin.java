package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.ai.FirearmAttack;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetFromAttackTargetIfTargetOutOfReach;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(PiglinAi.class)
public abstract class PiglinAIMixin {
    @Inject(method = "isWearingGold", at = @At("HEAD"), cancellable = true)
    private static void isWearingGold(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        for (ItemStack itemStack : livingEntity.getArmorSlots()) {
            for (ItemStack armorAttachment : SCUnderArmor.getArmorAttachments(itemStack)) {
                if (armorAttachment.isEmpty()) continue;

                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(armorAttachment.getItem());
                if (itemId.getPath().startsWith("golden_")) {
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }

    @Redirect(
            method = "initCoreActivity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/Brain;addActivity(Lnet/minecraft/world/entity/schedule/Activity;ILcom/google/common/collect/ImmutableList;)V"
            )
    )
    private static void knightsheraldry$addFirearmReload(
            Brain<Piglin> brain,
            Activity activity,
            int priority,
            ImmutableList<? extends BehaviorControl<? super Piglin>> tasks
    ) {
        List<BehaviorControl<? super Piglin>> extended = new ArrayList<>(tasks);
        extended.add(new FirearmAttack());
        brain.addActivity(activity, priority, ImmutableList.copyOf(extended));
    }

    @Inject(method = "hasCrossbow", at = @At("HEAD"), cancellable = true)
    private static void knightsheraldry$hasCrossbowOrFirearm(LivingEntity piglin, CallbackInfoReturnable<Boolean> cir) {
        if (FirearmAttack.isHoldingFirearm(piglin) && !FirearmAttack.isMeleeCommitted(piglin)) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(
            method = "initFightActivity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/behavior/SetWalkTargetFromAttackTargetIfTargetOutOfReach;create(F)Lnet/minecraft/world/entity/ai/behavior/BehaviorControl;"
            )
    )
    private static BehaviorControl<Mob> knightsheraldry$gateWalkTarget(float speedModifier) {
        BehaviorControl<Mob> walkTarget = SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(speedModifier);
        return BehaviorBuilder.triggerIf(
                mob -> !FirearmAttack.isHoldingFirearm(mob)
                        || FirearmAttack.isInMeleeRange(mob)
                        || FirearmAttack.isMeleeCommitted(mob),
                (OneShot<Mob>) walkTarget
        );
    }
}