package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Drinker;

/**
 * Abstract class that represents water items
 */
public abstract class Water extends Item {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Water(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Method to add the effect the water has on the drinker
     * @param drinker Drinker that drinks the water
     */
    public abstract void addEffect(Drinker drinker);
}
