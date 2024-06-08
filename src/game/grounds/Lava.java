package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Class representing lava in the game
 */
public class Lava extends Ground {
    /**
     * The amount of damage this Lava does
     */
    private static final int FLOOR_DAMAGE = 15;

    /**
     * Constructor
     */
    public Lava() {
        super('L');
    }

    /**
     * Informs the lava of the passing of time
     * @param location The location of the Ground
     * @see Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(FLOOR_DAMAGE);
        }
    }

    /**
     * Determines whether an actor can enter this Ground
     * @param actor the Actor to check
     * @return boolean
     * @see Ground#canActorEnter(Actor)
     * @see Status#ENEMY
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.ENEMY);
    }
}
