package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Item that represents a special Key in the game
 */
public class Key extends Item {

    /**
     * Instance of key
     */
    private static Key instance;

    /**
     * Constructor
     */
    private Key() {
        super("key", 'k', true);
    }

    /**
     * Returns an instance of key
     * @return An instance of key
     */
    public static Key getInstance() {
        if (instance == null){
            instance = new Key();
        }
        return instance;
    }
}
