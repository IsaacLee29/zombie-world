package game;

public class Leg extends Limb {

    public static final String LEG_DESCRIPTION = "Leg";

    public Leg() {
        super('l');
    }

    public Leg(Limb limb) {
        super(limb.getDisplayChar());
    }

    @Override
    public String toString() {
        return LEG_DESCRIPTION;
    }

    @Override
    public Limb makeCopy() {
        Limb copyLimb = new Leg(this);
        return copyLimb;
    }

    @Override
    public boolean equal(Limb limb) {
        return limb instanceof Leg;
    }
}
