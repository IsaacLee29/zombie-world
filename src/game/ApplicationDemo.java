package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the zombie apocalypse game.
 *
 * @author Isaac Lee Kian Min.
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        World world = new ZombieWorld(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());

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
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................",
                "................................................................................");

        // Create GameMaps
        GameMap compoundMap = new GameMap(groundFactory, compoundMapList);
        // Use new GameMap instead of ZombieGameMap to prevent tick from ticking twice
        GameMap townMap = new GameMap(groundFactory, townMapList);

        // Add GameMaps into World
        world.addGameMap(compoundMap);
        world.addGameMap(townMap);

        // Create and add Player
        Actor player = new Player("Player", '@', 100);
        world.addPlayer(player, compoundMap.at(42, 14));

        // Add objects into compoundMap
        Vehicle compoundMapVehicle = new Vehicle();
        compoundMapVehicle.addAction(new MoveActorAction(townMap.at(0,0), "TownMap"));
        compoundMap.at(50,15).addItem(compoundMapVehicle);

        // Add objects into townMap
        Vehicle townMapVehicle = new Vehicle();
        townMapVehicle.addAction(new MoveActorAction(compoundMap.at(42,15), "CompoundMap"));
        townMap.at(50,15).addItem(townMapVehicle);

        // Add Mambo Marie into compoundMap
        compoundMap.at(0, 0).addActor(new MamboMarie());

//        // Add Human to compoundMap
//        compoundMap.at(31, 20).addActor(new Human("Testing"));
//
//        // Add Zombie to compoundMap
//        compoundMap.at(30, 20).addActor(new Zombie("Mortalis"));

        // Play Game
        world.run();
    }
}
