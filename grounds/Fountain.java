package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Capability;
import game.actions.RefillAction;
import game.items.Bottle;
import game.items.Water;

/**
 * Class representing a fountain
 */
public abstract class Fountain extends Ground {
    /**
     * Constructor
     *
     * @param displayChar Character to be displayed on the map
     */
    public Fountain(char displayChar) {
        super(displayChar);
    }

    /**
     * Fills bottle with the fountain's water
     *
     * @param bottle Bottle to fill fountain water with
     */
    public void fill(Bottle bottle) { bottle.addWater(getWater()); }

    /**
     * Getter for the water that the fountain produces
     * @return the water that the fountain contains
     */
    public abstract Water getWater();

    /**
     * Returns a list of actions that can be done to the fountain
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of actions
     * @see Ground#allowableActions(Actor, Location, String)
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor() && (location.getActor().equals(actor))) {
            if (actor.hasCapability(Capability.FILLABLE)) {
                actions.add(new RefillAction(this));
            }
        }
        return actions;
    }
}
