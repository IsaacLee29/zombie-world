package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.zombies.Zombie;

import java.util.Objects;
import java.util.Random;

/**
 * A Corpse.
 * 
 * <p>This class models after the corpse of a dead {@code Actor} in the ZombieWorld. A dead 
 * actor that is a {@code Human} may revive to become a {@code Zombie} within 5-10 play turns.
 * 
 * <p>Provides methods to revive the deceased {@code ZombieActor} if it is a {@code Human} 
 * and to simulate the passage of time for the {@code Corpse}. 
 * 
 * <p>Use this class to create a corpse.
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
     * 
     * <p>{@code revivalCount} determines the number at which an actor may be revived.
     */
    private int count, revivalCount;

    /**
     * A random number generator.
     */
    private Random rand = new Random();

    /**
     * Constructor of a Corpse object.
     *
     * @param actor the actor that died.
     * @throws NullPointerException if {@code actor} references to null.
     */
    public Corpse(Actor actor) {
        super(actor.toString(), '%');
        Objects.requireNonNull(actor);
        deadActor = actor;
        count = 0;
        revivalCount = 5 + rand.nextInt(5);
    }
    
    /**
     * Inform the {@code Corpse} on the ground of the passage of time and to determine 
     * whether the deceased {@code Actor} is able to be revived.
     */
    @Override
    public void tick(Location currentLocation) {
        this.tick(currentLocation, deadActor);
    }

    /**
     * Inform the Corpse on the ground of the passage of time and to determine whether the
     * deceased {@code Actor} is able to be revived.
     * 
     * <p>If the {@code Corpse} represent a dead {@code Human}, it will revive the {@code Human} 
     * to become a {@code Zombie} within 5-10 play turns since {@code Human} can only be
     * killed by {@code Zombie} in the zombie game.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        Location destination = currentLocation;
        if (deadActor.hasCapability(RiseFromDead.ZOMBIE) && count == revivalCount) {
        	
        	// If another Actor is carrying the corpse or standing on the corpse
            if (currentLocation.containsAnActor()) {
                destination = getRandomExitLocation(currentLocation);

                // Find the exit that has no physical obstacles and Actors on it
                while (destination.containsAnActor() || !destination.getGround().canActorEnter(deadActor)) {
                    destination = getRandomExitLocation(currentLocation);
                }

                currentLocation.getActor().removeItemFromInventory(this);  // If Actor holding corpse
                currentLocation.removeItem(this); // If an actor is standing on the corpse
            } else {
                destination.removeItem(this);
            }
            destination.addActor(new Zombie("Zombie" + deadActor.toString()));
        }
        count++;
    }

    /**
     * Gets a random exit based on its current location.
     *
     * @param currentLocation - the current location of this corpse.
     * @return an exit location.
     */
    private Location getRandomExitLocation(Location currentLocation) {
        int random = rand.nextInt(currentLocation.getExits().size());
        return currentLocation.getExits().get(random).getDestination();
    }
}
