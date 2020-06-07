package game;

import edu.monash.fit2099.engine.*;

public class MamboMarie extends ZombieActor {

    // Consider making an abstract getPlayTurns method
    private int playTurns = 0;  // keep track of the number of turns played
    private Behaviour[] behaviours = new Behaviour[] { new ChantBehaviour(), new WanderBehaviour() };

    public MamboMarie() {
        super("Mambo Marie", 'M', 100, ZombieCapability.ALIVE, TypeOfZombieActor.MAMBOMARIE);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        playTurns ++;
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return null;
    }

//    @Override
//    public boolean vanishable() {
//        return true;
//    }
//
//    @Override
//    public boolean vanishing() {
//        return (playTurns % 30 == 1) && (playTurns > 30);
//    }
//
//    @Override
//    public Actor getActor() {
//        return this;
//    }

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
