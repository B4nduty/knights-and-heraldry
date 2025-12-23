package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {
    @Shadow
    protected abstract void loadTopLevel(ModelResourceLocation modelId);

    @Inject( method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelBakery;loadTopLevel(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void knightsheraldry$add3dModels(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> blockModelMap, Map<ResourceLocation, List<ModelBakery.LoadedJson>> blockStates, CallbackInfo ci) {
        String[] modelNames = {
                "dagger_3d",
                "stiletto_3d",
                "rapier_3d",
                "sword_3d",
                "v_sword_3d",
                "arming_sword_3d",
                "axe_3d",
                "broad_axe_3d",
                "crooked_axe_3d",
                "straight_crooked_axe_3d",
                "mace_3d",
                "spiked_mace_3d",
                "flail_icon",
                "ball_flail_icon",
                "hammer_3d",
                "war_hammer_3d",
                "longsword_3d",
                "v_longsword_3d",
                "falchion_3d",
                "scimitar_3d",
                "pitchfork_3d",
                "spear_3d",
                "pike_3d",
                "billhook_3d",
                "glaive_3d",
                "curved_glaive_3d",
                "halberd_3d",
                "lance_3d",
                "wooden_lance_3d",
                "poleaxe_3d",
                "polehammer_3d",
                "bec_de_corbin_3d",
                "morning_star_3d",
                "bardiche_3d",
                "greatsword_3d",
                "claymore_3d",
                "flamberge_3d",
                "zweihander_3d",
                "wardart_3d",
                "longbow_3d",
                "heavy_crossbow_icon",
                "arquebus_icon",
                "handgonne_icon"
        };

        String[] bannerCompatibleNames = {
                "surcoat",
                "surcoat_sleeveless"
        };

        Item[] manuscriptItems = {
                ModItems.DAGGER.get(),
                ModItems.STILETTO.get(),
                ModItems.RAPIER.get(),
                ModItems.SWORD.get(),
                ModItems.V_SWORD.get(),
                ModItems.ARMING_SWORD.get(),
                ModItems.AXE.get(),
                ModItems.BROAD_AXE.get(),
                ModItems.CROOKED_AXE.get(),
                ModItems.STRAIGHT_CROOKED_AXE.get(),
                ModItems.MACE.get(),
                ModItems.SPIKED_MACE.get(),
                ModItems.FLAIL.get(),
                ModItems.BALL_FLAIL.get(),
                ModItems.HAMMER.get(),
                ModItems.WAR_HAMMER.get(),
                ModItems.LONGSWORD.get(),
                ModItems.V_LONGSWORD.get(),
                ModItems.FALCHION.get(),
                ModItems.SCIMITAR.get(),
                ModItems.PITCHFORK.get(),
                ModItems.SPEAR.get(),
                ModItems.PIKE.get(),
                ModItems.BILLHOOK.get(),
                ModItems.GLAIVE.get(),
                ModItems.CURVED_GLAIVE.get(),
                ModItems.HALBERD.get(),
                ModItems.LANCE.get(),
                ModItems.WOODEN_LANCE.get(),
                ModItems.POLEAXE.get(),
                ModItems.POLEHAMMER.get(),
                ModItems.BEC_DE_CORBIN.get(),
                ModItems.MORNING_STAR.get(),
                ModItems.BARDICHE.get(),
                ModItems.GREATSWORD.get(),
                ModItems.CLAYMORE.get(),
                ModItems.FLAMBERGE.get(),
                ModItems.ZWEIHANDER.get(),
                ModItems.WARDART.get(),

                ModItems.QUILTED_COIF.get(),
                ModItems.GAMBESON.get(),
                ModItems.GAMBESON_BREECHES.get(),
                ModItems.GAMBESON_BOOTS.get(),

                ModItems.MAIL_COIF.get(),
                ModItems.HAUBERK.get(),
                ModItems.MAIL_BREECHES.get(),
                ModItems.MAIL_BOOTS.get(),

                ModItems.MAIL_SPAULDERS.get(),
                ModItems.BRIGANDINE_SPAULDERS.get(),
                ModItems.PLATE_SPAULDERS.get(),

                ModItems.BRIGANDINE.get(),

                ModItems.BRIG_BREASTPLATE.get(),
                ModItems.BRIG_BREASTPLATE_TASSETS.get(),

                ModItems.PLATE_CUIRASS.get(),
                ModItems.PLATE_CUIRASS_TASSETS.get(),
                ModItems.MAXIMILLIAN_CUIRASS.get(),
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(),
                ModItems.XIIII_PLATE_CUIRASS.get(),
                ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(),
                ModItems.XIIII_PLATE_BREASTPLATE.get(),

                ModItems.BARBUTE.get(),
                ModItems.BASCINET.get(),
                ModItems.KETTLE_HELM.get(),
                ModItems.NASAL_HELM.get(),
                ModItems.VIKING_HELM.get(),
                ModItems.BURGONET.get(),

                ModItems.ARMET.get(),
                ModItems.ARMET_2.get(),
                ModItems.VISORED_BARBUTE.get(),
                ModItems.HOUNDSKULL.get(),
                ModItems.CAGE.get(),
                ModItems.CAGE_2.get(),
                ModItems.VISORED_BASCINET.get(),
                ModItems.GREAT_HELM.get(),
                ModItems.SALLET.get(),
                ModItems.BURGONET_FALLING_BUFFE.get(),
                ModItems.CLOSE_HELM.get(),

                ModItems.FROGMOUTH.get(),
                ModItems.GREAT_ARMET.get(),
                ModItems.GREAT_ARMET_2.get(),
                ModItems.GREAT_BASCINET.get(),
                ModItems.GREAT_HOUNDSKUL_BASCINET.get(),
                ModItems.MAXIMILLIAN_HELMET.get(),
                ModItems.SAVOYARD.get(),

                ModItems.GAUNTLET.get(),
                ModItems.BRIGANDINE_HARNESS.get(),
                ModItems.PLATE_HARNESS.get(),

                ModItems.BRIGANDINE_CUISSES.get(),
                ModItems.PLATE_CUISSES.get(),

                ModItems.GREAVES.get(),

                ModItems.SABATONS.get(),

                ModItems.SURCOAT.get(),
                ModItems.SURCOAT_SLEEVELESS.get(),
                ModItems.CIVILIAN_SURCOAT.get(),
                ModItems.GIORNEA.get(),

                ModItems.CLOAK.get(),
                ModItems.TORN_CLOAK.get(),

                ModItems.HOOD.get(),
                ModItems.TORN_HOOD.get(),
                ModItems.JESTER_HOOD.get(),
                ModItems.HELMET_HOOD.get(),
                ModItems.HELMET_TORN_HOOD.get(),

                ModItems.LONGBOW.get(),

                ModItems.HEAVY_CROSSBOW.get(),

                ModItems.ARQUEBUS.get(),

                ModItems.HANDGONNE.get(),

                ModItems.SWALLOWTAIL_ARROW.get(),
                ModItems.BODKIN_ARROW.get(),
                ModItems.BROADHEAD_ARROW.get(),
                ModItems.CLOTH_ARROW.get(),

                ModItems.HORSE_BARDING.get(),

                ModItems.RIM_GUARDS.get(),
                ModItems.BESAGEWS.get()
        };

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

        for (String modelName : modelNames) {
            this.loadTopLevel(new ModelResourceLocation(KnightsHeraldry.MOD_ID, modelName, "inventory"));
        }

        for (Item manuscriptItem : manuscriptItems) {
            this.loadTopLevel(new ModelResourceLocation(KnightsHeraldry.MOD_ID, "manuscript_" + manuscriptItem, "inventory"));
        }

        for (String baseName : bannerCompatibleNames) {
            for (String patternName : bannerPatternNames) {
                this.loadTopLevel(new ModelResourceLocation(KnightsHeraldry.MOD_ID, baseName + "/" + patternName, "inventory"));
            }
        }
    }
}