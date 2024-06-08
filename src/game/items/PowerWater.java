package game.items;

import game.actors.Drinker;

/**
 * Special Water that increases intrinsic damage
 */
public class PowerWater extends Water {

    /**
     * Constructor
     */
    public PowerWater() {
        super("Power Water", 'A', false);
    }

    /**
     * Increases the intrinsic damage of the drinker
     * @param drinker Drinker that drinks the water
     * @see Water#addEffect(Drinker)
     */
    @Override
    public void addEffect(Drinker drinker) {
        drinker.increaseIntrinsicDamage();
    }
}
