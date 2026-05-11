package banduty.knightsheraldry.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class KHConfigs {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue lanceCooldown;
    public static final ModConfigSpec.BooleanValue damageTamedEntities;
    public static final ModConfigSpec.IntValue wardartCooldown;

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

        SPEC = builder.build();
    }
}

