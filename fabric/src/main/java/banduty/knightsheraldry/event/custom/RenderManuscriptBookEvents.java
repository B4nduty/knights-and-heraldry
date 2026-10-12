package banduty.knightsheraldry.event.custom;

import banduty.knightsheraldry.screen.ManuscriptBookScreen;
import banduty.stoneycore.event.custom.RenderBlueprintEvents;
import banduty.stoneycore.screen.BlueprintScreen;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.GuiGraphics;

public interface RenderManuscriptBookEvents {
    Event<RenderManuscriptBookEvents> EVENT = EventFactory.createArrayBacked(
            RenderManuscriptBookEvents.class,
            listeners -> (guiGraphics, mouseX, mouseY, delta, manuscriptBookScreen) -> {
                for (RenderManuscriptBookEvents listener : listeners) {
                    listener.render(guiGraphics, mouseX, mouseY, delta, manuscriptBookScreen);
                }
            }
    );

    void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, ManuscriptBookScreen manuscriptBookScreen);
}