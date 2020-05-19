package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that figure out a sow action that will sow a crop on the ground besides where the 
 * actor is standing. 
 * @author wengsheng
 *
 */

public class FarmingBehaviour extends Action implements Behaviour {
	/**
	 * The location besides the actor
	 */
	private Location destination;
	
	/**
	 * A factory for creating actions. 
	 *
	 * A Behaviour represents a kind of objective that an Actor can have.  For example
	 * it might want to seek out a particular kind of object, or follow another Actor, 
	 * or run away and hide.  Each implementation of Behaviour returns an Action that the 
	 * Actor could take to achieve its objective, or null if no useful options are available.
	 *
	 * An Actor's playTurn() method can use Behaviours to help decide which Action to 
	 * perform next.  It can also simply create Actions itself, but using Behaviours allows
	 * us to modularize the code that decides what to do, and that means that it can be 
	 * reused if (e.g.) more than one kind of Actor needs to be able to seek, follow, or hide.
	 *
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	public Action getAction(Actor actor, GameMap map) {
		for (Exit exit: map.locationOf(actor).getExits()) {
			destination = exit.getDestination();
			if (map.at(destination.x(), destination.y()).getDisplayChar() == '.') {
				if(Math.random() <= 0.33) {
					return this;
				}
			}
		}
		return null;
	}

	@Override
	/**
	 * Perform the FarmingBehaviour action by sowing a crop on the ground beside where the actor is
	 * standing.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		map.at(destination.x(), destination.y()).setGround(new Crop());
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " sowed crop."; 
	}

}
