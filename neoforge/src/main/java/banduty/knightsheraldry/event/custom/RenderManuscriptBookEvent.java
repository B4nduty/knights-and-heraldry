package banduty.knightsheraldry.event.custom;

import banduty.knightsheraldry.screen.ManuscriptBookScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class RenderManuscriptBookEvent extends Event implements ICancellableEvent {
    private final GuiGraphics guiGraphics;
    private final int mouseX;
    private final int mouseY;
    private final float delta;
    private final ManuscriptBookScreen manuscriptBookScreen;

    public RenderManuscriptBookEvent(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, ManuscriptBookScreen manuscriptBookScreen) {
        this.guiGraphics = guiGraphics;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.delta = delta;
        this.manuscriptBookScreen = manuscriptBookScreen;
    }

    public GuiGraphics getGuiGraphics() {
        return guiGraphics;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public float getDelta() {
        return delta;
    }

    public ManuscriptBookScreen getManuscriptBookScreen() {
        return manuscriptBookScreen;
    }
}