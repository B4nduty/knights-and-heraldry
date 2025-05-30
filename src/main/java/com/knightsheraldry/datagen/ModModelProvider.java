package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SMITHING_HAMMER, Models.HANDHELD);

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

        itemModelGenerator.register(ModItems.HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WAR_HAMMER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.V_LONGSWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.FALCHION, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SCIMITAR, Models.HANDHELD);

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

        itemModelGenerator.register(ModItems.CLOAK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TORN_CLOAK, Models.HANDHELD);

        itemModelGenerator.register(ModItems.HOOD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TORN_HOOD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELMET_HOOD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELMET_TORN_HOOD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.LONGBOW, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SWALLOWTAIL_ARROW, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BODKIN_ARROW, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BROADHEAD_ARROW, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CLOTH_ARROW, Models.HANDHELD);

        itemModelGenerator.register(ModItems.BLACK_POWDER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLUME, Models.HANDHELD);

        generateBannerPatternModels(ModItems.SURCOAT, itemModelGenerator);
        generateBannerPatternModels(ModItems.SURCOAT_SLEEVELESS, itemModelGenerator);

        itemModelGenerator.register(ModItems.CHAPERON, Models.HANDHELD);
    }

    public void generateBannerPatternModels(Item item, ItemModelGenerator itemModelGenerator) {
        Identifier itemId = Registries.ITEM.getId(item);

        String[] bannerPatternNames = {
                "bl",
                "bo",
                "br",
                "bri",
                "bs",
                "bt",
                "bts",
                "cbo",
                "cr",
                "cre",
                "cs",
                "dls",
                "drs",
                "flo",
                "glb",
                "gra",
                "gru",
                "hh",
                "hhb",
                "ld",
                "ls",
                "lud",
                "mc",
                "moj",
                "mr",
                "ms",
                "pig",
                "rd",
                "rs",
                "rud",
                "sc",
                "sku",
                "ss",
                "tl",
                "tr",
                "ts",
                "tt",
                "tts",
                "vh",
                "vhr"
        };

        for (String pattern : bannerPatternNames) {
            Identifier modelId = new Identifier(itemId.getNamespace(), "item/" + itemId.getPath() + "/" + pattern);

            TextureMap textures = TextureMap.layer0(
                    new Identifier(itemId.getNamespace(), "item/" + itemId.getPath() + "/" + pattern)
            );

            Models.HANDHELD.upload(modelId, textures, itemModelGenerator.writer);
        }
    }
}
