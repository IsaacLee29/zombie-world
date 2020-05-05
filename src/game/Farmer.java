package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class Farmer extends Human {
	private Behaviour [] behaviours = {
			new FarmingBehaviour(),
//			new HarvestBehaviour(),
			new WanderBehaviour()
			};
		
	
	public Farmer(String name) {
		super(name, 'F', 50);
	}
	
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
//		double probSowing = 0.33;
//		int x = map.locationOf(this).x() +(int)Math.round(Math.random());
//		int y = map.locationOf(this).x() +(int)Math.round(Math.random());
//		
//		if (map.at(x,y).getGround().getDisplayChar() == '.') {
//			if (Math.random() <= probSowing ) {
//				new Crop();
//			}
//		}
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

}
