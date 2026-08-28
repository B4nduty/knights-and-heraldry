package banduty.knightsheraldry.config;

import banduty.knightsheraldry.KnightsHeraldryFabric;

public class FabricKHConfigImpl extends KHConfigImpl {

    @Override
    public int getLanceCooldown() { return KnightsHeraldryFabric.CONFIG.lanceCooldown; }

    @Override
    public boolean getDamageTamedEntities() { return KnightsHeraldryFabric.CONFIG.damageTamedEntities; }

    @Override
    public int getWardartCooldown() { return KnightsHeraldryFabric.CONFIG.wardartCooldown; }

    @Override
    public boolean getEnableMobsSpawnWithKHArmor() {
        return KnightsHeraldryFabric.CONFIG.enableMobsSpawnWithKHArmor;
    }

    @Override
    public boolean getEnableMobsSpawnWithKHWeapons() {
        return KnightsHeraldryFabric.CONFIG.enableMobsSpawnWithKHWeapon;
    }
}