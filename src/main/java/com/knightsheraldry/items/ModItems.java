package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.GambesonItem;
import com.knightsheraldry.items.custom.armor.MailItem;
import com.knightsheraldry.items.custom.armor.PauldronsTrinketsItem;
import com.knightsheraldry.items.custom.item.*;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> items = new ArrayList<>();

    public static final Item SMITHING_HAMMER = registerItem("smithing_hammer",
            new SmithingHammer(new OwoItemSettings().maxCount(1)));

    public static final Item DAGGER = registerItem("dagger",
            new Dagger(-2F, new OwoItemSettings()));

    public static final Item STILETTO = registerItem("stiletto",
            new Stiletto(-2F, new OwoItemSettings()));

    public static final Item RAPIER = registerItem("rapier",
            new Rapier(-2.2F, new OwoItemSettings()));

    public static final Item SWORD = registerItem("sword",
            new Sword(-2.4F, new OwoItemSettings()));

    public static final Item V_SWORD = registerItem("v_sword",
            new Sword(-2.4F, new OwoItemSettings()));

    public static final Item ARMING_SWORD = registerItem("arming_sword",
            new Sword(-2.4F, new OwoItemSettings()));

    public static final Item AXE = registerItem("axe",
            new Axe(-2.6F, new OwoItemSettings()));

    public static final Item BROAD_AXE = registerItem("broad_axe",
            new Axe(-2.6F, new OwoItemSettings()));

    public static final Item CROOKED_AXE = registerItem("crooked_axe",
            new Axe(-2.6F, new OwoItemSettings()));

    public static final Item STRAIGHT_CROOKED_AXE = registerItem("straight_crooked_axe",
            new Axe(-2.6F, new OwoItemSettings()));

    public static final Item MACE = registerItem("mace",
            new Mace(-3.0F, new OwoItemSettings()));

    public static final Item SPIKED_MACE = registerItem("spiked_mace",
            new Mace(-3.0F, new OwoItemSettings()));

    public static final Item FLAIL = registerItem("flail",
            new Flail(-2.8F, new OwoItemSettings()));

    public static final Item BALL_FLAIL = registerItem("ball_flail",
            new Flail(-2.8F, new OwoItemSettings()));

    public static final Item HAMMER = registerItem("hammer",
            new Hammer(-2.8F, new OwoItemSettings()));

    public static final Item WAR_HAMMER = registerItem("war_hammer",
            new Hammer(-2.8F, new OwoItemSettings()));

    public static final Item LONGSWORD = registerItem("longsword",
            new LongSword(-2.5F, new OwoItemSettings()));

    public static final Item V_LONGSWORD = registerItem("v_longsword",
            new LongSword(-2.5F, new OwoItemSettings()));

    public static final Item FALCHION = registerItem("falchion",
            new Falchion(-2.5F, new OwoItemSettings()));

    public static final Item SCIMITAR = registerItem("scimitar",
            new Falchion(-2.5F, new OwoItemSettings()));

    public static final Item KATANA = registerItem("katana",
            new Katana(-2.4F, new OwoItemSettings()));

    public static final Item PITCHFORK = registerItem("pitchfork",
            new Pitchfork(-2.6F, new OwoItemSettings()));

    public static final Item SPEAR = registerItem("spear",
            new Spear(-2.4F, new OwoItemSettings()));

    public static final Item PIKE = registerItem("pike",
            new Pike(-2.8F, new OwoItemSettings()));

    public static final Item BILLHOOK = registerItem("billhook",
            new Billhook(-2.8F, new OwoItemSettings()));

    public static final Item GLAIVE = registerItem("glaive",
            new Glaive(-2.6F, new OwoItemSettings()));

    public static final Item CURVED_GLAIVE = registerItem("curved_glaive",
            new Glaive(-2.6F, new OwoItemSettings()));

    public static final Item HALBERD = registerItem("halberd",
            new Halberd(-2.8F, new OwoItemSettings()));

    public static final Item LANCE = registerItem("lance",
            new Lance(-3.0F, new OwoItemSettings().maxDamage(1)));

    public static final Item WOODEN_LANCE = registerItem("wooden_lance",
            new WoodenLance(-3.0F, new OwoItemSettings().maxDamage(1)));

    public static final Item POLEAXE = registerItem("poleaxe",
            new Poleaxe(-2.8F, new OwoItemSettings()));

    public static final Item POLEHAMMER = registerItem("polehammer",
            new Polehammer(-2.8F, new OwoItemSettings()));

    public static final Item BEC_DE_CORBIN = registerItem("bec_de_corbin",
            new Polehammer(-2.8F, new OwoItemSettings()));

    public static final Item MORNING_STAR = registerItem("morning_star",
            new MorningStar(-3.0F, new OwoItemSettings()));

    public static final Item BARDICHE = registerItem("bardiche",
            new Bardiche(-2.8F, new OwoItemSettings()));

    public static final Item WARSWORD = registerItem("warsword",
            new WarSword(-2.6F, new OwoItemSettings()));

    public static final Item WARSWORD_CLAYMORE = registerItem("warsword_claymore",
            new WarSword(-2.6F, new OwoItemSettings()));

    public static final Item WARSWORD_FLAMBERGE = registerItem("warsword_flamberge",
            new WarSword(-2.6F, new OwoItemSettings()));

    public static final Item WARSWORD_ZWEIHANDER = registerItem("warsword_zweihander",
            new WarSword(-2.6F, new OwoItemSettings()));

    public static final Item WARDART = registerItem("wardart",
            new WarDart(-2.4F, new OwoItemSettings()));


    public static final Item QUILTED_COIF = registerItem("quilted_coif",
            new GambesonItem(ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET, new OwoItemSettings()));
    public static final Item GAMBESON = registerItem("gambeson",
            new GambesonItem(ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE, new OwoItemSettings()));
    public static final Item GAMBESON_BREECHES = registerItem("gambeson_breeches",
            new GambesonItem(ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS, new OwoItemSettings()));
    public static final Item GAMBESON_BOOTS = registerItem("gambeson_boots",
            new GambesonItem(ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS, new OwoItemSettings()));


    public static final Item MAIL_COIF = registerItem("mail_coif",
            new MailItem(ModArmorMaterials.MAIL, ArmorItem.Type.HELMET, new OwoItemSettings()));
    public static final Item HAUBERK = registerItem("hauberk",
            new MailItem(ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE, new OwoItemSettings()));
    public static final Item MAIL_BREECHES = registerItem("mail_breeches",
            new MailItem(ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS, new OwoItemSettings()));
    public static final Item MAIL_BOOTS = registerItem("mail_boots",
            new MailItem(ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS, new OwoItemSettings()));

    public static final Item MAIL_PAULDRON = registerItem("mail_pauldron",
            new PauldronsTrinketsItem(new OwoItemSettings().maxCount(1)));
    public static final Item BRIGANDINE_PAULDRON = registerItem("brigandine_pauldron",
            new PauldronsTrinketsItem(new OwoItemSettings().maxCount(1)));
    public static final Item PLATE_PAULDRON = registerItem("plate_pauldron",
            new PauldronsTrinketsItem(new OwoItemSettings().maxCount(1)));

    private static <T extends Item> T registerItem(String name, T item) {
        Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
        items.add(item);
        return item;
    }

    public static void registerModItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
