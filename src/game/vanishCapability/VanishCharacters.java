package game.vanishCapability;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.Random;

public class VanishCharacters {

    private ArrayList<Actor> actors;
    private ArrayList<GameMap> maps;
    private ArrayList<Boolean> justVanished;

    private Random rand = new Random();

    public VanishCharacters() {
        actors = new ArrayList<>();
        maps = new ArrayList<>();
        justVanished = new ArrayList<>();
    }

    public boolean contains(Actor actor) {
        return actors.contains(actor);
    }

    public void addVanishedActors(Actor actor, GameMap map) {
        actors.add(actor);
        maps.add(map);
        justVanished.add(true);
    }

    public void execute() {
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            if (!justVanished.get(i) && !actor.vanishing()) {
                GameMap map = maps.get(i);
                int[] randomCoordinates = getRandomCoordinates(map);
                map.addActor(actor, map.at(randomCoordinates[0], randomCoordinates[1]));
                reset(actor);
            } else if (justVanished.get(i)) {
                justVanished.remove(i);
                justVanished.add(i, false);
            }
        }
    }

    private void reset(Actor actor) {
        int index = actors.indexOf(actor);
        justVanished.remove(index);
        maps.remove(index);
        actors.remove(index);
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
