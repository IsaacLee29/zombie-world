package game.brewery;

import edu.monash.fit2099.engine.Actor;

/**
 * A Poison Potion.
 * 
 * @author Isaac Lee Kian Min.
 */
public class PoisonPotion extends Potion {

    /**
     * The amount of health lost when potion used.
     */
    private static final int POISON = 10;

    /**
     * Constructor.
     */
    public PoisonPotion() {
        super("Posion Potion", 'p');
    }

    /**
     * This method causes the actor that uses this potion to lose health.
     */
    @Override
    public String usePotion(Actor actor) {
        actor.hurt(POISON);
        return String.format("%s loses %d health points", actor, POISON);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
