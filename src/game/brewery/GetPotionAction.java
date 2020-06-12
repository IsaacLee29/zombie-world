package game.brewery;

import edu.monash.fit2099.engine.*;

public class GetPotionAction extends Action {

    private Item potion;

    public GetPotionAction(Item newPotion) {
        potion = newPotion;
    }

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
