package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitAction extends Action{

	@Override
	public String execute(Actor actor, GameMap map) {
		System.out.println("Player has quit the game");
		System.exit(0);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}

}
