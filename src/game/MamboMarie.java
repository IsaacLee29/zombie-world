package game;

import edu.monash.fit2099.engine.*;
import game.vanishcapabilities.VanishAbleActors;
import game.vanishcapabilities.VanishActorAction;

public class MamboMarie extends ZombieActor implements VanishAbleActors {

    /**
     * Probability of MamboMarie not reappearing (i.e. remain vanished).
     */
    public static final double VANISH_PROBABILITY = 0.4;  //0.95

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
        if (playTurns % 30 == 1 && playTurns > 30) {
//            if (true) {
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
    public VanishAbleActors getVanishAble() {
        return this;
    }

    private class ChantBehaviour implements Behaviour {

        @Override
        public Action getAction(Actor actor, GameMap map) {
            if (playTurns % 10 == 0) {
                return new CreateZombieAction(5);
            }
            return null;
        }
    }
}
