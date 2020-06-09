package game;

import edu.monash.fit2099.engine.*;
import game.vanishCapability.VanishAble;
import game.vanishCapability.VanishCharacters;
import java.util.List;

public class ZombieGameMap extends GameMap {

    private VanishCharacters vanishedActors = new VanishCharacters();

    public ZombieGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    @Override
    public void tick() {
        super.tick();
        vanishedActors.execute();
    }

    @Override
    public void removeActor(Actor actor) {
        if (actor.hasCapability(VanishAble.VANISH) && actor.isConscious()) {
            vanishedActors.addVanishedActors(actor, this);
        }
        super.removeActor(actor);
    }

    @Override
    public boolean contains(Actor actor) {
        if (actor.getTypeOfZombieActor() == TypeOfZombieActor.MAMBOMARIE)
            return vanishedActors.contains(actor);
        else
            return super.contains(actor);
    }
}
