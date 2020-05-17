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

		if (weapon == null ||
				(actor.getTypeOfZombieActor() != TypeOfZombieActor.ZOMBIE && rand.nextBoolean())) {
			return missesTarget(actor);
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. ";

		target.hurt(damage);
		String lostLimb = target.loseLimbs(map);
		if (lostLimb != null) {
			result += System.lineSeparator() + lostLimb;
		}


		if (!target.isConscious()) {
			// THINK AGAIN ABOUT IF ELSE STATEMENTS
			Item corpse = new Corpse(target);
//			if (target.getTypeOfZombieActor() == TypeOfZombieActor.HUMAN) {
//				corpse = new Corpse(target);
//			} else {
//				corpse = new PortableItem("dead " + target, '%');
//			}

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
}
