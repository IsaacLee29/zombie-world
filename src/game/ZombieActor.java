package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

import java.util.Objects;
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
	
	/**
	 * Constructor of a ZombieActor object.
	 * 
	 * @param name the name of the Actor
	 * @param displayChar the character that will represent the Actor in the display
	 * @param hitPoints the Actor's starting hit points
	 * @param team the {@code ZombieCapability} of this zombie actor
	 * @param zombieActor the {@code TypeOfZombieActor} this zombie actor is
	 * @throws IllegalArgumentException if {@code hitPoints} is negative
	 * @throws NullPointerException if {@code zombieActor} references to null
	 */
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team, TypeOfZombieActor zombieActor) {
		super(name, displayChar, hitPoints);
		if (hitPoints < 0) {  // check for negative hitPoints
			throw new IllegalArgumentException("Hitpoints cannot be negative.");
		}
		Objects.requireNonNull(zombieActor);  // check for null references
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
}
