package banduty.knightsheraldry.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class KHConfigs {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue lanceCooldown;
    public static final ModConfigSpec.BooleanValue damageTamedEntities;
    public static final ModConfigSpec.IntValue wardartCooldown;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> bannerModelItemPaths;
    public static final ModConfigSpec.BooleanValue includeVanillaBannerPatterns;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> bannerPatternNames;

    public static final ModConfigSpec.IntValue repairKitSlotCapacity;
    public static final ModConfigSpec.IntValue repairKitRepairPercent;
    public static final ModConfigSpec.IntValue repairKitUseCooldown;
    public static final ModConfigSpec.IntValue repairKitEverythingPenalty;

    static {
        final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        lanceCooldown = builder
                .comment("Lance Cooldown")
                .defineInRange("lanceCooldown", 30, 0, Integer.MAX_VALUE);

        damageTamedEntities = builder
                .comment("Damage Tamed Entities")
                .define("damageTamedEntities", false);

        wardartCooldown = builder
                .comment("WarDart throw Cooldown")
                .defineInRange("wardartCooldown", 15, 0, Integer.MAX_VALUE);

        bannerModelItemPaths = builder
                .comment("Item registry IDs that should have banner base and pattern models")
                .defineList(
                        "bannerModelItemPaths",
                        List.of(
                                "knightsheraldry:surcoat",
                                "knightsheraldry:surcoat_sleeveless"
                        ),
                        entry -> entry instanceof String value
                                && value.matches("[a-z0-9_.-]+:[a-z0-9/._-]+")
                );

        includeVanillaBannerPatterns = builder
                .comment("Whether to register models for vanilla banner patterns")
                .define("includeVanillaBannerPatterns", true);

        bannerPatternNames = builder
                .comment("Additional banner pattern registry IDs to register models for")
                .defineList(
                        "bannerPatternNames",
                        List.of(),
                        entry -> entry instanceof String value
                                && value.matches("[a-z0-9_.-]+:[a-z0-9/._-]+")
                );

        repairKitSlotCapacity = builder
                .comment("Repairman's Kit: max items per material slot. Above 64, items come out in stacks of 64")
                .defineInRange("repairKitSlotCapacity", 64, 1, 999);

        repairKitRepairPercent = builder
                .comment("Repairman's Kit: percent of max durability restored per material spent")
                .defineInRange("repairKitRepairPercent", 40, 1, 100);

        repairKitUseCooldown = builder
                .comment("Repairman's Kit: cooldown in ticks after right-click in hand (20 ticks = 1 second)")
                .defineInRange("repairKitUseCooldown", 60, 0, Integer.MAX_VALUE);

        repairKitEverythingPenalty = builder
                .comment("Repairman's Kit: extra cooldown in ticks per item repaired when sneak-using (20 ticks = 1 second)")
                .defineInRange("repairKitEverythingPenalty", 100, 0, Integer.MAX_VALUE);

        SPEC = builder.build();
    }
}