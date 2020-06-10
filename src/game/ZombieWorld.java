package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

public class ZombieWorld extends World {

    public ZombieWorld(Display display) {
        super(display);
    }

    @Override
    protected boolean stillRunning() {
    	boolean humanInGame = false;
    	boolean zombieInGame = false;
    	boolean playerInGame = false;
    	boolean mamboMarieInGame = false;
    	
    	// if player is still alive
    	if (super.stillRunning()) {
    		playerInGame = true;
    		for (Actor actor: actorLocations) {
    			if(actor.getTypeOfZombieActor() == TypeOfZombieActor.HUMAN 
    					|| actor.getTypeOfZombieActor() == TypeOfZombieActor.FARMER) {
    				humanInGame = true;
    			}
    			if (actor.getTypeOfZombieActor() == TypeOfZombieActor.ZOMBIE 
    					|| actor.getTypeOfZombieActor() == TypeOfZombieActor.MAMBOMARIE) {
    				zombieInGame = true;
    			}  					
    		}
    		 mamboMarieInGame = checkForMamboMarie();
    	}
    	
    	// If either player is dead or all human is dead
    	if (!humanInGame || !playerInGame) {
    		display.println("Player Win");
    	}
    	
    	if (!zombieInGame && mamboMarieInGame) {
    		display.println("Player Lose");
    	}
    	
        return (playerInGame && humanInGame) && (zombieInGame || mamboMarieInGame);
    }

    private boolean checkForMamboMarie() {
        boolean retVal = false;

        // Checks if MamboMarie is on the game map
//        for (Actor actor : actorLocations) {
//            if (actor.getTypeOfZombieActor() == TypeOfZombieActor.MAMBOMARIE) {
//                retVal = true;
//            }
//        }

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
