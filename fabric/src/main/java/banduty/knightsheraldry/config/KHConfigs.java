package banduty.knightsheraldry.config;

import banduty.knightsheraldry.KnightsHeraldry;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = KnightsHeraldry.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class KHConfigs implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public int lanceCooldown = 30;

    @ConfigEntry.Gui.Tooltip
    public boolean damageTamedEntities = false;

    @ConfigEntry.Gui.Tooltip
    public int wardartCooldown = 15;
}