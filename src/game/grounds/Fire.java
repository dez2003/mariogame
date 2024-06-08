package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that represents fire that burns Actors
 */
public class Fire extends Ground {

    /**
     * The number of turns the fire lasts
     */
    private int turns;

    /**
     * Constructor.
     */
    public Fire() {
        super('f');
        this.turns = 0;
    }


    /**
     * Informs the fire of the passing of time
     * @param currentLocation The location of the ground on which we lie.
     * @see Ground#tick(Location)
     */
    @Override
    public void tick(Location currentLocation) {
        if (currentLocation.containsAnActor()){
            currentLocation.getActor().hurt(20);
        }
        turns++;
        if (turns == 3) {
            currentLocation.setGround(new Dirt());
        }
    }
}
