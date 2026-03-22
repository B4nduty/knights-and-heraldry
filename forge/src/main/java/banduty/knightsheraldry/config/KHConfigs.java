package banduty.knightsheraldry.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class KHConfigs {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.IntValue lanceCooldown;
    public static final ForgeConfigSpec.BooleanValue damageTamedEntities;
    public static final ForgeConfigSpec.IntValue wardartCooldown;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Common configuration settings")
                .push("common");

        lanceCooldown = builder
                .comment("Lance Cooldown")
                .defineInRange("lanceCooldown", 30, 0, Integer.MAX_VALUE);

        damageTamedEntities = builder
                .comment("Damage Tamed Entities")
                .define("damageTamedEntities", false);

        wardartCooldown = builder
                .comment("WarDart throw Cooldown")
                .defineInRange("wardartCooldown", 15, 0, Integer.MAX_VALUE);

        builder.pop();
        SPEC = builder.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .preserveInsertionOrder()
                .build();
        configData.load();
        spec.setConfig(configData);
    }
}

