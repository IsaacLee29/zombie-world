package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

/**
 * A Vehicle.
 *
 * <p>This class allows the {@code Player} to travel between different game maps.
 *
 * @author Isaac Lee Kian Min.
 */
public class Vehicle extends Item {

    /**
     * Constructor.
     */
    public Vehicle() {
        super("Vehicle", 'V', false);
    }

    /**
     * This method is used to add an action that the vehicle is allowed to do.
     * 
     * @param action an allowable action.
     */
    public void addAction(Action action) {
        this.allowableActions.add(action);
    }
}
