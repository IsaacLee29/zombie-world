package game;

import edu.monash.fit2099.engine.*;

/**
 * A factory for creating picking up actions.
 * 
 * Returns a PickUpAction that will pick up an instance of a target class that is-a {@code Item}.
 * 
 * @author Isaac Lee Kian Min
 */
public class PickUpItemBehaviour implements Behaviour {

    /**
     * The target class of the instance to be picked up.
     */
    private Class<?> targetClass;

    /**
     * Constructor of a PickUpItemBehaviour object.
     * 
     * @param parameter the target class of the instance to be picked up.
     */
    public PickUpItemBehaviour(Class<?> parameter) {
        targetClass = parameter;
    }

    /**
     * Finds & checks at its current location whether the {@code Item} on the ground is of 
     * the {@code targetClass}.
     * 
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a {@code PickUpItemAction} if and only if it manages to an instance of the target class.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item: map.locationOf(actor).getItems()) {
            if (targetClass.isInstance(item)) {
                return item.getPickUpAction();
            }
        }
        return null;
    }
}
