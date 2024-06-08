package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A factory for all consumable items in the game
 */
public interface Consumable {

    /**
     * The Actor consumes the Consumable
     * @param actor The Actor that consumes the item
     */
    void consumedBy(Actor actor);

    /**
     * The item added to ability to be consumed
     */
    void addConsumeAction();
}
