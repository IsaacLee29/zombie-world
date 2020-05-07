package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class Farmer extends Human {
	private Behaviour [] behaviours = {
			new FarmingBehaviour(),
			new FertiliseBehaviour(),
			new HarvestBehaviour(),
			new WanderBehaviour()
			};
		
	// Constructor for the farmer that takes in string name.
	public Farmer(String name) {
		super(name, 'F', 50);
	}
	// To get the action that the farmer can do
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

}
