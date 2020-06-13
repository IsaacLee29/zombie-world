package game.brewery;

import edu.monash.fit2099.engine.*;

/**
 * A special action to get a potion.
 *
 * <p>This class is used to allow actors get potions from the {@code Brewery}.
 * 
 * @author Isaac Lee Kian Min.
 */
public class GetPotionAction extends Action {

    /**
     * A potion to get.
     */
    private Item potion;

    /**
     * Constructor.
     *
     * @param newPotion a potion.
     */
    public GetPotionAction(Item newPotion) {
        potion = newPotion;
    }

    /**
     * This method executes the aciton of adding a potion into an actor's inventory.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of getting a potion.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(potion);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets a " + potion;
    }
}
