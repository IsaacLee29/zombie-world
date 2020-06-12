package game.brewery;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Potion extends Item {

    public Potion(String potionName, char displayChar) {
        super(potionName, displayChar, false);
    }

    @Override
    public List<Action> getAllowableActions() {
        // Potions are not portable, so its fine to override this method cause it will never be
        // on the ground.
        List<Action> actions = new ArrayList<>(super.getAllowableActions());
        actions.add(new UsePotionAction(this));
        return Collections.unmodifiableList(actions);
    }

    // Method to use the potion
    public abstract String usePotion(Actor actor);
}
