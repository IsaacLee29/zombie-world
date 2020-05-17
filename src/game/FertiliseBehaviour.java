package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FertiliseBehaviour extends Action implements Behaviour{
	private Crop crop;

	@Override
	// to get the action if the farmer is standing on a unripe crop using displayChar method
	public Action getAction(Actor actor, GameMap map) {
		Location locations = map.locationOf(actor);
		if(locations.getDisplayChar() == 'C') {
			return this;
		}
		return null;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		crop = (Crop) map.locationOf(actor).getGround();
		crop.fertilise();
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilised the crop.";
	}
}
