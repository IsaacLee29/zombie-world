package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/**
 * Class representing an ordinary Farmer.
 * Where this farmer have these behaviours which allow them to
 * farm, fertilize crop, harvest crop and wander around the map.
 * @author wengsheng
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
			new FarmingBehaviour(),
			new FertiliseBehaviour(),
			new HarvestBehaviour(),
			new WanderBehaviour()
			};
		
	/**
	 * The default constructor to create default farmer
	 * @param name the farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
	}
	

	@Override
	/**
	 * Select and return an action to perform on the current turn. 
	 * It will randomly choose an action 
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		boolean retVal = true;
		while (retVal) {
			Random r = new Random();
			int randomNumber = r.nextInt(behaviours.length);
			Action action = behaviours[randomNumber].getAction(this, map);
			
			if (action != null) {
				return action;
			}
		}
		
		return new DoNothingAction();
	}
	

}
