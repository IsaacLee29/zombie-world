package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An Action to drop a {@code Limb} to the ground.
 * <p>
 * Provides methods to drop the {@code Limb} and to help determine a random location.
 * <p>
 * Use this class to drop a {@code Limb} object onto a random location (either at its 
 * current location or an adjacent one).
 *  
 * @author Isaac Lee Kian Min
 */
public class DropLimbAction extends DropItemAction {

    /**
     * Constructor of a DropLimbAction object.
     * 
     * @param newItem a {@code Limb} to be dropped.
     */
    public DropLimbAction(Item newItem) {
        super(newItem);
    }

    /**
     * Drop the limb onto a random location (either at its current location or an adjacent 
     * one). 
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int x = random((int)(Math.random() * 30)) + map.locationOf(actor).x();
        int y = random((int)(Math.random() * 30)) + map.locationOf(actor).y();
        map.at(x, y).addItem(this.item);
        return null;
    }

    /**
     * Returns an integer number in range -1 < number < 1
     * <p>
     * Intended purpose is to help determine a random coordinate that is adjacent to
     * the {@code Actor} that had a {@code Limb} knocked off.
     *
     * @param num a randomly generated number.
     * @return an Integer number between -1 and 1
     */
    private int random(int num) {
        if (num <= 10) {
            num = 0;
        } else if (num > 10 && num <= 20) {
            num = 1;
        } else {
            num = -1;
        }
        return num;
    }
}
