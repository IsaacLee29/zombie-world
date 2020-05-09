package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
public class HarvestBehaviour extends Action implements Behaviour{
		
	private Location destination;
	@Override
	// To get the actions if there's any ripen crop beside the actor
	public Action getAction(Actor actor, GameMap map) {
//		ArrayList<Action> actions = new ArrayList<>();
				
		for(Exit exits : map.locationOf(actor).getExits()) {
			destination = exits.getDestination();
			if (exits.getDestination().getGround().getClass() == Crop.class) {
				Crop crop = (Crop) exits.getDestination().getGround();
				if (crop.getAge() >= 20) {
					return this;
//					return new HarvestAction(exits.getDestination());
				}
			}
		}
		return null;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.getClass() == Farmer.class) {
			map.at(destination.x(),destination.y()).setGround(new Dirt());
			map.at(destination.x(), destination.y()).addItem(new Food("food", '=', true));
			return menuDescription(actor);
		}
		else if (actor.getClass() == Player.class) {
			map.at(destination.x(),destination.y()).setGround(new Dirt());
			actor.addItemToInventory(new Food("food" ,'=', true));
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
