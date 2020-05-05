package game;

import edu.monash.fit2099.engine.*;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {

	public static final String[] ZOMBIE_PHRASES = {"Braaaaains", "Zombiesss", "Arghhh"};

	private Limbs zombieLimb;
	private double biteProbability = 0.5;
	private boolean lostALegPreviously;

	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		zombieLimb = new Limbs(2, 2);
		lostALegPreviously = false;
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (Math.random() <= biteProbability) {
			return new IntrinsicWeapon(30, "bites");
		} else {
			return new IntrinsicWeapon(10, "punches");
		}
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		pickUpWeapon(actions, map);  // Zombie picks up a WeaponItem
		zombieTalking(display);  // Zombie uttering words

		if (skipTurn()) {
			lostALegPreviously = false;
			return new DoNothingAction();
		}

		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}

		return new DoNothingAction();	
	}

	/**
	 * If there is a Weapon at this Zombie's location, it will pick it up.
	 * Zombie will only pick up the first Weapon it sees.
	 *
	 * @param actions list of possible Actions
	 * @param map the map where the current Zombie is
	 */
	private void pickUpWeapon(Actions actions, GameMap map) {
		// RE-CHECK THIS METHOD AGAIN, CUZ THIS MEANS THAT IT'LL PICKUP ANYTHING IT SEES,
		// EVEN IF IT'S NOT A WEAPON
		if (zombieLimb.numberOfArms() > 0) {
			for (Action action: actions) {
				if (action instanceof PickUpItemAction) {
					action.execute(this, map);
					actions.remove(action);
					break;
				}
			}
		}
	}

	/**
	 * Each Zombie has a 10% chance of saying a "Zombie-like phrase".
	 *
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	private void zombieTalking(Display display) {
		if (Math.random() <= 0.1) {
			int phrase = (int)(Math.random() * ZOMBIE_PHRASES.length);
			display.println(this + " says " + ZOMBIE_PHRASES[phrase]);
		}
	}

	public static boolean missBite(double probability) {
		return probability > 0.3;
	}

	private void halvePunchProbability() {
		this.biteProbability += 0.5*(1-biteProbability);
	}

	@Override
	public String loseLimbs(GameMap map) {
		String prefix = "";
		String result = null;
		if (!zombieLimb.hasNoLimbs() && Math.random() <= 1.0) {//0.25) {
			result = "";
			int knockHowManyLimbs = (int)(Math.ceil(Math.random() * zombieLimb.totalNumberOfLimbs()));
			while (knockHowManyLimbs > 0) {
				result += prefix;
				result += "lost " + zombieLimb.removeLimb((int)(Math.random() * zombieLimb.totalNumberOfLimbs()));
				prefix = "\n";
				knockHowManyLimbs -= 1;
			}
		}
		return result + lostWhat(map);
	}

	// Drops the first
	private void dropAWeapon(GameMap map) {
		boolean hasWeapon = false;
		int i = 0;
		int sizeOfInventory = this.inventory.size();
		while ((i < sizeOfInventory) && !hasWeapon) {
			if (this.inventory.get(i).asWeapon() != null) {
				Action action = new DropItemAction(this.inventory.get(i));
				action.execute(this, map);
				hasWeapon = true;
			}
			i += 1;
		}
	}

	// If lost arms, do what with weapons on hand?
	private void droppingAWeapon(int lostArms, GameMap map) {
		if (lostArms == 2) {
			while (inventory.contains(WeaponItem.class)) {
				dropAWeapon(map);
			}
		} else if (Math.random() <= 0.5){
			dropAWeapon(map);
		}
	}

	// If it loss legs, do what?
	private void HAHAHA(int lostLegs) {
		if (lostLegs == 2) {
			behaviours = new Behaviour[] {new AttackBehaviour(ZombieCapability.ALIVE)};
		} else {
			lostALegPreviously = true;
		}
	}

	// Determines what to do if it loses either an arm or a leg or both at the same time
	private String lostWhat(GameMap map) {
		String result = "";
		int lostArms = zombieLimb.max_arms - zombieLimb.numberOfArms();
		int lostLegs = zombieLimb.max_legs - zombieLimb.numberOfLegs();

		if (lostArms > 0 && lostLegs == 0) {
			halvePunchProbability();
			droppingAWeapon(lostArms, map);
			result = "lost " + lostArms + Arm.ARM_DESCRIPTION;
		} else if (lostArms == 0 && lostLegs > 0) {
			HAHAHA(lostLegs);
			result = "lost " + lostLegs + Leg.LEG_DESCRIPTION;
		} else {
			halvePunchProbability();
			droppingAWeapon(lostArms, map);
			HAHAHA(lostLegs);
			result = "lost " + lostArms + " " + Arm.ARM_DESCRIPTION + " and "
					+ lostLegs + " " + Leg.LEG_DESCRIPTION;
		}
		return result;
	}



	private boolean skipTurn() {
		boolean retVal = false;
		if (lostALegPreviously && zombieLimb.numberOfLegs() != zombieLimb.max_legs) {
			retVal = true;
		} else if (!lostALegPreviously && zombieLimb.numberOfLegs() != zombieLimb.max_legs){
			lostALegPreviously = true;
		}
		return retVal;
	}
}
