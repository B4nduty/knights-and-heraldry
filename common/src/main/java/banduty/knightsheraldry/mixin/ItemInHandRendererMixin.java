package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.item.khweapon.Estoc;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void kh$hideOffhandEstoc(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand,
                                     float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack,
                                     MultiBufferSource buffer, int light, CallbackInfo ci) {
        if (knightsheraldry$shouldHideOffhand(player, stack, hand == InteractionHand.OFF_HAND)) {
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