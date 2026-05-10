package banduty.knightsheraldry.config;

public class ForgeKHConfigImpl extends KHConfigImpl {
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
}