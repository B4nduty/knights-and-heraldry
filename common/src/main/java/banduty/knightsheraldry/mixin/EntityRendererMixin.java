package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {
    @Inject(method = "renderNameTag", at = @At("HEAD"), cancellable = true)
    private void stoneycore$onRenderLabelIfPresent(T entity, Component displayName, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float partialTick, CallbackInfo ci) {
        if (entity instanceof Player player) {
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.HEAD);
            for (ItemStack accessoryStack : SCUnderArmor.getAccessories(itemStack)) {
                if (accessoryStack.getItem() == KHItems.HOOD.get() || accessoryStack.getItem() == KHItems.TORN_HOOD.get() ||
                        accessoryStack.getItem() == KHItems.HELMET_HOOD.get() || accessoryStack.getItem() == KHItems.HELMET_TORN_HOOD.get()) ci.cancel();
            }
        }
    }
}