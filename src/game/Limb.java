package game;

import edu.monash.fit2099.engine.Item;

/**
 * A Limb.
 * 
 * <p>This {@code abstract} class provides a template to create objects that are a {@code Limb}. 
 * In the zombie game, a {@code Limb} is considered a physical object. 
 * 
 * <p>Provides methods to get the type of limb, making a deep copy and provide a description 
 * of the {@code Limb}.
 * 
 * <p>Use this class to model different types of limb.
 *
 * @author Isaac Lee Kian Min
 */
public abstract class Limb extends Item {

    /**
     * Indicates the type of limb.
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
     * A method to make a deep copy of the limb object and pass it a {@code Limb} reference.
     * <p>
     * Implement this method within classes that inherit this class to prevent privacy leaks.
     *
     * @return a copy of the {@code Limb} object.
     */
    public abstract Limb makeCopy();

    /**
     * A description of this type of limb.
     *
     * @return a String describing the type of this limb.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
