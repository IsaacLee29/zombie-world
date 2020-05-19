package game;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.engine.Action;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Zombie.
 * <p>
 * This Zombie is pretty boring.  It needs to be made more interesting.
 *
 * @author ram
 */
public class Zombie extends ZombieActor {

    /**
     * The probability of a Zombie missing a bite attack.
     */
    public static final double FAILED_BITE = 0.7;

    /**
     * The number of arms and legs a Zombie has.
     */
    public static final int ARMS_LEGS_NUMBER = 2;

    /**
     * The probability of a Zombie saying something.
     */
    public static final double TALKING_PROBABILITY = 0.1;

    /**
     * The probability of a Zombie losing a limb from an attack.
     */
    public static final double LOSE_LIMB_PROBABILITY = 1;  //0.25

    /**
     * The probability of a Zombie dropping a weapon after losing its arm.
     */
    public static final double DROP_WEAPON_PROBABILITY = 1;  //0.5

    /**
     * The phrases that a Zombie can say.
     */
    public static final String[] ZOMBIE_PHRASES = {"Braaaaains", "Zombiesss", "Arghhh"};

    /**
     * The probability of a Zombie using its bite intrinsic weapon for an attack.
     */
    private double biteProbability = 0.5;

    /**
     * If the Zombie previously lost a leg.
     */
    private boolean legKnockedPreviously = false;

//    /**
//     * If the Zombie previously lost a limb.
//     */
//    private boolean limbKnockedPreviously = false;

    /**
     * The limbs of a Zombie.
     */
    private Limbs zombieLimbs;

//    /**
//     * The current state of mobility of the Zombie.
//     */
//    private ZombieMobility mobility;

    /**
     * The behaviours of a Zombie.
     * <p>
     * It only picks up Weapons.
     * It can attack other ZombieActors.
     * It only hunts for Humans.
     * It can wander around the GameMap.
     */
    private Behaviour[] behaviours = {
            new PickUpItemBehaviour(Weapon.class),
            new AttackBehaviour(ZombieCapability.ALIVE),
            new HuntBehaviour(Human.class, 10),
            new WanderBehaviour()
    };

    public Zombie(String name) {
        super(name, 'Z', 100, ZombieCapability.UNDEAD, TypeOfZombieActor.ZOMBIE);
//        mobility = ZombieMobility.MOBILE;
        setLimbs();
    }

    /**
     * This method sets the number of Limbs a Zombie has.
     */
    private void setLimbs() {
        zombieLimbs = new Limbs(2*ARMS_LEGS_NUMBER);
        for (int i = 0; i < ARMS_LEGS_NUMBER; i++) {
            zombieLimbs.addLimb(new ZombieArm());
            zombieLimbs.addLimb(new ZombieLeg());
        }
    }

    /**
     * This method sets the behaviours of a Zombie.
     *
     * @param behaviours
     */
    private void setBehaviours(Behaviour[] behaviours) {
        this.behaviours = behaviours;
    }

    /**
     * Gets a Zombie's available weapons and determines whether it is able to
     * inflict damage using the weapon of choice.
     *
     * @return a weapon if it is able to inflict damage using it, otherwise null.
     */
    @Override
    public Weapon getWeapon() {
        Weapon weapon = super.getWeapon();
        if (weapon != null && weapon.verb().equalsIgnoreCase("bites")) {
            if (rand.nextDouble() <= FAILED_BITE) {  // If Zombie misses its bite
                weapon = null;
            } else {  // If Zombie successfully bite
                super.heal(5);
            }
        }
        return weapon;
    }

    /**
     * A Zombie can bite or punch.
     *
     * @return an intrinsic weapon of a Zombie.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        if (Math.random() <= biteProbability || zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_ARM)) {
            return new IntrinsicWeapon(20, "bites");
        } else {
            return new IntrinsicWeapon(10, "punches");
        }
    }

//    /**
//     * Do some damage to the Zombie.
//     * <p>
//     * Determines whether the Zombie loses any limbs after a successful attack on it.
//     *
//     * @param points number of hitpoints to deduct.
//     */
//    @Override
//    public void hurt(int points) {
//        super.hurt(points);
//        if (rand.nextDouble() <= LOSE_LIMB_PROBABILITY && zombieLimbs.totalNumberOfLimbs() > 0)
//            limbKnockedPreviously = true;
//    }

    /**
     * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.
     * If no humans are close enough it will wander randomly.
     * The Zombie will drop its limbs if it lost any as a result of an attack on it.
     * A Zombie is also able to say a "Zombie-like" phrase.
     *
     * @param actions    list of possible Actions
     * @param lastAction previous Action, if it was a multiturn action
     * @param map        the map where the current Zombie is
     * @param display    the Display where the Zombie's utterances will be displayed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

//        if (limbKnockedPreviously) {  // If Zombie lost a limb from an attack
//            display.println(knockOffLimb(map));
//            limbKnockedPreviously = false;
//        }

        zombieTalking(display);  // Zombie uttering words

        if (skipTurn()) {  // If Zombie skips a turn
            legKnockedPreviously = false;
        } else {
            for (Behaviour behaviour : behaviours) {
                Action action = behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }

        return new DoNothingAction();
    }


    /**
     * Each Zombie has a 10% chance of saying a "Zombie-like phrase".
     *
     * @param display the Display where the Zombie's utterances will be displayed
     */
    private void zombieTalking(Display display) {
        if (rand.nextDouble() <= TALKING_PROBABILITY) {
            int phrase = (int) (rand.nextDouble() * ZOMBIE_PHRASES.length);
            display.println(this + " says " + ZOMBIE_PHRASES[phrase]);
        }
    }

