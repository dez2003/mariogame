package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the action to save an actor
 */
public class SaveAction extends Action {

    /**
     * The savee that is being saved
     */
    private final Actor savee;

    /**
     * The message to speak once saved
     */
    private final String message;

    /**
     * Constructor
     * @param savee the actor being saved
     * @param message the message to be spoken once saved
     */
    public SaveAction(Actor savee, String message) {
        this.savee = savee;
        this.message = message;
    }

    /**
     * Executes this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the result of this action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        new Display().println(new SpeakAction(message, savee).execute(savee, map));
        map.removeActor(actor);
        return menuDescription(actor) + "! " + actor + " wins!";
    }

    /**
     * Gets a menu description for this action
     * @param actor The actor performing the action.
     * @return the menu description for this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " saves " + savee;
    }
}
