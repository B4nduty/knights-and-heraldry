package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.stoneycore.items.SCItems;
import net.minecraft.world.item.Item;

public interface KHHelmetDeco {
    static void registerHelmetDeco() {
        KnightsHeraldry.LOG.info("Registering Helmet Deco");

        registerItem(ModItems.PLUME, 1, 0);
        registerItem(SCItems.CROWN, 0, 1);
        registerItem(ModItems.TRI_PLUME, 1, 0);
        registerItem(ModItems.FLUFFY_PLUME, 1, 0);
        registerItem(ModItems.TORSE, 2, 1);
        registerItem(ModItems.TEUTONIC_SNAKES, 0, 0);
        registerItem(ModItems.TEUTONIC_BLACK_SNAKES, 0, 0);
        registerItem(ModItems.GOLD_HORNS, 0, 0);
        registerItem(ModItems.BLACK_HORNS, 0, 0);
        registerItem(ModItems.TEUTONIC_GOLD_WINGS, 0, 0);
        registerItem(ModItems.TEUTONIC_BLACK_WINGS, 0, 0);
        registerItem(ModItems.TEUTONIC_WINGS_BALL_ENDS, 0, 0);
        registerItem(ModItems.TEUTONIC_WINGS_SHARP_ENDS, 0, 0);
        registerItem(ModItems.DRAGON, 0, 0);
        registerItem(ModItems.LION, 0, 0);
        registerItem(ModItems.SNAKE, 0, 0);
        registerItem(ModItems.UNICORN, 0, 0);
        registerItem(ModItems.STAG, 0, 0);
        registerItem(ModItems.BOAR, 0, 0);
        registerItem(ModItems.EAGLE, 0, 0);
        registerItem(ModItems.PEGASUS, 0, 0);
    }

    private static void registerItem(Item item, int color, int group) {
        HelmetDeco.register(item, color, group);
    }
}
