package banduty.knightsheraldry.items.item.manuscriptbook;

import banduty.knightsheraldry.event.custom.RenderManuscriptBookEvents;
import banduty.knightsheraldry.screen.KHScreenHandlers;
import banduty.knightsheraldry.screen.ManuscriptBookScreen;
import banduty.knightsheraldry.screen.ManuscriptBookScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class FabricManuscriptBookHelper implements IManuscriptBookHelper {

    @Override
    public void openManuscriptBookScreen(ServerPlayer player, ItemStack stack) {
        player.openMenu(new ExtendedScreenHandlerFactory<ManuscriptBookData>() {
            @Override
            public ManuscriptBookData getScreenOpeningData(ServerPlayer player) {
                return new ManuscriptBookData(stack);
            }

            @Override
            public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
                return new ManuscriptBookScreenHandler(syncId, playerInventory, stack);
            }

            @Override
            public Component getDisplayName() {
                return Component.literal("Manuscript Book");
            }
        });
    }

    @Override
    public MenuType<?> manuscriptBookScreenHandler() {
        return KHScreenHandlers.MANUSCRIPT_BOOK_SCREEN_HANDLER.get();
    }

    @Override
    public void renderManuscriptBookEvents(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, ManuscriptBookScreen manuscriptBookScreen) {
        RenderManuscriptBookEvents.EVENT.invoker().render(guiGraphics, mouseX, mouseY, delta, manuscriptBookScreen);
    }
}