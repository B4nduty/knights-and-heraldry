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
    public Configs configs = new Configs();

    @Config(name = KnightsHeraldry.MOD_ID)
    public static final class Configs implements ConfigData {
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

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
                Lance Weapons can Damage Tamed Entities | Default: false
                """)
        public boolean getDamageTamedEntities = false;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
                WarDart throw Cooldown | Default: 15 sec
                """)
        int wardartThrowCooldown = 15;

        public int getWardartThrowCooldown() {
            return Math.max(0, wardartThrowCooldown);
        }
    }
}