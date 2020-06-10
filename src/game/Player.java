package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private Behaviour[] behaviours = { new HarvestBehaviour() };
	/**
	 * To keep track of the player hitPoints last turn and current turn
	 */
	private int lastHitPoints, currentHitPoints;
	/**
	 * The zombies within the sniper range
	 */
	private ArrayList<Actor> zombies = new ArrayList<>();
	/**
	 * Hashset of location that have checked
	 */
	private HashSet<Location> visitedLocations = new HashSet<Location>();
	/**
	 * The range of the sniper
	 */
	private static final int MAX_RANGE = 40;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitPoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		typeOfZombieActor = TypeOfZombieActor.PLAYER;
	}

	@Override
	/**
	 * Select and return an action to perform on the current turn. In this method,
	 * it will check whether the player inventory consumed zombie's limbs so it can
	 * add crafting action if it contains.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting
	 *                   things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		boolean retValSniper = false, retValSniperAmmunition = false, retValShotgun = false, retValShotgunAmmunition=false;
		Item sniperAmmunition = null, shotgunAmmunition = null;
		Weapon sniper = null, shotgun = null;
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// check whether the player inventory consist of zombie's arm or leg
		for (Item item : this.getInventory()) {
			if (item.getDisplayChar() == 'a' || item.getDisplayChar() == 'l') {
				actions.add(new CraftingWeaponAction(item));
			}
			
			if (item.hasCapability(RangeWeaponCapabilities.SNIPER_AIMING)) {
				retValSniper = true;
				sniper = item.asWeapon();
			}
			if (item instanceof SniperAmmunitionBox && item.getAmount()>0) {
				retValSniperAmmunition = true;
				sniperAmmunition = item;
			}
			
			if (item.hasCapability(RangeWeaponCapabilities.SHOTGUN_AIMING)) {
				retValShotgun = true;
				shotgun = item.asWeapon();
			}
			if(item instanceof ShotgunAmmunitionBox && item.getAmount()>0) {
				retValShotgunAmmunition = true;
				shotgunAmmunition = item;
			}
		}

		if (retValSniper && retValSniperAmmunition) {
			// updating the player hitPoints 
			currentHitPoints = this.hitPoints;
			// check current map contains zombie within the range 
			target(this, map.locationOf(this));
			// If there's zombies 
			if (zombies.size() > 0) {
				// Check player lastAction and the hitPoints with the last turns
				if (lastAction instanceof SniperAimAction && currentHitPoints == lastHitPoints) {
					actions.add(lastAction);
				}
				else {
					actions.add(new SniperAimAction(sniperAmmunition, sniper, zombies));
				}
				lastHitPoints = currentHitPoints;
			}
		}
		
		if(retValShotgun && retValShotgunAmmunition) {
			actions.add(new ShotgunAimAction(shotgunAmmunition, shotgun));
		}
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				actions.add(action);
			}
		}
		
		actions.add(new QuitAction());
		return menu.showMenu(this, actions, display);
	}
	
	/**
	 * Search whether there is any zombie within the range of the sniper
	 * @param actor the player
	 * @param here location of the player
	 */
	public void target(Actor actor, Location here) {
		visitedLocations.clear();
		zombies.clear();
		ArrayList<Location> now = new ArrayList<Location>();
		
		now.add(here);
		
		ArrayList<ArrayList<Location>> layer = new ArrayList<ArrayList<Location>>();
		layer.add(now);

		for (int i = 0; i < MAX_RANGE; i++) {
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
	private ArrayList<ArrayList<Location>> getNextLayer(Actor actor, ArrayList<ArrayList<Location>> layer) {
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
	private void search(ArrayList<ArrayList<Location>> layer) {
		for (ArrayList<Location> path : layer) {
			Location location = path.get(path.size() - 1);
			// Getting the target
			if (containsTarget(location)) {
				if(!zombies.contains(location.getActor())) {
					zombies.add(location.getActor());
				}
			}
		}
	}
	
	/**
	 * Check whether the current location contains actor of type zombie or MamboMarie
	 * @param here the location to be check 
	 * @return
	 */
	private boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				here.getActor().getTypeOfZombieActor() == TypeOfZombieActor.ZOMBIE || 
				here.getActor().getTypeOfZombieActor()==TypeOfZombieActor.MAMBOMARIE);
	}
	
	/**
	 * Get the weapon this Actor is using.
	 *
	 * If the current Actor is carrying weapons, returns the first one in the
	 * inventory. Otherwise, returns the Actor's natural fighting equipment e.g.
	 * fists.
	 *
	 * <p>In the zombie game, the player will determine its chances of missing
	 * while fighting.
	 *
	 * @return null if and only if it misses the target, otherwise its {@code Weapon}.
	 */
	@Override
	public Weapon getWeapon() {
		Weapon weapon = super.getWeapon();
		if (rand.nextBoolean()) {  // If player misses
			weapon = null;
		}
		return weapon;
	}
	
	
}
