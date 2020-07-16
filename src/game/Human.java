package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.farming.ConsumeAction;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	
	/**
	 * The zombies within the sniper range
	 */
	protected ArrayList<Actor> target = new ArrayList<>();
	/**
	 * Hashset of location that have checked
	 */
	protected HashSet<Location> visitedLocations = new HashSet<Location>();

	
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
	
	protected void target(Actor actor, Location here, int range) {
		visitedLocations.clear();
		target.clear();
		ArrayList<Location> now = new ArrayList<Location>();
		
		now.add(here);
		
		ArrayList<ArrayList<Location>> layer = new ArrayList<ArrayList<Location>>();
		layer.add(now);

		for (int i = 0; i < range; i++) {
			layer = getNextLayer(actor, layer);
			search(layer);
//			Location there = search(layer);
//			if (there != null)
//				return there.getMoveAction(actor, "towards a " + targetName, null);
		}

	}
	/**
	 * Search for all possible location with in the range of the sniper
	 * @param actor the player
	 * @param layer	ArrayList of ArrayList of location 
	 * @return
	 */
	protected ArrayList<ArrayList<Location>> getNextLayer(Actor actor, ArrayList<ArrayList<Location>> layer) {
		ArrayList<ArrayList<Location>> nextLayer = new ArrayList<ArrayList<Location>>();

		for (ArrayList<Location> path : layer) {
			List<Exit> exits = new ArrayList<Exit>(path.get(path.size() - 1).getExits());
			Collections.shuffle(exits);
			for (Exit exit : path.get(path.size() - 1).getExits()) {
				Location destination = exit.getDestination();
				if (!destination.getGround().canActorEnter(actor) || visitedLocations.contains(destination))
					continue;
				visitedLocations.add(destination);
				ArrayList<Location> newPath = new ArrayList<Location>(path);
				newPath.add(destination);
				nextLayer.add(newPath);
			}
		}
		return nextLayer;
	}
	
	/**
	 * Get the possible location in the range 
	 * @param layer Location to be search
	 */
	protected void search(ArrayList<ArrayList<Location>> layer) {
		for (ArrayList<Location> path : layer) {
			Location location = path.get(path.size() - 1);
			// Getting the target
			if (containsTarget(location)) {
				if(!target.contains(location.getActor())) {
					target.add(location.getActor());
				}
			}
		}
	}
	
	/**
	 * Check whether the current location contains actor of type zombie or MamboMarie
	 * @param here the location to be check 
	 * @return
	 */
	protected boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				(here.getActor().getTypeOfZombieActor() == TypeOfZombieActor.ZOMBIE || 
				here.getActor().getTypeOfZombieActor() == TypeOfZombieActor.MAMBOMARIE));
	}
}
