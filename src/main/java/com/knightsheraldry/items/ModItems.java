package com.knightsheraldry.items;

import banduty.stoneycore.items.armor.underarmor.SCDyeableUnderArmor;
import banduty.stoneycore.items.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.item.SCArrow;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.KHBodkinArrowEntity;
import com.knightsheraldry.entity.custom.KHBroadheadArrowEntity;
import com.knightsheraldry.entity.custom.KHClothArrowEntity;
import com.knightsheraldry.entity.custom.KHSwallowTailArrowEntity;
import com.knightsheraldry.items.armor.trinkets.*;
import com.knightsheraldry.items.item.SmithingHammer;
import com.knightsheraldry.items.item.khrangeweapon.Arquebus;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import com.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import com.knightsheraldry.items.item.khrangeweapon.Longbow;
import com.knightsheraldry.items.item.khweapon.*;
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
            new Dagger(-2F, new OwoItemSettings().maxDamage(251)));

    public static final Item STILETTO = registerItem("stiletto",
            new Stiletto(-2F, new OwoItemSettings().maxDamage(251)));

    public static final Item RAPIER = registerItem("rapier",
            new Rapier(-2.2F, new OwoItemSettings().maxDamage(251)));

    public static final Item SWORD = registerItem("sword",
            new Sword(-2.4F, new OwoItemSettings().maxDamage(251)));
    public static final Item V_SWORD = registerItem("v_sword",
            new Sword(-2.4F, new OwoItemSettings().maxDamage(251)));
    public static final Item ARMING_SWORD = registerItem("arming_sword",
            new Sword(-2.4F, new OwoItemSettings().maxDamage(251)));

    public static final Item AXE = registerItem("axe",
            new Axe(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item BROAD_AXE = registerItem("broad_axe",
            new Axe(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item CROOKED_AXE = registerItem("crooked_axe",
            new Axe(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item STRAIGHT_CROOKED_AXE = registerItem("straight_crooked_axe",
            new Axe(-2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item MACE = registerItem("mace",
            new Mace(-3.0F, new OwoItemSettings().maxDamage(251)));
    public static final Item SPIKED_MACE = registerItem("spiked_mace",
            new Mace(-3.0F, new OwoItemSettings().maxDamage(251)));

    public static final Item FLAIL = registerItem("flail",
            new Flail(-2.8F, new OwoItemSettings().maxDamage(251)));
    public static final Item BALL_FLAIL = registerItem("ball_flail",
            new Flail(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item HAMMER = registerItem("hammer",
            new Hammer(-2.8F, new OwoItemSettings().maxDamage(251)));
    public static final Item WAR_HAMMER = registerItem("war_hammer",
            new Hammer(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item LONGSWORD = registerItem("longsword",
            new LongSword(-2.5F, new OwoItemSettings().maxDamage(251)));
    public static final Item V_LONGSWORD = registerItem("v_longsword",
            new LongSword(-2.5F, new OwoItemSettings().maxDamage(251)));

    public static final Item FALCHION = registerItem("falchion",
            new Falchion(-2.5F, new OwoItemSettings().maxDamage(251)));
    public static final Item SCIMITAR = registerItem("scimitar",
            new Falchion(-2.5F, new OwoItemSettings().maxDamage(251)));

    public static final Item PITCHFORK = registerItem("pitchfork",
            new Pitchfork(-2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item SPEAR = registerItem("spear",
            new Spear(-2.4F, new OwoItemSettings().maxDamage(251)));

    public static final Item PIKE = registerItem("pike",
            new Pike(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item BILLHOOK = registerItem("billhook",
            new Billhook(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item GLAIVE = registerItem("glaive",
            new Glaive(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item CURVED_GLAIVE = registerItem("curved_glaive",
            new Glaive(-2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item HALBERD = registerItem("halberd",
            new Halberd(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item LANCE = registerItem("lance",
            new Lance(-3.0F, new OwoItemSettings().maxDamage(251), SCDamageCalculator.DamageType.PIERCING));
    public static final Item WOODEN_LANCE = registerItem("wooden_lance",
            new WoodenLance(-3.0F, new OwoItemSettings().maxDamage(1), SCDamageCalculator.DamageType.PIERCING));

    public static final Item POLEAXE = registerItem("poleaxe",
            new Poleaxe(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item POLEHAMMER = registerItem("polehammer",
            new Polehammer(-2.8F, new OwoItemSettings().maxDamage(251)));
    public static final Item BEC_DE_CORBIN = registerItem("bec_de_corbin",
            new Polehammer(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item MORNING_STAR = registerItem("morning_star",
            new MorningStar(-3.0F, new OwoItemSettings().maxDamage(251)));

    public static final Item BARDICHE = registerItem("bardiche",
            new Bardiche(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item WARSWORD = registerItem("warsword",
            new WarSword(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item WARSWORD_CLAYMORE = registerItem("warsword_claymore",
            new WarSword(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item WARSWORD_FLAMBERGE = registerItem("warsword_flamberge",
            new WarSword(-2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item WARSWORD_ZWEIHANDER = registerItem("warsword_zweihander",
            new WarSword(-2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item WARDART = registerItem("wardart",
            new WarDart(-2.4F, new OwoItemSettings().maxDamage(251)));


    public static final Item QUILTED_COIF = registerItem("quilted_coif",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET,
                    0.04d, 0.1d, 0, 10511680));
    public static final Item GAMBESON = registerItem("gambeson",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE,
                    0.04d, 0.1d, 0, 10511680));
    public static final Item GAMBESON_BREECHES = registerItem("gambeson_breeches",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS,
                    0.04d, 0.1d, 0, 10511680));
    public static final Item GAMBESON_BOOTS = registerItem("gambeson_boots",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS,
                    0.04d, 0.1d, 0, 10511680));

    public static final Item MAIL_COIF = registerItem("mail_coif",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.HELMET,
                    0.1d, 0.04d, 0));
    public static final Item HAUBERK = registerItem("hauberk",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE,
                    0.1d, 0.04d, 0));
    public static final Item MAIL_BREECHES = registerItem("mail_breeches",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS,
                    0.1d, 0.04d, 0));
    public static final Item MAIL_BOOTS = registerItem("mail_boots",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS,
                    0.1d, 0.04d, 0));

    public static final Item MAIL_PAULDRON = registerItem("mail_pauldron",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 0, 1, 0.0d));
    public static final Item BRIGANDINE_PAULDRON = registerItem("brigandine_pauldron",
            new KHDyeableChestplateTrinkets(new OwoItemSettings().maxCount(1), 1, 1, 0.0d,
                    true, 10511680));
    public static final Item PLATE_PAULDRON = registerItem("plate_pauldron",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.1d));

    public static final Item BRIGANDINE = registerItem("brigandine",
            new KHDyeableChestplateTrinkets(new OwoItemSettings().maxCount(1), 3, 1, 0.0d,
                    true, 10511680));

    public static final Item BRIG_BREASTPLATE = registerItem("brig_breastplate",
            new KHDyeableChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 1, 0.0d,
                    true, 10511680));
    public static final Item BRIG_BREASTPLATE_TASSETS = registerItem("brig_breastplate_tassets",
            new KHDyeableChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 1, 0.0d,
                    true, 10511680));

    public static final Item PLATE_CUIRASS = registerItem("plate_cuirass",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));
    public static final Item PLATE_CUIRASS_TASSETS = registerItem("plate_cuirass_tassets",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));
    public static final Item MAXIMILLIAN_CUIRASS = registerItem("maximillian_cuirass",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));
    public static final Item MAXIMILLIAN_CUIRASS_TASSETS = registerItem("maximillian_cuirass_tassets",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));
    public static final Item XIIII_PLATE_CUIRASS = registerItem("xiiii_plate_cuirass",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));
    public static final Item XIIII_PLATE_CUIRASS_TASSETS = registerItem("xiiii_plate_cuirass_tassets",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));
    public static final Item XIIII_PLATE_BREASTPLATE = registerItem("xiiii_plate_breastplate",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 4, 2, 0.1d));

    public static final Item BARBUTE_NO_VISOR = registerItem("barbute_no_visor",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 1, 1, 0.0d));
    public static final Item BASCINET_NO_VISOR = registerItem("bascinet_no_visor",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 1, 1, 0.0d));
    public static final Item KETTLE_HELM = registerItem("kettle_helm",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 1, 1, 0.0d));
    public static final Item NASAL_HELM = registerItem("nasal_helm",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 1, 1, 0.0d));
    public static final Item VIKING_HELM = registerItem("viking_helm",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 1, 1, 0.0d));

    public static final Item ARMET = registerItem("armet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item ARMET_2 = registerItem("armet_2",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item BARBUTE = registerItem("barbute",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item BASCINET = registerItem("bascinet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item CAGE = registerItem("cage",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item CAGE_2 = registerItem("cage_2",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item FLAT_BASCINET = registerItem("flat_bascinet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item GREAT_HELM = registerItem("great_helm",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item GREAT_HELM_2 = registerItem("great_helm_2",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));
    public static final Item SALLET = registerItem("sallet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.0d));

    public static final Item FROGMOUTH = registerItem("frogmouth",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 3, 3, 0.1d));
    public static final Item GREAT_ARMET = registerItem("great_armet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 3, 3, 0.1d));
    public static final Item GREAT_ARMET_2 = registerItem("great_armet_2",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 3, 3, 0.1d));
    public static final Item GREAT_BASCINET = registerItem("great_bascinet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 3, 3, 0.1d));
    public static final Item GREAT_HOUNDSKUL_BASCINET = registerItem("great_houndskul_bascinet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 3, 3, 0.1d));
    public static final Item MAXIMILLIAN_HELMET = registerItem("maximillian_helmet",
            new KHHelmetTrinkets(new OwoItemSettings().maxCount(1), 3, 3, 0.1d));

    public static final Item GAUNTLET = registerItem("gauntlet",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 2, 0, 0.0d));
    public static final Item BRIGANDINE_REREBRACE = registerItem("brigandine_rerebrace",
            new KHDyeableChestplateTrinkets(new OwoItemSettings().maxCount(1), 2, 1, 0.0d,
                    true, 10511680));
    public static final Item PLATE_REREBRACE = registerItem("plate_rerebrace",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.1d));

    public static final Item BRIGANDINE_CHAUSSES = registerItem("brigandine_chausses",
            new KHDyeableLeggingsTrinkets(new OwoItemSettings().maxCount(1), 2, 1, 0.0d,
                    true, 10511680));
    public static final Item PLATE_CHAUSSES = registerItem("plate_chausses",
            new KHLeggingsTrinkets(new OwoItemSettings().maxCount(1), 2, 2, 0.1d));

    public static final Item SABATONS = registerItem("sabatons",
            new KHBootsTrinkets(new OwoItemSettings().maxCount(1), 2, 1, 0.0d));

    public static final Item AVENTAIL = registerItem("aventail",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 0, 2, 0.0d));

    public static final Item RIM_GUARDS = registerItem("rim_guards",
            new Item(new OwoItemSettings().maxCount(1)));

    public static final Item BESAGEWS = registerItem("besagews",
            new Item(new OwoItemSettings().maxCount(1)));

    public static final Item SURCOAT = registerItem("surcoat",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 0, 0, 0.0d));
    public static final Item SURCOAT_SLEEVELESS = registerItem("surcoat_sleeveless",
            new KHChestplateTrinkets(new OwoItemSettings().maxCount(1), 0, 0, 0.0d));

    public static final Item CLOAK = registerItem("cloak",
            new KHCloakTrinkets(new OwoItemSettings().maxCount(1), 0, 0, 0.0d,
                    false, 10511680));
    public static final Item TORN_CLOAK = registerItem("torn_cloak",
            new KHCloakTrinkets(new OwoItemSettings().maxCount(1), 0, 0, 0.0d,
                    false, 10511680));

    public static final Item HOOD = registerItem("hood",
            new KHCloakTrinkets(new OwoItemSettings().maxCount(1), 0, 0, 0.0d,
                    false, 10511680));
    public static final Item TORN_HOOD = registerItem("torn_hood",
            new KHCloakTrinkets(new OwoItemSettings().maxCount(1), 0, 0, 0.0d,
                    false, 10511680));

    public static final Item BLACK_POWDER = registerItem("black_powder",
            new Item(new OwoItemSettings()));

    public static final Item LONGBOW = registerItem("longbow",
            new Longbow(new OwoItemSettings().maxCount(1).maxDamage(512)));

    public static final Item HEAVY_CROSSBOW = registerItem("heavy_crossbow",
            new HeavyCrossbow(new OwoItemSettings().maxCount(1).maxDamage(512)));

    public static final Item ARQUEBUS = registerItem("arquebus",
            new Arquebus(new OwoItemSettings().maxCount(1).maxDamage(512)));

    public static final Item HANDGONNE = registerItem("handgonne",
            new Handgonne(new OwoItemSettings().maxCount(1).maxDamage(512)));

    public static final Item SWALLOWTAIL_ARROW = registerItem("swallowtail_arrow",
            new SCArrow(new OwoItemSettings(), KHSwallowTailArrowEntity::new));
    public static final Item BODKIN_ARROW = registerItem("bodkin_arrow",
            new SCArrow(new OwoItemSettings(), KHBodkinArrowEntity::new));
    public static final Item BROADHEAD_ARROW = registerItem("broadhead_arrow",
            new SCArrow(new OwoItemSettings(), KHBroadheadArrowEntity::new));
    public static final Item CLOTH_ARROW = registerItem("cloth_arrow",
            new SCArrow(new OwoItemSettings(), KHClothArrowEntity::new));

    private static <T extends Item> T registerItem(String name, T item) {
        Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
        items.add(item);
        return item;
    }

    public static void registerItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
