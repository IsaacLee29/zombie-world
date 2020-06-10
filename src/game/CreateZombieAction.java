package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

/**
 * Special Action for creating Zombies.
 *
 * <p>Use this class to create new Zombies and insert them into the game map.
 */
public class CreateZombieAction extends Action {

    /**
     * Number of Zombies to be created.
     */
    private int numberOfZombie;

    /**
     * A random number generator.
     */
    private Random rand = new Random();

    /**
     * Constructor.
     *
     * @param actorNumber number of Zombies to create.
     */
    public CreateZombieAction(int actorNumber) {
        numberOfZombie = actorNumber;
    }

    /**
     * Creates Zombies and place them at random locations on the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (int i = 0; i < numberOfZombie; i++) {
            int[] randomCoordinates = getRandomCoordinates(actor, map);
            map.addActor(new Zombie("zombies"), map.at(randomCoordinates[0], randomCoordinates[1]));
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " created " + numberOfZombie + " new " + Zombie.class.getSimpleName();
    }

    /**
     * This method gets random x and y coordinates that are within range of the map.
     *
     * @param actor an actor performing the action.
     * @param map the map the actor is on.
     * @return an integer array of length 2, where the first and second elements are
     *         the x and y coordinates respectively.
     */
    private int[] getRandomCoordinates(Actor actor, GameMap map) {
        int x = getRandomX(map);
        int y = getRandomY(map);

        if (map.at(x, y).containsAnActor() || !map.at(x, y).canActorEnter(actor)) {
            return getRandomCoordinates(actor, map);
        } else {
            return new int[] {x, y};
        }
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
}
