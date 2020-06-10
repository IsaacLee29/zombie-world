package game.vanishcapabilities;

import edu.monash.fit2099.engine.Actor;

public interface VanishAbleActors {

    /**
     * A method used to determine the chances of a {@code VanishAbleActor} reappearing on the game map.
     * @return
     */
    boolean stillVanished();

    Actor getActor();

    VanishAbleActors getVanishAble();
}
