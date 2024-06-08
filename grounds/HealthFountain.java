package game.grounds;

import game.items.HealthWater;
import game.items.Water;

/**
 * Fountain where the water heals Actors
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor
     */
    public HealthFountain() { super('H'); }

    /**
     * Getter for the water this fountain produces
     * @return the water this fountain produces
     * @see Fountain#getWater()
     */
    @Override
    public Water getWater() { return new HealthWater(); }
}
