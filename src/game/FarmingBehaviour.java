package game;


import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class of Farming Behaviour
 *  
 * The implementation of this class is to allowed Farmer to sow a crop when the Farmer is standing 
 * besides a dirt. 
 *  
 * @author Hee Weng Sheng 
 *
 */

public class FarmingBehaviour extends Action implements Behaviour {
	/**
	 * The location besides the actor
	 */
	private Location destination;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	
	/**
	 * it will get all the locations besides where the actor is standing on 
	 * and check whether the location is a dirt. If it is a dirt it have a 33% chance to
	 * sow a crop. If there are multiple places that the actor can sow the crop then it will 
	 * randomly choose a location.
	 *
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		for (Exit exit: map.locationOf(actor).getExits()) {
			destination = exit.getDestination();
			if (map.at(destination.x(), destination.y()).getDisplayChar() == '.') {
				if(Math.random() <= 0.33) {
//					return this;
					actions.add(this);
				}
			}
		}
		if (actions.size() > 0){
			return actions.get(rand.nextInt(actions.size()));
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
	 * Returns a descriptive string when the actor sow a crop.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " sowed crop."; 
	}

}
