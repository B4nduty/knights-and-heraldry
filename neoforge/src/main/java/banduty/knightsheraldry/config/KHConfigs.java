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

        SPEC = builder.build();
    }
}