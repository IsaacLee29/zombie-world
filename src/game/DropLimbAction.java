package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Drops a Limb to the ground.
 * 
 * Use this class to drop a {@code Limb} object. It determines a random location (that is either
 * at its current location or an adjacent location to its current) that the limb will be drop onto.
 * 
 * @author Isaac Lee Kian Min
 */
public class DropLimbAction extends DropItemAction {

    /**
     * Constructor
     * 
     * @param newItem a {@code Limb} to be dropped.
     */
    public DropLimbAction(Item newItem) {
        super(newItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int x = random((int)(Math.random() * 30)) + map.locationOf(actor).x();
        int y = random((int)(Math.random() * 30)) + map.locationOf(actor).y();
        map.at(x, y).addItem(this.item);
        return null;
    }

    /**
     * Returns an integer number in range -1 < number < 1
     *
     * Intended purpose is to help determine a random coordinate that is adjacent to
     * the Actor that had a Limb knocked off.
     *
     * @param num A randomly generated number.
     * @return An Integer number between -1 and 1
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
