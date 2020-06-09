package game;

import edu.monash.fit2099.engine.*;
import game.vanishCapability.VanishAble;
import game.vanishCapability.VanishAction;

public class MamboMarie extends ZombieActor {

    public static final double VANISH_PROBABILITY = 0.4;

    private int playTurns = 0;  // keep track of the number of turns played
    private Behaviour[] behaviours = new Behaviour[] { new ChantBehaviour(), new WanderBehaviour() };

    public MamboMarie() {
        super("Mambo Marie", 'M', 100, ZombieCapability.UNDEAD, TypeOfZombieActor.MAMBOMARIE);
        addCapability(VanishAble.VANISH);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        playTurns ++;
        if (playTurns % 30 == 1 && playTurns > 30) {
//            if (true) {
            return new VanishAction(this);
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
    public boolean vanishing() {
        return Math.random() <= VANISH_PROBABILITY;
//        return true;
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
