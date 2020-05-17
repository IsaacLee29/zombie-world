package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.GroundInterface;

public class Crop extends Ground implements GroundInterface{
	/**
	 * the age of the crop
	 */
	private int age = 0;
	
	
	/**
	 * Default constructor to create a default crop
	 */
	public Crop() {
		super ('C');
	}
	
	/**
	 * Ground can also experience the joy of time.
	 * Which increment the age of the crop by 1
	 * @param location The location of the Ground 
	 */
	public void tick(Location location) {
		super.tick(location);
		
		age ++ ;
		// if the age of the crop is 20, the crop will be ripen and ready to harvest
		if (age >= 20) {
			displayChar = 'R';
		}
	}
	
	/**
	 * Fertilising the crop by increasing the age of the crop by 10
	 */
	public void fertilise() {
		age += 10;
	}
	
	/**
	 * getter for the age of the crop
	 * @return age of the crop
	 */
	public int getAge() {
		return age;
	}
	
	

}
