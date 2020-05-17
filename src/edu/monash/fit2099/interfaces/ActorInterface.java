package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.GameMap;
import game.ZombieType;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface extends ZombieType{

    /**
     * If an Actor has Limbs, it may lose it when attacked by another Actor.
     * Implement this method so that an Actor, who is attacked and has Limbs, may lose them.
     *
     * @return description of which Limb was lost as a result of an attack.
     */
    String loseLimbs(GameMap map);
}
