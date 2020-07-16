package game;

import edu.monash.fit2099.engine.*;
import game.vanishing.VanishAbleActor;
import game.vanishing.VanishActorAction;

/**
 * Mambo Marie.
 *
 * This class is used to simulate the character Mambo Marie.
 *
 * <p>Mambo Marie is able to create new zombies every 10 play turns and will vanish
 * after 30 play turns. Mambo Marie will respawn until it is defeated.
 */
public class MamboMarie extends ZombieActor implements VanishAbleActor {

    /**
     * Probability of MamboMarie not reappearing (i.e. remain vanished).
     */
    public static final double VANISH_PROBABILITY = 0.95;  //0.95

    /**
     * Determines whether MamboMarie used her last action to vanish.
     */
    private boolean justVanished;

    /**
     * The number of play turns played.
     */
    private int playTurns = 0;

    /**
     * The behaviours of MamboMarie.
     */
    private Behaviour[] behaviours = new Behaviour[] { new ChantBehaviour(), new WanderBehaviour() };


    /**
     * Constructor of a MamboMarie actor.
     */
    public MamboMarie() {
        super("Mambo Marie", 'M', 100, ZombieCapability.UNDEAD, TypeOfZombieActor.MAMBOMARIE);
    }

    /**
     * MamboMarie will performs an action based on its behaviours.
     *
     * <p>On top of that, if she has played over 30 turns, she will disappear temporarily from the game map
     * its currently on.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        playTurns ++;
        if (playTurns > 30) {
            playTurns = 0;  // reset play turns upon disappearing
            justVanished = true;
            return new VanishActorAction(this);
        }
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public boolean stillVanished() {
        if (justVanished) {
            justVanished = false;
            return true;
        }
        return rand.nextDouble() <= VANISH_PROBABILITY;
    }

    @Override
    public Actor getActor() {
        return this;
    }

    @Override
    public VanishAbleActor getVanishAble() {
        return this;
    }

    /**
     * A chanting behaviour for Mambo Marie.
     *
     * <p>The chanting behaviour causes Mambo Marie to create 5 new {@code Zombie}.
     */
    private class ChantBehaviour implements Behaviour {

        /**
         * This method returns a {@code CreateZombieAction} for every 10 turns Mambo Marie plays.
         *
         * @param actor the Actor acting
         * @param map the GameMap containing the Actor
         * @return a {@code CreateZombieAction} for every 10 turns Mambo Marie plays, otherwise false.
         */
        @Override
        public Action getAction(Actor actor, GameMap map) {
            if (playTurns % 10 == 0) {
                return new CreateZombieAction(5);
            }
            return null;
        }
    }
}
