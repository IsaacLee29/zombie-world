package game;

import edu.monash.fit2099.engine.Action;	
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class that figures out how to harvest the crop and drop the food on the ground or put it in 
 * the inventory depends on the type of actor. 
 * 
 * @author wengsheng
 *
 */

public class HarvestBehaviour extends Action implements Behaviour{
	/**
	 * the location of the crop to be harvest
	 */
	private Location destination;
	@Override
	// To get the actions if there's any ripen crop beside the actor

	public Action getAction(Actor actor, GameMap map) {	
		if(map.locationOf(actor).getGround().getDisplayChar() == 'R') {
			return this;
		}
		for(Exit exits : map.locationOf(actor).getExits()) {
			destination = exits.getDestination();
			if (destination.getGround().getDisplayChar() == 'R') {
				return this;
			}
		}
		return null;
	}

	@Override
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
