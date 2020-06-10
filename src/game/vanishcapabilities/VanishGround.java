package game.vanishcapabilities;

import edu.monash.fit2099.engine.*;
import game.Dirt;
import game.TypeOfZombieActor;

import java.util.HashMap;
import java.util.Random;

public class VanishGround extends Dirt {

    /**
     * Map {@code VanishAbleActors} to corresponding {@code VanishActorAction}.
     */
    private HashMap<VanishAbleActors, Action> vanishAbleActorsActionHashMap;

    /**
     * Constructor.
     *
     * @param actor the {@code VanishAbleActor} to be vanished.
     * @param action the associated {@code VanishActorAction}.
     */
    public VanishGround(VanishAbleActors actor, Action action) {
        vanishAbleActorsActionHashMap = new HashMap<>();
        vanishAbleActorsActionHashMap.put(actor, action);
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
        return !vanishAbleActorsActionHashMap.isEmpty() || containsMamboMarie();
    }

    /**
     * This method determines whether the ground contains {@code MamboMarie}.
     *
     * @return true if and only if {@code MamboMaire} still exists.
     */
    private boolean containsMamboMarie() {
        boolean retVal = false;
        for (VanishAbleActors actor : vanishAbleActorsActionHashMap.keySet()) {
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
        for (VanishAbleActors actor : vanishAbleActorsActionHashMap.keySet()) {
            Action action = vanishAbleActorsActionHashMap.get(actor).getNextAction();
            if (action != null) {
                vanishAbleActorsActionHashMap.put(actor, action);
            } else {
                randomLocation(location).addActor(actor.getActor());
//                location.map().at(0, 0).addActor(actor.getActor());
                vanishAbleActorsActionHashMap.remove(actor);
            }
        }
    }

    /**
     * This method is used to determine a random location on the {@code GameMap} specified
     * by the {@code location} passed-in.
     *
     * @param location the location of the Ground.
     * @return a random {@code Location}.
     */
    private Location randomLocation(Location location) {
        int xs, ys;
        Location newLocation;
        Random rand = new Random();

        xs = location.map().getXRange().max();
        ys = location.map().getYRange().max();
        newLocation = location.map().at(rand.nextInt(xs), rand.nextInt(ys));

        if (containsActor(newLocation)) {
            return randomLocation(location);
        }
        return newLocation;
    }

    /**
     * This method determines whether a location already has an actor on it.
     *
     * @param location a random location.
     * @return true if and only if an Actor is at the given location.
     */
    private boolean containsActor(Location location) {
        return location.map().isAnActorAt(location);
    }
}
