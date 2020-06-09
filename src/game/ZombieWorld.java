package game;

import edu.monash.fit2099.engine.GameMap;

/**
 * An actor in the zombie game should implement these interfaces to determine the type of 
 * {@code Actor} it is, whether an {@code Actor} has limbs and can have its limbs knocked 
 * off, and determine whether it is able to revive itself after being killed.
 * 
 * @author Isaac Lee Kian Min
 */
public interface ZombieWorld {

    /**
     * Get the type of {@code Actor} it is in the zombie game.
     * 
     * <p>Implement this method to get the type of {@code Actor} it is in zombie game.
     * 
     * @return type of actor.
     */
    Enum<?> getTypeOfZombieActor();

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

    /**
     * In the zombie game, an {@code Actor} is unable to revive after being killed, 
     * unless...
     * 
     * <p>Override this method to determine whether an {@code Actor} can be revived.
     * 
     * @return a default value of {@code false}.
     */
    default boolean ableToRevive() { return false; }
    
    default boolean vanishing() { return false; }
}
