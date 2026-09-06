package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.ArmorFamily;
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
                ArmorFamily.allFamilies(
                        KHItems.BARBUTE,
                        KHItems.BASCINET,
                        KHItems.KETTLE_HELM,
                        KHItems.NASAL_HELM,
                        KHItems.VIKING_HELM,
                        KHItems.BURGONET,
                        KHItems.VISORLESS_SALLET,
                        KHItems.MORION,
                        KHItems.ARMET,
                        KHItems.ARMET_2,
                        KHItems.VISORED_BARBUTE,
                        KHItems.HOUNDSKULL,
                        KHItems.CAGE,
                        KHItems.VISORED_BASCINET,
                        KHItems.GREAT_HELM,
                        KHItems.GREAT_HELM_2,
                        KHItems.SALLET,
                        KHItems.BURGONET_FALLING_BUFFE,
                        KHItems.CLOSE_HELM,
                        KHItems.BLACK_SALLET,
                        KHItems.VISORED_MORION,
                        KHItems.SALLET_BEVOR,
                        KHItems.BLACK_SALLET_BEVOR,
                        KHItems.FROGMOUTH,
                        KHItems.GREAT_ARMET,
                        KHItems.GREAT_ARMET_2,
                        KHItems.GREAT_BASCINET,
                        KHItems.GREAT_HOUNDSKUL_BASCINET,
                        KHItems.MAXIMILLIAN_HELMET,
                        KHItems.SAVOYARD,
                        KHItems.ARAGONESE_SALLET
                )
        );

        attachments(EquipmentSlot.CHEST, ALLOWED_MOBS,
                ArmorFamily.allFamilies(
                        KHItems.MAIL_SPAULDERS,
                        KHItems.BRIGANDINE_SPAULDERS,
                        KHItems.PLATE_SPAULDERS,
                        KHItems.BRIGANDINE,
                        KHItems.PLATE_CUIRASS,
                        KHItems.MAXIMILLIAN_CUIRASS,
                        KHItems.XIIII_PLATE_CUIRASS,
                        KHItems.XIIII_PLATE_BREASTPLATE,
                        KHItems.PLACKART,
                        KHItems.TASSETS,
                        KHItems.GAUNTLET,
                        KHItems.BRIGANDINE_HARNESS,
                        KHItems.PLATE_HARNESS
                )
        );

        attachments(EquipmentSlot.CHEST, ALLOWED_MOBS,
                KHItems.AVENTAIL,
                KHItems.CLOAK, KHItems.TORN_CLOAK
        );

        attachments(EquipmentSlot.LEGS, ALLOWED_MOBS,
                ArmorFamily.allFamilies(
                        KHItems.BRIGANDINE_CUISSES,
                        KHItems.PLATE_CUISSES,
                        KHItems.GREAVES
                )
        );

        attachments(EquipmentSlot.FEET, ALLOWED_MOBS,
                ArmorFamily.allFamilies(
                        KHItems.SABATONS
                )
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