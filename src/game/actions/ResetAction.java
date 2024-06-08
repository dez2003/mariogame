package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Capability;
import game.reset.ResetManager;

/**
 * Action that allows a user to reset the game
 */
public class ResetAction extends Action {

    /**
     * The command key
     */
    protected String hotKey;

    /**
     * Constructor
     */
    public ResetAction() {
        this.hotKey = "r";
    }

    /**
     * Executes this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of this action
     * @see Capability#CAN_RESET
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(map);
        actor.removeCapability(Capability.CAN_RESET);
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
        return actor + " resets game";
    }

    /**
     * Gives the hotkey for this action
     * @return the hotkey character
     * @see Action#hotkey()
     */
    @Override
    public String hotkey() {
        return hotKey;
    }
}
