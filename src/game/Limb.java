package game;

public abstract class Limb {

    private char displayChar;

    public Limb(char displayChar) {
        this.displayChar = displayChar;
    }

    public char getDisplayChar() {
        return this.displayChar;
    }

    /**
     * Makes a copy of objects instantiated from classes that inherit this class.
     * Assigns this copied objects to a reference of this class.
     *
     * @return subclass objects with a reference of this class.
     */
    public abstract Limb makeCopy();

    /**
     *
     * @param limb
     * @return
     */
    public abstract boolean equal(Limb limb);

}
