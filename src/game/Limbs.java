package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Limbs {

    private final int MAX_LIMBS;
    private List<Limb> limbs;
    // Try to implement this variable to keep track of number of limbs for every turn
    // Behaves like the lastAction collection
    ArrayList<Integer> previousNumOfLimbs;

    public Limbs(int maxLimbs) {
        MAX_LIMBS = maxLimbs;
        limbs = new ArrayList<>();
    }

    public boolean addLimb(Limb newLimb) {
        boolean retVal = false;
        if (limbs.size() < MAX_LIMBS) {
            limbs.add(newLimb);
            retVal = true;
        }
        return retVal;
    }

    public List<Limb> getLimbs() {
//        Collections.shuffle(limbs);
        return Collections.unmodifiableList(limbs);
    }

    public Limb removeLimb(Limb aLimb) {
        Limb removed = null;
        if (limbs.contains(aLimb)) {
            removed = aLimb.makeCopy();
            limbs.remove(aLimb);
        }
//        for (NewLimb newLimb: limbs) {
//            if (newLimb.getTypeOfLimb() == aLimb.getTypeOfLimb()) {
//                removed = aLimb.makeCopy();
//                limbs.remove(newLimb);
//                break;
//            }
//        }
        return removed;
    }

    public int howMany(TypeOfLimb typeOfLimb) {
        int counter = 0;
        for (Limb newLimb: limbs) {
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
