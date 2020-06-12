package game.vanishcapabilities;

import edu.monash.fit2099.engine.*;
import game.Dirt;
import game.RandomLocationGenerator;
import game.TypeOfZombieActor;

import java.util.HashMap;

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
     * A random location generator.
     */
    private RandomLocationGenerator randomLocationGenerator = new RandomLocationGenerator();

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
                randomLocationGenerator.getRandomLocation(actor.getActor(), location.map())
                        .addActor(actor.getActor());
                vanishAbleActorActionHashMap.remove(actor);
            }
        }
    }
}
