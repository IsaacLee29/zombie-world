package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FarmingBehaviour extends Action implements Behaviour {
	private Location destination;
	
	// to get the action of sowAction if there's dirt beside the farmer
	public Action getAction(Actor actor, GameMap map) {
		
		for (Exit exit: map.locationOf(actor).getExits()) {
//			Location destination = exit.getDestination();
			destination = exit.getDestination();
			if (map.at(destination.x(), destination.y()).getDisplayChar() == '.') {
				if(Math.random() <= 0.33) {
					return this;
				}
			}
		}
		return null;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		map.at(destination.x(), destination.y()).setGround(new Crop());
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " sowed crop."; 
	}

}
