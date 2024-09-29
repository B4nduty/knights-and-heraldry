package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STILETTO, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAPIER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.V_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ARMING_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BROAD_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CROOKED_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STRAIGHT_CROOKED_AXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPIKED_MACE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.FLAIL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BALL_FLAIL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WAR_HAMMER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.V_LONGSWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.FALCHION, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SCIMITAR, Models.HANDHELD);

        itemModelGenerator.register(ModItems.KATANA, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PITCHFORK, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SPEAR, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PIKE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BILLHOOK, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GLAIVE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURVED_GLAIVE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.HALBERD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.LANCE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.POLEAXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.POLEHAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BEC_DE_CORBIN, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MORNING_STAR, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BARDICHE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.WARSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_CLAYMORE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_FLAMBERGE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_ZWEIHANDER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.WARDART, Models.HANDHELD);

        itemModelGenerator.register(ModItems.QUILTED_COIF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GAMBESON_BREECHES, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GAMBESON_BOOTS, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MAIL_COIF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HAUBERK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAIL_BREECHES, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAIL_BOOTS, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLATE_CUIRASS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATE_CUIRASS_TASSETS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAXIMILLIAN_CUIRASS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAXIMILLIAN_CUIRASS_TASSETS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.XIIII_PLATE_CUIRASS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.XIIII_PLATE_CUIRASS_TASSETS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.XIIII_PLATE_BREASTPLATE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SABATONS, Models.HANDHELD);

        itemModelGenerator.register(ModItems.FROGMOUTH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GREAT_ARMET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GREAT_ARMET_2, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GREAT_BASCINET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GREAT_HOUNDSKUL_BASCINET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAXIMILLIAN_HELMET, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GAUNTLET, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLATE_REREBRACE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLATE_CHAUSSES, Models.HANDHELD);

        itemModelGenerator.register(ModItems.AVENTAIL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.RIM_GUARDS, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BESAGEWS, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SURCOAT, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SURCOAT_SLEEVELESS, Models.HANDHELD);
    }
}
