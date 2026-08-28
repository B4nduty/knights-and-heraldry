package banduty.knightsheraldry.config;

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
    public boolean getEnableMobsSpawnWithKHArmor() {
        return KHConfigs.enableMobsSpawnWithKHArmor.get();
    }

    @Override
    public boolean getEnableMobsSpawnWithKHWeapons() {
        return KHConfigs.enableMobsSpawnWithKHWeapon.get();
    }
}