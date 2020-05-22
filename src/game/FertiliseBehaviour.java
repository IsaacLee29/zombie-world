package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class of FertiliseBehaviour.
 * <p>
 * This class is to implements how the {@code Farmer} fertilise the Crop when
 * the {@code Farmer} is standing on the {@code Crop} which is not ripe yet.
 * <p>
 * It provides method to check whether the {@code Farmer} is standing on the
 * Crop and method to execute the action.
 * 
 * @author Hee Weng Sheng
 *
 */
public class FertiliseBehaviour extends Action implements Behaviour {
	/**
	 * The destination to fertilise the crop.
	 */
	private Location destination;

	@Override
	/**
	 * If the Farmer is standing on the {@code Crop} then the {@code Farmer} can
	 * fertilise the {@code Crop} which reduce the time to be ripen by 10.
	 *
	 * @param actor the Actor acting
	 * @param map   the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	public Action getAction(Actor actor, GameMap map) {
		destination = map.locationOf(actor);
		if (destination.getGround().hasCapability(CropCapability.UNRIPE)) {
			return this;
		}
		return null;
	}

	@Override
	/**
	 * Perform the FertiliseBehaviour action by fertilizing the {@code Crop} where
	 * the {@code Actor} is standing on.
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		Crop crop = (Crop) map.locationOf(actor).getGround();
		crop.fertilise();
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive string when the {@code Actor} fertilise the
	 * {@code Crop}.
	 * 
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " fertilised the crop.";
	}
}
