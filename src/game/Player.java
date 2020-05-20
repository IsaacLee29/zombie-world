package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private Behaviour[] behaviours = {
			new HarvestBehaviour(),
	};

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		typeOfZombieActor = TypeOfZombieActor.PLAYER;
	}

	@Override
	/**
	 * Select and return an action to perform on the current turn. 
	 * In this method, it will check whether the player inventory consumed zombie's limbs so it can 
	 * add crafting action if it contains.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//		int itemCounter = 0;
//		boolean retVal = false;
//		
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		// when the player hitPoints is lesser than he/she maxHitPoints then search whether the player inventory
		// contains food
//		if(this.hitPoints < this.maxHitPoints) {
//			for(Item item: this.getInventory()) {
//				if (item.getDisplayChar() == '=' ) {
//					retVal = true;
//					break;
//				}
//				itemCounter += 1;
//			}
//		}
//		// if there's a food in the inventory the player can choose to consume it
//		if (retVal) {
//			Food food = (Food) this.getInventory().get(itemCounter);
//			actions.add(new ConsumeAction(food, this.hitPoints, this.maxHitPoints));
//		}
		
		
		for(Item item: this.getInventory()) {
			if (item.getDisplayChar() == 'a' || item.getDisplayChar() == 'l') {
				actions.add(new CraftingWeaponAction(item));
			}
		}

		for (Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				actions.add(action);
			}
		}
		return menu.showMenu(this, actions, display);
	}
}
