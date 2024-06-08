package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ZapManager;

/**
 * Class representing a zapping action
 */
public class ZapAction extends Action {
    /**
     * The item that causes the zap
     */
    private final Item zapItem;

    /**
     * Constructor
     * @param zapItem item that causes the zap
     */
    public ZapAction(Item zapItem){
        this.zapItem = zapItem;
    }

    /**
     * Executes this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of this action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ZapManager.getInstance().zapAll();
        actor.removeItemFromInventory(zapItem);
        return menuDescription(actor);
    }

    /**
     * Gives a menu description for this action
     * @param actor The actor performing the action.
     * @return the description of this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + zapItem;
    }
}
