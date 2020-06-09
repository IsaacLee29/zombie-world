package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A shotgun that is used as a {@code WeaponItem}
 * 
 * <p> Uses this class to create a Shotgun object.
 * 
 * @author Hee Weng Sheng
 *
 */
public class Shotgun extends WeaponItem{

	/**
	 * Constructor of shotgun object.
	 */
	public Shotgun() {
		super("Shotgun", 's', 20, "Boom");
		this.addCapability(RangeWeaponCapabilities.SHOTGUN_AIMING);
	}

}
