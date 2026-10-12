package banduty.knightsheraldry.items.item.manuscriptbook;

import banduty.knightsheraldry.event.custom.RenderManuscriptBookEvent;
import banduty.knightsheraldry.screen.KHScreenHandlers;
import banduty.knightsheraldry.screen.ManuscriptBookScreen;
import banduty.knightsheraldry.screen.ManuscriptBookScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;

public class NeoForgeManuscriptBookHelper implements IManuscriptBookHelper {

    @Override
    public void openManuscriptBookScreen(ServerPlayer player, ItemStack stack) {
        player.openMenu(new MenuProvider() {
            @Override
            public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player p) {
                return new ManuscriptBookScreenHandler(syncId, playerInventory, stack);
            }

            @Override
            public Component getDisplayName() {
                return Component.literal("Manuscript Book");
            }
        }, (RegistryFriendlyByteBuf buf) -> {
            ItemStack.STREAM_CODEC.encode(buf, stack);
        });
    }

    @Override
    public MenuType<?> manuscriptBookScreenHandler() {
        return KHScreenHandlers.MANUSCRIPT_BOOK_SCREEN_HANDLER.get();
    }

    @Override
    public void renderManuscriptBookEvents(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, ManuscriptBookScreen manuscriptBookScreen) {
        NeoForge.EVENT_BUS.post(new RenderManuscriptBookEvent(guiGraphics, mouseX, mouseY, delta, manuscriptBookScreen));
    }
}