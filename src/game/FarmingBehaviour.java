package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FarmingBehaviour implements Behaviour {
	
	public FarmingBehaviour() {
		
	}
	
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		
		for (Exit exit: map.locationOf(actor).getExits()) {
			Location destination = exit.getDestination();
			if (destination.getGround().getClass() == Dirt.class) {
				if(Math.random() <= 0.33) {
					return new SowAction(destination);
				}
			}
		}
		return null;
	}
}
