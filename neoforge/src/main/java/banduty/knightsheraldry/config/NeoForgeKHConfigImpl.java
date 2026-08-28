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
    public boolean getDisableMobsSpawnWithKHArmor() {
        return KHConfigs.disableMobsSpawnWithKHArmor.get();
    }

    @Override
    public boolean getDisableMobsSpawnWithKHWeapons() {
        return KHConfigs.disableMobsSpawnWithKHWeapon.get();
    }
}