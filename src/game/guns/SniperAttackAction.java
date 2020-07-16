package game.guns;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
import game.AttackAction;

/**
 * Sniper Attack Action.
 * 
 * <p> This class is used to damage the target when the user shoot the zombie with a sniper. 
 * 
 * <p> Use this class when the user shoot the target with a sniper. 
 * 
 * @author wengsheng
 *
 */

public class SniperAttackAction extends AttackAction {

	/**
	 * A counter that keep track of how many round the player aim the target
	 */
	private int aimCounter;
	private Weapon sniper;
	/**
	 * A constructor of SniperAttackAction object.
	 * @param target the actor to be attack
	 * @param aimCounter the amount of rounds the actor takes to aim
	 * @param sniper the sniper the user is using
	 */
	public SniperAttackAction(Actor target, int aimCounter, Weapon sniper) {
		super(target);
		this.aimCounter = aimCounter;
		this.sniper = sniper;
	}
	
	/**
	 * Dealt damage to the target.
	 * 
	 * @param actor the actor who is shooting
	 * @param map the map that this action is performing on. 
	 * 
	 * @return string of description about the damage dealt to the zombie. 
	 */
	public String execute(Actor actor, GameMap map) {
		
		int damage = sniper.damage();
		
		if (aimCounter == 2) {
			damage = damage * 2;
		}
		
		if(aimCounter >= 3) {
			damage = 100;
		}
		
		String result = actor + " " + sniper.verb() + " " + target + " for " + damage + " damage. ";
		
		result = this.checkAfterAttack(damage, map, result, target);
		
		return result;
	}

}
