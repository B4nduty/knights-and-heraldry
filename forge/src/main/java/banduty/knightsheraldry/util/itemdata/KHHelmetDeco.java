package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.stoneycore.items.SCItems;
import net.minecraft.world.item.Item;

public interface KHHelmetDeco {
    static void registerHelmetDeco() {
        KnightsHeraldry.LOG.info("Registering Helmet Deco");

        registerItem(ModItems.PLUME.get(), 1, 0);
        registerItem(SCItems.CROWN.get(), 0, 1);
        registerItem(ModItems.TRI_PLUME.get(), 1, 0);
        registerItem(ModItems.FLUFFY_PLUME.get(), 1, 0);
        registerItem(ModItems.TORSE.get(), 2, 1);
        registerItem(ModItems.TEUTONIC_SNAKES.get(), 0, 0);
        registerItem(ModItems.TEUTONIC_BLACK_SNAKES.get(), 0, 0);
        registerItem(ModItems.GOLD_HORNS.get(), 0, 0);
        registerItem(ModItems.BLACK_HORNS.get(), 0, 0);
        registerItem(ModItems.TEUTONIC_GOLD_WINGS.get(), 0, 0);
        registerItem(ModItems.TEUTONIC_BLACK_WINGS.get(), 0, 0);
        registerItem(ModItems.TEUTONIC_WINGS_BALL_ENDS.get(), 0, 0);
        registerItem(ModItems.TEUTONIC_WINGS_SHARP_ENDS.get(), 0, 0);
        registerItem(ModItems.DRAGON.get(), 0, 0);
        registerItem(ModItems.LION.get(), 0, 0);
        registerItem(ModItems.SNAKE.get(), 0, 0);
        registerItem(ModItems.UNICORN.get(), 0, 0);
        registerItem(ModItems.STAG.get(), 0, 0);
        registerItem(ModItems.BOAR.get(), 0, 0);
        registerItem(ModItems.EAGLE.get(), 0, 0);
        registerItem(ModItems.PEGASUS.get(), 0, 0);
    }

    private static void registerItem(Item item, int color, int group) {
        HelmetDeco.register(item, color, group);
    }
}
