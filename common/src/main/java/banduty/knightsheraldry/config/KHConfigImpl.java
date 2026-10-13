package banduty.knightsheraldry.config;

import java.util.List;

public abstract class KHConfigImpl implements IKHConfig {
    @Override
    public int getLanceCooldown() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean getDamageTamedEntities() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int getWardartCooldown() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<String> getBannerModelItemPaths() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean includeVanillaBannerPatterns() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<String> getBannerPatternNames() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
