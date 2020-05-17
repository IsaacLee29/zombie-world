package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Special action to consume food by the actor
 * @author wengsheng
 *
 */

public class ConsumeAction extends Action {
	/**
	 * The food to be consumed by the actor
	 */
	private Food food;
	/**
	 * The actor current hit points 
	 */
	private int hitPoints;
	/**
	 * The actor maximum hit points 
	 */
	private int maxHitPoints;
	/**
	 * The amount healed by the actor
	 */
	private int healed;
	
	/**
	 * Default constructor to create default ConsumeAction
	 * @param food consumed by the actor
	 * @param hitPoints actor current hit points 
	 * @param maxHitPoints actor maximum hit points 
	 */
	public ConsumeAction(Food food ,int hitPoints, int maxHitPoints) {
		this.food = food;
		this.hitPoints = hitPoints;
		this.maxHitPoints = maxHitPoints;
	}
	

	@Override
	/**
	 * Perform the ConsumedAction.
	 * If the actor is a player it will consumed it from the player inventory.
	 * If the actor is a human it will consumed it from the ground 
	 * After consumed it it will be removed.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {
		if (actor.getDisplayChar() == '@') {
			healed = healHitPoints(actor);
			actor.removeItemFromInventory(food);
			
		}
		else if (actor.getDisplayChar() == 'H') {
			healed = healHitPoints(actor);
			map.locationOf(actor).removeItem(food);
		}
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		return actor + " consumed the food and heal for " + healed;
	}
	
	/**
	 * Heal the actor who consumed the food.
	 * @param actor
	 * @return
	 */
	public int healHitPoints(Actor actor) {
		// a method to heal the actor by having additional check whether after the actor consumed the food 
		// will heal for more than its current maxHitPoints
		if (hitPoints + food.getHealth() > maxHitPoints) {
			actor.heal(maxHitPoints - hitPoints);
			return maxHitPoints - hitPoints;
		}
		else {
			actor.heal(food.getHealth());
			return food.getHealth();
		}
	}
	
}
