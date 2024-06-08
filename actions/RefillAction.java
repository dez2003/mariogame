package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Fountain;
import game.items.Bottle;

/**
 * Special action for an Actor to refill.
 */
public class RefillAction extends Action {

    /**
     * The fountain that is being refilled from
     */
    private final Fountain fountain;

    /**
     * Constructor
     *
     * @param fountain The fountain being refilled from
     */
    public RefillAction(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Executes the action of refilling the bottle from the fountain
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A statement of the action done after execution
     * @see Action#execute(Actor, GameMap)
     */
    public String execute(Actor actor, GameMap map) {
        fountain.fill(Bottle.getInstance());
        return actor + " refilled Bottle";
    }

    /**
     * Returns a menu description of this action
     *
     * @param actor The actor performing the action.
     * @return A description of the refill action
     * @see Action#menuDescription(Actor)
     */
    public String menuDescription(Actor actor) {
        return actor + " fills " + Bottle.getInstance() + " with " + fountain.getWater();
    }
}
