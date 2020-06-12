package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

/**
 * A random location generator.
 *
 * This class is primarily used as to locate a random location on the game map.
 */
public class RandomLocationGenerator {

    /**
     * A random number generator.
     */
    private Random rand = new Random();

    /**
     * This method gets and locates a random location on the game map.
     *
     * @param actor an actor to be relocated.
     * @param map a game map.
     * @return a random location.
     */
    public Location getRandomLocation(Actor actor, GameMap map) {
        int x, y;
        x = getRandomX(map);
        y = getRandomY(map);
        if (containsObjects(actor, map.at(x, y))) {
            return getRandomLocation(actor, map);
        }
        return map.at(x, y);
    }

    /**
     * This method gets a random x coordinate of a location.
     *
     * @param map the map the actor is on.
     * @return number representing x coordinate.
     */
    private int getRandomX(GameMap map) {
        return rand.nextInt(map.getXRange().max());
    }

    /**
     * This method gets a random y coordinate of a location.
     *
     * @param map the map the actor is on.
     * @return number representing y coordinate.
     */
    private int getRandomY(GameMap map) {
        return rand.nextInt(map.getYRange().max());
    }

    /**
     * This method determines whether the location contains objects or is impassable.
     *
     * @param actor actor to be randomly placed.
     * @param location a random location.
     * @return true if and only if there are obstacles or is an impassable location, otherwise false.
     */
    private boolean containsObjects(Actor actor, Location location) {
        return location.containsAnActor() || !location.canActorEnter(actor);
    }
}
