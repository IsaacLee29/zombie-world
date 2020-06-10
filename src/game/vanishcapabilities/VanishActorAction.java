package game.vanishcapabilities;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VanishActorAction extends Action {

    // probability
    VanishAbleActors vanishAbleActors;

    public VanishActorAction(VanishAbleActors actor) {
        vanishAbleActors = actor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).setGround(new VanishGround(vanishAbleActors, this));
        map.removeActor(vanishAbleActors.getActor());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return vanishAbleActors + " vanished";
    }

    @Override
    public Action getNextAction() {
        if (vanishAbleActors.stillVanished())
            return this;
        else
            return null;
    }
}
