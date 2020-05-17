package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {

	protected TypeOfZombieActor typeOfZombieActor;
	
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
	public String loseLimbs(GameMap map) { return null; }

	@Override
	public TypeOfZombieActor getTypeOfZombieActor() {
		return this.typeOfZombieActor;
	}
}
