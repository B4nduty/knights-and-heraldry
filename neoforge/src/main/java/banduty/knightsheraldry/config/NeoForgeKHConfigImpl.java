package banduty.knightsheraldry.config;

import java.util.List;

public class NeoForgeKHConfigImpl extends KHConfigImpl {
    @Override
    public int getLanceCooldown() {
        return KHConfigs.lanceCooldown.get();
    }

    @Override
    public boolean getDamageTamedEntities() {
        return KHConfigs.damageTamedEntities.get();
    }

    @Override
    public int getWardartCooldown() {
        return KHConfigs.wardartCooldown.get();
    }

    @Override
    public List<String> getBannerModelItemPaths() {
        return KHConfigs.bannerModelItemPaths.get().stream()
                .map(String::valueOf)
                .toList();
    }

    @Override
    public boolean includeVanillaBannerPatterns() {
        return KHConfigs.includeVanillaBannerPatterns.get();
    }

    @Override
    public List<String> getBannerPatternNames() {
        return KHConfigs.bannerPatternNames.get().stream()
                .map(String::valueOf)
                .toList();
    }

    @Override
    public int getRepairKitSlotCapacity() {
        return KHConfigs.repairKitSlotCapacity.get();
    }

    @Override
    public int getRepairKitRepairPercent() {
        return KHConfigs.repairKitRepairPercent.get();
    }

    @Override
    public int getRepairKitUseCooldown() {
        return KHConfigs.repairKitUseCooldown.get();
    }

    @Override
    public int getRepairKitEverythingPenalty() {
        return KHConfigs.repairKitEverythingPenalty.get();
    }
}