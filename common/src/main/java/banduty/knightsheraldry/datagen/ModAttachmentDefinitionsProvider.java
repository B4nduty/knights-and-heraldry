package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.ArmorFamily;
import banduty.knightsheraldry.items.armor.ArmorVariant;
import banduty.knightsheraldry.items.armor.VariantCombo;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
    private static final String VISOR_DOUBLE_EYESLIT_WITH_TOP_PEEK = "double_eyeslit_with_top_peek";
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
        addFamily(map, KHItems.MAIL_SPAULDERS, new ArmorStats(1.0, 0.0, 2.5, 0.01, 0, 0, false, "chestplate"));

        addFamily(map, KHItems.BRIGANDINE_SPAULDERS, new ArmorStats(1.0, 1.0, 2.0, 0.0, 0, 0, false, "chestplate"));

        addFamily(map, KHItems.PLATE_SPAULDERS, new ArmorStats(2.0, 2.0, 3.0, 0.04, 0, 0, true, "chestplate"));

        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createChestplateMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        addFamily(map, KHItems.BRIGANDINE, new ArmorStats(1.0, 3.0, 6.0, 0.0, 0, 0, false, "chestplate"));

        addFamily(map, KHItems.PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, 0, true, "chestplate"));

        addFamily(map, KHItems.MAXIMILLIAN_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, 0, true, "chestplate"));

        addFamily(map, KHItems.XIIII_PLATE_CUIRASS, new ArmorStats(2.0, 3.0, 8.0, 0.15, 0, 0, true, "chestplate"));

        addFamily(map, KHItems.XIIII_PLATE_BREASTPLATE, new ArmorStats(1.0, 3.0, 7.5, 0.10, 0, 0, true, "chestplate"));

        addFamily(map, KHItems.PLACKART, new ArmorStats(0.5, 0.5, 2.0, 0.04, 0, 0, true, "chestplate"));

        addFamily(map, KHItems.TASSETS, new ArmorStats(1.0, 1.0, 3.0, 0.05, 0, 0, true, "chestplate"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createHelmetMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        // First tier helmets
        addFamily(map, KHItems.BARBUTE, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        addFamily(map, KHItems.BASCINET, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        addFamily(map, KHItems.KETTLE_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        addFamily(map, KHItems.NASAL_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        addFamily(map, KHItems.BURGONET, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        addFamily(map, KHItems.VIKING_HELM, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        addFamily(map, KHItems.VISORLESS_SALLET, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        addFamily(map, KHItems.MORION, new ArmorStats(1.0, 1.0, 2.0, 0.05, 0, 0, false, "helmet"));

        // Second tier helmets
        addFamily(map, KHItems.ARMET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        addFamily(map, KHItems.VISORED_BARBUTE, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        addFamily(map, KHItems.CLOSE_HELM, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        addFamily(map, KHItems.ARMET_2, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        addFamily(map, KHItems.SALLET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        addFamily(map, KHItems.BURGONET_FALLING_BUFFE, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        addFamily(map, KHItems.HOUNDSKULL, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_RIGHT));

        addFamily(map, KHItems.CAGE, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_FOUR_BARS));

        addFamily(map, KHItems.VISORED_BASCINET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        addFamily(map, KHItems.GREAT_HELM, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        addFamily(map, KHItems.GREAT_HELM_2, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_BOTH));

        addFamily(map, KHItems.BLACK_SALLET, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_PEEK));

        addFamily(map, KHItems.VISORED_MORION, new ArmorStats(2.0, 2.0, 3.0, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_WITH_TOP_PEEK));

        // Second to Third tier helmets
        addFamily(map, KHItems.SALLET_BEVOR, new ArmorStats(2.5, 2.0, 3.5, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        addFamily(map, KHItems.BLACK_SALLET_BEVOR, new ArmorStats(2.5, 2.0, 3.5, 0.10, 0, 0, false, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        // Third tier helmets
        addFamily(map, KHItems.FROGMOUTH, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_V_SHAPE));

        addFamily(map, KHItems.GREAT_ARMET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        addFamily(map, KHItems.GREAT_HOUNDSKUL_BASCINET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        addFamily(map, KHItems.GREAT_ARMET_2, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_SINGLE_EYESLIT));

        addFamily(map, KHItems.GREAT_BASCINET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BREATHES_FULL));

        addFamily(map, KHItems.MAXIMILLIAN_HELMET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG_BREATHES_BOTH));

        addFamily(map, KHItems.SAVOYARD, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT_BIG));

        addFamily(map, KHItems.ARAGONESE_SALLET, new ArmorStats(3.0, 3.0, 4.0, 0.15, 0, 0, true, "helmet", KnightsHeraldry.MOD_ID, VISOR_DOUBLE_EYESLIT));

        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createArmsMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.LEATHER_GLOVES, new ArmorStats(0.0, 0.0, 0.1, 0, 0, -10, false, "chestplate"));
        map.put(KHItems.MAIL_GLOVES, new ArmorStats(0.5, 0.0, 0.3, 0, 0, -10, false, "chestplate"));

        addFamily(map, KHItems.GAUNTLET, new ArmorStats(0.0, 2.0, 1.5, 0.05, 0, 30, false, "chestplate"));

        addFamily(map, KHItems.BRIGANDINE_HARNESS, new ArmorStats(1.0, 2.0, 2.0, 0.0, 0, 30, false, "chestplate"));

        addFamily(map, KHItems.PLATE_HARNESS, new ArmorStats(2.0, 2.0, 2.5, 0.05, 0, 30, true, "chestplate"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createLegsMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        addFamily(map, KHItems.BRIGANDINE_CUISSES, new ArmorStats(1.0, 1.0, 3.5, 0.0, 0, 0, false, "leggings"));

        addFamily(map, KHItems.PLATE_CUISSES, new ArmorStats(2.0, 1.0, 5.5, 0.10, 0, 0, true, "leggings"));

        addFamily(map, KHItems.GREAVES, new ArmorStats(0.0, 1.0, 2.5, 0.05, 0, 0, false, "leggings"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createFeetMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        addFamily(map, KHItems.SABATONS, new ArmorStats(1.0, 2.0, 2.0, 0.02, 0, 0, false, "boots"));
        return map;
    }

    private static Map<Supplier<Item>, ArmorStats> createExtraMap() {
        Map<Supplier<Item>, ArmorStats> map = new HashMap<>();
        map.put(KHItems.AVENTAIL, new ArmorStats(2.0, 0.0, 1.5, 0.0, 0, 0, false, "chestplate"));
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

            consumer.accept(item.get(), builder.build());
        }
    }

    private static void addFamily(Map<Supplier<Item>, ArmorStats> map,
                                  ArmorFamily family,
                                  ArmorStats base) {
        for (Map.Entry<VariantCombo, Supplier<Item>> entry : family.combos().entrySet()) {
            ArmorVariant tier = entry.getKey().tier();
            List<ArmorVariant> structural = entry.getKey().structural();
            Supplier<Item> item = entry.getValue();

            double toughness = tier.toughness(base.toughness());
            double weight = tier.weight(base.weight());
            double armor = base.armor();
            double deflect = base.deflectChance();
            for (ArmorVariant modifier : structural) {
                toughness = modifier.toughness(toughness);
                weight = modifier.weight(weight);
                armor = modifier.armor(armor);
                deflect = modifier.deflect(deflect);
            }

            map.put(item, new ArmorStats(
                    Math.round(toughness * 10.0) / 10.0,
                    Math.round(armor * 10.0) / 10.0,
                    Math.round(weight * 10.0) / 10.0,
                    Math.round(deflect * 100.0) / 100.0,
                    base.attackSpeed(), base.rechargeTime(), base.hasHungerDrain(), base.slot(),
                    base.visorNamespace(), base.visorType()
            ));
        }
    }
}