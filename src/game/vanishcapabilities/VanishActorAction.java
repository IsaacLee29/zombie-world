package game.vanishcapabilities;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action that makes an actor vanish.
 *
 * <p>This class is used to make a {@code VanishAbleActor} vanish from the game map it is 
 * currently on. It also defines the mechanism in which the actor may reappear onto the 
 * game map.
 * 
 * <p>Use this class make a {@code VanishAbleActor} vanish and reappear on the game map.
 */
public class VanishActorAction extends Action {

    /**
     * The actor to be vanished.
     */
    private VanishAbleActor vanishAbleActor;

    /**
     * Constructor.
     *
     * @param actor a {@code VanishAbleActors}.
     */
    public VanishActorAction(VanishAbleActor actor) {
        vanishAbleActor = actor;
    }

    /**
     * This method creates a {@code VanishGround} instance to store the a {@code VanishAbleActor}.
     * 
     * <p>This gives the illusion that the actor has vanished from the game map.
     * 
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return return description of actor vanishing.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).setGround(new VanishGround(vanishAbleActor, this));
        map.removeActor(vanishAbleActor.getActor());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return vanishAbleActor + " vanished";
    }

    /**
     * This method determines whether a {@code VanishAbleActors} still remains vanished.
     *
     * @return this action if and only if vanished actor still remains vanished, otherwise
     * null.
     */
    @Override
    public Action getNextAction() {
        if (vanishAbleActor.stillVanished())
            return this;
        else
            return null;
    }
}
