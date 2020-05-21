package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A Collection of Limb objects.
 *
 * A Limbs represents a collection of {@code Limb} objects that a particular Actor may have.
 * Provides methods to check how many limbs there are, adding a limb, removing a limb and getting
 * a copy of the collection of limbs.
 * 
 * Use this class if you want {@code Actors} in the ZombieWorld to have limbs.
 *
 * @author Isaac Lee Kian Min
 */
public class Limbs {

    /**
     * Maximum number of limbs allowed.
     */
    private final int MAX_LIMBS;

    /**
     * A collection of all limbs.
     */
    private List<Limb> limbs;

    /**
     * Constructor
     *
     * @param maxLimbs a number indicating the maximum number of limbs.
     * @throws IllegalArgumentException if passed a negative {@code maxLimbs}.
     */
    public Limbs(int maxLimbs) {
        if (maxLimbs < 0) {
            throw new IllegalArgumentException();
        }
        MAX_LIMBS = maxLimbs;
        limbs = new ArrayList<>();
    }

    /**
     * Gets an unmodifiable list of the collection of limbs.
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
     * @return true if and only if {@code Limb} added successfully.
     */
    public boolean addLimb(Limb newLimb) {
        Objects.requireNonNull(newLimb);
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
     * @return true if and only if the {@code Limb} removed successfully.
     */
    public boolean removeLimb(Limb aLimb) {
        Objects.requireNonNull(aLimb);
        boolean retVal = false;
        if (limbs.contains(aLimb)) {
            limbs.remove(aLimb);
            retVal = true;
        }
        return retVal;
    }

    /**
     * Is there a specific type of limb?
     *
     * This method checks whether there still exists a specified {@code TypeOfLimb} in {@code Limbs}.
     *
     * @param typeOfLimb the {@code TypeOfLimb} to be checked.
     * @return true if and only if the limb still exists.
     */
    public boolean noMoreType(TypeOfLimb typeOfLimb) {
        return count(typeOfLimb) <= 0;
    }

    /**
     * Counts the number of limbs that are a certain type.
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
     * Gets the total number of {@code Limb} objects in this {@code Limbs}..
     *
     * @return an integer value that indicates the total number of {@code Limb}.
     */
    public int totalNumberOfLimbs() {
        return limbs.size();
    }
}