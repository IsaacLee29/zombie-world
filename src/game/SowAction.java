package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class SowAction extends Action{
	private Location locations;
	
	public SowAction(Location locations) {
		this.locations = locations;
	}
	
	
	public String execute(Actor actor, GameMap map) {
		map.at(locations.x(),locations.y()).setGround(new Crop());
		return menuDescription(actor);
	}
	
	public String menuDescription(Actor actor) {
		return actor + " sowed crop."; 
		
	}
}
