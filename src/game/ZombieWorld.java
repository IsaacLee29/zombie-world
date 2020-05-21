package game;

import edu.monash.fit2099.engine.GameMap;

/**
 * An Actor of the ZombieWorld game implements these interfaces to determine the type of 
 * {@code ZombieActor} it is, whether a {@code ZombieActor} has limbs and can have its 
 * limbs knocked off and determine whether it is able to revive itself after being killed.
 * 
 * @author Isaac Lee Kian Min
 */
public interface ZombieWorld {

    /**
     * Get this Type of ZombieActor in the ZombieWorld.
     * 
     * Implement this method to get the types of ZombieActor in the ZombieWorld.
     * 
     * @return type of ZombieActor.
     */
    Enum<?> getTypeOfZombieActor();

    /**
     * An Actor in the ZombieWorld can have its limbs knocked off if it has limbs.
     * 
     * Override this method to determine how an Actor that has limbs may have its limbs
     * knocked off it or none.
     * 
     * @param map the map where the current ZombieActor is.
     * @return a default value of {@code null}.
     */
    default String knockOffLimb(GameMap map) { return null; }

    /**
     * In the ZombieWorld, a ZombieActor is unable to revive after being killed, unless...
     * 
     * Override this method to determine whether a ZombieActor can revive.
     * 
     * @return a default value of {@code false}.
     */
    default boolean ableToRevive() { return false; }
}
