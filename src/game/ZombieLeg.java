package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A Zombie's Leg.
 *
 * This class models a Zombie's Leg as a type of Limb that can be used as a Weapon
 * when detached from the host Zombie.
 */
public class ZombieLeg extends Limb implements Weapon {

    public ZombieLeg() {
        super(TypeOfLimb.ZOMBIE_LEG, 'l', true);
    }

    private ZombieLeg(ZombieLeg newLeg) {
        super(newLeg.typeOfLimb, newLeg.displayChar, true);
    }

    @Override
    public Limb makeCopy() {
        return new ZombieLeg(this);
    }

    @Override
    public DropItemAction getDropAction() {
        return new DropLimbAction(this);
    }

    @Override
    public String verb() {
        return "whacks";
    }

    @Override
    public int damage() {
        return 8;
    }
}
