package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Healer extends Human{

	
	private final static int HEALER_MAX_RANGE = 5;
	private Behaviour[]behaviours = {
			new WanderBehaviour()
	};
	
	public Healer(String name) {
		super(name, 'h', 100);
		this.typeOfZombieActor = TypeOfZombieActor.HEALER;
	}
	
	@Override
	/**
	 * Select an action based on the {@code Behaviour} the {@code Farmer} has. It
	 * get the {@code Farmer} {@code Behaviour} {@code Action} with
	 * Behaviour.getAction()
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
	
	@Override
	protected boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				(here.getActor().getTypeOfZombieActor() != TypeOfZombieActor.ZOMBIE || 
				here.getActor().getTypeOfZombieActor() != TypeOfZombieActor.MAMBOMARIE));
	}
}
