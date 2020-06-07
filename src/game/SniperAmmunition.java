package game;

import edu.monash.fit2099.engine.Item;

public class SniperAmmunition extends Item{

	public SniperAmmunition(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
		counter  = 10;
	}

}
