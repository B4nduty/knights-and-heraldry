package banduty.knightsheraldry.config;

public class FabricKHConfigImpl extends KHConfigImpl {
    private final KHConfig config;

    public FabricKHConfigImpl() {
        this.config = KHConfig.createAndLoad();
    }

    @Override
    public int getLanceCooldown() { return config.getLanceCooldown(); }

    @Override
    public boolean getDamageTamedEntities() { return config.getDamageTamedEntities(); }

    @Override
    public int getWardartCooldown() { return config.getWardartCooldown(); }
}