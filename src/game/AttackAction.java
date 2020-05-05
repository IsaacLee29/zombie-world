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
	 * Random number generator
	 */
	protected Random rand = new Random();


	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (weapon.verb().equalsIgnoreCase("bites")) {
			if (Zombie.missBite(rand.nextDouble())) {
				return missesTarget(actor); }
			else {
				actor.heal(5);  // got time only add new class
			}
		} else {
			if (rand.nextDouble() < 0) {//rand.nextBoolean()) {
				return missesTarget(actor);
			}
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. ";

		target.hurt(damage);
		String lostLimb = knockOffLimb(map);
		if (lostLimb != null) {
			result += System.lineSeparator() + target + lostLimb;
		}


		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '%');
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

	/**
	 * While attacking, the Actor missed the target.
	 *
	 * @param actor the Actor who missed the target
	 * @return String description of Actor missing the target
	 */
	private String missesTarget(Actor actor) {
		return actor + " misses " + target + ".";
	}

	private String knockOffLimb(GameMap map) {
		String retVal = null;
		if (rand.nextDouble() <= 1.0) {
			 retVal = target.loseLimbs(map);
		}
		return retVal;
	}
}
