package game;

import edu.monash.fit2099.engine.Item;

// Maybe rethinking of making Limb an interface
public abstract class Limb extends Item {

    protected TypeOfLimb typeOfLimb;  // What type of Limb (e.g. Arm or Leg)
    protected TypeOfZombieActor typeActor;  // Which Actor's Limb (e.g. Zombie)

    public Limb(TypeOfLimb newTypeOfLimb, char charDisplay) {
        super("Limb", charDisplay,false);
        typeOfLimb = newTypeOfLimb;
    }

    public TypeOfLimb getTypeOfLimb() {
        return typeOfLimb;
    }

    public abstract Limb makeCopy();
}