    /**
     * Knocks off a Zombie's limbs.
     * <p>
     * Knocks off at least one limb from the Zombie & drops it at the Zombie's current location
     * or a location adjacent to it.
     * Use this method to determined the number of limbs knocked off & drop the limb as a result
     * of an attack on it.
     *
     * @param map the map where the current Zombie is.
     * @return a description of the Zombie limbs knocked off.
     */
    public String knockOffLimb(GameMap map) {
        String result = null;

        if (rand.nextDouble() <= LOSE_LIMB_PROBABILITY && zombieLimbs.totalNumberOfLimbs() > 0) {
            int arms = zombieLimbs.count(TypeOfLimb.ZOMBIE_ARM);  // current number of arms
            int legs = zombieLimbs.count(TypeOfLimb.ZOMBIE_LEG);  // current number of legs
            List<Limb> deck = new ArrayList<>(zombieLimbs.getLimbs());
            Collections.shuffle(deck);  // shuffle to knock a random limb

            int temp = 1 + rand.nextInt(zombieLimbs.totalNumberOfLimbs());
            for (int i = 0; i < temp; i++) {
                zombieLimbs.removeLimb(deck.get(i)).getDropAction().execute(this, map);
            }

            arms -= zombieLimbs.count(TypeOfLimb.ZOMBIE_ARM);
            legs -= zombieLimbs.count(TypeOfLimb.ZOMBIE_LEG);

            losingArms(arms, map);
            losingLegs(legs);

            result = String.format("%s lost %d arms & %d legs", this, arms, legs);
        }

        return result;
    }

    /**
     * Reduces the Zombie's chances of punching by half.
     */
    private void halvePunchProbability() {
        this.biteProbability += 0.5 * (1 - biteProbability);
    }

    /**
     * Drops a weapon the Zombie has.
     * <p>
     * This method drops the first occurrence of a weapon in the Zombie's inventory.
     *
     * @param map the map where the current Zombie is.
     */
    private void dropAWeapon(GameMap map) {
        for (Item item : this.inventory) {
            if (item.asWeapon() != null) {
                item.getDropAction().execute(this, map);
                break;
            }
        }
    }


    /**
     * The effects of a Zombie losing an arm or both.
     * <p>
     * This method implements the consequences of a Zombie losing an arm.
     *
     * @param lostArms number of arms lost.
     */
    private void losingArms(int lostArms, GameMap map) {
        // If Zombie lost one arm
        if (lostArms == 1) {
            halvePunchProbability();
            if (rand.nextDouble() <= DROP_WEAPON_PROBABILITY) {
                dropAWeapon(map);
            }
        }

        // If Zombie lost both arms or loses its last arm
        if (zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_ARM) && lostArms > 0) {
            this.inventory.clear();  // Since zombie only knows how to pick up weapons
            if (!zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_LEG)) {
                setBehaviours(new Behaviour[]{
                        new AttackBehaviour(ZombieCapability.ALIVE),
                        new HuntBehaviour(Human.class, 10),
                        new WanderBehaviour()});
            } else {
                setBehaviours(new Behaviour[]{new AttackBehaviour(ZombieCapability.ALIVE)});
            }
        }
    }

    /**
     * The effects of a Zombie losing a leg or both.
     * <p>
     * This method implements the consequences of a Zombie losing a leg.
     *
     * @param lostLegs number of legs lost.
     */
    private void losingLegs(int lostLegs) {
        // If Zombie lost one leg
        if (!zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_LEG) && lostLegs == 1 ) {
            legKnockedPreviously = true;
//            mobility = ZombieMobility.PARTIALLY_MOBILE;
        }

        // If Zombie lost both legs or loses its last leg
        if (zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_LEG) && lostLegs > 0) {
//            mobility = ZombieMobility.IMMOBILE;
            if (!zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_ARM)) {
                setBehaviours(new Behaviour[]{
                        new PickUpItemBehaviour(Weapon.class),
                        new AttackBehaviour(ZombieCapability.ALIVE)});
            } else {
                setBehaviours(new Behaviour[]{new AttackBehaviour(ZombieCapability.ALIVE)});
            }
        }
    }

    /**
     * A Zombie may skip a turn because it lost a leg.
     * <p>
     * This method determines whether the Zombie will skip a turn.
     *
     * @return a boolean value on whether to skip a turn.
     */
    private boolean skipTurn() {
        boolean retVal = false;
        // If noMoreLegs
        if (zombieLimbs.noMoreType(TypeOfLimb.ZOMBIE_LEG)) {  // If Zombie already has no legs
            retVal = false;
        }
        // If Zombie's legs were knocked off previously
        else if (legKnockedPreviously && zombieLimbs.count(TypeOfLimb.ZOMBIE_LEG) < ARMS_LEGS_NUMBER) {
            retVal = true;
        }
        // If Zombie doesn't have all it's legs
        else if (!legKnockedPreviously && zombieLimbs.count(TypeOfLimb.ZOMBIE_LEG) < ARMS_LEGS_NUMBER) {
            legKnockedPreviously = true;
        }
        return retVal;
    }
}
