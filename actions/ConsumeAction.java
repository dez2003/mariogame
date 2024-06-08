package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * Special Action for eating Consumable items
 */
public class ConsumeAction extends Action {
    /**
     * The item that is to be consumed
     */
    private final Consumable consumable;

    /**
     * Constructor
     *
     * @param consumable The item being consumed
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the action of consuming the consumable item by the actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A statement of the action that has been done after execution
     * @see Action#execute(Actor, GameMap)
     * @see Consumable#consumedBy(Actor)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.consumedBy(actor);
        return actor + " consumed " + consumable;
    }

    /**
     * Returns a menu description of the consume action
     *
     * @param actor The actor performing the action.
     * @return A description
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
