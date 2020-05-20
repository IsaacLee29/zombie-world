package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A Zombie's Leg.
 *
 * This class models a Zombie's Leg, as a limb, that can be used as a Weapon
 * when detached from the host Zombie. Provides methods to drop the ZombieLeg and
 * get its damage and description when wielded as a simple club.
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
     * A copy constructor of a ZombieLeg.
     *
     * @param newLeg the {@code ZombieLeg} to be copied.
     */
    private ZombieLeg(ZombieLeg newLeg) {
        super(newLeg.typeOfLimb, newLeg.displayChar, true);
    }

    @Override
    public Limb makeCopy() {
        return new ZombieLeg(this);
    }

    /**
     * Returns a DropLimbAction that drops this limb.
     *
     * @return a DropLimbAction.
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
