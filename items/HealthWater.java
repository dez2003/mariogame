package game.items;

import game.actors.Drinker;

/**
 * Special Water that heals Actors
 */
public class HealthWater extends Water {

    /**
     * Constructor
     */
    public HealthWater() {
        super("Healing Water", 'H', false);
    }

    /**
     * Increases hit points for drinker
     * @param drinker Drinker that drinks the water
     * @see Water#addEffect(Drinker)
     */
    @Override
    public void addEffect(Drinker drinker) { drinker.increaseHitPoints(); }
}
