package game;

import edu.monash.fit2099.engine.Item;

public abstract class Limb extends Item {

    protected TypeOfLimb typeOfLimb;  // What type of Limb (e.g. Arm or Leg)

    public Limb(TypeOfLimb newTypeOfLimb, char charDisplay, boolean portable) {
        super("Limb", charDisplay,portable);
        typeOfLimb = newTypeOfLimb;
    }

    public TypeOfLimb getTypeOfLimb() {
        return typeOfLimb;
    }

    public abstract Limb makeCopy();

    @Override
    public String toString() {
        return typeOfLimb.toString();
    }
}
