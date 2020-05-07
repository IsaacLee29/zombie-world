package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A dropped Limb that can be wielded as simple club.
 *
 */
public class SimpleClub extends WeaponItem {

    private Limb limb;

    public SimpleClub(Limb droppedLimb, int damage, String verb) {
        super(droppedLimb.toString(), droppedLimb.getDisplayChar(), damage, verb);
        limb = droppedLimb;
    }
}