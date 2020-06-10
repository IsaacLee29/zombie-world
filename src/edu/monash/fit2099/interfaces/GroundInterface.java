package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {

    /**
     * This method is used to determine whether a ground houses actors that are able to vanish.
     *
     * <p>Override this method to check whether there are any vanish-able actors still alive but vanished
     * from the game maps.
     *
     * @return default value of false.
     */
    default boolean containsVanishAbleActors() { return false; }
}
