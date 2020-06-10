package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class ShotgunAttackAction extends AttackAction{
	private Weapon shotgun;
	private ArrayList<Location> locations;
	private final static double PROB_HITTING = 0.75;
	
	public ShotgunAttackAction(ArrayList<Location> locations, Weapon shotgun) {
		this.locations = locations;
		this.shotgun = shotgun;
	}
	
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
