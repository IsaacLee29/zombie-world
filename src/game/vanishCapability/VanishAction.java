package game.vanishCapability;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VanishAction extends Action {

    // probability
    Actor actorToVanish;

    public VanishAction(Actor actor) {
        actorToVanish = actor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        VanishCapability.addVanishedActors(actor, this, map);
        map.removeActor(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " vanished";
    }

//    @Override
//    public Action getNextAction() {
//        if (actorToVanish.vanishing())
//            return this;
//        else
//            return null;
//    }
}
