package banduty.knightsheraldry.client.tooltip;

import banduty.knightsheraldry.items.item.khammo.ClothArrow;
import banduty.knightsheraldry.client.input.KHKeyMappings;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import banduty.knightsheraldry.util.GripUtil;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.data.SCDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/** Tooltip lines shared by the Fabric and NeoForge tooltip events. */
public final class ItemTooltips {
    private ItemTooltips() {}

    public static void append(ItemStack stack, List<Component> tooltip) {
        if (GripUtil.isVersatile(stack)) {
            int index = Math.min(1, tooltip.size());
            tooltip.add(index, Component.translatable(GripUtil.isTwoHandedGrip(stack)
                    ? "text.tooltip.knightsheraldry.grip.two_handed" : "text.tooltip.knightsheraldry.grip.one_handed"));
            tooltip.add(index + 1, Component.translatable("text.tooltip.knightsheraldry.grip.switch",
                    KHKeyMappings.TOGGLE_GRIP.getTranslatedKeyMessage()));
        }

        if (stack.getItem() instanceof ClothArrow) {
            if (Boolean.TRUE.equals(stack.get(SCDataComponents.ARROW_IGNITED.get()))) {
                tooltip.add(Component.translatable("text.tooltip.knightsheraldry.extinguish"));
            } else if (Boolean.TRUE.equals(stack.get(KHDataComponents.EXTINGUISHED.get()))) {
                tooltip.add(Component.translatable("text.tooltip.knightsheraldry.extinguished"));
            } else {
                tooltip.add(Component.translatable("text.tooltip.knightsheraldry.ignite"));
            }
        }

        if (stack.getItem() instanceof Handgonne && Boolean.TRUE.equals(stack.get(SCDataComponents.CHARGED.get()))) {
            tooltip.add(Component.translatable("text.tooltip.knightsheraldry.right_click-flint_steel-fire"));
        }
    }
}
