package game.items;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

/**
 * A magical item where characters become invincible for a limited time.
 */
public class PowerStar extends Item implements Consumable {
    /**
     * The action for consuming a power star
     */
    private ConsumeAction consumeAction;

    /**
     * Fading duration
     */
    private int turns = 0;

    /**
     * Be able to tell if the item has been consumed or not
     */
    private boolean consumed = false;

    /**
     * Constructor
     */
    public PowerStar() {
        super("Power Star", '*', true);
        addConsumeAction();
    }

    /**
     * Functionality once the actor consumes the power star
     * @param actor The Actor that consumes the item
     * @see Consumable#consumedBy(Actor)
     * @see Status#POWER
     */
    @Override
    public void consumedBy(Actor actor) {
        consumed = true;
        if (!actor.getInventory().contains(this)) { actor.addItemToInventory(this); }
        removeAction(consumeAction);
        actor.addCapability(Status.POWER);
        actor.heal(200);
        turns = 0;
    }

    /**
     * Add a consume action to this Item if it hasn't been consumed already
     * @see Consumable#addConsumeAction() 
     */
    @Override
    public void addConsumeAction() {
        consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }

    /**
     * Calculates and informs the PowerStar of the passage of time when it is being held.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     * @see Item#tick(Location, Actor)
     * @see Status#POWER
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        turns++;
        if (turns == 10) {
            actor.removeCapability(Status.POWER);
            actor.removeItemFromInventory(this);
        }
        
        if (actor.hasCapability(Status.POWER)) {
            Display display = new Display();
            display.println(actor + " is INVINCIBLE - " + (10-turns) + " turns remaining.");
        }
    }

    /**
     * Informs the PowerStar of the passage of time if it is on the ground
     * @param currentLocation The location of the ground on which we lie.
     * @see Item#tick(Location)
     */
    @Override
    public void tick(Location currentLocation){
        if (consumed && currentLocation.getItems().contains(this)) {
            currentLocation.removeItem(this);
        }
        else {
            turns++;
            if (turns == 10) {
                currentLocation.removeItem(this);
            }
        }
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
}
