package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

/**
 * A class of Food.
 * <p>
 * This class is implemented to have a {@code Food} Object which can be consumed
 * by {@code Actor} and regain some health.
 * 
 * @author Hee Weng Sheng
 *
 */
public class Food extends Item {

	/**
	 * The amount of health gained when consumed the {@code Food}.
	 */
	private int healthGained;

	/**
	 * Default constructor to create default {@code Food}.
	 * 
	 * @param name         the food name
	 * @param displayChar  the character to display on the map
	 * @param portable     whether the food is portable
	 * @param healthGained the health gained when consume the food
	 * @throws IllegalArgumentException of the healthGained of the food is less than
	 *                                  0
	 */
	public Food(String name, char displayChar, boolean portable, int healthGained) {
		super(name, displayChar, portable);
		if (healthGained < 0) {
			throw new IllegalArgumentException("The healthGained of the food must be > 0");
		} else {
			this.healthGained = healthGained;
		}
	}

	/**
	 * A getter to get the health gained when consumed the {@code Food}
	 * 
	 * @return health gained when consumed the food.
	 */
	public int getHealth() {
		return healthGained;
	}

	public List<Action> getAllowableActions() {
		List<Action> action = new ArrayList<>(super.getAllowableActions());
		action.add(new ConsumeAction(this));
		return action;
	}

}
