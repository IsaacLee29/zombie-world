package game.farming;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * <p>
 * Special action to consume food by the actor
 * <p>
 * This class models when a {@code Actor} consumed the food as an action.
 * <p>
 * A {@code Actor} who is a human will consumed the food and removed the item
 * depends on where he consumed his food either from the ground or his
 * inventory.
 * <p>
 * Where as a {@code Actor} who is a human
 * 
 * @author Hee Weng Sheng
 *
 */

public class ConsumeAction extends Action {
	/**
	 * The food to be consumed by the actor
	 */
	private Food food;

	/**
	 * Default constructor to create default ConsumeAction
	 * 
	 * @param food consumed by the actor
	 * @throws NullPointerException if the food references to null.
	 */
	public ConsumeAction(Food food) {
		Objects.requireNonNull(food);
		this.food = food;
	}

	@Override
	/**
	 * Perform the ConsumedAction. The {@code Actor} will consumed the {@code Food}
	 * to regain some health. Then it will remove from the ground or inventory
	 * depends on where the {@code Actor} consumed the {@code Food} from.
	 * 
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		if (actor.getDisplayChar() == '@') { // if the actor is a player
			actor.heal(food.getHealth());
			actor.removeItemFromInventory(food);
			map.locationOf(actor).removeItem(food);

		} else if (actor.getDisplayChar() == 'H') { // if the actor is a human
			actor.heal(food.getHealth());
			map.locationOf(actor).removeItem(food);
			actor.removeItemFromInventory(food);
		}
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive string when the player consumed the food.
	 * 
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " consumed the food and healed";
	}
}
