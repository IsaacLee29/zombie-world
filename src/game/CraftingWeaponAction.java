package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class CraftingWeaponAction extends Action{
	private Item limb;
	
	public CraftingWeaponAction(Item limb) {
		this.limb = limb;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		if (limb.getDisplayChar() == 'a') {
			actor.addItemToInventory(new ZombieClub());
			actor.removeItemFromInventory(limb);
		}
		else if(limb.getDisplayChar() == 'l') {
			actor.addItemToInventory(new ZombieMace());
			actor.removeItemFromInventory(limb);
		}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		if (limb.getDisplayChar() == 'a') {
			return actor + " used a zomebie arm and crafted into a ZombieClub Weapon";
		}
		else if(limb.getDisplayChar() == 'l') {
			return actor + " used a zombie leg and crafted into a ZombieMace";
		}
		return null;
	}
}
