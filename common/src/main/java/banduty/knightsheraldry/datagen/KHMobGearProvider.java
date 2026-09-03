package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.MobGearDataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.List;

public class KHMobGearProvider extends MobGearDataProvider {
    private static final List<ResourceLocation> ALLOWED_MOBS = mobs(
            EntityType.ZOMBIE,
            EntityType.HUSK,
            EntityType.DROWNED,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    );

    public KHMobGearProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addEntries() {
        addWeapons();
        addBaseArmor();
        addAttachments();
    }

    private void addWeapons() {
        weapons(ALLOWED_MOBS,
                KHItems.DAGGER, KHItems.STILETTO, KHItems.RAPIER,
                KHItems.SWORD, KHItems.V_SWORD, KHItems.ARMING_SWORD,
                KHItems.AXE, KHItems.BROAD_AXE, KHItems.CROOKED_AXE, KHItems.STRAIGHT_CROOKED_AXE,
                KHItems.MACE, KHItems.SPIKED_MACE,
                KHItems.FLAIL, KHItems.BALL_FLAIL,
                KHItems.HAMMER, KHItems.WAR_HAMMER,
                KHItems.LONGSWORD, KHItems.V_LONGSWORD,
                KHItems.FALCHION, KHItems.SCIMITAR,
                KHItems.PITCHFORK, KHItems.SPEAR, KHItems.PIKE,
                KHItems.BILLHOOK,
                KHItems.GLAIVE, KHItems.CURVED_GLAIVE,
                KHItems.HALBERD,
                KHItems.POLEAXE, KHItems.POLEHAMMER, KHItems.BEC_DE_CORBIN,
                KHItems.MORNING_STAR, KHItems.BARDICHE,
                KHItems.GREATSWORD, KHItems.CLAYMORE, KHItems.FLAMBERGE, KHItems.ZWEIHANDER,
                KHItems.WARDART
        );
    }

    private void addBaseArmor() {
        armorSet(KHItems.QUILTED_COIF, KHItems.GAMBESON, KHItems.GAMBESON_BREECHES, KHItems.GAMBESON_BOOTS, ALLOWED_MOBS);
        armorSet(KHItems.MAIL_COIF, KHItems.HAUBERK, KHItems.MAIL_BREECHES, KHItems.MAIL_BOOTS, ALLOWED_MOBS);

        armor(EquipmentSlot.CHEST, KHItems.ARMING_DOUBLET, ALLOWED_MOBS);
        armor(EquipmentSlot.LEGS, KHItems.ARMING_HOSE, ALLOWED_MOBS);
    }

