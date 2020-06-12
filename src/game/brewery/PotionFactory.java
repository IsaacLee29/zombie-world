package game.brewery;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class PotionFactory {

    private Random rand = new Random();
    private ArrayList<Constructor<?>> potionConstructors = new ArrayList<>();

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
