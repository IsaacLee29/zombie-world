package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE, TypeOfZombieActor.HUMAN);
		addCapability(RiseFromDead.ZOMBIE);
	}

	/**
	 * The protected constructor can be used to create subtypes of Human, such as
	 * the Player
	 * 
	 * @param name        the human's display name
	 * @param displayChar character that will represent the Human in the map
	 * @param hitPoints   amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE, TypeOfZombieActor.HUMAN);
		addCapability(RiseFromDead.ZOMBIE);
	}

	@Override
	/**
	 * Select and return an action to perform on the current turn. In this playTurn
	 * it will check the hitPoints of the human to make sure it wont consumed the
	 * food if his/her hitPoints is max. If there's two action available for the
	 * Human it will randomly choose an action.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting
	 *                   things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// remove the consumeAction if the human hitPoints == maxHitPoints
		List<Action> actions1 = new ArrayList<>(actions.getUnmodifiableActionList());
		Random rand = new Random();
		for (Action action : actions1) {
			if (action instanceof ConsumeAction) {
				if (this.hitPoints == this.maxHitPoints) {
					actions1.remove(action);
					break;
				}
			}
		}
		actions1.add(behaviour.getAction(this, map));
		return actions1.get(rand.nextInt(actions.size()));
	}
}
