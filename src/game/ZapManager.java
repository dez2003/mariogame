package game;

import java.util.ArrayList;

/**
 * Class used to manage the objects that can be 'zapped' in the game
 */
public class ZapManager {

    /**
     * The instance of ZapManager
     */
    private static ZapManager instance;

    /**
     * A list of Zappable objects
     */
    private final ArrayList<Zappable> zappables;

    /**
     * Constructor
     */
    private ZapManager() { zappables = new ArrayList<>();}

    /**
     * Get the instance of ZapManager
     * @return the instance
     */
    public static ZapManager getInstance(){
        if (instance == null) {
            instance = new ZapManager();
        }
        return instance;
    }

    /**
     * Add a zappable instance to the list
     * @param zappable the zappable object to add
     */
    public void addZappable(Zappable zappable) { zappables.add(zappable); }

    /**
     * Zaps all Zappable objects in the list of zappables
     */
    public void zapAll() {
        for (Zappable zappable: zappables){
            zappable.isZapped();
        }
    }
}
