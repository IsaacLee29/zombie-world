package game.brewery;

import edu.monash.fit2099.engine.Actor;

public class HealingPotion extends Potion {

    private static final int HEAL = 10;

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
