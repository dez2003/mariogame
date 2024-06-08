package game.actors;

/**
 * Interface for Actors that drink fountain water
 */
public interface Drinker {

    /**
     * Method to increase the drinker's intrinsic damage
     */
    void increaseIntrinsicDamage();

    /**
     * Method to increase the drinker's hit points
     */
    void increaseHitPoints();
}
