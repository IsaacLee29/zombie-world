package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class HealAction extends Action {
	
	private ArrayList<Actor> patients = new ArrayList<>();
	
	public HealAction(ArrayList<Actor> patients) {
		this.patients = patients;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String retVal = "";
		for (Actor patient: patients) {
			patient.heal(10);
			retVal += actor + " heal " + patient;
		}
		return retVal;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + "healed.";
	}
}
