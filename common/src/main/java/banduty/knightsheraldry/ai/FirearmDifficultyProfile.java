package banduty.knightsheraldry.ai;

import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;

/**
 * Movement/engagement profile keyed off the world's Difficulty GAMEMODE
 * (Peaceful/Easy/Normal/Hard - Level#getDifficulty()). This is deliberately
 * separate from the regional/effective difficulty multiplier
 * (DifficultyInstance#getEffectiveDifficulty()), which is what drives shot
 * accuracy in FirearmAttack#getInaccuracyDegrees.
 */
public enum FirearmDifficultyProfile {

    EASY(8.0, 5.0, false, 0.0, false),
    NORMAL(12.0, 6.0, true, 3.0, false),
    HARD(20.0, 6.0, true, 4.0, true);

    /** Max distance it will fire from - beyond this it closes the gap instead. */
    public final double maxShootRange;
    /** Distance it backs off to immediately after firing. */
    public final double retreatDistance;
    /** Whether it slowly repositions while charging (false = frozen still). */
    public final boolean moveWhileRecharging;
    /** Distance at/under which it hands off to vanilla melee. 0 = never. */
    public final double meleeSwitchRange;
    /** Hard-only: if pressured within [meleeSwitchRange, maxShootRange] while
     *  not recharging, retreat goal extends to HARD_PRESSURE_RETREAT_DISTANCE
     *  (see FirearmAttack) instead of the flat retreatDistance. */
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
            default -> NORMAL; // NORMAL, and PEACEFUL as a harmless fallback (piglins aren't hostile there anyway)
        };
    }
}