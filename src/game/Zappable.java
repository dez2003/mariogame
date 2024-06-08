package game;

/**
 * Interface to enable an object of the game to be affected by being electrocuted by lightning
 */
public interface Zappable {

    /**
     * Method to do the required actions when an object is zapped
     */
    void isZapped();

    /**
     * Method to register the object as Zappable
     */
    default void registerZappable() { ZapManager.getInstance().addZappable(this);}
}
