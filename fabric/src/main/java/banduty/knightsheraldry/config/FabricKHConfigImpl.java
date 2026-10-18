package banduty.knightsheraldry.config;

import banduty.knightsheraldry.KnightsHeraldryFabric;

import java.util.List;

public class FabricKHConfigImpl extends KHConfigImpl {

    @Override
    public int getLanceCooldown() {
        return KnightsHeraldryFabric.CONFIG.lanceCooldown;
    }

    @Override
    public boolean getDamageTamedEntities() {
        return KnightsHeraldryFabric.CONFIG.damageTamedEntities;
    }

    @Override
    public int getWardartCooldown() {
        return KnightsHeraldryFabric.CONFIG.wardartCooldown;
    }

    @Override
    public List<String> getBannerModelItemPaths() {
        return List.copyOf(KnightsHeraldryFabric.CONFIG.bannerModelItemPaths);
    }

    @Override
    public boolean includeVanillaBannerPatterns() {
        return KnightsHeraldryFabric.CONFIG.includeVanillaBannerPatterns;
    }

    @Override
    public List<String> getBannerPatternNames() {
        return List.copyOf(KnightsHeraldryFabric.CONFIG.bannerPatternNames);
    }

    @Override
    public int getRepairKitSlotCapacity() {
        return KnightsHeraldryFabric.CONFIG.repairKitSlotCapacity;
    }

    @Override
    public int getRepairKitRepairPercent() {
        return KnightsHeraldryFabric.CONFIG.repairKitRepairPercent;
    }

    @Override
    public int getRepairKitUseCooldown() {
        return KnightsHeraldryFabric.CONFIG.repairKitUseCooldown;
    }

    @Override
    public int getRepairKitEverythingPenalty() {
        return KnightsHeraldryFabric.CONFIG.repairKitEverythingPenalty;
    }
}