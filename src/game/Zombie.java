package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	
	private Random rand = new Random();

	public static final double TALKING_PROBABILITY = 0.1;
	public static final double LOSE_LIMB_PROBABILITY = 0.25;
	public static final double DROPPING_WEAPON_PROBABILITY = 0.5;
	public static final String[] ZOMBIE_PHRASES = {"Braaaaains", "Zombiesss", "Arghhh"};

	private double biteProbability = 0.5;

	private Limbs zombieLimbs;

	private boolean legKnockedPreviously;
	/**
	 * Represents the current state of mobility of this Actor
	 */
	private ZombieMobility mobility;

	private Behaviour[] behaviours = {
			new PickUpItemBehaviour(Weapon.class),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	// Decide whether to use Locations or GameMap
//	List<Location> visitedLocations = new ArrayList<>();

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD, TypeOfZombieActor.ZOMBIE);
		legKnockedPreviously = false;
		mobility = ZombieMobility.MOBILE;
		setLimbs();
	}

	/**
	 * Set the number of Limbs a Zombie has.
	 */
	private void setLimbs() {
		zombieLimbs = new Limbs(4);
		for (int i = 0; i < 2; i++) {
			zombieLimbs.addLimb(new Arm(typeOfZombieActor));
			zombieLimbs.addLimb(new Leg(typeOfZombieActor));
		}
	}

	@Override
	public Weapon getWeapon() {
		Weapon weapon = super.getWeapon();
		if (weapon.verb().equalsIgnoreCase("bites")) {
			if (rand.nextDouble() <= 0.7) {  // If Zombie misses its bite
				weapon = null;
			} else {  // If Zombie successfully bite
				this.heal(5);
			}
		} else {
			if (rand.nextBoolean()) {  // If Zombie misses punch
				weapon = null;
			}
		}
		return weapon;
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (Math.random() <= biteProbability || zombieLimbs.howMany(TypeOfLimb.ARM) <= 0) {
			return new IntrinsicWeapon(20, "bites");
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

		zombieTalking(display);  // Zombie uttering words

//		Behaviour temp = new PickUpBehaviour(Weapon.class);
//		Action action1 = temp.getAction(this, map);
//		if (action1 != null) {
//			display.println(action1.execute(this, map));
//		}

		if (skipTurn()) {
			legKnockedPreviously = false;
			return new DoNothingAction();
		}

//		visitedLocations.add(map.locationOf(this));  // Check method again

		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}

		return new DoNothingAction();	
	}

	private void halvePunchProbability() {
		this.biteProbability += 0.5*(1-biteProbability);
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

	@Override
	public String loseLimbs(GameMap map) {
		String result = null;
		if (Math.random() <= LOSE_LIMB_PROBABILITY && zombieLimbs.totalNumberOfLimbs() > 0) {

			int arms = zombieLimbs.howMany(TypeOfLimb.ARM), legs = zombieLimbs.howMany(TypeOfLimb.LEG);
			List<Limb> deck = new ArrayList<>(zombieLimbs.getLimbs());
			Collections.shuffle(deck);

			for (int i = 0; i < Math.random() * zombieLimbs.totalNumberOfLimbs(); i++) {
				zombieLimbs.removeLimb(deck.get(i)).getDropAction().execute(this, map);
			}

			arms -= zombieLimbs.howMany(TypeOfLimb.ARM);
			legs -= zombieLimbs.howMany(TypeOfLimb.LEG);
			limbsSideEffects(map, arms, legs);
			result = String.format("%s lost %d arms & %d legs", this, arms, legs);
		}
		return result;
	}

//	public void knockOffLimb() {
//		// Just do whatever loseLimb did
//	}

	/**
	 * The main logic of losing Limbs for a Zombie.
	 *
	 * @param map the map where the current Zombie is
	 * @param numOfArm initial number of Arms this Zombie had
	 * @param numOfLeg initial number of Legs this Zombie had
	 * @return how many limbs were knocked off
	 */
	private void limbsSideEffects(GameMap map, int numOfArm, int numOfLeg) {
		if (numOfArm > 0 && numOfLeg == 0) {  // if no legs were lost
			halvePunchProbability();
			losingArms(numOfArm, map);
		} else if (numOfArm == 0 && numOfLeg > 0) {  // if no arms were lost
			losingLegs(numOfLeg);
		} else {  // if both arms and legs were lost
			halvePunchProbability();
			losingArms(numOfArm, map);
			losingLegs(numOfLeg);
		}
	}

	// If lost arms, do what with weapons on hand?
	private void losingArms(int lostArms, GameMap map) {
		if (lostArms == 2) {
			while (inventory.contains(WeaponItem.class)) {
				dropAWeapon(map);
			}
			behaviours = new Behaviour[] {
					new AttackBehaviour(ZombieCapability.ALIVE),
					new HuntBehaviour(Human.class, 10),
					new WanderBehaviour() };		
		} else if (Math.random() <= DROPPING_WEAPON_PROBABILITY) {
			dropAWeapon(map);
		}
	}

	// If it loss legs, do what?
	private void losingLegs(int lostLegs) {
		if (lostLegs == 2) {
			behaviours = new Behaviour[] {new AttackBehaviour(ZombieCapability.ALIVE)};
			mobility = ZombieMobility.IMMOBILE;
		} else {
			legKnockedPreviously = true;
			mobility = ZombieMobility.PARTIALLY_MOBILE;
		}
	}

	// Skips current turn
	private boolean skipTurn() {
		boolean retVal = false;
		if (mobility.noMoreLegs()) {  // If ZombieActor already has no legs
			retVal = false;
		} else if (legKnockedPreviously && mobility.notAllLegs()) {  // If ZombieActor legs were knocked off previously
			retVal = true;
		} else if (!legKnockedPreviously && mobility.notAllLegs()){  // If ZombieActor doesn't have all legs
			legKnockedPreviously = true;
		}
		return retVal;
	}

//	@Override
//	public void hurt(int points) {
//		// Override this method to implement the knockOffLimb behaviour
//		super.heal(points);
//		knockOffLimb();
//	}
}
