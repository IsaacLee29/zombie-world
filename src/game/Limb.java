package game;

import edu.monash.fit2099.engine.Item;

/**
 * A Limb.
 *
 * This class provides a template to create objects that are essentially limbs.
 * In the ZombieWorld, a limb is considered a physical object. Provides methods to get the
 * type of limb, making a deep copy and description of the limb.
 * 
 * Use this class if you want to model a limb.
 *
 * @author Isaac Lee Kian Min
 */
public abstract class Limb extends Item {

    /**
     * Determines what type of limb this is.
     */
    protected TypeOfLimb typeOfLimb;

    /**
     * Constructor.
     *
     * @param newTypeOfLimb the {@code TypeOfLimb} this {@code Limb} is.
     * @param charDisplay the character to use to represent this item if it is on the ground.
     * @param portable true if and only if the Item can be picked up.
     */
    public Limb(TypeOfLimb newTypeOfLimb, char charDisplay, boolean portable) {
        super("Limb", charDisplay,portable);
        typeOfLimb = newTypeOfLimb;
    }

    /**
     * Get the type of limb.
     *
     * @return the type of limb.
     */
    public TypeOfLimb getTypeOfLimb() {
        return typeOfLimb;
    }

    /**
     * Make a deep copy of the Limb object and pass it a {@code Limb} reference.
     *
     * Implement this method within classes that inherit this class to prevent privacy leaks.
     *
     * @return a copy of the Limb object.
     */
    public abstract Limb makeCopy();

    /**
     * A description of this type of limb.
     *
     * @return a String describing the type of this limb.
     */
    @Override
    public String toString() {
        return typeOfLimb.toString();
    }
}
