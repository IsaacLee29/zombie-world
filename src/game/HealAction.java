package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Heal Action.
 * 
 * <p> This class is used to allow the {@code Healer} to heal the patients within the range.
 * 
 
 * @author Hee Weng Sheng
 *
 */
public class HealAction extends Action {

	/**
	 * An array list of patients to be heal
	 */
	private ArrayList<Actor> patients = new ArrayList<>();
	/**
	 * The amount the hitPoints it will heal
	 */
	private final int HEAL = 10;
	
	/**
	 * Constructor for HealAction object.
	 * @param patients an array list of patients to be heal
	 */
	public HealAction(ArrayList<Actor> patients) {
		this.patients = patients;
	}

	/**
	 * Heal each patients in the array list and display who is the patient who got heal.
	 * @param actor the actor who heal the patients
	 * @param map the map where this action perform
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String retVal = "";
		for (Actor patient: patients) {
			patient.heal(HEAL);
			retVal += actor + " heal " + patient;
		}
		return retVal;
	}

	/**
	 * A description in the menu shows the actor heal the patients
	 * @param actor the actor who heal the patients
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + "healed.";
	}
}
