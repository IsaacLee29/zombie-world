package game;

import edu.monash.fit2099.engine.GameMap;

/**
 * An Actor of the ZombieWorld game implements these interfaces to determine the
 * type of Zombie Actor, whether an Actor that has limbs can have its limbs knocked off 
 * and determine whether it is able to revive itself after being killed.
 * 
 * @author Isaac Lee Kian Min
 */
public interface ZombieWorld {

    /**
     * Get this Type of Zombie Actor in the Zombie World.
     * 
     * Implement this method to get the types of Zombie Actor in the Zombie World.
     * 
     * @return type of Zombie Actor.
     */
    Enum<?> getTypeOfZombieActor();

    /**
     * An Actor in the ZombieWorld can have its limbs knocked off if it has limbs.
     * 
     * Override this method to determine how an Actor that has limbs may have its limbs
     * knocked off it.
     * 
     * @param map the map where the current Zombie Actor is.
     * @return a default value of null.
     */
    default String knockOffLimb(GameMap map) { return null; }

    /**
     * In the ZombieWorld, a ZombieActor is unable to revive after being killed, unless...
     * 
     * Override this method to determine whether a Zombie Actor can revive.
     * 
     * @return a default value of false.
     */
    default boolean ableToRevive() { return false; }
}
