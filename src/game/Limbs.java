package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A Collection of {@code Limb} objects.
 * 
 * <p>{@code Limbs} represents a collection of {@code Limb} objects that an {@code Actor} in 
 * the zombie game may have.
 * 
 * <p>Provides methods to check how many limbs there are, adding a limb, removing a limb and 
 * getting a copy of the collection of limbs.
 * 
 * <p>Use this class to model an {@code Actor} in the zombie game having limbs.
 *
 * @author Isaac Lee Kian Min
 */
public class Limbs {

    /**
     * Maximum number of limbs.
     */
    private final int MAX_LIMBS;

    /**
     * A collection of all limbs.
     */
    private List<Limb> limbs;

    /**
     * Constructor of a {@code Limbs} object.
     *
     * @param maxLimbs a number indicating the maximum number of limbs.
     * @throws IllegalArgumentException if passed a negative {@code maxLimbs}.
     */
    public Limbs(int maxLimbs) {
        if (maxLimbs < 0) {
            throw new IllegalArgumentException("Cannot have negative amount of limbs");
        }
        MAX_LIMBS = maxLimbs;
        limbs = new ArrayList<>();
    }

    /**
     * Get an unmodifiable list of the collection of limbs.
     *
     * @return an unmodifiable list of limbs.
     */
    public List<Limb> getLimbs() {
        return Collections.unmodifiableList(limbs);
    }

    /**
     * Add a limb.
     *
     * @param newLimb a {@code Limb} to be added.
     * @throws NullPointerException if {@code newLimb} is a null reference.
     * @return true if and only if {@code Limb} added successfully.
     */
    public boolean addLimb(Limb newLimb) {
        Objects.requireNonNull(newLimb);  // check for null references
        boolean retVal = false;
        if (limbs.size() < MAX_LIMBS) {
            limbs.add(newLimb);
            retVal = true;
        }
        return retVal;
    }

    /**
     * Remove a limb.
     *
     * @param aLimb a {@code Limb} to be removed.
     * @throws NullPointerException if {@code aLimb} is a null reference.
     * @return true if and only if the {@code Limb} removed successfully.
     */
    public boolean removeLimb(Limb aLimb) {
        Objects.requireNonNull(aLimb);  // check for null references
        boolean retVal = false;
        if (limbs.contains(aLimb)) {
            limbs.remove(aLimb);
            retVal = true;
        }
        return retVal;
    }
    
    /**
     * Counts the number of a certain type of limb in the collection of limbs.
     *
     * @param typeOfLimb {@code TypeOfLimb} to be counted.
     * @return an integer indicating the number of limbs that are {@code TypeOfLimb} type.
     */
    public int count(TypeOfLimb typeOfLimb) {
        int counter = 0;
        for (Limb newLimb : limbs) {
            if (newLimb.getTypeOfLimb() == typeOfLimb) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Is there a specific type of limb?
     * 
     * <p>This method checks whether the collection of limbs still contains a specific 
     * {@code TypeOfLimb}
     *
     * @param typeOfLimb the {@code TypeOfLimb} to be checked.
     * @return true if and only if the collection of limbs contains the specified 
     * {@code TypeOfLimb}.
     */
    public boolean noMoreType(TypeOfLimb typeOfLimb) {
        return count(typeOfLimb) <= 0;
    }

    /**
     * Gets the total number of {@code Limb} objects in this {@code Limbs}.
     *
     * @return an integer value that indicates the total number of {@code Limb}.
     */
    public int totalNumberOfLimbs() {
        return limbs.size();
    }
}