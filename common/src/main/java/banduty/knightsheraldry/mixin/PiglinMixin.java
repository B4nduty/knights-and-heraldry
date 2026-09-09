package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.combat.range.goal.SCFirearmAttackGoal;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Piglin.class)
public abstract class PiglinMixin {

    @Unique
    private static final float CHANCE_TO_CARRY_FIREARM = 0.15F;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void knightsheraldry$addFirearmGoal(EntityType<? extends AbstractPiglin> entityType, Level level, CallbackInfo ci) {
        Piglin piglin = (Piglin) (Object) this;
        GoalSelector goalSelector = ((MobAccessor) piglin).knightsheraldry$getGoalSelector();
        goalSelector.addGoal(4, new SCFirearmAttackGoal<>(piglin, 1.0D, 16.0F));
    }

    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void knightsheraldry$maybeEquipFirearm(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Piglin self = (Piglin) (Object) this;
        if (self.isBaby()) return;
        if (random.nextFloat() >= CHANCE_TO_CARRY_FIREARM) return;

        ItemStack firearm = random.nextBoolean()
                ? new ItemStack(KHItems.ARQUEBUS.get())
                : new ItemStack(KHItems.HANDGONNE.get());

        firearm.set(KHDataComponents.PIGLIN.get(), true);

        self.setItemSlot(EquipmentSlot.MAINHAND, firearm);
        self.setDropChance(EquipmentSlot.MAINHAND, 0.08F);
    }
}