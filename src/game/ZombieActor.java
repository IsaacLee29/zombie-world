package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

import java.util.Random;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {

	/**
	 * An enum that represents the type of ZombieActor in the ZombieWorld.
	 */
	protected TypeOfZombieActor typeOfZombieActor;

	/**
	 * Random number generator.
	 */
	protected Random rand = new Random();
	
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team, TypeOfZombieActor zombieActor) {
		super(name, displayChar, hitPoints);
		typeOfZombieActor = zombieActor;
		
		addCapability(team);
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}

	@Override
	public TypeOfZombieActor getTypeOfZombieActor() {
		return this.typeOfZombieActor;
	}

	/**
	 * Overrides the getWeapon() method defined in Actor to include the chances of an Actor
	 * in the ZombieWorld missing its target while fighting.
	 *
	 * @return null - if {@code ZombieActor} misses target.
	 * @return Weapon - if {@code ZombieActor} didn't miss target.
	 */
	@Override
	public Weapon getWeapon() {
		if (rand.nextBoolean()) {  // If Actor misses attack
			return null;
		}
		return super.getWeapon();
	}

	@Override
	public String knockOffLimb(GameMap map) {
		return null;
	}
}
