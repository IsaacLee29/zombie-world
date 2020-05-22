package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A zombie's arm.
 * 
 * <p>This class models a zombie's arm, as a {@code Limb}, that can be used as a {@code Weapon}
 * when detached from the host zombie. 
 * 
 * <p>Provides methods to drop the {@code ZombieArm} and get its damage and description when 
 * wielded as a simple club.
 * 
 * <p>Use this class to create a zombie's arm.
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
     * A copy constructor of a {@code ZombieArm}.
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
        return "hits";
    }

    @Override
    public int damage() {
        return 5;
    }
}
