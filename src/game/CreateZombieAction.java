package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

/**
 * Special Action for creating Zombies.
 *
 * <p>Use this class to create new Zombies and insert them into the game map.
 *
 * @author Isaac Lee Kian Min.
 */
public class CreateZombieAction extends Action {

    /**
     * A List of possible Zombie names.
     */
    private static final String[] ZOMBIE_NAMES =
            new String[] {"Groan", "Boo", "Uuuurgh", "Mortalis", "Aaargh"};

    /**
     * A random location generator.
     */
    private RandomLocationGenerator randomLocationGenerator = new RandomLocationGenerator();

    /**
     * Number of Zombies to be created.
     */
    private int numberOfZombie;

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
            String name = getRandomZombieName();
            randomLocationGenerator.getRandomLocation(actor, map).addActor(new Zombie(name));
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " created " + numberOfZombie + " new " + Zombie.class.getSimpleName();
    }

    /**
     * This method gets a random zombie name so that every newly created Zombie would not
     * have the same names (at least not all of them).
     * 
     * @return a Zombie name.
     */
    private String getRandomZombieName() {
        return ZOMBIE_NAMES[(int)((ZOMBIE_NAMES.length-1) * Math.random())];
    }
}