    private void addAttachments() {
        attachments(EquipmentSlot.HEAD, ALLOWED_MOBS,
                KHItems.BARBUTE, KHItems.DARK_BARBUTE, KHItems.GOLDEN_BARBUTE,
                KHItems.BASCINET, KHItems.DARK_BASCINET, KHItems.GOLDEN_BASCINET,
                KHItems.KETTLE_HELM, KHItems.DARK_KETTLE_HELM, KHItems.GOLDEN_KETTLE_HELM,
                KHItems.NASAL_HELM, KHItems.DARK_NASAL_HELM, KHItems.GOLDEN_NASAL_HELM,
                KHItems.VIKING_HELM, KHItems.DARK_VIKING_HELM, KHItems.GOLDEN_VIKING_HELM,
                KHItems.BURGONET, KHItems.DARK_BURGONET, KHItems.GOLDEN_BURGONET,
                KHItems.VISORLESS_SALLET, KHItems.DARK_VISORLESS_SALLET, KHItems.GOLDEN_VISORLESS_SALLET,
                KHItems.MORION, KHItems.DARK_MORION, KHItems.GOLDEN_MORION,
                KHItems.ARMET, KHItems.DARK_ARMET, KHItems.GOLDEN_ARMET,
                KHItems.ARMET_2, KHItems.DARK_ARMET_2, KHItems.GOLDEN_ARMET_2,
                KHItems.VISORED_BARBUTE, KHItems.DARK_VISORED_BARBUTE, KHItems.GOLDEN_VISORED_BARBUTE,
                KHItems.HOUNDSKULL, KHItems.DARK_HOUNDSKULL, KHItems.GOLDEN_HOUNDSKULL,
                KHItems.CAGE, KHItems.DARK_CAGE, KHItems.GOLDEN_CAGE,
                KHItems.VISORED_BASCINET, KHItems.DARK_VISORED_BASCINET, KHItems.GOLDEN_VISORED_BASCINET,
                KHItems.GREAT_HELM, KHItems.DARK_GREAT_HELM, KHItems.GOLDEN_GREAT_HELM,
                KHItems.GREAT_HELM_2, KHItems.DARK_GREAT_HELM_2, KHItems.GOLDEN_GREAT_HELM_2,
                KHItems.SALLET, KHItems.DARK_SALLET, KHItems.GOLDEN_SALLET,
                KHItems.BURGONET_FALLING_BUFFE, KHItems.DARK_BURGONET_FALLING_BUFFE, KHItems.GOLDEN_BURGONET_FALLING_BUFFE,
                KHItems.CLOSE_HELM, KHItems.DARK_CLOSE_HELM, KHItems.GOLDEN_CLOSE_HELM,
                KHItems.BLACK_SALLET, KHItems.DARK_BLACK_SALLET, KHItems.GOLDEN_BLACK_SALLET,
                KHItems.VISORED_MORION, KHItems.DARK_VISORED_MORION, KHItems.GOLDEN_VISORED_MORION,
                KHItems.SALLET_BEVOR, KHItems.DARK_SALLET_BEVOR, KHItems.GOLDEN_SALLET_BEVOR,
                KHItems.BLACK_SALLET_BEVOR, KHItems.DARK_BLACK_SALLET_BEVOR, KHItems.GOLDEN_BLACK_SALLET_BEVOR,
                KHItems.FROGMOUTH, KHItems.DARK_FROGMOUTH, KHItems.GOLDEN_FROGMOUTH,
                KHItems.GREAT_ARMET, KHItems.DARK_GREAT_ARMET, KHItems.GOLDEN_GREAT_ARMET,
                KHItems.GREAT_ARMET_2, KHItems.DARK_GREAT_ARMET_2, KHItems.GOLDEN_GREAT_ARMET_2,
                KHItems.GREAT_BASCINET, KHItems.DARK_GREAT_BASCINET, KHItems.GOLDEN_GREAT_BASCINET,
                KHItems.GREAT_HOUNDSKUL_BASCINET, KHItems.DARK_GREAT_HOUNDSKUL_BASCINET, KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET,
                KHItems.MAXIMILLIAN_HELMET, KHItems.DARK_MAXIMILLIAN_HELMET, KHItems.GOLDEN_MAXIMILLIAN_HELMET,
                KHItems.SAVOYARD, KHItems.DARK_SAVOYARD, KHItems.GOLDEN_SAVOYARD,
                KHItems.ARAGONESE_SALLET, KHItems.DARK_ARAGONESE_SALLET, KHItems.GOLDEN_ARAGONESE_SALLET
        );

        attachments(EquipmentSlot.CHEST, ALLOWED_MOBS,
                KHItems.MAIL_SPAULDERS, KHItems.MAIL_SPAULDERS_BESAGEWS, KHItems.GOLDEN_MAIL_SPAULDERS, KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS,
                KHItems.BRIGANDINE_SPAULDERS, KHItems.BRIGANDINE_SPAULDERS_BESAGEWS,
                KHItems.DARK_BRIGANDINE_SPAULDERS, KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS,
                KHItems.GOLDEN_BRIGANDINE_SPAULDERS, KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS,
                KHItems.PLATE_SPAULDERS, KHItems.PLATE_SPAULDERS_BESAGEWS, KHItems.PLATE_SPAULDERS_RIMMED, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED,
                KHItems.DARK_PLATE_SPAULDERS, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS, KHItems.DARK_PLATE_SPAULDERS_RIMMED, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED,
                KHItems.GOLDEN_PLATE_SPAULDERS, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS, KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED,
                KHItems.BRIGANDINE, KHItems.DARK_BRIGANDINE, KHItems.GOLDEN_BRIGANDINE,
                KHItems.PLATE_CUIRASS, KHItems.DARK_PLATE_CUIRASS, KHItems.GOLDEN_PLATE_CUIRASS,
                KHItems.MAXIMILLIAN_CUIRASS, KHItems.DARK_MAXIMILLIAN_CUIRASS, KHItems.GOLDEN_MAXIMILLIAN_CUIRASS,
                KHItems.XIIII_PLATE_CUIRASS, KHItems.DARK_XIIII_PLATE_CUIRASS, KHItems.GOLDEN_XIIII_PLATE_CUIRASS,
                KHItems.XIIII_PLATE_BREASTPLATE, KHItems.DARK_XIIII_PLATE_BREASTPLATE, KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE,
                KHItems.PLACKART, KHItems.DARK_PLACKART, KHItems.GOLDEN_PLACKART,
                KHItems.TASSETS, KHItems.DARK_TASSETS, KHItems.GOLDEN_TASSETS,
                KHItems.GAUNTLET, KHItems.DARK_GAUNTLET, KHItems.GOLDEN_GAUNTLET,
                KHItems.BRIGANDINE_HARNESS, KHItems.DARK_BRIGANDINE_HARNESS, KHItems.GOLDEN_BRIGANDINE_HARNESS,
                KHItems.PLATE_HARNESS, KHItems.DARK_PLATE_HARNESS, KHItems.GOLDEN_PLATE_HARNESS,
                KHItems.AVENTAIL,
                KHItems.CLOAK, KHItems.TORN_CLOAK
        );

        attachments(EquipmentSlot.LEGS, ALLOWED_MOBS,
                KHItems.BRIGANDINE_CUISSES, KHItems.DARK_BRIGANDINE_CUISSES, KHItems.GOLDEN_BRIGANDINE_CUISSES,
                KHItems.PLATE_CUISSES, KHItems.DARK_PLATE_CUISSES, KHItems.GOLDEN_PLATE_CUISSES,
                KHItems.GREAVES, KHItems.DARK_GREAVES, KHItems.GOLDEN_GREAVES
        );

        attachments(EquipmentSlot.FEET, ALLOWED_MOBS,
                KHItems.SABATONS, KHItems.DARK_SABATONS, KHItems.GOLDEN_SABATONS
        );

        attachments(EquipmentSlot.HEAD, ALLOWED_MOBS,
                KHItems.HOOD, KHItems.TORN_HOOD, KHItems.JESTER_HOOD, KHItems.HELMET_HOOD, KHItems.HELMET_TORN_HOOD,
                KHItems.CHAPERON, KHItems.GILDED_CHAPERON
        );

        attachments(EquipmentSlot.CHEST, ALLOWED_MOBS,
                KHItems.LEATHER_GLOVES, KHItems.MAIL_GLOVES,
                KHItems.SURCOAT, KHItems.SURCOAT_SLEEVELESS, KHItems.CIVILIAN_SURCOAT, KHItems.GIORNEA
        );
    }
}