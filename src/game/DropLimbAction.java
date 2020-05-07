package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action for ZombieActors that had their Limbs knocked off.
 *
 */
public class DropLimbAction extends DropItemAction {

    public DropLimbAction(Limb limb, int damage, String verb) {
        super(new SimpleClub(limb, damage, verb));
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int x = random((int)(Math.random() * 30)) + map.locationOf(actor).x();
        int y = random((int)(Math.random() * 30)) + map.locationOf(actor).y();
        map.at(x, y).addItem(this.item);
        return super.menuDescription(actor);
    }

    /**
     * Returns an Integer number in range -1 < number < 1
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
