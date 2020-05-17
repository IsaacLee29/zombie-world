package game;

/**
 * The Mobility of a ZombieActor.
 * Used this enum class to determine the state of mobility of a ZombieActor.
 */
public enum ZombieMobility {
    MOBILE,
    PARTIALLY_MOBILE,
    IMMOBILE;

    public boolean hasAllLegs() {
        return this == MOBILE;
    }

    public boolean notAllLegs() {
        return this == PARTIALLY_MOBILE;
    }

    public boolean noMoreLegs() {
        return this == IMMOBILE;
    }
}
