package com.knightsheraldry.util.weaponutil;

import com.knightsheraldry.event.KeyInputHandler;
import net.minecraft.text.Text;

import java.util.List;

public class TooltipClientSide {
    public static void setTooltip(List<Text> tooltip) {
        tooltip.add(Text.translatable("tooltip.knightsheraldry.need_to_hold",
                KeyInputHandler.reload.getBoundKeyLocalizedText()));
    }
}
