package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.brewery.Brewery;
import game.brewery.HealingPotion;
import game.brewery.PoisonPotion;
import game.brewery.PotionFactory;

/**
 * The main class for the zombie apocalypse game.
 */
public class Application {

    public static void main(String[] args) {
        World world = new ZombieWorld(new Display());

        FancyGroundFactory compoundGroundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
        

        List<String> compoundMapList = Arrays.asList(
                "................................................................................",
                "................................................................................",
                "....................................##########..................................",
                "..........................###########........#####..............................",
                "............++...........##......................########.......................",
                "..............++++.......#..............................##......................",
                ".............+++...+++...#...............................#......................",
                ".........................##..............................##.....................",
                "..........................#...............................#.....................",
                ".........................##...............................##....................",
                ".........................#...............................##.....................",
                ".........................###..............................##....................",
                "...........................####......................######.....................",
                "..............................#########.........####............................",
                "............+++.......................#.........#...............................",
                ".............+++++....................#.........#...............................",
                "...............++........................................+++++..................",
                ".............+++....................................++++++++....................",
                "............+++.......................................+++.......................",
                "................................................................................",
                ".........................................................................++.....",
                "........................................................................++.++...",
                ".........................................................................++++...",
                "..........................................................................++....",
                "................................................................................");

        List<String> townMapList = Arrays.asList(
        		"................................................................................",
                "........................................................................+++.....",
                "........................................................................+++.....",
                "..........................#############################....#............+++.....",
                "..............++++........#................................#....................",
                "..............++++........#................................#....................",
                "..............++++........#...........+....................#....................",
                "..........................#................................#....................",
                "..........................#................................#....................",
                "..........................#................................#....................",
                "..........................#................................#....................",
                "..........................#................................#....................",
                "..........................#................................#....................",
                "..........................#....#############################....................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "......................................................+++.......................",
                "......................................................+++.......................",
                "................................................................................",
                "................................................................................",
                "...........+++..................................................................",
                "...........+++..................................................................",
                "...........+++..................................................................",
                "................................................................................");

        // Create GameMaps
        GameMap compoundMap = new GameMap(compoundGroundFactory, compoundMapList);
        GameMap townMap = new GameMap(compoundGroundFactory, townMapList);

        // Add GameMaps into World
        world.addGameMap(compoundMap);
        world.addGameMap(townMap);
        
        
        // compoundMap
        // Create and add Player
        Actor player = new Player("Player", '@', 100);
        world.addPlayer(player, compoundMap.at(42, 14));
        
        // Add objects
        Vehicle compoundMapVehicle = new Vehicle();
        compoundMapVehicle.addAction(new MoveActorAction(townMap.at(0,0), "TownMap"));
        compoundMap.at(50,15).addItem(compoundMapVehicle);
        compoundMap.at(74, 20).addItem(new Plank());

        // Add Mambo Marie
        compoundMap.at(0, 0).addActor(new MamboMarie());
        
        // Add Farmer
        compoundMap.at(43, 14).addActor(new Farmer("Felix"));
        
        // Place some random humans
        String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
        		"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};

        int x, y;
        for (String name : humans) {
        	do {
        		x = (int) Math.floor(Math.random() * 20.0 + 30.0);
        		y = (int) Math.floor(Math.random() * 7.0 + 5.0);
        	} 
        	while (compoundMap.at(x, y).containsAnActor());
        	compoundMap.at(x,  y).addActor(new Human(name));	
        }
        
        // FIXME: Add more zombies!
        compoundMap.at(30, 20).addActor(new Zombie("Groan"));
        compoundMap.at(30,  18).addActor(new Zombie("Boo"));
        compoundMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
        compoundMap.at(42, 16).addActor(new Zombie("Mortalis"));
        compoundMap.at(1, 10).addActor(new Zombie("Gaaaah"));
        compoundMap.at(62, 12).addActor(new Zombie("Aaargh"));	
        
        
        // townMap
        
        // Create Brewery
        PotionFactory potionFactory = new PotionFactory(new HealingPotion(), new PoisonPotion());
        townMap.at(3, 2).setGround(new Brewery(potionFactory));
        townMap.at(75, 3).setGround(new Brewery(potionFactory));
        townMap.at(12, 19).setGround(new Brewery(potionFactory));
        townMap.at(75, 19).setGround(new Brewery(potionFactory));
        
        // Add objects
        Vehicle townMapVehicle = new Vehicle();
        townMapVehicle.addAction(new MoveActorAction(compoundMap.at(42,15), "CompoundMap"));
        townMap.at(50,16).addItem(townMapVehicle);

        // Add Sniper and Shotgun
        townMap.at(42, 15).addItem(new Sniper());
		townMap.at(50, 15).addItem(new SniperAmmunitionBox("SniperAmmunitionBox", '^', true));
		townMap.at(30, 15).addItem(new Shotgun());
		townMap.at(10, 15).addItem(new ShotgunAmmunitionBox("ShotgunAmmunitionBox", '$', true));
        
        // Add Doctor
        Actor doctor = new Healer("Doctor");
        townMap.addActor(doctor, townMap.at(28, 12));
        
        // Add Farmer
        townMap.at(43, 14).addActor(new Farmer("Felix"));
        
        // Add Humans
        humans = new String[] {"Ethan", "Weng Sheng", "Isaac", "Zi Yan",
        						"Kaya", "Shaun", "Adeline", "Kay"};
        for (String name : humans) {
        	do {
        		x = (int) Math.floor(Math.random() * 5.0 + 27.0);
        		y = (int) Math.floor(Math.random() * 7.0 + 5.0);
        	} 
        	while (townMap.at(x, y).containsAnActor());
        	townMap.at(x,  y).addActor(new Human(name));	
        }

        // Play Game
        world.run();
    }
}
