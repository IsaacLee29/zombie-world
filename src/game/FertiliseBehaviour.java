package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A class of Fertilise Behaviour
 * 
 * The implementation of this class is to allow farmer to feritilise the Crop when he/she is 
 * standing on the Crop. Which it can increase the age of the Crop. So the Crop can ripe faster.
 * 
 * @author Hee Weng Sheng 
 *
 */
public class FertiliseBehaviour extends Action implements Behaviour{
	/**
	 * The crop to be fertilise.
	 */
	private Crop crop;

	@Override
	/**
	 * If the Farmer is standing on the crop then the farming can fertilise the crop 
	 * which increases the age of the crop by 10.
	 *
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	public Action getAction(Actor actor, GameMap map) {
		Location locations = map.locationOf(actor);
		if(locations.getDisplayChar() == 'C') {
			return this;
		}
		return null;
	}

	@Override
	/**
	 * Perform the FertiliseBehaviour action by fertilizing the crop where the actor is
	 * standing on.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		crop = (Crop) map.locationOf(actor).getGround();
		crop.fertilise();
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive string when the Farmer fertilise the crop.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " fertilised the crop.";
	}
}
