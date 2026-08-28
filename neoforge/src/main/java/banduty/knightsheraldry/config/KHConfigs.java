package banduty.knightsheraldry.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class KHConfigs {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue lanceCooldown;
    public static final ModConfigSpec.BooleanValue damageTamedEntities;
    public static final ModConfigSpec.IntValue wardartCooldown;
    public static final ModConfigSpec.BooleanValue enableMobsSpawnWithKHArmor;
    public static final ModConfigSpec.BooleanValue enableMobsSpawnWithKHWeapon;

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

        enableMobsSpawnWithKHArmor = builder
                .comment("Enable Mobs Spawn with KH Armor")
                .define("enableMobsSpawnWithKHArmor", false);

        enableMobsSpawnWithKHWeapon = builder
                .comment("Enable Mobs Spawn with KH Weapons")
                .define("enableMobsSpawnWithKHWeapon", false);

        SPEC = builder.build();
    }
}

