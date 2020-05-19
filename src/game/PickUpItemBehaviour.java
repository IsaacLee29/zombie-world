package game;

import edu.monash.fit2099.engine.*;
import game.Behaviour;

/**
 * If wanna pick up a certain type of item (i.e. a subclass of Item).
 */
public class PickUpItemBehaviour implements Behaviour {

    private Class<?> targetClass;  // Item class type

    public PickUpItemBehaviour(Class<?> parameter) {
        targetClass = parameter;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item: map.locationOf(actor).getItems()) {
            if (targetClass.isInstance(item)) {  // If item can be casted as a specified class type
                return item.getPickUpAction();
            }
        }
        return null;
    }
}