package game.brewery;

import edu.monash.fit2099.engine.*;

/**
 * A Potion maker's shop.
 *
 * This class models after a potion master and his shop. The potion master is a wealthy man
 * and is a master brewer of potions. He decides to freely give out potion, but be aware, 
 * not all potions are beneficial...
 */
public class Brewery extends Ground {

    /**
     * A potion factory.
     */
    private PotionFactory potionFactory;

    /**
     * Constructor.
     *
     * @param newPotionFactory a potion factory.
     */
    public Brewery(PotionFactory newPotionFactory) {
        super('B');
        potionFactory = newPotionFactory;
    }

    /**
     * This method creates {@code GetPotionAction} whenever an actor is within close proximity
     * to the shop (i.e. it gives potions to these actors).
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an unmodifiable actions list.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(new GetPotionAction(potionFactory.getPotion()));
        return actions;
    }
}
