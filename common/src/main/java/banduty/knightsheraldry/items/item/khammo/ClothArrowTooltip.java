package banduty.knightsheraldry.items.item.khammo;

import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ClothArrowTooltip {
    public static void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ClientLevel clientLevel = Minecraft.getInstance().level;
        if (clientLevel == null) return;
        if (Boolean.TRUE.equals(stack.get(SCDataComponents.IGNITED.get()))) {
            long igniteTime = stack.getOrDefault(SCDataComponents.IGNITE_TIME.get(), 0L);
            long remainingTicks = ClothArrow.IGNITE_DURATION_TICKS - (clientLevel.getGameTime() - igniteTime);
            if (remainingTicks < 0) remainingTicks = 0;

            long seconds = remainingTicks / 20;
            long hours = seconds / 3600;
            seconds %= 3600;
            long minutes = seconds / 60;
            seconds %= 60;

            tooltipComponents.add(Component.literal(String.format("Ignited - Time left: %02d:%02d:%02d", hours, minutes, seconds)));
        }
    }
}
