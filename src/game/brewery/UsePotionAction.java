package game.brewery;

import edu.monash.fit2099.engine.*;
import game.Corpse;

/**
 * A special action to use potions.
 *
 * This class is allows actors to use the potion they have.
 */
public class UsePotionAction extends Action {

    /**
     * The potions used.
     */
    private Potion potion;

    /**
     * Constructor.
     *
     * @param newPotion the potion to be used.
     */
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

    /**
     * This method is used to check whether an actor is still conscious after using the
     * potion.
     *
     * @param actor the actor that used the potion.
     * @param map the map the actor is in.
     * @return if actor is not conscious (i.e. got killed), return a description, otherwise an empty
     *          description.
     */
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
