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
    public int totalNumberOfLimbs() {
        return allLimbs.size();
    }

    public int numberOfArms() {
        return arms.size();
    }

    public int numberOfLegs() {
        return legs.size();
    }

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

    public boolean hasArms() {
        return arms.size() > 0;
    }

    public boolean hasLegs() {
        return legs.size() > 0;
    }

    public boolean hasNoLimbs() {
        return allLimbs.size() == 0;
    }

    public String removeArm(int i) {
        arms.remove(allLimbs.get(i));
        allLimbs.remove(i);
        return Arm.ARM_DESCRIPTION;
    }

    public String removeLeg(int i) {
        legs.remove(allLimbs.get(i));
        allLimbs.remove(i);
        return Leg.LEG_DESCRIPTION;
    }

    String removeLimb(int i) {
        Limb limbToRemove = allLimbs.get(i);
        if (limbToRemove instanceof Arm && hasArms()) {
            removeArm(i);
        }
        else if (hasLegs()){
            removeLeg(i);
        }
        return limbToRemove.toString();
    }

    public boolean isAnArm(String description) {
        return description.equalsIgnoreCase(Arm.ARM_DESCRIPTION);
    }


}
