package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * A Zombie Apocalyptic Word.
 * 
 * <p>This class extends the existing {@code World} class to implement other checks
 * and behaviours.
 * 
 * @author Isaac Lee
 */
public class ZombieWorld extends World {
	
	/**
	 * Does the player want to quit the game?
	 */
	boolean quitingGame = false;
	
    /**
     * Constructor.
     * 
     * @param display the Display that will display this World.
     */
	public ZombieWorld(Display display) {
        super(display);
    }

    /**
     * This method checks whether the player wants to quit the game on top of
     * processing the actor's play turn.
     */
	@Override
	protected void processActorTurn(Actor actor) {
    	if (actor.getTypeOfZombieActor() == TypeOfZombieActor.PLAYER) {
    		if (!quitGame()) {
    			super.processActorTurn(actor);
    		}
    	}
    	else {
    		super.processActorTurn(actor);
    	}
    }
    
    /**
     * This method checks certain conditions to determine the current state of the
     * game (i.e. check whether specific actors are alive) on top of checking whether 
     * the player is still alive.
     */
	@Override
    protected boolean stillRunning() {
    	
    	boolean humanInGame = false;
    	boolean zombieInGame = false;
    	boolean playerInGame = false;
    	boolean mamboMarieInGame = false;
    	
    	if (quitingGame) {
    		return false;
    	}
    	
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
    		display.println("Player Lose");
    	}
    	
    	if (!zombieInGame && !mamboMarieInGame) {
    		display.println("Player Win");
    	}
    	
        return (playerInGame && humanInGame) && (zombieInGame || mamboMarieInGame);
    }

    /**
     * This method is used to check whether {@code MamboMarie} is still alive.
     * @return
     */
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
    
    /**
     * This method is used as a menu to prompt the player on whether it wants
     * to quit the game.
     * 
     * @return true if and only if it wants to quit the game, otherwise false.
     */
	private boolean quitGame() {
    	HashMap <Character, Boolean> charMapToBoolean = new HashMap<>();
    	display.println("a: Quit Game" );
    	display.println("b: Continue");
    	
    	charMapToBoolean.put('a', true);
    	charMapToBoolean.put('b', false);
    	char key;
		do {
			key = display.readChar();
		}
		while (!charMapToBoolean.containsKey(key));
		quitingGame = charMapToBoolean.get(key);
		return quitingGame;  	
    }
}
