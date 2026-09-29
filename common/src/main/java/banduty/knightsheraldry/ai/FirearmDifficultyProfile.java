package banduty.knightsheraldry.ai;

import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;

public enum FirearmDifficultyProfile {

    EASY(6.0, 2.0, false, 0.0, false),
    NORMAL(8.0, 4.0, true, 3.0, false),
    HARD(10.0, 6.0, true, 4.0, true);

    public final double maxShootRange;
    public final double retreatDistance;
    public final boolean moveWhileRecharging;
    public final double meleeSwitchRange;
    public final boolean extendedRetreatOnPressure;

    FirearmDifficultyProfile(double maxShootRange, double retreatDistance, boolean moveWhileRecharging,
                             double meleeSwitchRange, boolean extendedRetreatOnPressure) {
        this.maxShootRange = maxShootRange;
        this.retreatDistance = retreatDistance;
        this.moveWhileRecharging = moveWhileRecharging;
        this.meleeSwitchRange = meleeSwitchRange;
        this.extendedRetreatOnPressure = extendedRetreatOnPressure;
    }

    public static FirearmDifficultyProfile fromLevel(Level level) {
        Difficulty difficulty = level.getDifficulty();
        return switch (difficulty) {
            case EASY -> EASY;
            case HARD -> HARD;
            default -> NORMAL;
        };
    }
}