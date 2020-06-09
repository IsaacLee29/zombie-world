package game;

/**
 *A Sniper's ammunition box. 
 *
 *<p> Uses this class to create Sniper's ammunition box.
 *
 * @author Hee Weng Sheng
 */

public class SniperAmmunitionBox extends AmmunitionBox{
	/**
	 * Constructor of SniperAmmunitionBox object.
	 * @param name name of SniperAmmunitonBox
	 * @param displayChar character to be displayed on the map for SniperAmmunitionBox
	 * @param portable	is SniperAmmunition portable 
	 */
	public SniperAmmunitionBox(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable, 10);
	}
	
}
