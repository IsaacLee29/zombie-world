package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * A counter that keep track of how many round the player aim the target
	 */
	private int sniperAimCounter; 
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	public AttackAction(Actor target, int sniperAimCounter) {
		this.target = target;
		this.sniperAimCounter = sniperAimCounter;
	}
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (weapon == null) {  // i.e. an Actor misses
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		
		if (sniperAimCounter == 2) {
			damage = damage * 2;
		}
		else if (sniperAimCounter >= 3) {
			damage = 100;
		}
		
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. ";

		target.hurt(damage);
		String knockedLimb = target.knockOffLimb(map);  // If able to have limbs knocked off
		if (knockedLimb != null) {
			result += System.lineSeparator() + knockedLimb;
		}

		if (!target.isConscious()) {
			Item corpse = new Corpse(target);  // Create a Corpse of the target

			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
