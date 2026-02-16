package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.util.itemdata.ModTags;
import banduty.stoneycore.util.data.itemdata.SCTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(SCTags.WEAPONS_3D.getTag())
            .add(ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD,
                    ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE,
                    ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE, ModItems.SPIKED_MACE, ModItems.HAMMER,
                    ModItems.WAR_HAMMER, ModItems.LONGSWORD, ModItems.V_LONGSWORD, ModItems.FALCHION, ModItems.SCIMITAR,
                    ModItems.PITCHFORK, ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK,
                    ModItems.GLAIVE, ModItems.CURVED_GLAIVE, ModItems.HALBERD, ModItems.LANCE, ModItems.WOODEN_LANCE,
                    ModItems.POLEAXE, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR,
                    ModItems.BARDICHE,  ModItems.WARDART, ModItems.GREATSWORD, ModItems.CLAYMORE,
                    ModItems.FLAMBERGE, ModItems.ZWEIHANDER, ModItems.LONGBOW);

        getOrCreateTagBuilder(SCTags.BROKEN_WEAPONS.getTag())
            .add(ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER, ModItems.SWORD,
                    ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE, ModItems.BROAD_AXE,
                    ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE,
                    ModItems.SPIKED_MACE, ModItems.HAMMER, ModItems.WAR_HAMMER, ModItems.LONGSWORD,
                    ModItems.V_LONGSWORD, ModItems.FALCHION, ModItems.SCIMITAR,
                    ModItems.PITCHFORK, ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK,
                    ModItems.GLAIVE, ModItems.CURVED_GLAIVE, ModItems.HALBERD, ModItems.POLEAXE,
                    ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR,
                    ModItems.BARDICHE,  ModItems.WARDART, ModItems.GREATSWORD, ModItems.CLAYMORE,
                    ModItems.FLAMBERGE, ModItems.ZWEIHANDER, ModItems.FLAIL, ModItems.BALL_FLAIL);

        getOrCreateTagBuilder(SCTags.WEAPONS_SHIELD.getTag())
                .add(ModItems.RAPIER, ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD, ModItems.AXE,
                        ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE, ModItems.MACE,
                        ModItems.SPIKED_MACE, ModItems.HAMMER, ModItems.WAR_HAMMER, ModItems.LONGSWORD,
                        ModItems.V_LONGSWORD, ModItems.FALCHION, ModItems.SCIMITAR, ModItems.PITCHFORK,
                        ModItems.SPEAR, ModItems.PIKE, ModItems.BILLHOOK, ModItems.GLAIVE, ModItems.CURVED_GLAIVE,
                        ModItems.HALBERD, ModItems.POLEAXE, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN, ModItems.MORNING_STAR,
                        ModItems.BARDICHE, ModItems.GREATSWORD, ModItems.CLAYMORE, ModItems.FLAMBERGE,
                        ModItems.ZWEIHANDER);

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

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(ModItems.CLOAK, ModItems.TORN_CLOAK);

        getOrCreateTagBuilder(SCTags.BANNER_COMPATIBLE.getTag())
                .add(ModItems.SURCOAT, ModItems.SURCOAT_SLEEVELESS);

        getOrCreateTagBuilder(SCTags.HIDE_NAME_TAG.getTag())
                .add(ModItems.HOOD, ModItems.TORN_HOOD, ModItems.JESTER_HOOD, ModItems.HELMET_HOOD, ModItems.HELMET_TORN_HOOD);

        getOrCreateTagBuilder(ModTags.DYES.getTag())
                .add(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.LIGHT_BLUE_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_GRAY_DYE,
                        Items.LIME_DYE, Items.MAGENTA_DYE, Items.PINK_DYE, Items.ORANGE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, Items.BROWN_DYE, Items.PURPLE_DYE);
    }
}