package game;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A Zombie's Arm.
 *
 * This class models a Zombie's Arm as a type of Limb that can be used as a Weapon
 * when detached from the host Zombie.
 */
public class ZombieArm extends Limb implements Weapon {

    public ZombieArm() {
        super(TypeOfLimb.ZOMBIE_ARM, 'a', true);
    }

    private ZombieArm(ZombieArm newArm) {
        super(newArm.typeOfLimb, newArm.displayChar, true);
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
    public String verb() {
        return "hits";
    }

    @Override
    public int damage() {
        return 5;
    }
}
