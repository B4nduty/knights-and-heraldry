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
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.items.armor.accessory.*;
import com.knightsheraldry.items.item.DyeableItems;
import com.knightsheraldry.items.item.SmithingHammer;
import com.knightsheraldry.items.item.khrangeweapon.Arquebus;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import com.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import com.knightsheraldry.items.item.khweapon.*;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
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
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2F, new OwoItemSettings().maxDamage(251)));

    public static final Item STILETTO = registerItem("stiletto",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2F, new OwoItemSettings().maxDamage(251)));

    public static final Item RAPIER = registerItem("rapier",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.2F, new OwoItemSettings().maxDamage(251)));

    public static final Item SWORD = registerItem("sword",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new OwoItemSettings().maxDamage(251)));
    public static final Item V_SWORD = registerItem("v_sword",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new OwoItemSettings().maxDamage(251)));
    public static final Item ARMING_SWORD = registerItem("arming_sword",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new OwoItemSettings().maxDamage(251)));

    public static final Item AXE = registerItem("axe",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item BROAD_AXE = registerItem("broad_axe",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item CROOKED_AXE = registerItem("crooked_axe",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item STRAIGHT_CROOKED_AXE = registerItem("straight_crooked_axe",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item MACE = registerItem("mace",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.0F, new OwoItemSettings().maxDamage(251)));
    public static final Item SPIKED_MACE = registerItem("spiked_mace",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.0F, new OwoItemSettings().maxDamage(251)));

    public static final Item FLAIL = registerItem("flail",
            new Flail(-2.8F, new OwoItemSettings().maxDamage(251)));
    public static final Item BALL_FLAIL = registerItem("ball_flail",
            new Flail(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item HAMMER = registerItem("hammer",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));
    public static final Item WAR_HAMMER = registerItem("war_hammer",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item LONGSWORD = registerItem("longsword",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new OwoItemSettings().maxDamage(251)));
    public static final Item V_LONGSWORD = registerItem("v_longsword",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new OwoItemSettings().maxDamage(251)));

    public static final Item FALCHION = registerItem("falchion",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new OwoItemSettings().maxDamage(251)));
    public static final Item SCIMITAR = registerItem("scimitar",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new OwoItemSettings().maxDamage(251)));

    public static final Item PITCHFORK = registerItem("pitchfork",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item SPEAR = registerItem("spear",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new OwoItemSettings().maxDamage(251)));

    public static final Item PIKE = registerItem("pike",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item BILLHOOK = registerItem("billhook",
            new Billhook(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item GLAIVE = registerItem("glaive",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item CURVED_GLAIVE = registerItem("curved_glaive",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item HALBERD = registerItem("halberd",
            new Halberd(-2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item LANCE = registerItem("lance",
            new Lance(-3.0F, new OwoItemSettings().maxDamage(251), SCDamageCalculator.DamageType.PIERCING));
    public static final Item WOODEN_LANCE = registerItem("wooden_lance",
            new WoodenLance(-3.0F, new OwoItemSettings().maxDamage(1), SCDamageCalculator.DamageType.PIERCING));

    public static final Item POLEAXE = registerItem("poleaxe",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item POLEHAMMER = registerItem("polehammer",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));
    public static final Item BEC_DE_CORBIN = registerItem("bec_de_corbin",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item MORNING_STAR = registerItem("morning_star",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.0F, new OwoItemSettings().maxDamage(251)));

    public static final Item BARDICHE = registerItem("bardiche",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new OwoItemSettings().maxDamage(251)));

    public static final Item WARSWORD = registerItem("warsword",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item WARSWORD_CLAYMORE = registerItem("warsword_claymore",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item WARSWORD_FLAMBERGE = registerItem("warsword_flamberge",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));
    public static final Item WARSWORD_ZWEIHANDER = registerItem("warsword_zweihander",
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new OwoItemSettings().maxDamage(251)));

    public static final Item WARDART = registerItem("wardart",
            new WarDart(-2.4F, new OwoItemSettings().maxDamage(251)));


    public static final Item QUILTED_COIF = registerItem("quilted_coif",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET, 10511680));
    public static final Item GAMBESON = registerItem("gambeson",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE, 10511680));
    public static final Item GAMBESON_BREECHES = registerItem("gambeson_breeches",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS, 10511680));
    public static final Item GAMBESON_BOOTS = registerItem("gambeson_boots",
            new SCDyeableUnderArmor(new OwoItemSettings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS, 10511680));

    public static final Item MAIL_COIF = registerItem("mail_coif",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.HELMET));
    public static final Item HAUBERK = registerItem("hauberk",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE));
    public static final Item MAIL_BREECHES = registerItem("mail_breeches",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS));
    public static final Item MAIL_BOOTS = registerItem("mail_boots",
            new SCUnderArmor(new OwoItemSettings(), ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS));

    public static final Item MAIL_PAULDRON = registerItem("mail_pauldron",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item BRIGANDINE_PAULDRON = registerItem("brigandine_pauldron",
            new KHDyeableChestplateAccessory(new OwoItemSettings().maxCount(1), true, 10511680));
    public static final Item PLATE_PAULDRON = registerItem("plate_pauldron",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item BRIGANDINE = registerItem("brigandine",
            new KHDyeableChestplateAccessory(new OwoItemSettings().maxCount(1), true, 10511680));

    public static final Item BRIG_BREASTPLATE = registerItem("brig_breastplate",
            new KHDyeableChestplateAccessory(new OwoItemSettings().maxCount(1), true, 10511680));
    public static final Item BRIG_BREASTPLATE_TASSETS = registerItem("brig_breastplate_tassets",
            new KHDyeableChestplateAccessory(new OwoItemSettings().maxCount(1), true, 10511680));

    public static final Item PLATE_CUIRASS = registerItem("plate_cuirass",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item PLATE_CUIRASS_TASSETS = registerItem("plate_cuirass_tassets",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item MAXIMILLIAN_CUIRASS = registerItem("maximillian_cuirass",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item MAXIMILLIAN_CUIRASS_TASSETS = registerItem("maximillian_cuirass_tassets",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item XIIII_PLATE_CUIRASS = registerItem("xiiii_plate_cuirass",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item XIIII_PLATE_CUIRASS_TASSETS = registerItem("xiiii_plate_cuirass_tassets",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item XIIII_PLATE_BREASTPLATE = registerItem("xiiii_plate_breastplate",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item BARBUTE_NO_VISOR = registerItem("barbute_no_visor",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item BASCINET_NO_VISOR = registerItem("bascinet_no_visor",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item KETTLE_HELM = registerItem("kettle_helm",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item NASAL_HELM = registerItem("nasal_helm",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item VIKING_HELM = registerItem("viking_helm",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item ARMET = registerItem("armet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item ARMET_2 = registerItem("armet_2",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item BARBUTE = registerItem("barbute",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item BASCINET = registerItem("bascinet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item CAGE = registerItem("cage",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item CAGE_2 = registerItem("cage_2",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item FLAT_BASCINET = registerItem("flat_bascinet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item GREAT_HELM = registerItem("great_helm",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item GREAT_HELM_2 = registerItem("great_helm_2",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item SALLET = registerItem("sallet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item FROGMOUTH = registerItem("frogmouth",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item GREAT_ARMET = registerItem("great_armet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item GREAT_ARMET_2 = registerItem("great_armet_2",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item GREAT_BASCINET = registerItem("great_bascinet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item GREAT_HOUNDSKUL_BASCINET = registerItem("great_houndskul_bascinet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item MAXIMILLIAN_HELMET = registerItem("maximillian_helmet",
            new KHHelmetAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item GAUNTLET = registerItem("gauntlet",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item BRIGANDINE_REREBRACE = registerItem("brigandine_rerebrace",
            new KHDyeableChestplateAccessory(new OwoItemSettings().maxCount(1), true, 10511680));
    public static final Item PLATE_REREBRACE = registerItem("plate_rerebrace",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item BRIGANDINE_CHAUSSES = registerItem("brigandine_chausses",
            new KHDyeableLeggingsAccessory(new OwoItemSettings().maxCount(1), true, 10511680));
    public static final Item PLATE_CHAUSSES = registerItem("plate_chausses",
            new KHLeggingsAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item SABATONS = registerItem("sabatons",
            new KHBootsAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item AVENTAIL = registerItem("aventail",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item RIM_GUARDS = registerItem("rim_guards",
            new Item(new OwoItemSettings().maxCount(1)));

    public static final Item BESAGEWS = registerItem("besagews",
            new Item(new OwoItemSettings().maxCount(1)));

    public static final Item SURCOAT = registerItem("surcoat",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));
    public static final Item SURCOAT_SLEEVELESS = registerItem("surcoat_sleeveless",
            new KHChestplateAccessory(new OwoItemSettings().maxCount(1)));

    public static final Item CLOAK = registerItem("cloak",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    false));
    public static final Item TORN_CLOAK = registerItem("torn_cloak",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    false));

    public static final Item HOOD = registerItem("hood",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    false));
    public static final Item TORN_HOOD = registerItem("torn_hood",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    false));
    public static final Item JESTER_HOOD = registerItem("jester_hood",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    true));
    public static final Item HELMET_HOOD = registerItem("helmet_hood",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    false));
    public static final Item HELMET_TORN_HOOD = registerItem("helmet_torn_hood",
            new KHCloak(new OwoItemSettings().maxCount(1),
                    false));

    public static final Item BLACK_POWDER = registerItem("black_powder",
            new Item(new OwoItemSettings()));

    public static final Item LONGBOW = registerItem("longbow",
            new Item(new OwoItemSettings().maxCount(1).maxDamage(512)));

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

    public static final Item HORSE_BARDING = registerItem("horse_barding",
            new HorseBardingArmorItem(7, new OwoItemSettings().maxCount(1)));

    public static final Item PLUME = registerItem("plume",
            new DyeableItems(new OwoItemSettings().maxCount(1)));

    public static final Item CHAPERON = registerItem("chaperon",
            new KHChaperon(new OwoItemSettings().maxCount(1), false));

    public static final Item GILDED_CHAPERON = registerItem("gilded_chaperon",
            new KHChaperon(new OwoItemSettings().maxCount(1), true));

    private static <T extends Item> T registerItem(String name, T item) {
        Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
        items.add(item);
        return item;
    }

    public static void registerItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
