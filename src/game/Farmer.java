package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/**
 * Class representing an ordinary Farmer.
 * 
 * A class to implement what the farmer can do based on the behaviours the farmer has.
 * Where this farmer have these behaviours which allow them to
 * farm, fertilize crop, harvest crop and wander around the map.
 * @author Hee Weng Sheng
 *
 */

public class Farmer extends Human {
	/**
	 * These is an array of Behaviours for the farmers which are
	 * FarmingBehaviour which allows them to sowed a crop
	 * FertiliseBehaviour which allows the farmer to fertilise the crop 
	 * HarvestBehaviour which allows the farmer to harvest the ripe crop
	 * WanderBehaviour which allows the farmer to move around the map
	 */
	private Behaviour [] behaviours = {
			new HarvestBehaviour(),
			new FertiliseBehaviour(),
			new FarmingBehaviour(),
			new WanderBehaviour()
			};
		
	/**
	 * The default constructor to create default farmer
	 * @param name the farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
		this.typeOfZombieActor = TypeOfZombieActor.FARMER;
	}
	

	@Override
	/**
	 * Select an action randomly and execute it.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//		boolean retVal = true;
//		while (retVal) {
//			Random r = new Random();
//			int randomNumber = r.nextInt(behaviours.length);
//			Action action = behaviours[randomNumber].getAction(this, map);
//			
//			if (action != null) {
//				return action;
//			}
//		}

		for (Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		
//		for (Behaviour behaviour: behaviours) {
//			Action action = behaviour.getAction(this, map);
//			if (action != null) {
//				return action;
//			}
//		}
//		if (actions.size() > 0) {
//			return actions.get(rand.nextInt(actions.size()));
//		}
		
		return new DoNothingAction();
	}
	

}
