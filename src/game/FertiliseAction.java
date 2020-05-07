package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FertiliseAction extends Action{
	
	private Location location;
	// FertiliseAction constructor used to create fertiliseAction object
	public FertiliseAction(Location location) {
		this.location = location;
	}

	@Override
	// Actor execute his action of fertilising the crop and displaying the action
	public String execute(Actor actor, GameMap map) {
		Crop crop = (Crop) map.locationOf(actor).getGround();
		crop.fertilise();
		return menuDescription(actor);
	}

	@Override
	// display the action the actor did
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " fertilised the crop at ";
	}
	
	

}
