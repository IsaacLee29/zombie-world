package game.vanishcapabilities;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.TypeOfZombieActor;

public class ZombieWorld extends World {

    public ZombieWorld(Display display) {
        super(display);
    }

    @Override
    protected boolean stillRunning() {
        return super.stillRunning() && checkForMamboMarie();
    }

    private boolean checkForMamboMarie() {
        boolean retVal = false;

        // Checks if MamboMarie is on the game map
        for (Actor actor : actorLocations) {
            if (actor.getTypeOfZombieActor() == TypeOfZombieActor.MAMBOMARIE) {
                retVal = true;
            }
        }

        // Checks if MamboMarie vanished
        for (GameMap map : gameMaps) {
            for (int x : map.getXRange()) {
                for (int y : map.getYRange()) {
                    if (map.at(x, y).getGround().containsVanishAbleActors())
                        retVal = true;
                }
            }
        }
        return retVal;
    }
}
