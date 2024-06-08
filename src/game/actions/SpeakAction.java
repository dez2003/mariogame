package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Allows actors to be able to speak
 */
public class SpeakAction extends Action{
    /**
     * String storing the statement being spoken
     */
    private final String statement;

    /**
     * An Actor who is the speaker of the statement
     */
    private final Actor speaker;

    /**
     * Constructor
     * @param statement the statement that is being spoken
     * @param speaker the speaker of the statement
     */
    public SpeakAction (String statement, Actor speaker){
        this.statement = statement;
        this.speaker = speaker;
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
        return speaker + ": '"+ statement + '"';
    }

    /**
     * Gets a menu description for this action
     * @param actor The actor performing the action.
     * @return the menu description for this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + speaker;
    }
}

