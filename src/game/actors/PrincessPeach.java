package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.SaveAction;
import game.items.Key;

/**
 * Princess that gets taken hostage by Bowser
 */
public class PrincessPeach extends Actor {

    /**
     * Constructor
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 0);
    }

    /**
     * Returns a list of Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     * @see Status#HOSTILE_TO_ENEMY
     * @see SaveAction
     * @see Key
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.getInventory().contains(Key.getInstance())) {
            actions.add(new SaveAction(this, "Victory at last!"));
        }
        return actions;
    }

    /**
     * Selects and returns an action that Actor does in this turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to be performed
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) { return new DoNothingAction(); }
}
