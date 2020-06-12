package game.brewery;

import edu.monash.fit2099.engine.Actor;

/**
 * A Poison Potion.
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

    @Override
    public String usePotion(Actor actor) {
        actor.hurt(POISON);
        return String.format("%s loses %d health points", actor, POISON);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
