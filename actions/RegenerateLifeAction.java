package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.LifeManager;
import game.items.LifeRegenerator;

/**
 * Class representing the action to regenerate lives
 */
public class RegenerateLifeAction extends Action {

    /**
     * The regenerator that initiated this action
     */
    private final LifeRegenerator regenerator;

    /**
     * Constructor
     * @param regenerator the item that triggered this action
     */
    public RegenerateLifeAction(LifeRegenerator regenerator) {
        this.regenerator = regenerator;
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
        actor.removeItemFromInventory(regenerator);
        LifeManager.getInstance().addLives(regenerator.getAmount());
        return menuDescription(actor) + " (" + LifeManager.getInstance().getLives() + " total)";
    }

    /**
     * Gives a menu description for this action
     * @param actor The actor performing the action.
     * @return the description of this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        String word = regenerator.getAmount() == 1 ? " life" : " lives";
        return actor + " regenerates " + regenerator.getAmount() + word;
    }
}
