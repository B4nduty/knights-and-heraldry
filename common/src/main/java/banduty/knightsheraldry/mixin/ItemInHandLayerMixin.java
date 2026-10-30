package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.item.khweapon.Estoc;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public class ItemInHandLayerMixin {
    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void kh$hideOffhandEstoc(LivingEntity entity, ItemStack stack, ItemDisplayContext ctx, HumanoidArm arm,
                                     PoseStack poseStack, MultiBufferSource buffer, int light, CallbackInfo ci) {
        boolean isOffhand = arm != entity.getMainArm();
        if (knightsheraldry$shouldHideOffhand(entity, stack, isOffhand)) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean knightsheraldry$shouldHideOffhand(LivingEntity entity, ItemStack stack, boolean isOffhand) {
        if (!isOffhand || !(stack.getItem() instanceof Estoc)) return false;
        if (!(entity.getMainHandItem().getItem() instanceof Estoc)) return false;
        return entity.getVehicle() instanceof LivingEntity;
    }
}