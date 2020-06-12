package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

/**
 * Shotgun Aim Action.
 * 
 * This class is used to prompt user for a direction to shoot.
 * 
 * <p> Shotgun is able to shoot in different direction. Eg, North, North East, East ...
 * 
 * <p> Then it will fire out the bullet in that direction where its range is 3 squares.
 * 
 * <p> An example how shotgun works. If user choose to fire north, it can hit anything in the 3 squares 
 * north of the shooter, norteast of the shooter, northwest of the shooter, or anything in between. 
 * 
 *
 * @author Hee Weng Sheng
 *
 */

public class ShotgunAimAction extends Action{
	/**
	 * The ammunition box used in this shotgun
	 */
	private Item shotgunAmmunition;
	/**
	 * A display object to display text in console.
	 */
	private Display display = new Display();
	/**
	 * The shotgun that we are using.
	 */
	private Weapon shotgun;
	
	/**
	 * Constructor of shotgun aim action.
	 * @param shotgunAmmunition the ammunition box we used
	 * @param shotgun the shotgun that we are using 
	 */
	public ShotgunAimAction(Item shotgunAmmunition, Weapon shotgun) {
		this.shotgunAmmunition = shotgunAmmunition;
		this.shotgun = shotgun;
	}
	
	/**
	 * It will prompt user for input to select a direction to fired. 
	 * 
	 * <p> After choosing a direction, then it will compute the area that will be fired with bullets
	 * 
	 * <p> Then the user will fire it.
	 * 
	 * @param actor actor that is shooting
	 * @param map map that the actor is currently on 
	 * @return A description of the action.  
	 *
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Exit direction = submenu(actor, map);
		ArrayList<Location> locations = computeAreaOfEffect(direction, map);
		ShotgunAttackAction attack = new ShotgunAttackAction(locations, shotgun);
		String retVal = attack.execute(actor, map);
		this.shotgunAmmunition.changeAmmount(-1);
		return retVal;
	}

	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " uses shotgun.";
	}
	
	public Exit submenu(Actor actor, GameMap map) {
		ArrayList<Character> freeChars = new ArrayList<>();
		HashMap <Character, Exit> keyToExitMap = new HashMap<>();
		
		for (char i = 'a'; i <= 'h'; i++) {
			freeChars.add(i);
		}
		List<Exit> exit = map.locationOf(actor).getExits();
		for (Exit exits: exit){
			char c;
			exits.getName();
			c = freeChars.get(0);
			freeChars.remove(Character.valueOf(c));
			keyToExitMap.put(c, exits);
			display.println(c + ": Shoot " + exits.getName());
		}
		char key;
		do {
			key = display.readChar();
		}
		while (!keyToExitMap.containsKey(key));
		
		return keyToExitMap.get(key);
	}
	
	private ArrayList<Location> computeAreaOfEffect(Exit direction, GameMap map) {
		ArrayList<Location> location = new ArrayList<>();
		Location startingLocation = direction.getDestination();
		int xAxis = startingLocation.x();
		int yAxis = startingLocation.y();
		
		if(direction.getName() == "North") {
			int yAxisCounter = 0, xAxisCounter = -1;
			while (yAxisCounter >= -2 && xAxisCounter >= -3) {
				for (int i = xAxisCounter ; i <= Math.abs((xAxisCounter)); i++){
					checkAndInsert(xAxis + i, yAxis + yAxis, map, location);
//					location.add(map.at(xAxis + i, yAxis + yAxisCounter));
				}
				xAxisCounter -= 1;
				yAxisCounter -= 1;
			}
		}
		
		else if(direction.getName() == "North-East") {
			//	....
			//	....
			//	....
			//	@XXX
			for (int i = 0; i <= 2; i++) {
				checkAndInsert(xAxis + i, yAxis + 1, map, location);
//				location.add(map.at(xAxis + i, yAxis + 1));
			}
			//	XXXX
			//	XXXX
			//	XXXX
			//	@...
			int yAxisCounter = 0;
			while (yAxisCounter >= -2) {
				for (int i = -1; i <= 2; i++) {
//					location.add(map.at(xAxis + i, yAxis + yAxisCounter));
					checkAndInsert(xAxis + i, yAxis + yAxisCounter, map, location);
				}
				yAxisCounter -= 1;
			}
			
		}
		
		else if (direction.getName() == "East") {
			int yAxisCounter = -1, xAxisCounter = 0;
			while (xAxisCounter <= 2 && yAxisCounter >= -3) {
				for (int i = yAxisCounter; i <= Math.abs(yAxisCounter); i++) {
//					location.add(map.at(xAxis + xAxisCounter, yAxis + i));
					checkAndInsert(xAxis + xAxisCounter, yAxis + i, map, location);
				}
				xAxisCounter += 1;
				yAxisCounter -= 1;
			}
		}
		
		else if (direction.getName() == "South-East") {
			//	@XXX
			//	....
			//	....
			//	....	
			for (int i = 0; i <= 2; i++ ) {
//				location.add(map.at(xAxis + i, yAxis - 1));
				checkAndInsert(xAxis + i, yAxis - 1, map, location);
			}
			//	@...
			//	XXXX
			//	XXXX
			//	XXXX
			int yAxisCounter = 0;
			while(yAxisCounter <= 2) {
				for (int i = -1; i <= 2; i++) {
//					location.add(map.at(xAxis + i, yAxis + yAxisCounter));
					checkAndInsert(xAxis + i, yAxis + yAxisCounter, map, location);
				}
				yAxisCounter += 1;
			}
		}
		
		else if (direction.getName() == "South") {
			int xAxisCounter = -1, yAxisCounter = 0;
			while (xAxisCounter >= -3 && yAxisCounter <= 2) {
				for (int i = xAxisCounter; i <= Math.abs(xAxisCounter); i++) {
//					location.add(map.at(xAxis + i, yAxis + yAxisCounter));
					checkAndInsert(xAxis + i, yAxis + yAxisCounter, map, location);

				}
				xAxisCounter -= 1;
				yAxisCounter += 1;
			}
		}
		
		else if (direction.getName() == "South-West") {
			for (int i = -2; i <= 0; i++) {
//				location.add(map.at(xAxis + i, yAxis - 1));
				checkAndInsert(xAxis + i, yAxis - 1, map, location);

			}
			int yAxisCounter = 0;
			while(yAxisCounter <= 2) {
				for (int i = -2; i <= 1; i++) {
//					location.add(map.at(xAxis + i, yAxis + yAxisCounter));
					checkAndInsert(xAxis + i, yAxis + yAxisCounter, map, location);

				}
				yAxisCounter += 1;
			}
		}
		
		else if (direction.getName() == "West") {
			int xAxisCounter = 0, yAxisCounter = -1;
			while (xAxisCounter >= -2 && yAxisCounter >= -3) {
				for (int i = yAxisCounter; i <= Math.abs(yAxisCounter); i++) {
//					location.add(map.at(xAxis + xAxisCounter, yAxis + i));
					checkAndInsert(xAxis + xAxisCounter, yAxis + i, map, location);
				}
				xAxisCounter -= 1;
				yAxisCounter -= 1;
			}
		}
		
		else if (direction.getName() == "North-West") {
					//	....
					//	....
					//	....
					//	XXX@				
			for (int i = -2; i <= 0; i++) {
//				location.add(map.at(xAxis + i, yAxis + 1));
				checkAndInsert(xAxis + i, yAxis + 1, map, location);

			}
			int yAxisCounter = 0;
			while(yAxisCounter >= -2) {
				for(int i = -2; i <= 1; i++) {
//					location.add(map.at(xAxis + i, yAxis + yAxisCounter));
					checkAndInsert(xAxis + i, yAxis + yAxisCounter, map, location);

				}
				yAxisCounter -= 1;
			}
		}
		
		return location;
	}
	
	/**
	 * Check whether the xAxis and yAxis is a location on the map
	 * 
	 * @param xAxis the xAxis to be check. 
	 * @param yAxis	the yAxis to be check
	 * @param map	map that used to check
	 * @param location	ArrayList of location 
	 * 
	 * @exception xAxis and yAxis must be a point on the map
	 */
	private void checkAndInsert(int xAxis, int yAxis, GameMap map, ArrayList<Location> location) {
		try {
			Location point = map.at(xAxis, yAxis);
			location.add(point);
		}
		catch (Exception e){
		}
	}
	
	

}
