package game;

public class Arm extends Limb{

    static final String ARM_DESCRIPTION = "Arm";

    public Arm() {
        super('a');
    }

    public Arm(Limb limb) {
        super(limb.getDisplayChar());
    }

    @Override
    public String toString() {
        return ARM_DESCRIPTION;
    }

    @Override
    public Limb makeCopy() {
        Limb copyLimb = new Arm(this);
        return copyLimb;
    }

    @Override
    public boolean equal(Limb limb) {
        return limb instanceof Arm;
    }
}
