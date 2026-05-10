package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.items.SCItems;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public interface KHHelmetDeco {
    static void init() {
        KnightsHeraldry.LOG.info("Initializing Helmet Deco Registry for " + KnightsHeraldry.MOD_ID);

        bind(KHItems.PLUME, 1, 0);
        bind(SCItems.CROWN, 0, 1);
        bind(KHItems.TRI_PLUME, 1, 0);
        bind(KHItems.FLUFFY_PLUME, 1, 0);
        bind(KHItems.TORSE, 2, 1);
        bind(KHItems.TEUTONIC_SNAKES, 0, 0);
        bind(KHItems.TEUTONIC_BLACK_SNAKES, 0, 0);
        bind(KHItems.GOLD_HORNS, 0, 0);
        bind(KHItems.BLACK_HORNS, 0, 0);
        bind(KHItems.TEUTONIC_GOLD_WINGS, 0, 0);
        bind(KHItems.TEUTONIC_BLACK_WINGS, 0, 0);
        bind(KHItems.TEUTONIC_WINGS_BALL_ENDS, 0, 0);
        bind(KHItems.TEUTONIC_WINGS_SHARP_ENDS, 0, 0);
        bind(KHItems.DRAGON, 0, 0);
        bind(KHItems.LION, 0, 0);
        bind(KHItems.SNAKE, 0, 0);
        bind(KHItems.UNICORN, 0, 0);
        bind(KHItems.STAG, 0, 0);
        bind(KHItems.BOAR, 0, 0);
        bind(KHItems.EAGLE, 0, 0);
        bind(KHItems.PEGASUS, 0, 0);
    }

    private static void bind(Supplier<? extends Item> item, int color, int group) {
        HelmetDeco.register(item.get(), color, group);
    }
}