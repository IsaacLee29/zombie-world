package game.brewery;

import edu.monash.fit2099.engine.*;

public class Brewery extends Ground {

    PotionFactory potionFactory;

    public Brewery(PotionFactory newPotionFactory) {
        super('B');
        potionFactory = newPotionFactory;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        actions.add(new GetPotionAction(potionFactory.getPotion()));
        return actions;
    }
}
