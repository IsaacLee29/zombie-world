package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A Zombie's arm.
 *
 * This class models a Zombie's arm, as a limb, that can be used as a Weapon
 * when detached from the host Zombie. Provides methods to drop the ZombieArm and
 * get its damage and description when wielded as a simple club.
 *
 * @author Isaac Lee Kian Min
 */
public class ZombieArm extends Limb implements Weapon {

    /**
     * Constructor of a ZombieArm object.
     */
    public ZombieArm() {
        super(TypeOfLimb.ZOMBIE_ARM, 'a', true);
    }

    /**
     * A copy constructor of a ZombieArm.
     *
     * @param newArm the {@code ZombieArm} to be copied.
     */
    private ZombieArm(ZombieArm newArm) {
        super(newArm.typeOfLimb, newArm.displayChar, true);
    }

    @Override
    public Limb makeCopy() {
        return new ZombieArm(this);
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
        return "hits";
    }

    @Override
    public int damage() {
        return 5;
    }
}
