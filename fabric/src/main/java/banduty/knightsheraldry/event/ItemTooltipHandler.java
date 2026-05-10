package banduty.knightsheraldry.event;

import banduty.knightsheraldry.items.item.khammo.ClothArrow;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ItemTooltipHandler implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> list) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (itemStack.getItem() instanceof ClothArrow) {
            if (Boolean.TRUE.equals(itemStack.get(SCDataComponents.IGNITED.get()))) {
                list.add(Component.translatable("text.tooltip.knightsheraldry.extinguish"));
            } else if (Boolean.TRUE.equals(itemStack.get(KHDataComponents.EXTINGUISHED.get()))) {
                list.add(Component.translatable("text.tooltip.knightsheraldry.ignite"));
            }
        }

        if (itemStack.getItem() instanceof Handgonne && Boolean.TRUE.equals(itemStack.get(SCDataComponents.CHARGED.get()))) {
            list.add(Component.translatable("text.tooltip.knightsheraldry.right_click-flint_steel-fire"));
        }
    }
}