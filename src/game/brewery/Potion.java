package game.brewery;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Potion.
 *
 * This class is used as an abstraction to create new potions.
 */
public abstract class Potion extends Item {

    /**
     * Constructor.
     *
     * @param potionName the name of the potion.
     * @param displayChar the display character of the potion.
     */
    public Potion(String potionName, char displayChar) {
        // In the game, potions are not portable
        super(potionName, displayChar, false);
    }

    /**
     * This class creates adds a {@code UsePotionAction} to it's allowable actions.
     *
     * <p>This allows actor's to use this potion's abilities.
     *
     * @return an unmodifiable list of actions.
     */
    @Override
    public List<Action> getAllowableActions() {
        // Potions are not portable
        List<Action> actions = new ArrayList<>(super.getAllowableActions());
        actions.add(new UsePotionAction(this));
        return Collections.unmodifiableList(actions);
    }

    /**
     * This method implements the effects of the potion towards the actor that has it.
     *
     * @param actor an actor that has this potion.
     * @return a description of the effects of this potion on the actor.
     */
    public abstract String usePotion(Actor actor);
}
