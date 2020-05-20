package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

/**
 * A Corpse that refers to a dead actor.
 *
 * This class models after the corpse when an Actor dies in the ZombieWorld. A dead actor that
 * is a human may revive to become a zombie within 5-10 play turns if the human was killed by
 * the Zombie.
 *
 * @author Isaac Lee Kian Min
 */
public class Corpse extends PortableItem {

    /**
     * The deceased Actor
     */
    private Actor deadActor;

    /**
     * {@code count} counts the number of play turns passed.
     * {@code revivalCount} determines the number at which an actor may be revived.
     */
    private int count, revivalCount;

    /**
     * Constructor of a Corpse object.
     *
     * @param actor the actor that died.
     */
    public Corpse(Actor actor) {
        super(actor.toString(), '%');
        deadActor = actor;
        count = 0;
        revivalCount = (int)(5 + (Math.random() * 5));
    }

    /**
     * Overrides its superclass method to check whether the dead actor is able to be revived
     * and that is was a human (since humans can only be attacked by a Zombie). If so, a new
     * Zombie is added into either the current location of the corpse or an adjacent location.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (deadActor.ableToRevive() && count == revivalCount) {
            // If an Actor is carrying the corpse or standing on the corpse
            if (currentLocation.containsAnActor()) {
                Random rand = new Random();
                int random = rand.nextInt(currentLocation.getExits().size());
                // Adds the corpse to a random location
                currentLocation.getExits().get(random).getDestination()
                        .addActor(new Zombie(deadActor.toString()));
                currentLocation.getActor().removeItemFromInventory(this);  // If Actor holding corpse
                currentLocation.removeItem(this); // If corpse on the ground
            } else {
                currentLocation.removeItem(this);
                currentLocation.addActor(new Zombie(deadActor.toString()));
            }
        }
        count++;
    }
}
