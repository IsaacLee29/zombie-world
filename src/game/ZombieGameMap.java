package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.List;

public class ZombieGameMap extends GameMap {

//    HashMap<VanishAble, Actor> theVanished = new HashMap<>();
    HashMap<Actor, Location> bosses = new HashMap<>();

    public ZombieGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }


    @Override
    public void tick() {
        super.tick();
//        vanishable();
    }

//    private void vanishable() {
//        for (Actor actor : actorLocations) {
//            boolean vanished = false;
//            if (actor.vanishable()) {
//                bosses.put(actor, locationOf(actor));
//                removeActor(actor);
//                vanished = true;
//            }
//            if (!vanished) {
//                reappear();
//            }
//        }
//    }
//
//    private void reappear() {
//        for (Actor boss : bosses.keySet()) {
//            if (!contains(boss) && Math.random() <= 1) {
//                addActor(boss, bosses.get(boss));
//            }
//        }
//    }
}
