package banduty.knightsheraldry.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class KHConfigs {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue lanceCooldown;
    public static final ModConfigSpec.BooleanValue damageTamedEntities;
    public static final ModConfigSpec.IntValue wardartCooldown;
    public static final ModConfigSpec.BooleanValue disableMobsSpawnWithKHArmor;
    public static final ModConfigSpec.BooleanValue disableMobsSpawnWithKHWeapon;

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

        disableMobsSpawnWithKHArmor = builder
                .comment("Disable Mobs Spawn with KH Armor")
                .define("disableMobsSpawnWithKHArmor", false);

        disableMobsSpawnWithKHWeapon = builder
                .comment("Disable Mobs Spawn with KH Weapons")
                .define("disableMobsSpawnWithKHWeapon", false);

        SPEC = builder.build();
    }
}

