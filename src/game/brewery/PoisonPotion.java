package game.brewery;

import edu.monash.fit2099.engine.Actor;

public class PoisonPotion extends Potion {

    private static final int POISON = 10;

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
