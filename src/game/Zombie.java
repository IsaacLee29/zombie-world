package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {

	public static final double TALKING_PROBABILITY = 0.1;
	public static final double LOSE_LIMB_PROBABILITY = 0.25;
	public static final double DROPPING_WEAPON_PROBABILITY = 0.5;
	public static final String[] ZOMBIE_PHRASES = {"Braaaaains", "Zombiesss", "Arghhh"};

	private double biteProbability = 0.5;

	private Limbs zombieLimb;
	/**
	 * Represents the current state of mobility of this Actor
	 */
	private ZombieMobility mobility;

	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		zombieLimb = new Limbs(2, 2);
		mobility = ZombieMobility.HAS_ALL_LEGS;
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

		pickUpWeapon(map, display);  // Zombie picks up a WeaponItem
		zombieTalking(display);  // Zombie uttering words

		if (skipTurn()) {
			mobility.setLostLimbPreviously(false);
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
	 * @param map the map where the current Zombie is
	 */
	private void pickUpWeapon(GameMap map, Display display) {
		ArrayList<Item> itemList = new ArrayList<>(map.locationOf(this).getItems());
		for (Item anItem: itemList) {
			if (anItem.asWeapon() != null) {
				display.println(anItem.getPickUpAction().execute(this, map));
				break;
			}
		}
	}

	/**
	 * Each Zombie has a 10% chance of saying a "Zombie-like phrase".
	 *
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	private void zombieTalking(Display display) {
		if (Math.random() <= TALKING_PROBABILITY) {
			int phrase = (int)(Math.random() * ZOMBIE_PHRASES.length);
			display.println(this + " says " + ZOMBIE_PHRASES[phrase]);
		}
	}

	public static boolean missBite(double probability) {
		return probability <= 0.7;
	}

	private void halvePunchProbability() {
		this.biteProbability += 0.5*(1-biteProbability);
	}

	@Override
	public String loseLimbs(GameMap map) {
		if (!mobility.noMoreLegs() && Math.random() <= LOSE_LIMB_PROBABILITY) {
			ArrayList<Limb> temp = new ArrayList<>();
			int iniNumArm = zombieLimb.numberOfArms(), iniNumLeg = zombieLimb.numberOfLegs();
			int knockHowManyLimbs = (int)(Math.ceil(Math.random() * zombieLimb.totalNumberOfLimbs()));
			while (knockHowManyLimbs > 0) {
				temp.add(zombieLimb.removeLimb((int)(Math.random() * zombieLimb.totalNumberOfLimbs())));
				knockHowManyLimbs -= 1;
			}
			dropLimbs(temp, map);
			return lostWhat(map, iniNumArm, iniNumLeg);
		}
		return null;
	}

	// Drops the first Weapon in the inventory
	private void dropAWeapon(GameMap map) {
		for (Item item: this.inventory) {
			if (item.asWeapon() != null) {
				item.getDropAction().execute(this, map);
				break;
			}
		}
	}

	// If lost arms, do what with weapons on hand?
	private void droppingAWeapon(int lostArms, GameMap map) {
		if (lostArms == 2) {
			while (inventory.contains(WeaponItem.class)) {
				dropAWeapon(map);
			}
		} else if (Math.random() <= DROPPING_WEAPON_PROBABILITY){
			dropAWeapon(map);
		}
	}

	// If it loss legs, do what?
	private void lostLegs(int lostLegs) {
		if (lostLegs == 2) {
			behaviours = new Behaviour[] {new AttackBehaviour(ZombieCapability.ALIVE)};
			mobility = ZombieMobility.NO_MORE_LEGS;
		} else {
			mobility.setLostLimbPreviously(true);
			mobility = ZombieMobility.NOT_ALL_LEGS;
		}
	}

	// Drops the knocked off limbs to adjacent locations
	private void dropLimbs(ArrayList<Limb> limbArrayList, GameMap map) {
		for (Limb limb: limbArrayList) {
			Action drop = new DropLimbAction(limb, 5, "hits");
			drop.execute(this, map);
		}
	}

	/**
	 * The main logic of losing Limbs for a Zombie.
	 *
	 * @param map the map where the current Zombie is
	 * @param numOfArm initial number of Arms this Zombie had
	 * @param numOfLeg initial number of Legs this Zombie had
	 * @return how many limbs were knocked off
	 */
	private String lostWhat(GameMap map, int numOfArm, int numOfLeg) {
		int lostArms = numOfArm - zombieLimb.numberOfArms();
		int lostLegs = numOfLeg - zombieLimb.numberOfLegs();

		if (lostArms > 0 && lostLegs == 0) {  // if no legs were lost
			halvePunchProbability();
			droppingAWeapon(lostArms, map);
		} else if (lostArms == 0 && lostLegs > 0) {  // if no arms were lost
			lostLegs(lostLegs);
		} else {  // if both arms and legs were lost
			halvePunchProbability();
			droppingAWeapon(lostArms, map);
			lostLegs(lostLegs);
		}
		return String.format("lost %d limbs.", lostArms + lostLegs);
	}

	// Skips current turn
	private boolean skipTurn() {
		return mobility.isLostLimbPreviously();
	}
}
