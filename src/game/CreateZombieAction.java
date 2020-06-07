package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

public class CreateZombieAction extends Action {

    private int numberOfZombie;
    private Random rand = new Random();

    public CreateZombieAction(int actorNumber) {
        numberOfZombie = actorNumber;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (int i = 0; i < numberOfZombie; i++) {
            int[] randomCoordinates = getRandomCoordinates(map);
            map.addActor(new Zombie("zombies"), map.at(randomCoordinates[0], randomCoordinates[1]));
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " created " + numberOfZombie + " new " + Zombie.class.getSimpleName();
    }

    private int[] getRandomCoordinates(GameMap map) {
        int x = getRandomX(map);
        int y = getRandomY(map);

        if (containsActor(map, x, y)) {
            return getRandomCoordinates(map);
        } else {
            return new int[] {x, y};
        }
    }

    private int getRandomX(GameMap map) {
        return rand.nextInt(map.getXRange().max());
    }

    private int getRandomY(GameMap map) {
        return rand.nextInt(map.getYRange().max());
    }

    private boolean containsActor(GameMap map, int x, int y) {
        return map.isAnActorAt(map.at(x, y));
    }
}
