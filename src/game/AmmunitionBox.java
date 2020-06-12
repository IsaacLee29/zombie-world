package game;

import edu.monash.fit2099.engine.Item;
/**
 * An AmmunitionBox for different kinds of ammunition. Eg sniper ammunition and shotgun ammunition.
 * 
 * <p> This class models an AmmunitionBox, as an {@code Item}, that can be used by weapons
 * that requires ammunition in order to operate.
 * 
 * <p> It provides methods to get the amount of ammunition in the box, change the amount of 
 * ammunition left in the box and description to show how manys ammunition left in the 
 * box. 
 * 
 * @author Hee Weng Sheng                 
 *
 */
public abstract class AmmunitionBox extends Item{
	
	/**
	 * amount of ammunition in the box
	 */
	protected int ammunitionAmount;
	
	/**
	 * Construct of AmmunitionBox object
	 * @param name name of ammunition box 
	 * @param displayChar character to be display for the ammunition box
	 * @param portable is ammunition box portable
	 * @param ammunitionAmount the amount of ammunition in the box
	 */
	public AmmunitionBox(String name, char displayChar, boolean portable, int ammunitionAmount) {
		super(name, displayChar, portable);
		this.ammunitionAmount = ammunitionAmount;
	}
	
	/**
	 * Getting the amount of ammunition 
	 */
	public int getAmount() {
		return this.ammunitionAmount;
	}
	
	/**
	 * change teh amount of ammunition
	 * @param amount the amount to be change
	 */
	public void changeAmmount(int amount) {
		this.ammunitionAmount += amount;
	}
	
	/**
	 * Description of the amount of ammunition left
	 */
	public String toString() {
		return name + " amount: " + getAmount();
	}

}
