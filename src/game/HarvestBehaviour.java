package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;	
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class of HarvestBehaviour. 
 * 
 * The implementation of this class is to allowed Farmer or player to harvest the ripe crop when 
 * they are standing on or beside the ripe Crop. When they harvest the crop, then i can turn into 
 * food which allows the player/human to consumed it.
 * 
 * @author Hee Weng Sheng
 *
 */

public class HarvestBehaviour extends Action implements Behaviour{
	/**
	 * the location of the crop to be harvest
	 */
	private Location destination;
	/**
	 * A random number generator
	 */
	protected Random rand = new Random();
	@Override
	/**
	 * To get the actions if there's any ripen crop beside the actor. If there's multiple ripe
	 * Crop around the actor then it will randomly harvest one of the crop.
	 */
	public Action getAction(Actor actor, GameMap map) {	
		ArrayList<Action> actions = new ArrayList<>();
		if(map.locationOf(actor).getGround().getDisplayChar() == 'R') {
//			return this;
			actions.add(this);
		}
		for(Exit exits : map.locationOf(actor).getExits()) {
			destination = exits.getDestination();
			if (destination.getGround().getDisplayChar() == 'R') {
//				return this;
				actions.add(this);
			}
		}
		if (actions.size() > 0) {
			return actions.get(rand.nextInt(actions.size()));
		}
		return null;
	}

	@Override
	/**
	 * Perform the HarvestBehaviour action which harvest the crop on the Location the actor is
	 * standing on or beside and create a Food object. The food will drop on the ground if the 
	 * farmer harvested the crop. Whereas, the food will be added into the player inventory if the
	 * player harvested the crop.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		if (actor.getDisplayChar() == 'F') {
			map.at(destination.x(),destination.y()).setGround(new Dirt());
			map.at(destination.x(), destination.y()).addItem(new Food("food", '=', true,10));
			return menuDescription(actor);
		}
		else if (actor.getDisplayChar() == '@') {
			map.at(destination.x(),destination.y()).setGround(new Dirt());
			actor.addItemToInventory(new Food("food" ,'=', true, 10));
			return menuDescription(actor);
		}
		return null;
	}
	

	@Override
	/**
	 * Returns a descriptive string when the actor harvested the crop.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		if (actor.getClass() == Farmer.class) {
			return actor + " harvested the crop.";
		}
		else if(actor.getClass() == Player.class) {
			return actor + " harvested the crop and put it in invertory";
		}
		return null;
	}
	

}
