package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Healer.
 * 
 * This class is used to stimulate the Healer characteristics.
 * 
 * <p> Healer is able to heal the actor of type {@code Human} or {@code Farmer} or {@code Player}.
 * 
 * <p> It will wander around the map when there are no actor for the healer to heal.
 * @author Hee Weng Sheng
 *
 */
public class Healer extends Human{

	/**
	 * Healer healing range
	 */
	private final static int HEALER_MAX_RANGE = 3;
	
	/**
	 * Behaviours of the healer
	 */
	private Behaviour[]behaviours = {
			new WanderBehaviour()
	};
	
	/**
	 * Constructor of a healer.
	 * @param name the name of the healer.
	 */
	public Healer(String name) {
		super(name, 'h', 100);
		this.typeOfZombieActor = TypeOfZombieActor.HEALER;
	}
	
	@Override
	/**
	 * If the healer surrounding have actor of {@code Human} or {@code Farmer} or {@code Player}
	 * then it will return Heal Action.
	 * 
	 * Else it will wander around.
	 * 
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting
	 *                   things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}
		
		target(this, map.locationOf(this), HEALER_MAX_RANGE);
		
		if (target.size() != 0) {
			return new HealAction(target);
		}
		
		for(Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
	
	/**
	 * To check whether the actor is not type of {@code Zombie} or {@code MamboMarie}
	 */
	@Override
	protected boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				(here.getActor().getTypeOfZombieActor() != TypeOfZombieActor.ZOMBIE || 
				here.getActor().getTypeOfZombieActor() != TypeOfZombieActor.MAMBOMARIE));
	}
}
