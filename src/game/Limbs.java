package game;

import java.util.ArrayList;

public class Limbs {

    final int max_arms;
    final int max_legs;
    final int max_limbs;

    private ArrayList<Limb> arms = new ArrayList<>();
    private ArrayList<Limb> legs = new ArrayList<>();
    private ArrayList<Limb> allLimbs = new ArrayList<>();


    public Limbs(int numOfArms, int numOfLegs) {
        max_arms = numOfArms;
        max_legs = numOfLegs;
        max_limbs = max_arms + max_legs;
        setArms(max_arms);
        setLegs(max_legs);
    }

    // Reason why setArm and setLeg is private to ensure no one else can add Arms or Legs except Limbs
    private void setArms(int numOfArms) {
        for (int i = 0; i < numOfArms; i++) {
            Limb newArm = new Arm();
            arms.add(newArm);
            allLimbs.add(newArm);
        }
    }

    private void setLegs(int numOfLegs) {
        for (int i = 0; i < numOfLegs; i++) {
            Limb newLeg = new Leg();
            legs.add(newLeg);
            allLimbs.add(newLeg);
        }
    }


    public int totalNumberOfLimbs() {
        return allLimbs.size();
    }

    public int numberOfArms() {
        return arms.size();
    }

    public int numberOfLegs() {
        return legs.size();
    }


    public boolean hasArms() {
        return numberOfArms() > 0;
    }

    public boolean hasLegs() {
        return numberOfLegs() > 0;
    }

    public boolean hasNoLimbs() {
        return totalNumberOfLimbs() == 0;
    }


    public Limb removeLimb(int i) {
        Limb limbToRemove = allLimbs.get(i);
        if (limbToRemove instanceof Arm && hasArms()) {
            removeArm(i);
        }
        else if (hasLegs()){
            removeLeg(i);
        }
        return limbToRemove;
    }

    public Limb removeArm(int i) {
        Limb temp = allLimbs.get(i);
        arms.remove(temp);
        allLimbs.remove(i);
        return temp;
    }

    public Limb removeLeg(int i) {
        Limb temp = allLimbs.get(i);
        legs.remove(temp);
        allLimbs.remove(i);
        return temp;
    }


    public String toString() {
        return numberOfArms() + " arms & " + numberOfLegs() + " legs.";
    }
}
