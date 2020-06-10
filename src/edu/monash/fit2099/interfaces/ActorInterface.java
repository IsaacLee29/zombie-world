package edu.monash.fit2099.interfaces;
import edu.monash.fit2099.engine.GameMap;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {

    /**
     * Get the type of {@code Actor} it is in the zombie game.
     *
     * <p>Implement this method to get the type of {@code Actor} it is in zombie game.
     *
     * @return type of actor.
     */
    default Enum<?> getTypeOfZombieActor() { return null; };

    /**
     * An {@code Actor} in the zombie game can have its limbs knocked off, if it has limbs.
     *
     * <p>Override this method to determine how an actor that has limbs may have its limbs
     * knocked off it or none.
     *
     * @param map the map where the current {@code Actor} is.
     * @return a default value of {@code null}.
     */
    default String knockOffLimb(GameMap map) { return null; }
}
