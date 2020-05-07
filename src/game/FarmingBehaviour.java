package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FarmingBehaviour implements Behaviour {
	
	// to get the action of sowAction if there's dirt beside the farmer
	public Action getAction(Actor actor, GameMap map) {
		
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
