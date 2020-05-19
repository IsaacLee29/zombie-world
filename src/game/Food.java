package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
/**
 * Class representing items that can be consumed and gained health when consumed.
 * @author wengsheng
 *
 */
public class Food extends Item{
	
	/**
	 * The amount of health gained when consumed the food.
	 */
	private int healthGained ;
	
	/**
	 * Default constructor to create default Food.
	 * @param name the food name
	 * @param displayChar the character to display on the map
	 * @param portable whether the food is portable
	 * @param healthGained the health gained when consume the food
	 */
	public Food(String name, char displayChar, boolean portable, int healthGained) {
		super(name, displayChar, portable);
		this.healthGained = healthGained;
	}
	
	/**
	 * A getter to get the health gained when consumed the food
	 * @return health gained when consumed the food.
	 */
	public int getHealth() {
		return healthGained;
	}
	
	public List<Action> getAllowableActions() {
		List <Action> action = new ArrayList<>(super.getAllowableActions());
		action.add(new ConsumeAction(this));
		return action;
	}

	
}
