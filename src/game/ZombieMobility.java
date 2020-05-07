package game;

/**
 * The Mobility of a ZombieActor.
 * Used this enum class to determine the state of mobility of a ZombieActor.
 */
public enum ZombieMobility {
    HAS_ALL_LEGS,
    NOT_ALL_LEGS,
    NO_MORE_LEGS;

    // DECIDE AGAIN WHERE SHOULD THIS VARIABLE BE AT?
    private boolean lostLimbPreviously = false;

    public void setLostLimbPreviously(boolean bool) {
        this.lostLimbPreviously = bool;
    }


    public boolean isLostLimbPreviously() {
        boolean retVal = false;
        if (noMoreLegs()) {  // If ZombieActor already has no legs
            retVal = false;
        } else if (lostLimbPreviously && notAllLegs()) {  // If ZombieActor legs were knocked off previously
            retVal = true;
        } else if (!lostLimbPreviously && notAllLegs()){  // If ZombieActor doesn't have all legs
            lostLimbPreviously = true;
        }
        return retVal;
    }

    public boolean hasAllLegs() {
        return this == HAS_ALL_LEGS;
    }

    public boolean notAllLegs() {
        return this == NOT_ALL_LEGS;
    }

    public boolean noMoreLegs() {
        return this == NO_MORE_LEGS;
    }
}
