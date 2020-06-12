package game.brewery;

import edu.monash.fit2099.engine.*;
import game.Corpse;

public class UsePotionAction extends Action {

    private Potion potion;

    public UsePotionAction(Potion newPotion) {
        potion = newPotion;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = potion.usePotion(actor);
        result += actorDead(actor, map);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the " + potion.toString();
    }

    private String actorDead(Actor actor, GameMap map) {
        String result = "";
        if (!actor.isConscious()) {
            Item corpse = new Corpse(actor);  // Create a Corpse of the target

            map.locationOf(actor).addItem(corpse);

            Actions dropActions = new Actions();
            for (Item item : actor.getInventory())
                dropActions.add(item.getDropAction());
            for (Action drop : dropActions)
                drop.execute(actor, map);
            map.removeActor(actor);

            result += System.lineSeparator() + actor + " is killed.";
        } else {
            actor.removeItemFromInventory(potion);
        }
        return result;
    }
}
