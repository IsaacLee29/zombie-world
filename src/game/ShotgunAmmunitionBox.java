package game;

/**
 * A Shotgun's Ammunition Box.
 * 
 * <p> Uses this class to create Shotgun's ammunition box.
 * 
 * @author wengsheng
 *
 */

public class ShotgunAmmunitionBox extends AmmunitionBox{

	/**
	 * Constructor of Shotgun Ammunition Box object.
	 *  
	 * @param name name of the Shotgun's ammunition box
	 * @param displayChar displayed character for the shotgun's ammunition box
	 * @param portable is shotgun's ammunition box portable
	 * @param ammunitionAmount the ammount of ammunition contains in a box
	 * 
	 */
	public ShotgunAmmunitionBox(String name, char displayChar, boolean portable) {
		// Maybe i can raise an exeception to not allow negative amount of ammunition. 
		super(name, displayChar, portable, 10);
	}

}
