package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Limbs {

    private final int MAX_LIMBS;
    private List<Limb> limbs;

    public Limbs(int maxLimbs) {
        if (maxLimbs < 0) {
            throw new IllegalArgumentException();
        }
        MAX_LIMBS = maxLimbs;
        limbs = new ArrayList<>();
    }

    public List<Limb> getLimbs() {
        return Collections.unmodifiableList(limbs);
    }

    public boolean addLimb(Limb newLimb) {
        boolean retVal = false;
        if (limbs.size() < MAX_LIMBS) {
            limbs.add(newLimb);
            retVal = true;
        }
        return retVal;
    }

    public Limb removeLimb(Limb aLimb) {
        Limb removed = null;
        if (limbs.contains(aLimb)) {
            removed = aLimb.makeCopy();
            limbs.remove(aLimb);
        }
        return removed;
    }

    public boolean noMoreType(TypeOfLimb typeOfLimb) {
        return count(typeOfLimb) <= 0;
    }

    public int count(TypeOfLimb typeOfLimb) {
        int counter = 0;
        for (Limb newLimb : limbs) {
            if (newLimb.getTypeOfLimb() == typeOfLimb) {
                counter++;
            }
        }
        return counter;
    }

    public int totalNumberOfLimbs() {
        return limbs.size();
    }
}