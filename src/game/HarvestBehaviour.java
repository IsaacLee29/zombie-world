package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class of HarvestBehaviour.
 * <p>
 * This class is to implements how the {@code Farmer} and {@code Player} can
 * harvest the ripe {@code Crop} for {@code Food} when they are standing on or
 * next to it.
 * <p>
 * It provides method to check whether who is standing on or next to the
 * {@code Crop} which are ripe and a method to execute the action by harvesting
 * the ripe {@code Crop} to get {@code Food}.
 * 
 * @author Hee Weng Sheng
 *
 */

public class HarvestBehaviour extends Action implements Behaviour {
	/**
	 * the location of the crop to be harvest
	 */
	private Location destination;

	@Override
	/**
	 * To get the actions if there's any ripen crop beside the actor or the actor is
	 * standing on it. The actor will harvest the crop.
	 */
	public Action getAction(Actor actor, GameMap map) {
		// check whether the actor is standing on a ripe Crop
		if (map.locationOf(actor).getGround().hasCapability(CropCapability.RIPE)) {
			destination = map.locationOf(actor);
			return this;
		}
		// check whether the actor is standing next to a ripe Crop
		for (Exit exits : map.locationOf(actor).getExits()) {
			destination = exits.getDestination();
			if (destination.getGround().hasCapability(CropCapability.RIPE)) {
				return this;
			}
		}
		return null;
	}

	@Override
	/**
	 * Perform the HarvestBehaviour action which harvest the {@code Crop} on the
	 * Location the {@code Actor} is standing on or beside and create a {@code Food}
	 * object. The {@code Food} will drop on the {@code Ground} if the
	 * {@code Farmer} harvested the {@code Crop}. Whereas, the {@code Food} will be
	 * added into the {@code Player} inventory if the {@code Player} harvested the
	 * {@code Crop}.
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		// check the actor is a Farmer
		if (actor.getDisplayChar() == 'F') {
			map.at(destination.x(), destination.y()).setGround(new Dirt());
			map.at(destination.x(), destination.y()).addItem(new Food("food", '=', true, 10));
			return menuDescription(actor);
		}
		// check the actor is a Player
		else if (actor.getDisplayChar() == '@') {
			map.at(destination.x(), destination.y()).setGround(new Dirt());
			actor.addItemToInventory(new Food("food", '=', true, 10));
			return menuDescription(actor);
		}
		return null;
	}

	@Override
	/**
	 * Returns a descriptive string when the actor harvested the crop.
	 * 
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		if (actor.getClass() == Farmer.class) {
			return actor + " harvested the crop.";
		} else if (actor.getClass() == Player.class) {
			return actor + " harvested the crop and put it in invertory";
		}
		return null;
	}

}
