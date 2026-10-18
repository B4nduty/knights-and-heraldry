package banduty.knightsheraldry.config;

import banduty.knightsheraldry.KnightsHeraldry;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.List;

@Config(name = KnightsHeraldry.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class KHConfigs implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public int lanceCooldown = 30;

    @ConfigEntry.Gui.Tooltip
    public boolean damageTamedEntities = false;

    @ConfigEntry.Gui.Tooltip
    public int wardartCooldown = 15;

    @ConfigEntry.Gui.Tooltip
    public List<String> bannerModelItemPaths = new ArrayList<>(List.of(
            "knightsheraldry:surcoat",
            "knightsheraldry:surcoat_sleeveless"
    ));

    @ConfigEntry.Gui.Tooltip
    public boolean includeVanillaBannerPatterns = true;

    @ConfigEntry.Gui.Tooltip
    public List<String> bannerPatternNames = new ArrayList<>();

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 999)
    public int repairKitSlotCapacity = 64;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
    public int repairKitRepairPercent = 40;

    @ConfigEntry.Gui.Tooltip
    public int repairKitUseCooldown = 60;

    @ConfigEntry.Gui.Tooltip
    public int repairKitEverythingPenalty = 100;
}