package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Corpse extends PortableItem {

    private Actor deadActor;
    private int count, revivalCount;

    public Corpse(Actor actor) {
        super(actor.toString(), '%');
        deadActor = actor;
        count = 0;
        revivalCount = (int)(5 + (Math.random() * 5));
    }

    @Override
    public void tick(Location currentLocation) {
        if (count == revivalCount && deadActor.getTypeOfZombieActor() == TypeOfZombieActor.HUMAN) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Zombie(deadActor.toString()));
        }
        count++;
    }
}
