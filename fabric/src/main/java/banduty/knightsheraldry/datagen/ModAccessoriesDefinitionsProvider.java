package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModAccessoriesDefinitionsProvider extends DefinitionsProvider.Accessories {
    public ModAccessoriesDefinitionsProvider(PackOutput output) {
        super(output);
    }

    private record ArmorStats(double toughness, double armor, double weight, double deflectChance,
                              boolean hasHungerDrain, String slot, String visorNamespace, String visorType) {
        ArmorStats(double toughness, double armor, double weight, double deflectChance, boolean hasHungerDrain, String slot) {
            this(toughness, armor, weight, deflectChance, hasHungerDrain, slot, null, null);
        }
    }

    private static final String VISOR_DOUBLE_EYESLIT = "double_eyeslit";
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
        map.put(ModItems.MAIL_SPAULDERS, new ArmorStats(1.0, 0.0, 2.5, 0.01, false, "chest"));
        map.put(ModItems.MAIL_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 1.0, 2.7, 0.02, false, "chest"));
        map.put(ModItems.GOLDEN_MAIL_SPAULDERS, new ArmorStats(1.0, 0.0, 2.6, 0.01, false, "chest"));
        map.put(ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 1.0, 2.8, 0.02, false, "chest"));

        map.put(ModItems.BRIGANDINE_SPAULDERS, new ArmorStats(1.0, 1.0, 2.0, 0.0, false, "chest"));
        map.put(ModItems.BRIGANDINE_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 2.0, 2.2, 0.1, false, "chest"));
        map.put(ModItems.DARK_BRIGANDINE_SPAULDERS, new ArmorStats(2.0, 1.0, 2.2, 0.0, false, "chest"));
        map.put(ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS, new ArmorStats(2.0, 2.0, 2.4, 0.1, false, "chest"));
        map.put(ModItems.GOLDEN_BRIGANDINE_SPAULDERS, new ArmorStats(1.0, 1.0, 2.1, 0.0, false, "chest"));
        map.put(ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 2.0, 2.3, 0.1, false, "chest"));

        map.put(ModItems.PLATE_SPAULDERS, new ArmorStats(2.0, 2.0, 3.0, 0.04, true, "chest"));
        map.put(ModItems.PLATE_SPAULDERS_BESAGEWS, new ArmorStats(2.0, 3.0, 3.2, 0.05, true, "chest"));
        map.put(ModItems.PLATE_SPAULDERS_RIMMED, new ArmorStats(3.0, 2.0, 3.2, 0.05, true, "chest"));
        map.put(ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED, new ArmorStats(3.0, 3.0, 3.4, 0.06, true, "chest"));
        map.put(ModItems.DARK_PLATE_SPAULDERS, new ArmorStats(3.0, 2.0, 3.3, 0.04, true, "chest"));
        map.put(ModItems.DARK_PLATE_SPAULDERS_BESAGEWS, new ArmorStats(3.0, 3.0, 3.5, 0.05, true, "chest"));
        map.put(ModItems.DARK_PLATE_SPAULDERS_RIMMED, new ArmorStats(4.0, 2.0, 3.5, 0.05, true, "chest"));
        map.put(ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED, new ArmorStats(4.0, 3.0, 3.7, 0.06, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_SPAULDERS, new ArmorStats(2.0, 2.0, 3.2, 0.04, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS, new ArmorStats(2.0, 3.0, 3.4, 0.05, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED, new ArmorStats(3.0, 2.0, 3.4, 0.05, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED, new ArmorStats(3.0, 3.0, 3.6, 0.06, true, "chest"));
        return map;
    }

    private static Map<Item, ArmorStats> createChestplateMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(ModItems.BRIGANDINE, new ArmorStats(1.0, 3.0, 6.0, 0.0, false, "chest"));
        map.put(ModItems.DARK_BRIGANDINE, new ArmorStats(2.0, 3.0, 6.6, 0.0, false, "chest"));
        map.put(ModItems.GOLDEN_BRIGANDINE, new ArmorStats(1.0, 3.0, 6.3, 0.0, false, "chest"));

        map.put(ModItems.BRIG_BREASTPLATE, new ArmorStats(1.0, 4.0, 7.5, 0.0, false, "chest"));
        map.put(ModItems.DARK_BRIG_BREASTPLATE, new ArmorStats(2.0, 4.0, 8.3, 0.0, false, "chest"));
        map.put(ModItems.GOLDEN_BRIG_BREASTPLATE, new ArmorStats(1.0, 4.0, 7.9, 0.0, false, "chest"));

        map.put(ModItems.BRIG_BREASTPLATE_TASSETS, new ArmorStats(2.0, 4.0, 8.5, 0.0, false, "chest"));
        map.put(ModItems.DARK_BRIG_BREASTPLATE_TASSETS, new ArmorStats(3.0, 4.0, 9.4, 0.0, false, "chest"));
        map.put(ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS, new ArmorStats(2.0, 4.0, 8.9, 0.0, false, "chest"));

        map.put(ModItems.PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, true, "chest"));
        map.put(ModItems.DARK_PLATE_CUIRASS, new ArmorStats(3.0, 3.0, 8.8, 0.15, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.4, 0.15, true, "chest"));

        map.put(ModItems.MAXIMILLIAN_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, true, "chest"));
        map.put(ModItems.DARK_MAXIMILLIAN_CUIRASS, new ArmorStats(3.0, 3.0, 8.8, 0.15, true, "chest"));
        map.put(ModItems.GOLDEN_MAXIMILLIAN_CUIRASS, new ArmorStats(2.0, 3.0, 8.4, 0.15, true, "chest"));

        map.put(ModItems.XIIII_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, true, "chest"));
        map.put(ModItems.DARK_XIIII_PLATE_CUIRASS, new ArmorStats(3.0, 3.0, 8.8, 0.15, true, "chest"));
        map.put(ModItems.GOLDEN_XIIII_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.4, 0.15, true, "chest"));

        map.put(ModItems.PLATE_CUIRASS_TASSETS, new ArmorStats(3.0, 4.0, 11.0, 0.20, true, "chest"));
        map.put(ModItems.DARK_PLATE_CUIRASS_TASSETS, new ArmorStats(4.0, 4.0, 12.1, 0.20, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_CUIRASS_TASSETS, new ArmorStats(3.0, 4.0, 11.6, 0.20, true, "chest"));

        map.put(ModItems.MAXIMILLIAN_CUIRASS_TASSETS, new ArmorStats(3.0, 4.0, 11.0, 0.20, true, "chest"));
        map.put(ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS, new ArmorStats(4.0, 4.0, 12.1, 0.20, true, "chest"));
        map.put(ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS, new ArmorStats(3.0, 4.0, 11.6, 0.20, true, "chest"));

        map.put(ModItems.XIIII_PLATE_CUIRASS_TASSETS, new ArmorStats(3.0, 4.0, 11.0, 0.20, true, "chest"));
        map.put(ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS, new ArmorStats(4.0, 4.0, 12.1, 0.20, true, "chest"));
        map.put(ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS, new ArmorStats(3.0, 4.0, 11.6, 0.20, true, "chest"));

        map.put(ModItems.XIIII_PLATE_BREASTPLATE, new ArmorStats(1.0, 3.0, 7.5, 0.10, true, "chest"));
        map.put(ModItems.DARK_XIIII_PLATE_BREASTPLATE, new ArmorStats(2.0, 3.0, 8.25, 0.10, true, "chest"));
        map.put(ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE, new ArmorStats(1.0, 3.0, 7.9, 0.10, true, "chest"));
        return map;
    }

    private static Map<Item, ArmorStats> createHelmetMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        // First tier helmets
        map.put(ModItems.BARBUTE, new ArmorStats(1.0, 1.0, 2.0, 0.05, false, "head"));
        map.put(ModItems.DARK_BARBUTE, new ArmorStats(2.0, 1.0, 2.2, 0.05, false, "head"));
        map.put(ModItems.GOLDEN_BARBUTE, new ArmorStats(1.0, 1.0, 2.1, 0.05, false, "head"));

        map.put(ModItems.BASCINET, new ArmorStats(1.0, 1.0, 2.0, 0.05, false, "head"));
        map.put(ModItems.DARK_BASCINET, new ArmorStats(2.0, 1.0, 2.2, 0.05, false, "head"));
        map.put(ModItems.GOLDEN_BASCINET, new ArmorStats(1.0, 1.0, 2.1, 0.05, false, "head"));

        map.put(ModItems.KETTLE_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, false, "head"));
        map.put(ModItems.DARK_KETTLE_HELM, new ArmorStats(2.0, 1.0, 2.2, 0.05, false, "head"));
        map.put(ModItems.GOLDEN_KETTLE_HELM, new ArmorStats(1.0, 1.0, 2.1, 0.05, false, "head"));

        map.put(ModItems.NASAL_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, false, "head"));
        map.put(ModItems.DARK_NASAL_HELM, new ArmorStats(2.0, 1.0, 2.2, 0.05, false, "head"));
        map.put(ModItems.GOLDEN_NASAL_HELM, new ArmorStats(1.0, 1.0, 2.1, 0.05, false, "head"));

        map.put(ModItems.BURGONET, new ArmorStats(1.0, 1.0, 2.0, 0.05, false, "head"));
        map.put(ModItems.DARK_BURGONET, new ArmorStats(2.0, 1.0, 2.2, 0.05, false, "head"));
        map.put(ModItems.GOLDEN_BURGONET, new ArmorStats(1.0, 1.0, 2.1, 0.05, false, "head"));

        map.put(ModItems.VIKING_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.DARK_VIKING_HELM, new ArmorStats(2.0, 1.0, 2.2, 0.05, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.GOLDEN_VIKING_HELM, new ArmorStats(1.0, 1.0, 2.1, 0.05, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        // Second tier helmets
        map.put(ModItems.ARMET, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.DARK_ARMET, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.GOLDEN_ARMET, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(ModItems.VISORED_BARBUTE, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.DARK_VISORED_BARBUTE, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.GOLDEN_VISORED_BARBUTE, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(ModItems.CLOSE_HELM, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.DARK_CLOSE_HELM, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.GOLDEN_CLOSE_HELM, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(ModItems.ARMET_2, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.DARK_ARMET_2, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.GOLDEN_ARMET_2, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(ModItems.SALLET, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.DARK_SALLET, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.GOLDEN_SALLET, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(ModItems.BURGONET_FALLING_BUFFE, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.DARK_BURGONET_FALLING_BUFFE, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.GOLDEN_BURGONET_FALLING_BUFFE, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(ModItems.HOUNDSKULL, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));
        map.put(ModItems.DARK_HOUNDSKULL, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));
        map.put(ModItems.GOLDEN_HOUNDSKULL, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));

        map.put(ModItems.CAGE, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));
        map.put(ModItems.DARK_CAGE, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));
        map.put(ModItems.GOLDEN_CAGE, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));

        map.put(ModItems.VISORED_BASCINET, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(ModItems.DARK_VISORED_BASCINET, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(ModItems.GOLDEN_VISORED_BASCINET, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        map.put(ModItems.GREAT_HELM, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(ModItems.DARK_GREAT_HELM, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(ModItems.GOLDEN_GREAT_HELM, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        map.put(ModItems.GREAT_HELM_2, new ArmorStats(2.0, 2.0, 3.0, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(ModItems.DARK_GREAT_HELM_2, new ArmorStats(3.0, 2.0, 3.3, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(ModItems.GOLDEN_GREAT_HELM_2, new ArmorStats(2.0, 2.0, 3.2, 0.10, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        // Third tier helmets
        map.put(ModItems.FROGMOUTH, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));
        map.put(ModItems.DARK_FROGMOUTH, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));
        map.put(ModItems.GOLDEN_FROGMOUTH, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));

        map.put(ModItems.GREAT_ARMET, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.DARK_GREAT_ARMET, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.GOLDEN_GREAT_ARMET, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(ModItems.GREAT_HOUNDSKUL_BASCINET, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.DARK_GREAT_HOUNDSKUL_BASCINET, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(ModItems.GREAT_ARMET_2, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.DARK_GREAT_ARMET_2, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(ModItems.GOLDEN_GREAT_ARMET_2, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(ModItems.GREAT_BASCINET, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));
        map.put(ModItems.DARK_GREAT_BASCINET, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));
        map.put(ModItems.GOLDEN_GREAT_BASCINET, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));

        map.put(ModItems.MAXIMILLIAN_HELMET, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(ModItems.DARK_MAXIMILLIAN_HELMET, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(ModItems.GOLDEN_MAXIMILLIAN_HELMET, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        map.put(ModItems.SAVOYARD, new ArmorStats(3.0, 3.0, 4.0, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));
        map.put(ModItems.DARK_SAVOYARD, new ArmorStats(4.0, 3.0, 4.4, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));
        map.put(ModItems.GOLDEN_SAVOYARD, new ArmorStats(3.0, 3.0, 4.2, 0.15, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));

        return map;
    }

    private static Map<Item, ArmorStats> createArmsMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(ModItems.GAUNTLET, new ArmorStats(0.0, 2.0, 1.5, 0.05, false, "chest"));
        map.put(ModItems.DARK_GAUNTLET, new ArmorStats(1.0, 2.0, 1.7, 0.05, false, "chest"));
        map.put(ModItems.GOLDEN_GAUNTLET, new ArmorStats(0.0, 2.0, 1.6, 0.05, false, "chest"));

        map.put(ModItems.BRIGANDINE_HARNESS, new ArmorStats(1.0, 2.0, 2.0, 0.0, false, "chest"));
        map.put(ModItems.DARK_BRIGANDINE_HARNESS, new ArmorStats(2.0, 2.0, 2.2, 0.0, false, "chest"));
        map.put(ModItems.GOLDEN_BRIGANDINE_HARNESS, new ArmorStats(1.0, 2.0, 2.1, 0.0, false, "chest"));

        map.put(ModItems.PLATE_HARNESS, new ArmorStats(2.0, 2.0, 2.5, 0.05, true, "chest"));
        map.put(ModItems.DARK_PLATE_HARNESS, new ArmorStats(3.0, 2.0, 2.8, 0.05, true, "chest"));
        map.put(ModItems.GOLDEN_PLATE_HARNESS, new ArmorStats(2.0, 2.0, 2.6, 0.05, true, "chest"));
        return map;
    }

    private static Map<Item, ArmorStats> createLegsMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(ModItems.BRIGANDINE_CUISSES, new ArmorStats(1.0, 1.0, 3.5, 0.0, false, "legs"));
        map.put(ModItems.DARK_BRIGANDINE_CUISSES, new ArmorStats(2.0, 1.0, 3.9, 0.0, false, "legs"));
        map.put(ModItems.GOLDEN_BRIGANDINE_CUISSES, new ArmorStats(1.0, 1.0, 3.7, 0.0, false, "legs"));

        map.put(ModItems.PLATE_CUISSES, new ArmorStats(2.0, 1.0, 5.5, 0.10, true, "legs"));
        map.put(ModItems.DARK_PLATE_CUISSES, new ArmorStats(3.0, 1.0, 6.1, 0.10, true, "legs"));
        map.put(ModItems.GOLDEN_PLATE_CUISSES, new ArmorStats(2.0, 1.0, 5.8, 0.10, true, "legs"));

        map.put(ModItems.GREAVES, new ArmorStats(0.0, 1.0, 2.5, 0.05, false, "legs"));
        map.put(ModItems.DARK_GREAVES, new ArmorStats(1.0, 1.0, 2.8, 0.05, false, "legs"));
        map.put(ModItems.GOLDEN_GREAVES, new ArmorStats(0.0, 1.0, 2.6, 0.05, false, "legs"));
        return map;
    }

    private static Map<Item, ArmorStats> createFeetMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(ModItems.SABATONS, new ArmorStats(1.0, 2.0, 2.0, 0.02, false, "feet"));
        map.put(ModItems.DARK_SABATONS, new ArmorStats(2.0, 2.0, 2.2, 0.02, false, "feet"));
        map.put(ModItems.GOLDEN_SABATONS, new ArmorStats(1.0, 2.0, 2.1, 0.02, false, "feet"));
        return map;
    }

    private static Map<Item, ArmorStats> createExtraMap() {
        Map<Item, ArmorStats> map = new HashMap<>();
        map.put(ModItems.BEVOR, new ArmorStats(0.5, 0.0, 0.5, 0.0, false, "head"));
        map.put(ModItems.DARK_BEVOR, new ArmorStats(1.5, 0.0, 0.6, 0.0, false, "head"));
        map.put(ModItems.GOLDEN_BEVOR, new ArmorStats(0.5, 0.0, 0.5, 0.0, false, "head"));

        map.put(ModItems.AVENTAIL, new ArmorStats(2.0, 0.0, 1.5, 0.0, false, "chest"));
        return map;
    }

    @Override
    protected void generateDefinitions(AccessoriesConsumer consumer) {
        processMap(consumer, createSpauldersMap());
        processMap(consumer, createChestplateMap());
        processMap(consumer, createHelmetMap());
        processMap(consumer, createArmsMap());
        processMap(consumer, createLegsMap());
        processMap(consumer, createFeetMap());
        processMap(consumer, createExtraMap());
    }

    private void processMap(AccessoriesConsumer consumer, Map<Item, ArmorStats> map) {
        for (Map.Entry<Item, ArmorStats> entry : map.entrySet()) {
            Item item = entry.getKey();
            ArmorStats stats = entry.getValue();

            Builder builder = Builder.create()
                    .armor(stats.armor, stats.toughness)
                    .weight(stats.weight)
                    .slot(stats.slot.toUpperCase());

            if (stats.hasHungerDrain) {
                builder.hunger(0.1);
            }

            if (stats.deflectChance > 0) {
                builder.deflect(stats.deflectChance);
            }

            if (stats.visorNamespace != null && stats.visorType != null) {
                builder.visor(new ResourceLocation(stats.visorNamespace, stats.visorType));
            }

            consumer.accept(item, builder.build());
        }
    }
}