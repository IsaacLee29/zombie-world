package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A class that figures out ferlizing the crop that the actor is standing on which reduce
 * the time taken for the crop to ripe.
 * @author wengsheng
 *
 */
public class FertiliseBehaviour extends Action implements Behaviour{
	/**
	 * The crop to be fertilise.
	 */
	private Crop crop;

	@Override
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
	 * Returns the key used in the menu to trigger this Action.
	 *
	 * There's no central management system for this, so you need to be careful not to use the same one twice.
	 * See https://en.wikipedia.org/wiki/Connascence
	 *
	 * @return The key we use for this Action in the menu, or null to have it assigned for you.
	 */
	public String menuDescription(Actor actor) {
		return actor + " fertilised the crop.";
	}
}
