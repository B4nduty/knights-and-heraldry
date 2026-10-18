package banduty.knightsheraldry.client.tooltip;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.item.RepairmansKitItem;
import banduty.knightsheraldry.util.itemdata.RepairKitContents;
import banduty.stoneycore.StoneyCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

/**
 * Repairman's Kit tooltip: a row of five slots (wool, leather, chain, iron, gold). Each slot shows the
 * material's item icon with the stored amount in the bottom-right corner, like an inventory stack.
 * Full slots (64) are drawn in yellow; empty slots are faded. While the kit's use cooldown is running,
 * a countdown line is shown under the slots.
 */
public class RepairKitClientTooltip implements ClientTooltipComponent {
    // same sprite the under armor tooltip uses
    private static final ResourceLocation SLOT_BACKGROUND_SPRITE =
            ResourceLocation.fromNamespaceAndPath(StoneyCore.MOD_ID, "container/bundle/slot_background");

    private static final RepairmansKitItem.Material[] MATERIALS = RepairmansKitItem.Material.values();

    private static final int SLOT_SIZE = 24;
    private static final int ICON_OFFSET = 4;
    private static final int EMPTY_OVERLAY = 0xA08B8B8B;
    private static final int COUNT_COLOR = 0xFFFFFF;
    private static final int FULL_COUNT_COLOR = 0xFFFF55;

    private static final int COOLDOWN_LINE_HEIGHT = 12;
    private static final int COOLDOWN_COLOR = 0xFFAA00;

    private final RepairKitContents contents;
    /** Ticks of cooldown left; this component is rebuilt every frame, so this stays current. */
    private final long cooldownTicksLeft;

    public RepairKitClientTooltip(RepairKitContents contents) {
        this.contents = contents;

        // The cooldown is shared by every kit, so any kit's tooltip shows it, not just the one used.
        long left = 0;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level != null && minecraft.player != null
                && minecraft.player.getCooldowns().isOnCooldown(KHItems.REPAIRMANS_KIT.get())) {
            left = Math.max(0, RepairmansKitItem.getClientCooldownEnd() - minecraft.level.getGameTime());
        }
        this.cooldownTicksLeft = left;
    }

    private Component cooldownText() {
        String seconds = String.format(Locale.ROOT, "%.1fs", cooldownTicksLeft / 20.0);
        return Component.translatable("text.tooltip." + KnightsHeraldry.MOD_ID + ".repairmans_kit.cooldown", seconds);
    }

    @Override
    public int getHeight() {
        return SLOT_SIZE + (cooldownTicksLeft > 0 ? COOLDOWN_LINE_HEIGHT : 0);
    }

    @Override
    public int getWidth(Font font) {
        int width = MATERIALS.length * SLOT_SIZE;
        if (cooldownTicksLeft > 0) width = Math.max(width, font.width(cooldownText()));
        return width;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        for (int i = 0; i < MATERIALS.length; i++) {
            RepairmansKitItem.Material material = MATERIALS[i];
            int count = contents.get(material.ordinal());

            int slotX = x + i * SLOT_SIZE;
            int iconX = slotX + ICON_OFFSET;
            int iconY = y + ICON_OFFSET;

            guiGraphics.blitSprite(SLOT_BACKGROUND_SPRITE, slotX, y, SLOT_SIZE, SLOT_SIZE);
            guiGraphics.renderItem(material.toStack(1), iconX, iconY, i);

            // drawn above the item, same depth vanilla uses for stack counts
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0F, 0.0F, 200.0F);
            if (count <= 0) {
                guiGraphics.fill(iconX, iconY, iconX + 16, iconY + 16, EMPTY_OVERLAY);
            } else {
                String text = String.valueOf(count);
                guiGraphics.drawString(font, text, iconX + 19 - 2 - font.width(text), iconY + 6 + 3,
                        count >= RepairmansKitItem.maxPerSlot() ? FULL_COUNT_COLOR : COUNT_COLOR, true);
            }
            guiGraphics.pose().popPose();
        }

        if (cooldownTicksLeft > 0) {
            guiGraphics.drawString(font, cooldownText(), x, y + SLOT_SIZE + 2, COOLDOWN_COLOR, true);
        }
    }
}