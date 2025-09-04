package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.HelmetDeco;
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
        itemModelGenerator.register(ModItems.DAGGER.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.STILETTO.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.RAPIER.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.SWORD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.V_SWORD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.ARMING_SWORD.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.AXE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.BROAD_AXE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.CROOKED_AXE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.STRAIGHT_CROOKED_AXE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.MACE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPIKED_MACE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.HAMMER.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.WAR_HAMMER.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.LONGSWORD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.V_LONGSWORD.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.FALCHION.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.SCIMITAR.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.PITCHFORK.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.SPEAR.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.PIKE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.BILLHOOK.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.GLAIVE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURVED_GLAIVE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.HALBERD.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.LANCE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.POLEAXE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.POLEHAMMER.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.BEC_DE_CORBIN.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.MORNING_STAR.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.BARDICHE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.WARSWORD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_CLAYMORE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_FLAMBERGE.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.WARSWORD_ZWEIHANDER.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.WARDART.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.QUILTED_COIF.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.GAMBESON_BREECHES.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.GAMBESON_BOOTS.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.MAIL_COIF.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.HAUBERK.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAIL_BREECHES.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAIL_BOOTS.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLATE_CUIRASS.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.PLATE_CUIRASS_TASSETS.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAXIMILLIAN_CUIRASS.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.XIIII_PLATE_CUIRASS.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.XIIII_PLATE_BREASTPLATE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.SABATONS.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.FROGMOUTH.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.GAUNTLET.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLATE_REREBRACE.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.PLATE_CHAUSSES.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.AVENTAIL.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.RIM_GUARDS.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.BESAGEWS.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.SURCOAT.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.SURCOAT_SLEEVELESS.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.CLOAK.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.TORN_CLOAK.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.HOOD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.TORN_HOOD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELMET_HOOD.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELMET_TORN_HOOD.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.LONGBOW.get(), Models.HANDHELD);

        itemModelGenerator.register(ModItems.SWALLOWTAIL_ARROW.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.BODKIN_ARROW.get(), Models.HANDHELD);
        itemModelGenerator.register(ModItems.BROADHEAD_ARROW.get(), Models.HANDHELD);

        generateBannerPatternModels(ModItems.SURCOAT.get(), itemModelGenerator);
        generateBannerPatternModels(ModItems.SURCOAT_SLEEVELESS.get(), itemModelGenerator);

        itemModelGenerator.register(ModItems.CHAPERON.get(), Models.HANDHELD);

        for (Item deco : HelmetDeco.HELMET_DECO.keySet()) {
            if (deco == ModItems.JOUSTING_BAND.get()) continue;
            itemModelGenerator.register(deco, Models.HANDHELD);
        }
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
