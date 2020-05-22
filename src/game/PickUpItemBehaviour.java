package game;

import edu.monash.fit2099.engine.*;

/**
 * A factory for creating pick up actions.
 * 
 * <p>This class creates a {@code PickUpAction} that will pick up an instance of a target class 
 * that is-a {@code Item}.
 * 
 * <p>Use this class to pick up a specific instance of an item.
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
     * Finds and checks at its current location whether the item on the ground is of the 
     * {@code targetClass}.
     * 
     * @return a {@code PickUpItemAction} if and only if it manages to an instance of the 
     * target class.
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
