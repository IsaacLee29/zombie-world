package game.brewery;

import edu.monash.fit2099.engine.Actor;

/**
 * A Healing Potion.
 */
public class HealingPotion extends Potion {

    /**
     * The amount of health gained when potion used.
     */
    private static final int HEAL = 10;

    /**
     * Constructor.
     */
    public HealingPotion() {
        super("Healing Potion", 'h');
    }

    @Override
    public String usePotion(Actor actor) {
        actor.heal(HEAL);
        return String.format("%s heals %d health points", actor, HEAL);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
