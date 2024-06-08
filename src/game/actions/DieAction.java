package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the action to die
 */
public class DieAction extends Action {

    /**
     * Boolean value that states whether the object that dies should be removed from its map
     */
    private final boolean shouldBeRemoved;

    /**
     * Constructor
     * @param shouldBeRemoved whether the thing that dies should be removed
     */
    public DieAction(boolean shouldBeRemoved) {
        this.shouldBeRemoved = shouldBeRemoved;
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
        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : actor.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(actor, map);

        if (shouldBeRemoved) {
            map.removeActor(actor);
        }
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
        return actor + " is killed";
    }
}
