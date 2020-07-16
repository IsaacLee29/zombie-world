package game.vanishing;

import edu.monash.fit2099.engine.Actor;

/**
 * An actor that is able to vanish from the game map.
 * 
 * <p>This interface is used to define an actor that is able to vanish and reappear 
 * on the game map. Use this interface if you want to mimic an actor that is able to 
 * vanish and reappear on the game map.
 */
public interface VanishAbleActor {

    /**
     * A method used to determine the chances of a {@code VanishAbleActor} reappearing on the game map.
     *
     * <p>Implement this method to determine each individual actors chances of reappearing.
     *
     * @return true if and only if actor reappears on game map, otherwise false.
     */
    boolean stillVanished();

    /**
     * A method used to get this actor with a {@code Actor} reference.
     *
     * <p>Implement this method to get a {@code Actor} reference.
     *
     * @return an actor.
     */
    Actor getActor();

    /**
     * A method used to get this actor with a {@code VanishAbleActor} reference.
     *
     * <p>Implement this method to get a {@code VanishAbleActor} reference.
     *
     * @return a VanishAbleActor.
     */
    VanishAbleActor getVanishAble();
}
