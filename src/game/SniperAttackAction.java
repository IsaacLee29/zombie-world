package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

public class SniperAttackAction extends AttackAction{

	/**
	 * A counter that keep track of how many round the player aim the target
	 */
	private int aimCounter;

	/**
	 * A constructor of SniperAttackAction object.
	 * @param target the actor to be attack
	 * @param aimCounter the amount of rounds the actor takes to aim
	 */
	public SniperAttackAction(Actor target, int aimCounter) {
		super(target);
		this.aimCounter = aimCounter;
		
	}
	
	public String execute(Actor actor, GameMap map) {
		
		Weapon weapon = actor.getWeapon();
		
		int damage = weapon.damage();
		
		if (aimCounter == 2) {
			damage = damage * 2;
		}
		
		if(aimCounter >= 3) {
			damage = 100;
		}
		
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. ";
		
		result = this.checkAfterAttack(damage, map, result);
		
		return result;
	}

}
