package banduty.knightsheraldry.items.item.manuscriptbook;

import banduty.knightsheraldry.screen.ManuscriptBookScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public interface IManuscriptBookHelper {
    void openManuscriptBookScreen(ServerPlayer player, ItemStack stack);

    MenuType<?> manuscriptBookScreenHandler();

    void renderManuscriptBookEvents(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, ManuscriptBookScreen manuscriptBookScreen);

    record ManuscriptBookData(ItemStack stack) {
        public static final StreamCodec<RegistryFriendlyByteBuf, ManuscriptBookData> STREAM_CODEC =
                StreamCodec.of(ManuscriptBookData::write, ManuscriptBookData::read);

        public static void write(RegistryFriendlyByteBuf buf, ManuscriptBookData data) {
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, data.stack());
        }

        public static ManuscriptBookData read(RegistryFriendlyByteBuf buf) {
            return new ManuscriptBookData(
                    ItemStack.OPTIONAL_STREAM_CODEC.decode(buf)
            );
        }
    }
}
