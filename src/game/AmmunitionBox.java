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
 * @author wengsheng
 *
 */
public abstract class AmmunitionBox extends Item{
	
	protected int ammunitionAmount;

	public AmmunitionBox(String name, char displayChar, boolean portable, int ammunitionAmount) {
		super(name, displayChar, portable);
		this.ammunitionAmount = ammunitionAmount;
	}
	
	public int getAmount() {
		return this.ammunitionAmount;
	}
	
	public void changeAmmount(int amount) {
		this.ammunitionAmount += amount;
	}
	
	public String toString() {
		return name + " amount: " + getAmount();
	}

}
