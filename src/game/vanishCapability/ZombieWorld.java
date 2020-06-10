package game.vanishCapability;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;
import game.TypeOfZombieActor;

public class ZombieWorld extends World {

    public ZombieWorld(Display display) {
        super(display);
    }

    @Override
    protected boolean stillRunning() {
    	// if player is still alive; I WILL CALLED ANOTHER METHOD TO CHECK WHETHER THE HUMAN IN 
    	// THE COMPOUND IS STILL ALIVE. BUT NOW THE QUESTION IS HOW TO I GET EACH ACTOR THAT IS IN THE MAP 
    	// LATER COME BACK CHECK WEHTER THE PARENT CLASS CONTAINS A PROTECTED ATTRIBUTES FOR ALL THE 
    	// ACTORS
    	boolean humanInGame = false;
    	boolean zombieInGame = false;
    	boolean playerInGame = false;
    	
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
//    		for ()
    	}
        return super.stillRunning();
    }
}
