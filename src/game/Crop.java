package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.GroundInterface;

/**
 * A class of Crop
 * 
 * A class that implements Crop which can be farmed by farmer 
 * and harvest by both player and farmer when it is ripe. 
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
	 * Default constructor to create a default crop
	 */
	public Crop() {
		super ('C');
	}
	
	/**
	 * Which increment the age of the crop by 1 every turn.
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
	 * Fertilising the crop which increasing the age of the crop by 10
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
	
//	public Actions allowableActions(Actor actor, Location location, String direction){
//		Actions actions = new Actions();
//		if (age < 20) {
//			actions.add(new FertiliseBehaviour());
//		}
//		if (age >= 20) {
//			actions.add(new HarvestBehaviour());
//		}
//		return actions;
//	}	
//	

}
