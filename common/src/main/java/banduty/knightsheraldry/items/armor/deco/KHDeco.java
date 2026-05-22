package banduty.knightsheraldry.items.armor.deco;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.items.custom.armor.deco.Deco;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface KHDeco {
    static void init() {
        KnightsHeraldry.LOG.info("Initializing Helmet Deco Registry for " + KnightsHeraldry.MOD_ID);

        bind(KHItems.PLUME, 1, ArmorItem.Type.HELMET, ArmorItem.Type.BODY);
        bind(KHItems.TRI_PLUME, 1, ArmorItem.Type.HELMET);
        bind(KHItems.FLUFFY_PLUME, 1, ArmorItem.Type.HELMET);
        bind(SCItems.CROWN, 1, ArmorItem.Type.HELMET);
        bind(KHItems.TORSE, 1, ArmorItem.Type.HELMET);
        bind(KHItems.TEUTONIC_SNAKES, 0, ArmorItem.Type.HELMET);
        bind(KHItems.TEUTONIC_BLACK_SNAKES, 0, ArmorItem.Type.HELMET);
        bind(KHItems.GOLD_HORNS, 0, ArmorItem.Type.HELMET);
        bind(KHItems.BLACK_HORNS, 0, ArmorItem.Type.HELMET);
        bind(KHItems.TEUTONIC_GOLD_WINGS, 0, ArmorItem.Type.HELMET);
        bind(KHItems.TEUTONIC_BLACK_WINGS, 0, ArmorItem.Type.HELMET);
        bind(KHItems.TEUTONIC_WINGS_BALL_ENDS, 0, ArmorItem.Type.HELMET);
        bind(KHItems.TEUTONIC_WINGS_SHARP_ENDS, 0, ArmorItem.Type.HELMET);
        bind(KHItems.DRAGON, 0, ArmorItem.Type.HELMET);
        bind(KHItems.LION, 0, ArmorItem.Type.HELMET);
        bind(KHItems.SNAKE, 0, ArmorItem.Type.HELMET);
        bind(KHItems.UNICORN, 0, ArmorItem.Type.HELMET);
        bind(KHItems.STAG, 0, ArmorItem.Type.HELMET);
        bind(KHItems.BOAR, 0, ArmorItem.Type.HELMET);
        bind(KHItems.EAGLE, 0, ArmorItem.Type.HELMET);
        bind(KHItems.PEGASUS, 0, ArmorItem.Type.HELMET);
    }

    private static void bind(Supplier<? extends Item> item, int group, ArmorItem.Type... allowedArmorTypes) {
        Deco.register(item.get(), group, allowedArmorTypes);
    }
}