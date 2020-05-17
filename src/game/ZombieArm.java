package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZombieArm extends Limb implements Weapon {

    public ZombieArm(TypeOfZombieActor newTypeActor) {
        super(TypeOfLimb.ARM, 'a');
        typeActor = newTypeActor;
        portable = true;
    }

    private ZombieArm(ZombieArm newZombieArm) {
        super(newZombieArm.typeOfLimb, newZombieArm.displayChar);
        this.typeActor = newZombieArm.typeActor;
    }

    @Override
    public Limb makeCopy() {
        return new ZombieArm(this);
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
        return "hits";
    }

    @Override
    public int damage() {
        return 5;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
