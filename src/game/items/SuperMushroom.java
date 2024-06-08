package game.items;

import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

/**
 * A magical item where characters can jump freely and increase their max HP.
 */
public class SuperMushroom extends Item implements Consumable {

    /**
     * Boolean representing if the item has been consumed
     */
    private boolean consumed = false;

    /**
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        addConsumeAction();
    }

    /**
     * The actor consumes the super mushroom
     * @param actor The Actor that consumes the item
     * @see Consumable#consumedBy(Actor)
     * @see Status#TALL
     */
    @Override
    public void consumedBy(Actor actor) {
        consumed = true;
        actor.removeItemFromInventory(this);
        actor.increaseMaxHp(50);
        actor.addCapability(Status.TALL);
    }

    /**
     * Add a consume action to this Item
     * @see Consumable#addConsumeAction()
     */
    @Override
    public void addConsumeAction() {
        this.addAction(new ConsumeAction(this));
    }

    /**
     * Gets the action to drop the item if any
     * @param actor to perform the drop
     * @return the DropItemAction if any
     * @see Item#getDropAction(Actor)
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Informs the SuperMushroom of the passage of time if it is on the ground
     * @param currentLocation The location of the ground on which we lie.
     * @see Item#tick(Location)
     */
    @Override
    public void tick(Location currentLocation) {
        if (consumed && currentLocation.getItems().contains(this)) {
            currentLocation.removeItem(this);
        }
    }
}
