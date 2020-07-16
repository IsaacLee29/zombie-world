package game.guns;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A Sniper that is used as a {@code WeaponItem}
 * 
 * <p> Uses this class to create a Shotgun object.
 * 
 * @author Hee Weng Sheng
 *
 */
public class Sniper extends WeaponItem{
	
	/**
	 * Constructor of Sniper object.
	 */
	public Sniper() {
		super("Sniper", 'S', 45, "Biang");
		this.addCapability(RangeWeaponCapabilities.SNIPER_AIMING);
	}
}
