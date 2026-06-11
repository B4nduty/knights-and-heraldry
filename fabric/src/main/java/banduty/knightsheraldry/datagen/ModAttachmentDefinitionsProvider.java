package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModAttachmentDefinitionsProvider extends DefinitionsProvider.ArmorAttachment {
    public ModAttachmentDefinitionsProvider(PackOutput output) {
        super(output);
    }

    private record ArmorStats(double toughness, double armor, double weight, double deflectChance, float attackSpeed,
                              boolean hasHungerDrain, String slot, String visorNamespace, String visorType) {
        ArmorStats(double toughness, double armor, double weight, double deflectChance, float attackSpeed, boolean hasHungerDrain, String slot) {
            this(toughness, armor, weight, deflectChance, attackSpeed, hasHungerDrain, slot, null, null);
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
    private static Map<Supplier<Item>, ArmorStats> createSpauldersMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.MAIL_SPAULDERS, new ArmorStats(1.0, 0.0, 2.5, 0.01, 0, false, "chest"));
        map.put(KHItems.MAIL_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 1.0, 2.7, 0.02, 0, false, "chest"));
        map.put(KHItems.GOLDEN_MAIL_SPAULDERS, new ArmorStats(1.0, 0.0, 2.6, 0.01, 0, false, "chest"));
        map.put(KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 1.0, 2.8, 0.02, 0, false, "chest"));

        map.put(KHItems.BRIGANDINE_SPAULDERS, new ArmorStats(1.0, 1.0, 2.0, 0.0, 0, false, "chest"));
        map.put(KHItems.BRIGANDINE_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 2.0, 2.2, 0.1, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE_SPAULDERS, new ArmorStats(2.0, 1.0, 2.2, 0.0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS, new ArmorStats(2.0, 2.0, 2.4, 0.1, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE_SPAULDERS, new ArmorStats(1.0, 1.0, 2.1, 0.0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS, new ArmorStats(1.0, 2.0, 2.3, 0.1, 0, false, "chest"));

        map.put(KHItems.PLATE_SPAULDERS, new ArmorStats(2.0, 2.0, 3.0, 0.04, 0, true, "chest"));
        map.put(KHItems.PLATE_SPAULDERS_BESAGEWS, new ArmorStats(2.0, 3.0, 3.2, 0.05, 0, true, "chest"));
        map.put(KHItems.PLATE_SPAULDERS_RIMMED, new ArmorStats(3.0, 2.0, 3.2, 0.05, 0, true, "chest"));
        map.put(KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED, new ArmorStats(3.0, 3.0, 3.4, 0.06, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS, new ArmorStats(3.0, 2.0, 3.3, 0.04, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS_BESAGEWS, new ArmorStats(3.0, 3.0, 3.5, 0.05, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS_RIMMED, new ArmorStats(4.0, 2.0, 3.5, 0.05, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED, new ArmorStats(4.0, 3.0, 3.7, 0.06, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS, new ArmorStats(2.0, 2.0, 3.2, 0.04, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS, new ArmorStats(2.0, 3.0, 3.4, 0.05, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED, new ArmorStats(3.0, 2.0, 3.4, 0.05, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED, new ArmorStats(3.0, 3.0, 3.6, 0.06, 0, true, "chest"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createChestplateMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.BRIGANDINE, new ArmorStats(1.0, 3.0, 6.0, 0.0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE, new ArmorStats(2.0, 3.0, 6.6, 0.0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE, new ArmorStats(1.0, 3.0, 6.3, 0.0, 0, false, "chest"));

        map.put(KHItems.PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_CUIRASS, new ArmorStats(3.0, 3.0, 8.8, 0.15, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.4, 0.15, 0, true, "chest"));

        map.put(KHItems.MAXIMILLIAN_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, true, "chest"));
        map.put(KHItems.DARK_MAXIMILLIAN_CUIRASS, new ArmorStats(3.0, 3.0, 8.8, 0.15, 0, true, "chest"));
        map.put(KHItems.GOLDEN_MAXIMILLIAN_CUIRASS, new ArmorStats(2.0, 3.0, 8.4, 0.15, 0, true, "chest"));

        map.put(KHItems.XIIII_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, true, "chest"));
        map.put(KHItems.DARK_XIIII_PLATE_CUIRASS, new ArmorStats(3.0, 3.0, 8.8, 0.15, 0, true, "chest"));
        map.put(KHItems.GOLDEN_XIIII_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.4, 0.15, 0, true, "chest"));

        map.put(KHItems.XIIII_PLATE_BREASTPLATE, new ArmorStats(1.0, 3.0, 7.5, 0.10, 0, true, "chest"));
        map.put(KHItems.DARK_XIIII_PLATE_BREASTPLATE, new ArmorStats(2.0, 3.0, 8.25, 0.10, 0, true, "chest"));
        map.put(KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE, new ArmorStats(1.0, 3.0, 7.9, 0.10, 0, true, "chest"));

        map.put(KHItems.PLACKART, new ArmorStats(0.5, 0.5, 2.0, 0.04, 0, true, "chest"));
        map.put(KHItems.DARK_PLACKART, new ArmorStats(0.5, 0.5, 2.3, 0.04, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLACKART, new ArmorStats(0.5, 0.5, 2.2, 0.04, 0, true, "chest"));

        map.put(KHItems.TASSETS, new ArmorStats(1.0, 1.0, 3.0, 0.05, 0, true, "chest"));
        map.put(KHItems.DARK_TASSETS, new ArmorStats(1.0, 1.0, 3.3, 0.05, 0, true, "chest"));
        map.put(KHItems.GOLDEN_TASSETS, new ArmorStats(1.0, 1.0, 3.2, 0.05, 0, true, "chest"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createHelmetMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        // First tier helmets
        map.put(KHItems.BARBUTE, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head"));
        map.put(KHItems.DARK_BARBUTE, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head"));
        map.put(KHItems.GOLDEN_BARBUTE, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head"));

        map.put(KHItems.BASCINET, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head"));
        map.put(KHItems.DARK_BASCINET, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head"));
        map.put(KHItems.GOLDEN_BASCINET, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head"));

        map.put(KHItems.KETTLE_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head"));
        map.put(KHItems.DARK_KETTLE_HELM, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head"));
        map.put(KHItems.GOLDEN_KETTLE_HELM, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head"));

        map.put(KHItems.NASAL_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head"));
        map.put(KHItems.DARK_NASAL_HELM, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head"));
        map.put(KHItems.GOLDEN_NASAL_HELM, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head"));

        map.put(KHItems.BURGONET, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head"));
        map.put(KHItems.DARK_BURGONET, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head"));
        map.put(KHItems.GOLDEN_BURGONET, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head"));

        map.put(KHItems.VIKING_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_VIKING_HELM, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_VIKING_HELM, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.VISORLESS_SALLET, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, false, "head"));
        map.put(KHItems.DARK_VISORLESS_SALLET, new ArmorStats(2.0, 1.0, 2.2, 0.05, 0, false, "head"));
        map.put(KHItems.GOLDEN_VISORLESS_SALLET, new ArmorStats(1.0, 1.0, 2.1, 0.05, 0, false, "head"));

        // Second tier helmets
        map.put(KHItems.ARMET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_ARMET, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_ARMET, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.VISORED_BARBUTE, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_VISORED_BARBUTE, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_VISORED_BARBUTE, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.CLOSE_HELM, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_CLOSE_HELM, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_CLOSE_HELM, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.ARMET_2, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_ARMET_2, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_ARMET_2, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.SALLET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_SALLET, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_SALLET, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.BURGONET_FALLING_BUFFE, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_BURGONET_FALLING_BUFFE, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_BURGONET_FALLING_BUFFE, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.HOUNDSKULL, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));
        map.put(KHItems.DARK_HOUNDSKULL, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));
        map.put(KHItems.GOLDEN_HOUNDSKULL, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));

        map.put(KHItems.CAGE, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));
        map.put(KHItems.DARK_CAGE, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));
        map.put(KHItems.GOLDEN_CAGE, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));

        map.put(KHItems.VISORED_BASCINET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.DARK_VISORED_BASCINET, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_VISORED_BASCINET, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        map.put(KHItems.GREAT_HELM, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.DARK_GREAT_HELM, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_GREAT_HELM, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        map.put(KHItems.GREAT_HELM_2, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.DARK_GREAT_HELM_2, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_GREAT_HELM_2, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        map.put(KHItems.BLACK_SALLET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));
        map.put(KHItems.DARK_BLACK_SALLET, new ArmorStats(3.0, 2.0, 3.3, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));
        map.put(KHItems.GOLDEN_BLACK_SALLET, new ArmorStats(2.0, 2.0, 3.2, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));

        // Second to Third tier helmets
        map.put(KHItems.SALLET_BEVOR, new ArmorStats(2.5, 2.0, 3.5, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_SALLET_BEVOR, new ArmorStats(3.5, 2.0, 3.8, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_SALLET_BEVOR, new ArmorStats(2.5, 2.0, 3.7, 0.10, 0, false, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        // Third tier helmets
        map.put(KHItems.FROGMOUTH, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));
        map.put(KHItems.DARK_FROGMOUTH, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));
        map.put(KHItems.GOLDEN_FROGMOUTH, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));

        map.put(KHItems.GREAT_ARMET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_GREAT_ARMET, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_GREAT_ARMET, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.GREAT_HOUNDSKUL_BASCINET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_GREAT_HOUNDSKUL_BASCINET, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        map.put(KHItems.GREAT_ARMET_2, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.DARK_GREAT_ARMET_2, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));
        map.put(KHItems.GOLDEN_GREAT_ARMET_2, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        map.put(KHItems.GREAT_BASCINET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));
        map.put(KHItems.DARK_GREAT_BASCINET, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));
        map.put(KHItems.GOLDEN_GREAT_BASCINET, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));

        map.put(KHItems.MAXIMILLIAN_HELMET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.DARK_MAXIMILLIAN_HELMET, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));
        map.put(KHItems.GOLDEN_MAXIMILLIAN_HELMET, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        map.put(KHItems.SAVOYARD, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));
        map.put(KHItems.DARK_SAVOYARD, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));
        map.put(KHItems.GOLDEN_SAVOYARD, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));

        map.put(KHItems.ARAGONESE_SALLET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.DARK_ARAGONESE_SALLET, new ArmorStats(4.0, 3.0, 4.4, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));
        map.put(KHItems.GOLDEN_ARAGONESE_SALLET, new ArmorStats(3.0, 3.0, 4.2, 0.15, 0, true, "head", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createArmsMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.GAUNTLET, new ArmorStats(0.0, 2.0, 1.5, 0.05, 0, false, "chest"));
        map.put(KHItems.DARK_GAUNTLET, new ArmorStats(1.0, 2.0, 1.7, 0.05, 0, false, "chest"));
        map.put(KHItems.GOLDEN_GAUNTLET, new ArmorStats(0.0, 2.0, 1.6, 0.05, 0, false, "chest"));

        map.put(KHItems.BRIGANDINE_HARNESS, new ArmorStats(1.0, 2.0, 2.0, 0.0, 0, false, "chest"));
        map.put(KHItems.DARK_BRIGANDINE_HARNESS, new ArmorStats(2.0, 2.0, 2.2, 0.0, 0, false, "chest"));
        map.put(KHItems.GOLDEN_BRIGANDINE_HARNESS, new ArmorStats(1.0, 2.0, 2.1, 0.0, 0, false, "chest"));

        map.put(KHItems.PLATE_HARNESS, new ArmorStats(2.0, 2.0, 2.5, 0.05, 0, true, "chest"));
        map.put(KHItems.DARK_PLATE_HARNESS, new ArmorStats(3.0, 2.0, 2.8, 0.05, 0, true, "chest"));
        map.put(KHItems.GOLDEN_PLATE_HARNESS, new ArmorStats(2.0, 2.0, 2.6, 0.05, 0, true, "chest"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createLegsMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.BRIGANDINE_CUISSES, new ArmorStats(1.0, 1.0, 3.5, 0.0, 0, false, "legs"));
        map.put(KHItems.DARK_BRIGANDINE_CUISSES, new ArmorStats(2.0, 1.0, 3.9, 0.0, 0, false, "legs"));
        map.put(KHItems.GOLDEN_BRIGANDINE_CUISSES, new ArmorStats(1.0, 1.0, 3.7, 0.0, 0, false, "legs"));

        map.put(KHItems.PLATE_CUISSES, new ArmorStats(2.0, 1.0, 5.5, 0.10, 0, true, "legs"));
        map.put(KHItems.DARK_PLATE_CUISSES, new ArmorStats(3.0, 1.0, 6.1, 0.10, 0, true, "legs"));
        map.put(KHItems.GOLDEN_PLATE_CUISSES, new ArmorStats(2.0, 1.0, 5.8, 0.10, 0, true, "legs"));

        map.put(KHItems.GREAVES, new ArmorStats(0.0, 1.0, 2.5, 0.05, 0, false, "legs"));
        map.put(KHItems.DARK_GREAVES, new ArmorStats(1.0, 1.0, 2.8, 0.05, 0, false, "legs"));
        map.put(KHItems.GOLDEN_GREAVES, new ArmorStats(0.0, 1.0, 2.6, 0.05, 0, false, "legs"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createFeetMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.SABATONS, new ArmorStats(1.0, 2.0, 2.0, 0.02, 0, false, "feet"));
        map.put(KHItems.DARK_SABATONS, new ArmorStats(2.0, 2.0, 2.2, 0.02, 0, false, "feet"));
        map.put(KHItems.GOLDEN_SABATONS, new ArmorStats(1.0, 2.0, 2.1, 0.02, 0, false, "feet"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createExtraMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.AVENTAIL, new ArmorStats(2.0, 0.0, 1.5, 0.0, 0, false, "chest"));
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

    private void processMap(ArmorAttachmentConsumer consumer, Map<Supplier<Item>, ArmorStats> map) {
        for (Map.Entry<Supplier<Item>, ArmorStats> entry : map.entrySet()) {
            Supplier<Item> item = entry.getKey();
            ArmorStats stats = entry.getValue();

            Builder builder = Builder.create()
                    .armor(stats.armor, stats.toughness)
                    .weight(stats.weight)
                    .attackSpeed(stats.attackSpeed)
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

            consumer.accept(item.get(), builder.build());
        }
    }
}