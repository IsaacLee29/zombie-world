package game;

import edu.monash.fit2099.engine.GameMap;

/**
 * The ZombieWorld game has to implement this interface to determine the type of ZombieActor.
 */
public interface ZombieType {

    /**
     * Get this Type of Zombie Actor in the Zombie World.
     * @return type of Zombie actor.
     */
    Enum<?> getTypeOfZombieActor();

    /**
     * An Actor in the ZombieWorld can have its limbs knocked off if it has limbs.
     * @param map the map where the current Zombie is.
     * @return a description of the Zombie limbs knocked off.
     */
    String knockOffLimb(GameMap map);
}
