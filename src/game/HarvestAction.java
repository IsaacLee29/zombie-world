package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class HarvestAction extends Action{
	
	private Location location;
	// Harvest Constructor to create object with parameter of location
	public HarvestAction(Location location) {
		this.location = location;
	}
	
	// To execute the action of the actor
	public String execute(Actor actor, GameMap map) {
		if (actor.getClass() == Farmer.class) {
			map.at(location.x(),location.y()).setGround(new Dirt());
			map.at(location.x(), location.y()).addItem(new Food("food", '=', true));
			return menuDescription(actor);
		}
		else if (actor.getClass() == Player.class) {
			map.at(location.x(),location.y()).setGround(new Dirt());
			actor.addItemToInventory(new Food("food" ,'=', true));
			return menuDescription(actor);
		}
		return null;
	}

	@Override
	// To display what the actor did
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
