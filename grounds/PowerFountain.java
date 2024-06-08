package game.grounds;

import game.items.PowerWater;
import game.items.Water;

/**
 * Fountain where the water increases intrinsic damage
 */
public class PowerFountain extends Fountain{

    /**
     * Constructor
     */
    public PowerFountain() {
        super('A');
    }

    /**
     * Getter for the water this fountain produces
     * @return the water this fountain produces
     * @see Fountain#getWater()
     */
    @Override
    public Water getWater() { return new PowerWater(); }
}
