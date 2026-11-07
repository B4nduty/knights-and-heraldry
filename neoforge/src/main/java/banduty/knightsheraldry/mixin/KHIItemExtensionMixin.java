package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.armor.attachment.KHHood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IItemExtension.class)
public interface KHIItemExtensionMixin {
    /** A hood worn straight on the head uses its plain hood texture instead of a vanilla armor layer texture. */
    @Inject(method = "getArmorTexture", at = @At("HEAD"), cancellable = true)
    private void knightsheraldry$getHoodTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer,
                                                boolean innerModel, CallbackInfoReturnable<ResourceLocation> cir) {
        if (stack.getItem() instanceof KHHood) cir.setReturnValue(KHHood.getTexture(stack, false));
    }
}
