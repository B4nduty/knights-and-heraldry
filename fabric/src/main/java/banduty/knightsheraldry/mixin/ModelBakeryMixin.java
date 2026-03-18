package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
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

    @Inject(method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelBakery;loadTopLevel(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void knightsheraldry$add3dModels(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> blockModelMap, Map<ResourceLocation, List<ModelBakery.LoadedJson>> blockStates, CallbackInfo ci) {
        Item[] weapons3D = {
                ModItems.DAGGER,
                ModItems.STILETTO,
                ModItems.RAPIER,
                ModItems.SWORD,
                ModItems.V_SWORD,
                ModItems.ARMING_SWORD,
                ModItems.AXE,
                ModItems.BROAD_AXE,
                ModItems.CROOKED_AXE,
                ModItems.STRAIGHT_CROOKED_AXE,
                ModItems.MACE,
                ModItems.SPIKED_MACE,
                ModItems.HAMMER,
                ModItems.WAR_HAMMER,
                ModItems.LONGSWORD,
                ModItems.V_LONGSWORD,
                ModItems.FALCHION,
                ModItems.SCIMITAR,
                ModItems.PITCHFORK,
                ModItems.SPEAR,
                ModItems.PIKE,
                ModItems.BILLHOOK,
                ModItems.GLAIVE,
                ModItems.CURVED_GLAIVE,
                ModItems.HALBERD,
                ModItems.LANCE,
                ModItems.WOODEN_LANCE,
                ModItems.POLEAXE,
                ModItems.POLEHAMMER,
                ModItems.BEC_DE_CORBIN,
                ModItems.MORNING_STAR,
                ModItems.BARDICHE,
                ModItems.GREATSWORD,
                ModItems.CLAYMORE,
                ModItems.FLAMBERGE,
                ModItems.ZWEIHANDER,
                ModItems.WARDART,
                ModItems.LONGBOW
        };

        // 2D ICON-ONLY weapons
        Item[] weaponsIcon = {
                ModItems.FLAIL,
                ModItems.BALL_FLAIL,
                ModItems.HEAVY_CROSSBOW,
                ModItems.ARQUEBUS,
                ModItems.HANDGONNE
        };

        Item[] patternedItems = new Item[]{
                ModItems.SURCOAT,
                ModItems.SURCOAT_SLEEVELESS
        };

        Item[] manuscriptItems = {
                ModItems.DAGGER,
                ModItems.STILETTO,
                ModItems.RAPIER,
                ModItems.SWORD,
                ModItems.V_SWORD,
                ModItems.ARMING_SWORD,
                ModItems.AXE,
                ModItems.BROAD_AXE,
                ModItems.CROOKED_AXE,
                ModItems.STRAIGHT_CROOKED_AXE,
                ModItems.MACE,
                ModItems.SPIKED_MACE,
                ModItems.FLAIL,
                ModItems.BALL_FLAIL,
                ModItems.HAMMER,
                ModItems.WAR_HAMMER,
                ModItems.LONGSWORD,
                ModItems.V_LONGSWORD,
                ModItems.FALCHION,
                ModItems.SCIMITAR,
                ModItems.PITCHFORK,
                ModItems.SPEAR,
                ModItems.PIKE,
                ModItems.BILLHOOK,
                ModItems.GLAIVE,
                ModItems.CURVED_GLAIVE,
                ModItems.HALBERD,
                ModItems.LANCE,
                ModItems.WOODEN_LANCE,
                ModItems.POLEAXE,
                ModItems.POLEHAMMER,
                ModItems.BEC_DE_CORBIN,
                ModItems.MORNING_STAR,
                ModItems.BARDICHE,
                ModItems.GREATSWORD,
                ModItems.CLAYMORE,
                ModItems.FLAMBERGE,
                ModItems.ZWEIHANDER,
                ModItems.WARDART,

                ModItems.QUILTED_COIF,
                ModItems.GAMBESON,
                ModItems.GAMBESON_BREECHES,
                ModItems.GAMBESON_BOOTS,

                ModItems.MAIL_COIF,
                ModItems.HAUBERK,
                ModItems.MAIL_BREECHES,
                ModItems.MAIL_BOOTS,

                ModItems.MAIL_SPAULDERS,
                ModItems.BRIGANDINE_SPAULDERS,
                ModItems.PLATE_SPAULDERS,

                ModItems.BRIGANDINE,

                ModItems.BRIG_BREASTPLATE,
                ModItems.BRIG_BREASTPLATE_TASSETS,

                ModItems.PLATE_CUIRASS,
                ModItems.PLATE_CUIRASS_TASSETS,
                ModItems.MAXIMILLIAN_CUIRASS,
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS,
                ModItems.XIIII_PLATE_CUIRASS,
                ModItems.XIIII_PLATE_CUIRASS_TASSETS,
                ModItems.XIIII_PLATE_BREASTPLATE,

                ModItems.BARBUTE,
                ModItems.BASCINET,
                ModItems.KETTLE_HELM,
                ModItems.NASAL_HELM,
                ModItems.VIKING_HELM,
                ModItems.BURGONET,

                ModItems.ARMET,
                ModItems.ARMET_2,
                ModItems.VISORED_BARBUTE,
                ModItems.HOUNDSKULL,
                ModItems.CAGE,
                ModItems.VISORED_BASCINET,
                ModItems.GREAT_HELM,
                ModItems.SALLET,
                ModItems.BURGONET_FALLING_BUFFE,
                ModItems.CLOSE_HELM,

                ModItems.FROGMOUTH,
                ModItems.GREAT_ARMET,
                ModItems.GREAT_ARMET_2,
                ModItems.GREAT_BASCINET,
                ModItems.GREAT_HOUNDSKUL_BASCINET,
                ModItems.MAXIMILLIAN_HELMET,
                ModItems.SAVOYARD,

                ModItems.GAUNTLET,
                ModItems.BRIGANDINE_HARNESS,
                ModItems.PLATE_HARNESS,

                ModItems.BRIGANDINE_CUISSES,
                ModItems.PLATE_CUISSES,

                ModItems.GREAVES,

                ModItems.SABATONS,

                ModItems.SURCOAT,
                ModItems.SURCOAT_SLEEVELESS,
                ModItems.CIVILIAN_SURCOAT,
                ModItems.GIORNEA,

                ModItems.CLOAK,
                ModItems.TORN_CLOAK,

                ModItems.HOOD,
                ModItems.TORN_HOOD,
                ModItems.JESTER_HOOD,
                ModItems.HELMET_HOOD,
                ModItems.HELMET_TORN_HOOD,

                ModItems.LONGBOW,

                ModItems.HEAVY_CROSSBOW,

                ModItems.ARQUEBUS,

                ModItems.HANDGONNE,

                ModItems.SWALLOWTAIL_ARROW,
                ModItems.BODKIN_ARROW,
                ModItems.BROADHEAD_ARROW,
                ModItems.CLOTH_ARROW,

                ModItems.HORSE_BARDING,

                ModItems.RIM_GUARDS,
                ModItems.BESAGEWS
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
        
        // ===== register 3D models =====
        for (Item item : weapons3D) {

            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);

            this.loadTopLevel(new ModelResourceLocation(
                    id.getNamespace(),
                    id.getPath() + "_3d",
                    "inventory"
            ));
        }

        // ===== register icon models =====
        for (Item item : weaponsIcon) {

            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);

            this.loadTopLevel(new ModelResourceLocation(
                    id.getNamespace(),
                    id.getPath() + "_icon",
                    "inventory"
            ));
        }

        for (Item manuscriptItem : manuscriptItems) {
            this.loadTopLevel(new ModelResourceLocation(KnightsHeraldry.MOD_ID, "manuscript_" + manuscriptItem, "inventory"));
        }

        for (Item item : patternedItems) {

            for (String pattern : bannerPatternNames) {

                String modelPath = item + "/" + pattern;

                this.loadTopLevel(new ModelResourceLocation(KnightsHeraldry.MOD_ID, modelPath, "inventory"));
            }
        }
    }
}