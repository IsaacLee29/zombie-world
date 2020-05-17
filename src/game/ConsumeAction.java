package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ConsumeAction extends Action {
	private Food food;
	private int hitPoints;
	private int maxHitPoints;
	private int healed;
	// Constructor for Consume Action that takes in Food Object
	public ConsumeAction(Food food ,int hitPoints, int maxHitPoints) {
		this.food = food;
		this.hitPoints = hitPoints;
		this.maxHitPoints = maxHitPoints;
	}
	

	@Override
	// Consume the food and heal for the amount from the food 
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
	// Display the action of the actor did
	public String menuDescription(Actor actor) {
		return actor + " consumed the food and heal for " + healed;
	}
	
	// a method to heal the actor by having additional check whether after the actor consumed the food 
	// will heal for more than its current maxHitPoints
	public int healHitPoints(Actor actor) {
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
