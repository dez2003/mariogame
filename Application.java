package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Bowser;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.grounds.*;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.trees.Sprout;

import java.util.Arrays;
import java.util.List;

/**
 * The main class for the Mario World edu.monash.fit2099.game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new HealthFountain(), new PowerFountain());

		List<String> map = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				"............................................#...................................",
				".............................................##.................................",
				"...............................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........................................#___H####..............................",
				"........................................#____A###...............................",
				"........................................#______###..............................",
				".........................................#_____###..............................",
				".................................................##.............................",
				"...................................................#............................",
				"....................................................#...........................",
				".....................................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		GameMap gameMap = new GameMap(groundFactory, map);

		int SPROUT_COUNT = 20;
		for (int i = 0; i < SPROUT_COUNT; i++){
			int x;
			int y;
			do {
				x = (int) (Math.random()*(gameMap.getXRange().max()+1));
				y = (int) (Math.random()*(gameMap.getYRange().max()+1));
			}
			while (!gameMap.at(x, y).getGround().hasCapability(Status.FERTILE));
			gameMap.at(x, y).setGround(new Sprout());
		}

		world.addGameMap(gameMap);

		Actor mario = new Player("Player", 'm', 100, gameMap.at(42, 10));
		world.addPlayer(mario, gameMap.at(42, 10));
		gameMap.at(43,11).addActor(new Toad());
		gameMap.at(35, 11).addItem(new SuperMushroom());
		gameMap.at(35, 12).addItem(new PowerStar());

		FancyGroundFactory lavaGroundFactory = new FancyGroundFactory(new Dirt(), new Sprout());

		List<String> lavaMapString = Arrays.asList(
				"...........+...................................+....................",
				"....................................................................",
				".............................................................+......",
				".....................+..............................................",
				"....................................................................",
				"....................................................................",
				".........................................+..........................",
				"...+....................+...........................................",
				"....................................................................",
				"....................................................+...............",
				"....................+...............................................",
				"....................................................................",
				"..+.................................................................",
				"..............................+.................+...................");

		GameMap lavaMap = new GameMap(lavaGroundFactory, lavaMapString);
		world.addGameMap(lavaMap);

		int x = (int) (Math.random()*(lavaMap.getXRange().max())) + 1;
		int y = (int) (Math.random()*(lavaMap.getYRange().max()+1));
		lavaMap.at(x, y).addActor(new PrincessPeach());
		lavaMap.at(x-1, y).addActor(new Bowser(lavaMap.at(x-1, y)));

		int LAVA_COUNT = 100;
		for (int i = 0; i < LAVA_COUNT; i++) {
			do {
				x = (int) (Math.random() * (lavaMap.getXRange().max() + 1));
				y = (int) (Math.random() * (lavaMap.getYRange().max() + 1));
			}
			while (!lavaMap.at(x, y).getGround().hasCapability(Status.FERTILE));
			lavaMap.at(x, y).setGround(new Lava());
		}

		WarpPipe lavaWarpPipe = new WarpPipe(lavaMap.at(0, 0));
		lavaMap.at(0, 0).setGround(lavaWarpPipe);

		int WARP_PIPE_COUNT = 5;
		for (int i = 0; i < WARP_PIPE_COUNT; i++){
			do {
				x = (int) (Math.random()*(gameMap.getXRange().max()+1));
				y = (int) (Math.random()*(gameMap.getYRange().max()+1));
			}
			while (!gameMap.at(x, y).getGround().hasCapability(Status.FERTILE));
			gameMap.at(x, y).setGround(new WarpPipe(gameMap.at(x, y), lavaWarpPipe));
		}


		world.run();
	}
}
