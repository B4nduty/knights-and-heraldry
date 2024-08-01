package com.knightsheraldry.config;

import com.knightsheraldry.KnightsHeraldry;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = KnightsHeraldry.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class ModConfigs extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject()
    public Common common = new Common();

    @Config(name = KnightsHeraldry.MOD_ID + "-common")
    public static final class Common implements ConfigData {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
                Vanilla Weapons deals 0 of Damage | Default: false
                """)
        public boolean getVanillaWeaponsDamage0 = false;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
                Use Stamina on Blocking or use Stamina while Blocking | Default: true
                """)
        public boolean getBlocking = true;
    }
}