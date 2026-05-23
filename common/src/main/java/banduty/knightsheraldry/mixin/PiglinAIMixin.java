package banduty.knightsheraldry.mixin;

import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinAIMixin {
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
}
