package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Crop extends Ground{
	
	private int age = 0;
	
	//Constructor for Crop
	public Crop() {
		super ('C');
	}
	
	// This method is used to increase the life of a crop every turn
	public void tick(Location location) {
		super.tick(location);
		
		age ++ ;
		// if the age of the crop is 20, the crop will be ripen and ready to harvest
		if (age == 20) {
			displayChar = 'R';
		}
	}
	

}
