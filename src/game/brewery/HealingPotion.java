package game.brewery;

import edu.monash.fit2099.engine.Actor;

/**
 * A Healing Potion.
 * 
 * @author Isaac Lee Kian Min.
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

    /**
     * This method causes the actor that uses this potion to gain health.
     */
    @Override
    public String usePotion(Actor actor) {
        actor.heal(HEAL);
        return String.format("%s heals %d health points", actor, HEAL);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
