package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leg extends Limb implements Weapon {

    public Leg(TypeOfZombieActor newTypeActor) {
        super(TypeOfLimb.LEG, 'l');
        typeActor = newTypeActor;
        portable = true;
    }

    private Leg(Leg newZombieLeg) {
        super(newZombieLeg.typeOfLimb, newZombieLeg.displayChar);
        this.typeActor = newZombieLeg.typeActor;
    }

    @Override
    public Limb makeCopy() {
        return new Leg(this);
    }

    @Override
    public DropItemAction getDropAction() {
        return new DropLimbAction(this);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> temp = new ArrayList<>(super.getAllowableActions());
        if (typeActor == TypeOfZombieActor.ZOMBIE) {
            // add crafting action
        }
        return Collections.unmodifiableList(temp);
    }

    @Override
    public String verb() {
        return "whacks";
    }

    @Override
    public int damage() {
        return 10;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
