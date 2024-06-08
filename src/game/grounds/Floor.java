package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Checks if an actor can enter this Floor
	 * @param actor the Actor to check
	 * @return boolean
	 * @see Ground#canActorEnter(Actor)
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.ENEMY);
	}
}
