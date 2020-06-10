package game.vanishcapabilities;

import edu.monash.fit2099.engine.*;
import game.Dirt;
import game.TypeOfZombieActor;

import java.util.HashMap;
import java.util.Random;

/**
 * A ground that houses {@code VanishAbleActor}.
 *
 * <p>This class is used to house a {@code VanishAbleActor} after it vanishes from the game map.
 * This gives the illusion that the actor has disappeared from the world.
 *
 * <p>This class also determines whether the {@code VanishAbleActor} remains vanished, i.e. it
 * can reappear on the game map, at a random location.
 *
 * @author Isaac Lee Kian Min.
 */
public class VanishGround extends Dirt {

    /**
     * Map {@code VanishAbleActors} to corresponding {@code VanishActorAction}.
     */
    private HashMap<VanishAbleActor, Action> vanishAbleActorActionHashMap;

    /**
     * Constructor.
     *
     * @param actor the {@code VanishAbleActor} to be vanished.
     * @param action the associated {@code VanishActorAction}.
     */
    public VanishGround(VanishAbleActor actor, Action action) {
        vanishAbleActorActionHashMap = new HashMap<>();
        vanishAbleActorActionHashMap.put(actor, action);
    }

    /**
     * Overrides the default implementation to explicitly check for whether the ground contains
     * {@code MamboMarie} besides checking for other {@code VanishAbleActors}.
     *
     * @return true if and only if {@code MamboMarie} still exists or there still contains
     * vanished actors.
     */
    @Override
    public boolean containsVanishAbleActors() {
        return !vanishAbleActorActionHashMap.isEmpty() || containsMamboMarie();
    }

    /**
     * This method determines whether the ground contains {@code MamboMarie}.
     *
     * @return true if and only if {@code MamboMaire} still exists.
     */
    private boolean containsMamboMarie() {
        boolean retVal = false;
        for (VanishAbleActor actor : vanishAbleActorActionHashMap.keySet()) {
            if (actor.getActor().getTypeOfZombieActor() == TypeOfZombieActor.MAMBOMARIE)
                retVal = true;
        }
        return retVal;
    }

    /**
     * Overrides the parent class implementation to explicitly check whether a {@code VanishAbleActors}
     * is able to reappear on the {@code GameMap}.
     *
     * @param location the location of the Ground.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        reappear(location);
    }

    /**
     * This method determines whether a {@code VanishAbleActors} is able to reappear on the
     * {@code GameMap} containing this {@code location}. If successful, they will appear at
     * a random location on the game map.
     *
     * @param location the location of the Ground.
     */
    private void reappear(Location location) {
        for (VanishAbleActor actor : vanishAbleActorActionHashMap.keySet()) {
            Action action = vanishAbleActorActionHashMap.get(actor).getNextAction();
            if (action != null) {
                vanishAbleActorActionHashMap.put(actor, action);
            } else {
                randomLocation(actor, location).addActor(actor.getActor());
//                location.map().at(0, 0).addActor(actor.getActor());
                vanishAbleActorActionHashMap.remove(actor);
            }
        }
    }

    /**
     * This method is used to determine a random location on the {@code GameMap} specified
     * by the {@code location} passed-in.
     *
     * @param actor actor to be randomly placed.
     * @param location the location of the Ground.
     * @return a random {@code Location}.
     */
    private Location randomLocation(VanishAbleActor actor, Location location) {
        int xs, ys;
        Location newLocation;
        Random rand = new Random();

        xs = location.map().getXRange().max();
        ys = location.map().getYRange().max();
        newLocation = location.map().at(rand.nextInt(xs), rand.nextInt(ys));

        if (containsObjects(actor, newLocation)) {
            return randomLocation(actor, location);
        }
        return newLocation;
    }

    /**
     * This method determines whether a location already has obstacles on it.
     *
     * @param actor actor to be randomly placed.
     * @param location a random location.
     * @return true if and only if there are obstacles at the given location.
     */
    private boolean containsObjects(VanishAbleActor actor, Location location) {
        return location.containsAnActor() || location.canActorEnter(actor.getActor());
    }
}
