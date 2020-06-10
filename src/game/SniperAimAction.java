package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class SniperAimAction extends Action{
	
	private int aimCounter;
	private ArrayList<Actor> zombies = new ArrayList<>();
	private Actor targetedZombie, tempZombie;
	private boolean fired;
	private Item sniperAmmunition;
	private static final double PROB_WITHOUT_AIMING = 0.75; 
	private static final double PROB_WITH_ONE_ROUND_AIMING = 0.90;
	private Weapon sniper;
	private AttackAction attack;
	private Display display = new Display();

//	public AimAction(Item weapon, TypeOfZombieActor typeOfZombie) {
	public SniperAimAction(Item sniperAmmunition, Weapon sniper, ArrayList<Actor> zombies) {
//		this.weapon = weapon;
		this.aimCounter = 0;
		this.sniperAmmunition = sniperAmmunition;
		this.sniper = sniper;
		this.zombies = zombies;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
//		target(actor, map.locationOf(actor));
		tempZombie = aimSubmenu();
		if (tempZombie != targetedZombie) {
			aimCounter = 0;
			targetedZombie = tempZombie;
		}
		aimCounter += 1;
		fired = shootSubmenu();
		if (fired == true) {
			String retVal = shoot(actor, map);
			aimCounter = 0;
			sniperAmmunition.changeAmmount(-1);
			return retVal;
		}
		return actor + " aimed " + targetedZombie +" for the " + aimCounter + " turns.";
	}


	private String shoot(Actor actor, GameMap map) {
		boolean retVal = false;
		String str = "";
		if (aimCounter == 1) {
			if (Math.random() <= PROB_WITHOUT_AIMING) {
				retVal = true;
			}
		}
		else if (aimCounter == 2) {
			if (Math.random() <= PROB_WITH_ONE_ROUND_AIMING) {
				retVal = true;
			}
		}
		else if (aimCounter == 3) {
			retVal = true;
		}
		if (retVal == true) {
			attack = new SniperAttackAction(targetedZombie, aimCounter, sniper);
			str = attack.execute(actor, map);
			return str;
		}
		return actor + "missed " + targetedZombie + ".";
	}

	@Override
	public String menuDescription(Actor actor) {
		String retVal = "";
		if (aimCounter == 0) {
			retVal += actor + " use sniper on a new target";
		}
		else {
			retVal += actor + " continue aiming " + targetedZombie + " or choose a new target."; 
		}
		return retVal;
	}
	
	public Actor aimSubmenu() {
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Actor> keyToActorMap = new HashMap<Character, Actor>();

		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);
		
		for (Actor actor: zombies) {
			char c;
			// Run out of characters
			if (freeChars.isEmpty()){
				break;
			}
			c = freeChars.get(0);
			freeChars.remove(Character.valueOf(c));
			keyToActorMap.put(c, actor);
			display.println(c + ": Target " + actor);
		}

		char key;
		do {
			key = display.readChar();
		}
		while (!keyToActorMap.containsKey(key));
		
		return keyToActorMap.get(key);
		
	}
	
	public boolean shootSubmenu() {
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Boolean> keyToBooleanMap = new HashMap<>();
		for (char i = 'a'; i <= 'b'; i++) {
			freeChars.add(i);
		}
		keyToBooleanMap.put('a', true);
		keyToBooleanMap.put('b', false);
		
		display.println("a: Shoot");
		display.println("b: Continue aiming");
		
		char key;
		do {
			key = display.readChar();
		}
		while (!keyToBooleanMap.containsKey(key));
		
		return keyToBooleanMap.get(key);
	}
}
