package banduty.knightsheraldry.client.item;

import banduty.knightsheraldry.client.item.armor.KHHoodAttachmentRenderer;
import banduty.knightsheraldry.items.armor.attachment.KHHood;
import banduty.knightsheraldry.model.CloakHoodModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

/** Renders a hood worn straight on the head with the hood model (texture comes from KHIItemExtensionMixin). */
public class KHHoodClientExtensions implements IClientItemExtensions {
    private final KHHoodAttachmentRenderer renderer = new KHHoodAttachmentRenderer();

    @Override
    @SuppressWarnings("unchecked")
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
        CloakHoodModel model = renderer.getModel();
        ((HumanoidModel<LivingEntity>) defaultModel).copyPropertiesTo(model);
        return model;
    }

    @Override
    public void setupModelAnimations(LivingEntity entity, ItemStack stack, EquipmentSlot slot, Model model,
                                     float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (model instanceof CloakHoodModel hoodModel) hoodModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public int getDefaultDyeColor(ItemStack stack) {
        return FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, KHHood.DEFAULT_COLOR));
    }
}
