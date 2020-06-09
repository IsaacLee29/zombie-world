package game.vanishCapability;

import edu.monash.fit2099.engine.Actor;

public interface Vanish {

    boolean vanishing();

    Actor getActor();

    Vanish getVanishable();
}
