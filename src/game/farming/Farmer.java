package game.farming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.*;

/**
 * Class representing an ordinary Farmer.
 * <p>
 * A class to implement what the {@code Farmer} can do based on the
 * {@code Behaviour} the {@code Farmer} has. Where the {@code Farmer} have these
 * behaviours which allow them to farm, fertilize crop, harvest crop and wander
 * around the map.
 * <p>
 * Provides method to do which {@code Action} when is the {@code Farmer} turn.
 * 
 * @author Hee Weng Sheng
 *
 */

public class Farmer extends Human {
	/**
	 * These is an array of {@code Behaviour} for the farmers which are
	 * {@code FarmingBehaviour} which allows them to sowed a crop
	 * {@code FertiliseBehaviour} which allows the {@code Farmer} to fertilise the
	 * crop {@code HarvestBehaviour} which allows the {@code Farmer} to harvest the
	 * ripe crop {@code WanderBehaviour} which allows the {@code Farmer} to move
	 * around the map
	 */
	private Behaviour[] behaviours = { new HarvestBehaviour(), new FertiliseBehaviour(), new FarmingBehaviour(),
			new WanderBehaviour() };

	/**
	 * The default constructor to create default farmer
	 * 
	 * @param name the farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
		this.typeOfZombieActor = TypeOfZombieActor.FARMER;
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
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) { // if the action is not null then it will return that action to be done by the
									// farmer
				return action;
			}
		}
		return new DoNothingAction();
	}

}
