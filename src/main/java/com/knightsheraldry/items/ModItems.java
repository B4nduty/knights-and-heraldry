package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
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
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET,
                    4, 10, 0, 
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/gambeson.png"), true));
    public static final Item GAMBESON = registerItem("gambeson",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE,
                    4, 10, 0, 
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/gambeson.png"), true));
    public static final Item GAMBESON_BREECHES = registerItem("gambeson_breeches",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS,
                    4, 10, 0,
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/gambeson.png"), true));
    public static final Item GAMBESON_BOOTS = registerItem("gambeson_boots",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS,
                    4, 10, 0,
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/gambeson.png"), true));

    public static final Item MAIL_COIF = registerItem("mail_coif",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.HELMET,
                    10, 4, 0,
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/mail.png"), false));
    public static final Item HAUBERK = registerItem("hauberk",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE,
                    10, 4, 0,
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/mail.png"), false));
    public static final Item MAIL_BREECHES = registerItem("mail_breeches",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS,
                    10, 4, 0, 
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/mail.png"), false));
    public static final Item MAIL_BOOTS = registerItem("mail_boots",
            new KHArmorItem(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS,
                    10, 4, 0, 
                    new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/mail.png"), false));

    public static final Item MAIL_PAULDRON = registerItem("mail_pauldron",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 0, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/mail_pauldron.png"), false));
    public static final Item BRIGANDINE_PAULDRON = registerItem("brigandine_pauldron",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 1, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/brigandine_pauldron.png"), true));
    public static final Item PLATE_PAULDRON = registerItem("plate_pauldron",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 2, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/plate_pauldron.png"), false));

    public static final Item BRIGANDINE = registerItem("brigandine",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 3, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/brigandine.png"), true));

    public static final Item BRIG_BREASTPLATE = registerItem("brig_breastplate",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/brig_breastplate.png"), true));
    public static final Item BRIG_BREASTPLATE_TASSETS = registerItem("brig_breastplate_tassets",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/brig_breastplate_tassets.png"), true));

    public static final Item PLATE_CUIRASS = registerItem("plate_cuirass",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/plate_cuirass.png"), false));
    public static final Item PLATE_CUIRASS_TASSETS = registerItem("plate_cuirass_tassets",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/plate_cuirass_tassets.png"), false));
    public static final Item MAXIMILLIAN_CUIRASS = registerItem("maximillian_cuirass",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/maximillian_cuirass.png"), false));
    public static final Item MAXIMILLIAN_CUIRASS_TASSETS = registerItem("maximillian_cuirass_tassets",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/maximillian_cuirass_tassets.png"), false));
    public static final Item XIIII_PLATE_CUIRASS = registerItem("xiiii_plate_cuirass",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/xiiii_plate_cuirass.png"), false));
    public static final Item XIIII_PLATE_CUIRASS_TASSETS = registerItem("xiiii_plate_cuirass_tassets",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/xiiii_plate_cuirass_tassets.png"), false));
    public static final Item XIIII_PLATE_BREASTPLATE = registerItem("xiiii_plate_breastplate",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 4, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/xiiii_plate_breastplate.png"), false));

    public static final Item BARBUTE_NO_VISOR = registerItem("barbute_no_visor",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 1, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/barbute_no_visor.png"), false));
    public static final Item BASCINET_NO_VISOR = registerItem("bascinet_no_visor",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 1, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/bascinet_no_visor.png"), false));
    public static final Item KETTLE_HELM = registerItem("kettle_helm",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 1, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/kettle_helm.png"), false));
    public static final Item NASAL_HELM = registerItem("nasal_helm",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 1, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/nasal_helm.png"), false));
    public static final Item VIKING_HELM = registerItem("viking_helm",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 1, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/viking_helm.png"), false));

    public static final Item ARMET = registerItem("armet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/armet.png"), false));
    public static final Item ARMET_2 = registerItem("armet_2",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/armet_2.png"), false));
    public static final Item BARBUTE = registerItem("barbute",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/barbute.png"), false));
    public static final Item BASCINET = registerItem("bascinet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/bascinet.png"), false));
    public static final Item CAGE = registerItem("cage",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/cage.png"), false));
    public static final Item CAGE_2 = registerItem("cage_2",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/cage_2.png"), false));
    public static final Item FLAT_BASCINET = registerItem("flat_bascinet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/flat_bascinet.png"), false));
    public static final Item GREAT_HELM = registerItem("great_helm",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/great_helm.png"), false));
    public static final Item GREAT_HELM_2 = registerItem("great_helm_2",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/great_helm_2.png"), false));
    public static final Item SALLET = registerItem("sallet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 2, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/sallet.png"), false));

    public static final Item FROGMOUTH = registerItem("frogmouth",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 3, 3,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/frogmouth.png"), false));
    public static final Item GREAT_ARMET = registerItem("great_armet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 3, 3,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/great_armet.png"), false));
    public static final Item GREAT_ARMET_2 = registerItem("great_armet_2",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 3, 3,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/great_armet_2.png"), false));
    public static final Item GREAT_BASCINET = registerItem("great_bascinet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 3, 3,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/great_bascinet.png"), false));
    public static final Item GREAT_HOUNDSKUL_BASCINET = registerItem("great_houndskul_bascinet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 3, 3,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/great_houndskul_bascinet.png"), false));
    public static final Item MAXIMILLIAN_HELMET = registerItem("maximillian_helmet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.HELMET, 3, 3,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/maximillian_helmet.png"), false));

    public static final Item GAUNTLET = registerItem("gauntlet",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 2, 0,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/gauntlet.png"), false));

    public static final Item BRIGANDINE_REREBRACE = registerItem("brigandine_rerebrace",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 2, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/brigandine_rerebrace.png"), true));

    public static final Item PLATE_REREBRACE = registerItem("plate_rerebrace",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.CHESTPLATE, 2, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/plate_rerebrace.png"), false));

    public static final Item BRIGANDINE_CHAUSSES = registerItem("brigandine_chausses",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.LEGGINGS, 2, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/brigandine_chausses.png"), true));

    public static final Item PLATE_CHAUSSES = registerItem("plate_chausses",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.LEGGINGS, 2, 2,
                    0.1d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/plate_chausses.png"), false));

    public static final Item SABATONS = registerItem("sabatons",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.BOOTS, 2, 1,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/sabatons.png"), false));

    public static final Item AVENTAIL = registerItem("aventail",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.BOOTS, 0, 2,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/aventail.png"), false));

    public static final Item RIM_GUARDS = registerItem("rim_guards",
            new KHTrinketsItem(new OwoItemSettings().maxCount(1), KHTrinketsItem.Type.BOOTS, 0, 0,
                    0.0d, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"), false));

    private static <T extends Item> T registerItem(String name, T item) {
        Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
        items.add(item);
        return item;
    }

    public static void registerModItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
