package game.brewery;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

/**
 * A factory for making potions.
 *
 * <p>This class is used as a factory to brew (i.e. create) different types of potions.
 * 
 * @author Isaac Lee Kian Min.
 */
public class PotionFactory {

    /**
     * A random number generator.
     */
    private Random rand = new Random();

    /**
     * A list of the different potions this factory can make.
     */
    private ArrayList<Constructor<?>> potionConstructors = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param potionTypes different types of potions this factory is able to brew.
     */
    public PotionFactory(Potion... potionTypes) {
        for (Potion potion : potionTypes) {
            try {
                Constructor<?> constructor = potion.getClass().getConstructor();
                potionConstructors.add(constructor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns a randomly brewed potion that is it able to brew.
     *
     * @return a potion.
     */
    public Potion getPotion() {
        int randomPotion = rand.nextInt(potionConstructors.size());
        try {
            return (Potion) potionConstructors.get(randomPotion).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
