package game;

import edu.monash.fit2099.engine.*;

/**
 * A factory for creating picking up actions.
 * 
 * This class models after the behaviour of wanting to pick up a specific object that
 * is an {@code Item}. If it manages to find it, it will create a pick up action.
 * 
 * @author Isaac Lee Kian Min
 */
public class PickUpItemBehaviour implements Behaviour {

    /**
     * The type of Item targeted.
     */
    private Class<?> targetClass;

    /**
     * Constructor of a PickUpItemBehaviour object.
     * @param parameter the class of a type of Item.
     */
    public PickUpItemBehaviour(Class<?> parameter) {
        targetClass = parameter;
    }

    /**
     * Finds for the targeted item type at the current location.
     * 
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a {@code PickUpItemAction} if and only if it manages to find one.
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
