package game.guns;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;
import game.AttackAction;

/**
 * Shotgun Attack Action.
 * 
 * <p> An action that perform the shooting action of a shotgun. It will fired at the direction that the user choose. 
 * Any {@code Actor} within the range will be damage. 
 * 
 * <p> Use this action when the user want to shoot with shotgun. 
 * @author Hee Weng Sheng
 *
 */

public class ShotgunAttackAction extends AttackAction {
	/**
	 * The shotgun that the user is using 
	 */
	private Weapon shotgun;
	/**
	 * The location of bullets that are going to fired be at 
	 */
	private ArrayList<Location> locations;
	/**
	 * A constant for the probability of hitting an {@code Actor}
	 */
	private final static double PROB_HITTING = 0.75;
	
	/**
	 * Constructor of Shotgun Attack Action
	 * 
	 * @param locations the location of bullets that are going to be fired at
	 * @param shotgun the shotgun that the user is using
	 */
	public ShotgunAttackAction(ArrayList<Location> locations, Weapon shotgun) {
		this.locations = locations;
		this.shotgun = shotgun;
	}
	
	/**
	 * Perform the shooting action which will damage the {@code Actor} within the shooting range. 
	 * It has a probaility of 75% that the {@code Actor} will get hit. 
	 * 
	 * @param actor the actor who is firing
	 * @param map the map that this action is perform on
	 * @return a string that display who is damage and who dodges it. 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		int damage = shotgun.damage();
		for (Location location: locations) {
			if (location.containsAnActor()) {
				if (Math.random() <= PROB_HITTING) {
					result +=  System.lineSeparator() + actor + " " + shotgun.verb() + " "
							+ location.getActor() + " for " + damage;
					result = this.checkAfterAttack(damage, map, result, location.getActor());
				}
				else {
					result += actor + " misses " + location.getActor();
				}
			}
		}
		return result;
	}

}
