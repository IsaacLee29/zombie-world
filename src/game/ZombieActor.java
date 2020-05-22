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
	 * Represents the type of ZombieActor in the zombie game.
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
	 * Get the weapon this Actor is using.
	 * 
	 * If the current Actor is carrying weapons, returns the first one in the
	 * inventory. Otherwise, returns the Actor's natural fighting equipment e.g.
	 * fists.
	 * <p>
	 * In the zombie game, the zombie actor will determine its chances of missing
	 * while fighting.
	 *
	 * @return null if and only if it misses the target, otherwise its {@code Weapon}.
	 */
	@Override
	public Weapon getWeapon() {
		if (rand.nextBoolean()) {  // If Actor misses attack
			return null;
		}
		return super.getWeapon();
	}
}
