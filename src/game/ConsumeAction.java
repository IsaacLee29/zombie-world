package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ConsumeAction extends Action {
	private Food food;
	// Constructor for Consume Action that takes in Food Object
	public ConsumeAction(Food food) {
		this.food = food;
	}

	@Override
	// Consume the food and heal for the amount from the food 
	public String execute(Actor actor, GameMap map) {
		if (actor.getClass() == Player.class) {
			actor.heal(food.getHealth());
			actor.removeItemFromInventory(food);
		}
		else if (actor.getClass() == Human.class) {
			actor.heal(food.getHealth());
			map.locationOf(actor).removeItem(food);
		}
		return menuDescription(actor);
	}

	@Override
	// Display the action of the actor did
	public String menuDescription(Actor actor) {
		return actor + " consumed the food and heal for " + food.getHealth();
	}
	
}
