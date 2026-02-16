package banduty.knightsheraldry.config;

import banduty.knightsheraldry.KnightsHeraldry;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = KnightsHeraldry.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class KHConfigs extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject()
    public Common common = new Common();

    @Config(name = KnightsHeraldry.MOD_ID + "-common")
    public static final class Common implements ConfigData {
        @Comment("Lance Cooldown")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 180)
        public int getLanceCooldown = 30;

        @Comment("Damage Tamed Entities")
        public boolean getDamageTamedEntities = false;

        @Comment("WarDart throw Cooldown")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 180)
        public int getWardartCooldown = 15;
    }
}