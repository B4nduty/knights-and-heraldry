package com.knightsheraldry.datagen;

import banduty.stoneycore.util.itemdata.SCTags;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.KHTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(SCTags.WEAPONS_3D.getTag())
            .add(ModItems.SMITHING_HAMMER, ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD,
                    ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE,
                    ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE, ModItems.SPIKED_MACE, ModItems.HAMMER,
                    ModItems.WAR_HAMMER, ModItems.LONGSWORD, ModItems.V_LONGSWORD, ModItems.FALCHION, ModItems.SCIMITAR,
                    ModItems.PITCHFORK, ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK,
                    ModItems.GLAIVE, ModItems.CURVED_GLAIVE, ModItems.HALBERD, ModItems.LANCE, ModItems.WOODEN_LANCE,
                    ModItems.POLEAXE, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR,
                    ModItems.BARDICHE,  ModItems.WARDART, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE,
                    ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER, ModItems.LONGBOW);

        getOrCreateTagBuilder(SCTags.WEAPONS_SHIELD.getTag())
                .add(ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE,
                        ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE,
                        ModItems.SPIKED_MACE, ModItems.HAMMER, ModItems.WAR_HAMMER, ModItems.LONGSWORD,
                        ModItems.V_LONGSWORD, ModItems.FALCHION, ModItems.SCIMITAR, ModItems.PITCHFORK,
                        ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK, ModItems.GLAIVE, ModItems.CURVED_GLAIVE,
                        ModItems.HALBERD, ModItems.POLEAXE, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR,
                        ModItems.BARDICHE, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE,
                        ModItems.WARSWORD_ZWEIHANDER);

        getOrCreateTagBuilder(SCTags.WEAPONS_DAMAGE_BEHIND.getTag())
                .add(ModItems.DAGGER);

        getOrCreateTagBuilder(SCTags.WEAPONS_IGNORES_ARMOR.getTag())
                .add(ModItems.STILETTO);

        getOrCreateTagBuilder(SCTags.WEAPONS_DISABLE_SHIELD.getTag())
                .add(ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE);

        getOrCreateTagBuilder(SCTags.WEAPONS_BYPASS_BLOCK.getTag())
                .add(ModItems.FLAIL, ModItems.BALL_FLAIL);

        getOrCreateTagBuilder(SCTags.WEAPONS_HARVEST.getTag())
                .add(ModItems.PITCHFORK);

        getOrCreateTagBuilder(SCTags.GEO_2D_ITEMS.getTag())
                .add(ModItems.FLAIL, ModItems.BALL_FLAIL, ModItems.HEAVY_CROSSBOW, ModItems.ARQUEBUS, ModItems.HANDGONNE);


        getOrCreateTagBuilder(SCTags.ALWAYS_WEARABLE.getTag())
                .add(ModItems.HOOD, ModItems.TORN_HOOD, ModItems.CLOAK, ModItems.TORN_CLOAK);

        getOrCreateTagBuilder(SCTags.VISORED_HELMET.getTag())
                .add(ModItems.ARMET, ModItems.ARMET_2, ModItems.BARBUTE, ModItems.BASCINET, ModItems.CAGE,
                        ModItems.CAGE_2, ModItems.FLAT_BASCINET, ModItems.GREAT_HELM, ModItems.GREAT_HELM_2,
                        ModItems.SALLET, ModItems.FROGMOUTH, ModItems.GREAT_ARMET, ModItems.GREAT_ARMET_2,
                        ModItems.GREAT_BASCINET, ModItems.GREAT_HOUNDSKUL_BASCINET, ModItems.MAXIMILLIAN_HELMET);

        getOrCreateTagBuilder(KHTags.DEFLECTIVE_ARMOR.getTag())
                .add(ModItems.MAIL_COIF, ModItems.HAUBERK, ModItems.MAIL_BREECHES, ModItems.MAIL_BOOTS,
                        ModItems.MAIL_PAULDRON, ModItems.PLATE_PAULDRON, ModItems.PLATE_CUIRASS, ModItems.PLATE_CUIRASS_TASSETS,
                        ModItems.MAXIMILLIAN_CUIRASS, ModItems.MAXIMILLIAN_CUIRASS_TASSETS, ModItems.XIIII_PLATE_CUIRASS,
                        ModItems.XIIII_PLATE_CUIRASS_TASSETS, ModItems.XIIII_PLATE_BREASTPLATE, ModItems.BARBUTE_NO_VISOR,
                        ModItems.BASCINET_NO_VISOR, ModItems.KETTLE_HELM, ModItems.NASAL_HELM, ModItems.VIKING_HELM,
                        ModItems.ARMET, ModItems.ARMET_2, ModItems.BARBUTE, ModItems.BASCINET, ModItems.CAGE, ModItems.CAGE_2,
                        ModItems.FLAT_BASCINET, ModItems.GREAT_HELM, ModItems.GREAT_HELM_2, ModItems.SALLET, ModItems.FROGMOUTH,
                        ModItems.GREAT_ARMET, ModItems.GREAT_ARMET_2, ModItems.GREAT_BASCINET, ModItems.GREAT_HOUNDSKUL_BASCINET,
                        ModItems.MAXIMILLIAN_HELMET, ModItems.GAUNTLET, ModItems.PLATE_REREBRACE, ModItems.PLATE_CHAUSSES,
                        ModItems.SABATONS);

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(ModItems.CLOAK, ModItems.TORN_CLOAK);

        getOrCreateTagBuilder(SCTags.BANNER_COMPATIBLE.getTag())
                .add(ModItems.SURCOAT, ModItems.SURCOAT_SLEEVELESS);

        getOrCreateTagBuilder(SCTags.HIDE_NAME_TAG.getTag())
                .add(ModItems.CLOAK, ModItems.TORN_CLOAK);

        getOrCreateTagBuilder(SCTags.MELEE_COMBAT_MECHANICS.getTag())
                .add(ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD,
                        ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE,
                        ModItems.MACE, ModItems.SPIKED_MACE, ModItems.FLAIL, ModItems.BALL_FLAIL, ModItems.HAMMER,
                        ModItems.WAR_HAMMER, ModItems.LONGSWORD, ModItems.V_LONGSWORD, ModItems.FALCHION,
                        ModItems.SCIMITAR, ModItems.PITCHFORK, ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK,
                        ModItems.GLAIVE, ModItems.CURVED_GLAIVE, ModItems.HALBERD, ModItems.POLEAXE, ModItems.POLEHAMMER,
                        ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR, ModItems.BARDICHE, ModItems.WARSWORD,
                        ModItems.WARSWORD_CLAYMORE, ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER,
                        ModItems.WARDART);

        getOrCreateTagBuilder(SCTags.RANGED_WEAPON_COMBAT_MECHANICS.getTag())
                .add(ModItems.LONGBOW, ModItems.HEAVY_CROSSBOW, ModItems.ARQUEBUS, ModItems.HANDGONNE);
    }
}