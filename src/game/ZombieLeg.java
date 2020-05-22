package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A zombie's leg.
 * 
 * <p>This class models a zombie's leg, as a {@code Limb}, that can be used as a {@code Weapon}
 * when detached from the host zombie. 
 * 
 * <p>Provides methods to drop the {@code ZombieLeg} and get its damage and description when 
 * wielded as a simple club.
 * 
 * <p>Use this class to create a zombie's leg.
 *
 * @author Isaac Lee Kian Min
 */
public class ZombieLeg extends Limb implements Weapon {

    /**
     * Constructor of a ZombieLeg object.
     */
    public ZombieLeg() {
        super(TypeOfLimb.ZOMBIE_LEG, 'l', true);
    }

    /**
     * A copy constructor of a {@code ZombieLeg}.
     *
     * @param newArm the {@code ZombieLeg} to be copied.
     */
    private ZombieLeg(ZombieLeg newLeg) {
        super(newLeg.typeOfLimb, newLeg.displayChar, true);
    }

    @Override
    public Limb makeCopy() {
        return new ZombieLeg(this);
    }

    /**
     * Returns a {@code DropLimbAction} that drops this {@code Limb}.
     *
     * @return a {@code DropLimbAction}.
     */
    @Override
    public DropItemAction getDropAction() {
        return new DropLimbAction(this);
    }

    @Override
    public String verb() {
        return "smacks";
    }

    @Override
    public int damage() {
        return 8;
    }
}
