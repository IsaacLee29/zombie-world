package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A Crop class.
 * 
 * <p>
 * This class models a Crop, as a {@code Ground}, that can grow and harvest it for food. 
 * <p>
 * In this class, it provides method to fertilise the {@code Crop} which decrease the time left to ripen by 10 turns
 * and a method to increment the age of the {@code Crop} by 1.
 * <p> 
 *  This class is used to create an Crop Object. 
 *  
 * @author Hee Weng Sheng
 *
 */
public class Crop extends Ground{
	/**
	 * the age of the crop
	 */
	private int age = 0;
	/**
	 * Default constructor to create a default {@code Crop}
	 */
	public Crop() {
		super ('C');
		this.addCapability(CropCapability.UNRIPE);
	}
	
	/**
	 * Which increment the age of the {@code Crop} by 1 every turn.
	 * @param location The location of the Ground 
	 */
	public void tick(Location location) {
		super.tick(location);
		
		age ++ ;
		// if the age of the crop is 20, the crop will be ripen and ready to harvest
		if (age >= 20) {
			displayChar = 'R';
			this.removeCapability(CropCapability.UNRIPE);
			this.addCapability(CropCapability.RIPE);
		}
	}
	
	/**
	 * Fertilizing the {@code Crop} which reduce the time left to be ripen by 10
	 */
	public void fertilise() {
		age += 10;		// increment the age of the {@code Crop} by 10
	}
	
	/**
	 * getter for the age of the {@code Crop}
	 * @return age of the crop
	 */
	public int getAge() {
		return age;
	}

}
