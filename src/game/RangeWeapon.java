package game;

import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class RangeWeapon extends WeaponItem implements Weapon{
	
	public RangeWeapon(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
}
