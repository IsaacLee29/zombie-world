package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
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
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// check whether the player inventory consist of zombie's arm or leg
		for (Item item : this.getInventory()) {
			if (item.getDisplayChar() == 'a' || item.getDisplayChar() == 'l') {
				actions.add(new CraftingWeaponAction(item));
			}
		
//			if (item.hasCapability(RangeWeaponCapabilities.SNIPER_AIMING)) {
//				 // getting back the same aimAction to aim back the same person
//				// setting the current hitPoints of the player
//				currentHitPoints = this.hitPoints; 
//				// check if the player have 
//				for (int i = 0; i < this.getInventory().size(); i++) {
//					Item ammunition = this.getInventory().get(i);
//					if (ammunition instanceof SniperAmmunitionBox && ammunition.getAmount()>0){
//						if(lastAction instanceof AimAction && currentHitPoints == lastHitPoints) {
//							actions.add(lastAction);
//						}
//						else if (retVal){
//							actions.add(new AimAction(ammunition));
//						}
//						lastHitPoints = currentHitPoints;
//					}
//				}
//			}
			if (item.hasCapability(RangeWeaponCapabilities.SNIPER_AIMING)) {
				retValSniper = true;
			}
			if (item instanceof SniperAmmunitionBox && item.getAmount()>0) {
				retValSniperAmmunition = true;
				sniperAmmunition = item;
			}
			
			if (item.hasCapability(RangeWeaponCapabilities.SHOTGUN_AIMING)) {
				retValShotgun = true;
			}
			if(item instanceof ShotgunAmmunitionBox && item.getAmount()>0) {
				retValShotgunAmmunition = true;
				shotgunAmmunition = item;
			}
		}

		if (retValSniper && retValSniperAmmunition) {
			// updating the player hitPoints 
			currentHitPoints = this.hitPoints;
			// Check player lastAction and the hitPoints with the last turns
			if (lastAction instanceof SniperAimAction && currentHitPoints == lastHitPoints) {
				actions.add(lastAction);
			}
			else {
				actions.add(new SniperAimAction(sniperAmmunition));
			}
			lastHitPoints = currentHitPoints;
		}
		
		if(retValShotgun && retValShotgunAmmunition) {
			actions.add(new ShotgunAimAction(shotgunAmmunition));
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
