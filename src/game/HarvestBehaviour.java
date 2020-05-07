package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import java.util.ArrayList;
public class HarvestBehaviour implements Behaviour{

	@Override
	// To get the actions if there's any ripen crop beside the actor
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		
		for(Exit exits : map.locationOf(actor).getExits()) {
			if (exits.getDestination().getGround().getClass() == Crop.class) {
				Crop crop = (Crop) exits.getDestination().getGround();
				if (crop.getAge() >= 20) {
					return new HarvestAction(exits.getDestination());
				}
			}
		}
		return null;
	}
	
	
	

}
