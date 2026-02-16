package banduty.knightsheraldry.config;

import banduty.knightsheraldry.KnightsHeraldryForge;

public class ForgeKHConfigImpl extends KHConfigImpl {
    @Override
    public int getLanceCooldown() {
        return KnightsHeraldryForge.CONFIG.common.getLanceCooldown;
    }

    @Override
    public boolean getDamageTamedEntities() {
        return KnightsHeraldryForge.CONFIG.common.getDamageTamedEntities;
    }

    @Override
    public int getWardartCooldown() {
        return KnightsHeraldryForge.CONFIG.common.getWardartCooldown;
    }
}