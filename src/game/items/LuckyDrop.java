package game.items;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;

import java.util.Collection;

/**
 * A magical item where if used, characters have the chance to be given a random item or nothing
 */
public class LuckyDrop extends Item implements Consumable {
    /**
     * A Collection of all the possible items this lucky drop could be
     */
    private final Collection<Item> possibleItems;

    /**
     * Constructor.
     * @param possibleItems the possible items that this lucky drop could be
     */
    public LuckyDrop(Collection<Item> possibleItems) {
        super("Lucky Drop", '?', true);
        this.possibleItems = possibleItems;
        addConsumeAction();
    }

    /**
     * The actor consumes the lucky drop
     * @param actor The Actor that consumes the item
     * @see Consumable#consumedBy(Actor)
     */
    @Override
    public void consumedBy(Actor actor) {
        actor.removeItemFromInventory(this);

        Item randomItem = getRandomItem();
        String statement = this + " gave " + actor;
        if (randomItem != null) {
            actor.addItemToInventory(randomItem);
            statement += " a " + randomItem + "!";
        }
        else {
            statement += " nothing :(";
        }
        new Display().println(statement);
    }

    /**
     * Method to return a random item from the list of possible items
     * @return a random item chosen from the list
     */
    private Item getRandomItem() {
        if(Math.random() < 0.6) {
            int randomIndex = (int) (Math.random() * possibleItems.size());
            int i = 0;
            for (Item item : possibleItems) {
                if (i == randomIndex) {
                    return item;
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Add a consume action to this Item
     * @see Consumable#addConsumeAction()
     */
    @Override
    public void addConsumeAction() {
        this.addAction(new ConsumeAction(this));
    }
}
