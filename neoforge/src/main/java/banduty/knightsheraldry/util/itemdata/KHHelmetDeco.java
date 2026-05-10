package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.items.SCItems;
import net.minecraft.world.item.Item;

public interface KHHelmetDeco {
    static void registerHelmetDeco() {
        KnightsHeraldry.LOG.info("Registering Helmet Deco");

        registerItem(KHItems.PLUME.get(), 1, 0);
        registerItem(SCItems.CROWN.get(), 0, 1);
        registerItem(KHItems.TRI_PLUME.get(), 1, 0);
        registerItem(KHItems.FLUFFY_PLUME.get(), 1, 0);
        registerItem(KHItems.TORSE.get(), 2, 1);
        registerItem(KHItems.TEUTONIC_SNAKES.get(), 0, 0);
        registerItem(KHItems.TEUTONIC_BLACK_SNAKES.get(), 0, 0);
        registerItem(KHItems.GOLD_HORNS.get(), 0, 0);
        registerItem(KHItems.BLACK_HORNS.get(), 0, 0);
        registerItem(KHItems.TEUTONIC_GOLD_WINGS.get(), 0, 0);
        registerItem(KHItems.TEUTONIC_BLACK_WINGS.get(), 0, 0);
        registerItem(KHItems.TEUTONIC_WINGS_BALL_ENDS.get(), 0, 0);
        registerItem(KHItems.TEUTONIC_WINGS_SHARP_ENDS.get(), 0, 0);
        registerItem(KHItems.DRAGON.get(), 0, 0);
        registerItem(KHItems.LION.get(), 0, 0);
        registerItem(KHItems.SNAKE.get(), 0, 0);
        registerItem(KHItems.UNICORN.get(), 0, 0);
        registerItem(KHItems.STAG.get(), 0, 0);
        registerItem(KHItems.BOAR.get(), 0, 0);
        registerItem(KHItems.EAGLE.get(), 0, 0);
        registerItem(KHItems.PEGASUS.get(), 0, 0);
    }

    private static void registerItem(Item item, int color, int group) {
        HelmetDeco.register(item, color, group);
    }
}
