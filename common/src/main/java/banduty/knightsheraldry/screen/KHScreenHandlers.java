package banduty.knightsheraldry.screen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.manuscriptbook.IManuscriptBookHelper;
import banduty.knightsheraldry.platform.Services;
import banduty.knightsheraldry.platform.services.IPlatformHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public interface KHScreenHandlers {
    Supplier<MenuType<ManuscriptBookScreenHandler>> MANUSCRIPT_BOOK_SCREEN_HANDLER = registerExtended("manuscript_book_gui", ManuscriptBookScreenHandler::new, IManuscriptBookHelper.ManuscriptBookData.STREAM_CODEC);

    @SuppressWarnings("unchecked")
    private static <T extends AbstractContainerMenu, D> Supplier<MenuType<T>> registerExtended(
            String name, IPlatformHelper.IExtendedFactory<T, D> factory, StreamCodec<RegistryFriendlyByteBuf, D> codec) {
        return Services.PLATFORM.register(
                (Registry<MenuType<T>>) (Registry<?>) BuiltInRegistries.MENU,
                name,
                () -> Services.PLATFORM.createMenuType(factory, codec)
        );
    }

    static void register() {
        KnightsHeraldry.LOG.info("Registering Menu for " + KnightsHeraldry.MOD_ID);
    }
}