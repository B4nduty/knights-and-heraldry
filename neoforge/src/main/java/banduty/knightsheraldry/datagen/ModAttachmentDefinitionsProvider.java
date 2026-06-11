package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModAttachmentDefinitionsProvider extends DefinitionsProvider.ArmorAttachment {
    public ModAttachmentDefinitionsProvider(PackOutput output) {
        super(output);
    }

    private record ArmorStats(double toughness, double armor, double weight, double deflectChance, float attackSpeed, int rechargeTime,
                              boolean hasHungerDrain, String slot, String visorNamespace, String visorType) {
        ArmorStats(double toughness, double armor, double weight, double deflectChance, float attackSpeed, int rechargeTime, boolean hasHungerDrain, String slot) {
            this(toughness, armor, weight, deflectChance, attackSpeed, rechargeTime, hasHungerDrain, slot, null, null);
        }
    }

    private static final String VISOR_DOUBLE_EYESLIT = "double_eyeslit";
    private static final String VISOR_DOUBLE_EYESLIT_WITH_PEEK = "double_eyeslit_with_peek";
    private static final String VISOR_SINGLE_EYESLIT = "single_eyeslit";
    private static final String VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT = "double_eyeslit_breathes_right";
    private static final String VISOR_FOUR_BARS = "four_bars";
    private static final String VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH = "double_eyeslit_big_breathes_both";
    private static final String VISOR_DOUBLE_EYESLIT_BREATHES_BOTH = "double_eyeslit_breathes_both";
    private static final String VISOR_V_SHAPE = "v_shape";
    private static final String VISOR_DOUBLE_EYESLIT_BREATHES_FULL = "double_eyeslit_breathes_full";
    private static final String VISOR_DOUBLE_EYESLIT_BIG = "double_eyeslit_big";

    // Maps using enhanced structure
    private static Map<Item, ArmorStats> createSpauldersMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(KHItems.MAIL_SPAULDERS.get(), new ArmorStats(1.0, 0.0, 2.5, 0.01, 0, 0, false, "chest"));
        map.put(KHItems.MAIL_SPAULDERS_BESAGEWS.get(), new ArmorStats(1.0, 1.0, 2.7, 0.02, 0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_MAIL_SPAULDERS.get(), new ArmorStats(1.0, 0.0, 2.6, 0.01, 0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(), new ArmorStats(1.0, 1.0, 2.8, 0.02, 0, 0, false, "chest"));

        map.put(KHItems.BRIGANDINE_SPAULDERS.get(), new ArmorStats(1.0, 1.0, 2.0, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), new ArmorStats(1.0, 2.0, 2.2, 0.1, 0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE_SPAULDERS.get(), new ArmorStats(2.0, 1.0, 2.2, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), new ArmorStats(2.0, 2.0, 2.4, 0.1, 0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), new ArmorStats(1.0, 1.0, 2.1, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(), new ArmorStats(1.0, 2.0, 2.3, 0.1, 0, 0, false, "chest"));

        map.put(KHItems.PLATE_SPAULDERS.get(), new ArmorStats(2.0, 2.0, 3.0, 0.04, 0, 0, true, "chest"));
        map.put(KHItems.PLATE_SPAULDERS_BESAGEWS.get(), new ArmorStats(2.0, 3.0, 3.2, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.PLATE_SPAULDERS_RIMMED.get(), new ArmorStats(3.0, 2.0, 3.2, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), new ArmorStats(3.0, 3.0, 3.4, 0.06, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS.get(), new ArmorStats(3.0, 2.0, 3.3, 0.04, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), new ArmorStats(3.0, 3.0, 3.5, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), new ArmorStats(4.0, 2.0, 3.5, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), new ArmorStats(4.0, 3.0, 3.7, 0.06, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS.get(), new ArmorStats(2.0, 2.0, 3.2, 0.04, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), new ArmorStats(2.0, 3.0, 3.4, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), new ArmorStats(3.0, 2.0, 3.4, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), new ArmorStats(3.0, 3.0, 3.6, 0.06, 0, 0, true, "chest"));
        return map;
    }

    private static Map<Item, ArmorStats> createChestplateMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(KHItems.BRIGANDINE.get(), new ArmorStats(1.0, 3.0, 6.0, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE.get(), new ArmorStats(2.0, 3.0, 6.6, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE.get(), new ArmorStats(1.0, 3.0, 6.3, 0.0, 0, 0, false, "chest"));

        map.put(KHItems.PLATE_CUIRASS.get(), new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_CUIRASS.get(), new ArmorStats(3.0, 3.0, 8.8, 0.15, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_CUIRASS.get(), new ArmorStats(2.0, 3.0, 8.4, 0.15, 0, 0, true, "chest"));

        map.put(KHItems.MAXIMILLIAN_CUIRASS.get(), new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, 0, true, "chest"));
        map.put(KHItems.DARK_MAXIMILLIAN_CUIRASS.get(), new ArmorStats(3.0, 3.0, 8.8, 0.15, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(), new ArmorStats(2.0, 3.0, 8.4, 0.15, 0, 0, true, "chest"));

        map.put(KHItems.XIIII_PLATE_CUIRASS.get(), new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, 0, true, "chest"));
        map.put(KHItems.DARK_XIIII_PLATE_CUIRASS.get(), new ArmorStats(3.0, 3.0, 8.8, 0.15, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), new ArmorStats(2.0, 3.0, 8.4, 0.15, 0, 0, true, "chest"));

        map.put(KHItems.XIIII_PLATE_BREASTPLATE.get(), new ArmorStats(1.0, 3.0, 7.5, 0.10, 0, 0, true, "chest"));
        map.put(KHItems.DARK_XIIII_PLATE_BREASTPLATE.get(), new ArmorStats(2.0, 3.0, 8.25, 0.10, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(), new ArmorStats(1.0, 3.0, 7.9, 0.10, 0, 0, true, "chest"));

        map.put(KHItems.PLACKART.get(), new ArmorStats(0.5, 0.5, 2.0, 0.04, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLACKART.get(), new ArmorStats(0.5, 0.5, 2.3, 0.04, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLACKART.get(), new ArmorStats(0.5, 0.5, 2.2, 0.04, 0, 0, true, "chest"));

        map.put(KHItems.TASSETS.get(), new ArmorStats(1.0, 1.0, 3.0, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.DARK_TASSETS.get(), new ArmorStats(1.0, 1.0, 3.3, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_TASSETS.get(), new ArmorStats(1.0, 1.0, 3.2, 0.05, 0, 0, true, "chest"));
        return map;
    }

    private static Map<Item, ArmorStats> createHelmetMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        // First tier helmets
        map.put(KHItems.BARBUTE.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head"));
        map.put(KHItems.DARK_BARBUTE.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head"));
        map.put(KHItems.GOLDEN_BARBUTE.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head"));

        map.put(KHItems.BASCINET.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head"));
        map.put(KHItems.DARK_BASCINET.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head"));
        map.put(KHItems.GOLDEN_BASCINET.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head"));

        map.put(KHItems.KETTLE_HELM.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head"));
        map.put(KHItems.DARK_KETTLE_HELM.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head"));
        map.put(KHItems.GOLDEN_KETTLE_HELM.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head"));

        map.put(KHItems.NASAL_HELM.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head"));
        map.put(KHItems.DARK_NASAL_HELM.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head"));
        map.put(KHItems.GOLDEN_NASAL_HELM.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head"));

        map.put(KHItems.BURGONET.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head"));
        map.put(KHItems.DARK_BURGONET.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head"));
        map.put(KHItems.GOLDEN_BURGONET.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head"));

        map.put(KHItems.VIKING_HELM.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_VIKING_HELM.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_VIKING_HELM.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.VISORLESS_SALLET.get(), new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "head"));
        map.put(KHItems.DARK_VISORLESS_SALLET.get(), new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, 0, false, "head"));
        map.put(KHItems.GOLDEN_VISORLESS_SALLET.get(), new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, 0, false, "head"));

        // Second tier helmets
        map.put(KHItems.ARMET.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_ARMET.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_ARMET.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.VISORED_BARBUTE.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_VISORED_BARBUTE.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_VISORED_BARBUTE.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.CLOSE_HELM.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_CLOSE_HELM.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_CLOSE_HELM.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.ARMET_2.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_ARMET_2.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_ARMET_2.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.SALLET.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_SALLET.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_SALLET.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.BURGONET_FALLING_BUFFE.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_BURGONET_FALLING_BUFFE.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_BURGONET_FALLING_BUFFE.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.HOUNDSKULL.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));
        map.put(KHItems.DARK_HOUNDSKULL.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));
        map.put(KHItems.GOLDEN_HOUNDSKULL.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));

        map.put(KHItems.CAGE.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));
        map.put(KHItems.DARK_CAGE.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));
        map.put(KHItems.GOLDEN_CAGE.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));

        map.put(KHItems.VISORED_BASCINET.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.DARK_VISORED_BASCINET.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_VISORED_BASCINET.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        map.put(KHItems.GREAT_HELM.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.DARK_GREAT_HELM.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_GREAT_HELM.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        map.put(KHItems.GREAT_HELM_2.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.DARK_GREAT_HELM_2.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_GREAT_HELM_2.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        map.put(KHItems.BLACK_SALLET.get(), new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));
        map.put(KHItems.DARK_BLACK_SALLET.get(), new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));
        map.put(KHItems.GOLDEN_BLACK_SALLET.get(), new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));

        // Second to Third tier helmets
        map.put(KHItems.SALLET_BEVOR.get(), new ArmorStats(2.5, 2.0, 3.5, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_SALLET_BEVOR.get(), new ArmorStats(3.5, 2.0, 3.8, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_SALLET_BEVOR.get(), new ArmorStats(2.5, 2.0, 3.7, 0.10, 0, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        // Third tier helmets
        map.put(KHItems.FROGMOUTH.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));
        map.put(KHItems.DARK_FROGMOUTH.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));
        map.put(KHItems.GOLDEN_FROGMOUTH.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));

        map.put(KHItems.GREAT_ARMET.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_GREAT_ARMET.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_GREAT_ARMET.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.GREAT_HOUNDSKUL_BASCINET.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.GREAT_ARMET_2.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_GREAT_ARMET_2.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_GREAT_ARMET_2.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.GREAT_BASCINET.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));
        map.put(KHItems.DARK_GREAT_BASCINET.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));
        map.put(KHItems.GOLDEN_GREAT_BASCINET.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));

        map.put(KHItems.MAXIMILLIAN_HELMET.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.DARK_MAXIMILLIAN_HELMET.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_MAXIMILLIAN_HELMET.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        map.put(KHItems.SAVOYARD.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));
        map.put(KHItems.DARK_SAVOYARD.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));
        map.put(KHItems.GOLDEN_SAVOYARD.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));

        map.put(KHItems.ARAGONESE_SALLET.get(), new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_ARAGONESE_SALLET.get(), new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_ARAGONESE_SALLET.get(), new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        return map;
    }

    private static Map<Item, ArmorStats> createArmsMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(KHItems.LEATHER_GLOVES.get(), new ArmorStats(0.0, 0.0, 0.1, 0, 0, -10, false, "chest"));
        map.put(KHItems.MAIL_GLOVES.get(), new ArmorStats(0.5, 0.0, 0.3, 0, 0, -10, false, "chest"));

        map.put(KHItems.GAUNTLET.get(), new ArmorStats(0.0, 2.0, 1.5, 0.05, 0, 30, false, "chest"));
        map.put(KHItems.DARK_GAUNTLET.get(), new ArmorStats(1.0, 2.0, 1.7, 0.05, 0, 30, false, "chest"));
        map.put(KHItems.GOLDEN_GAUNTLET.get(), new ArmorStats(0.0, 2.0, 1.6, 0.05, 0, 30, false, "chest"));

        map.put(KHItems.BRIGANDINE_HARNESS.get(), new ArmorStats(1.0, 2.0, 2.0, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE_HARNESS.get(), new ArmorStats(2.0, 2.0, 2.2, 0.0, 0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE_HARNESS.get(), new ArmorStats(1.0, 2.0, 2.1, 0.0, 0, 0, false, "chest"));

        map.put(KHItems.PLATE_HARNESS.get(), new ArmorStats(2.0, 2.0, 2.5, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_HARNESS.get(), new ArmorStats(3.0, 2.0, 2.8, 0.05, 0, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_HARNESS.get(), new ArmorStats(2.0, 2.0, 2.6, 0.05, 0, 0, true, "chest"));
        return map;
    }

    private static Map<Item, ArmorStats> createLegsMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(KHItems.BRIGANDINE_CUISSES.get(), new ArmorStats(1.0, 1.0, 3.5, 0.0, 0, 0, false, "legs"));
        map.put(KHItems.DARK_BRIGANDINE_CUISSES.get(), new ArmorStats(2.0, 1.0, 3.9, 0.0, 0, 0, false, "legs"));
        map.put(KHItems.GOLDEN_BRIGANDINE_CUISSES.get(), new ArmorStats(1.0, 1.0, 3.7, 0.0, 0, 0, false, "legs"));

        map.put(KHItems.PLATE_CUISSES.get(), new ArmorStats(2.0, 1.0, 5.5, 0.10, 0, 0, true, "legs"));
        map.put(KHItems.DARK_PLATE_CUISSES.get(), new ArmorStats(3.0, 1.0, 6.1, 0.10, 0, 0, true, "legs"));
        map.put(KHItems.GOLDEN_PLATE_CUISSES.get(), new ArmorStats(2.0, 1.0, 5.8, 0.10, 0, 0, true, "legs"));

        map.put(KHItems.GREAVES.get(), new ArmorStats(0.0, 1.0, 2.5, 0.05, 0, 0, false, "legs"));
        map.put(KHItems.DARK_GREAVES.get(), new ArmorStats(1.0, 1.0, 2.8, 0.05, 0, 0, false, "legs"));
        map.put(KHItems.GOLDEN_GREAVES.get(), new ArmorStats(0.0, 1.0, 2.6, 0.05, 0, 0, false, "legs"));
        return map;
    }

    private static Map<Item, ArmorStats> createFeetMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(KHItems.SABATONS.get(), new ArmorStats(1.0, 2.0, 2.0, 0.02, 0, 0, false, "feet"));
        map.put(KHItems.DARK_SABATONS.get(), new ArmorStats(2.0, 2.0, 2.2, 0.02, 0, 0, false, "feet"));
        map.put(KHItems.GOLDEN_SABATONS.get(), new ArmorStats(1.0, 2.0, 2.1, 0.02, 0, 0, false, "feet"));
        return map;
    }

    private static Map<Item, ArmorStats> createExtraMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(KHItems.AVENTAIL.get(), new ArmorStats(1.0, 0.0, 1.5, 0.0, 0, 0, false, "chest"));
        return map;
    }

    @Override
    protected void generateDefinitions(ArmorAttachmentConsumer consumer) {
        processMap(consumer, createSpauldersMap());
        processMap(consumer, createChestplateMap());
        processMap(consumer, createHelmetMap());
        processMap(consumer, createArmsMap());
        processMap(consumer, createLegsMap());
        processMap(consumer, createFeetMap());
        processMap(consumer, createExtraMap());
    }

    private void processMap(ArmorAttachmentConsumer consumer, Map<Item, ArmorStats> map) {
        for (Map.Entry<Item, ArmorStats> entry : map.entrySet()) {
            Item item = entry.getKey();
            ArmorStats stats = entry.getValue();

            Builder builder = Builder.create()
                    .armor(stats.armor, stats.toughness)
                    .weight(stats.weight)
                    .attackSpeed(stats.attackSpeed)
                    .rechargeTime(stats.rechargeTime)
                    .slot(stats.slot.toUpperCase());

            if (stats.hasHungerDrain) {
                builder.hunger(0.1);
            }

            if (stats.deflectChance > 0) {
                builder.deflect(stats.deflectChance);
            }

            if (stats.visorNamespace != null && stats.visorType != null) {
                builder.visor(ResourceLocation.fromNamespaceAndPath(stats.visorNamespace, stats.visorType));
            }

            consumer.accept(item, builder.build());
        }
    }
}