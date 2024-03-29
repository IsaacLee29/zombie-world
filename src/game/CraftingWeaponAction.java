package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.zombies.ZombieClub;
import game.zombies.ZombieMace;

/**
 * <p>
 * A special action to craft weapon.
 * <p>
 * An action to craft a {@code WeaponItem} using {@code Limbs} of the zombie.
 * <p>
 * It can craft into a {@code ZombieClub} using the {@code ZombieArm}.
 * <p>
 * It can craft into a {@code ZombieMace} using the {@code ZombieLeg}.
 * 
 * In this class player can use zombie's limb to craft into a weapon which
 * depends on the zombie's limb they are using as an action.
 * 
 * @author Hee Weng Sheng
 *
 */
public class CraftingWeaponAction extends Action {
	/**
	 * Which Limb the player are using to craft into weapon
	 */
	private Item limb;

	/**
	 * Constructor
	 * 
	 * @param limb the limb of the zombie using to craft into weapon
	 * @throws NullPointerException if the item references to null.
	 */
	public CraftingWeaponAction(Item limb) {
		Objects.requireNonNull(limb);
		this.limb = limb;
	}

	@Override
	/**
	 * Perform the Crafting Weapon Action. If the actor is using a {@code ZombieArm}
	 * to craft, it will craft into a {@code ZombieClub} Weapon. If the actor is
	 * using a {@code ZombieLeg} to craft, it will craft into a {@code ZombieMace}
	 * Weapon. After crafting it will add into the actor inventory and remove the
	 * the item use to craft from the inventory.
	 * 
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public String execute(Actor actor, GameMap map) {

		if (limb.getDisplayChar() == 'a') {
			actor.addItemToInventory(new ZombieClub());
			actor.removeItemFromInventory(limb);
		} else if (limb.getDisplayChar() == 'l') {
			actor.addItemToInventory(new ZombieMace());
			actor.removeItemFromInventory(limb);
		}
		return menuDescription(actor);
	}

	@Override
	/**
	 * Returns a descriptive string when the player crafted the weapon.
	 * 
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String menuDescription(Actor actor) {
		if (limb.getDisplayChar() == 'a') {
			return actor + " used a zomebie arm and crafted into a ZombieClub Weapon";
		} else if (limb.getDisplayChar() == 'l') {
			return actor + " used a zombie leg and crafted into a ZombieMace Weapon";
		}
		return null;
	}
}
