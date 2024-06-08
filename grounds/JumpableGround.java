package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Capability;
import game.Status;
import game.actions.DestroyGroundAction;
import game.actions.JumpAction;

/**
 * Interface representing a Ground object that can be jumped on
 */
public abstract class JumpableGround extends Ground {

    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     */
    public JumpableGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Method to get the jump success rate of the particular JumpableGround
     * @return int success rate (%chance)
     */
    public abstract int jumpSuccessRate();

    /**
     * Getting the fall damage if the jump to this JumpableGround is unsuccessful
     * @return int damage
     */
    public abstract int fallDamage();

    /**
     * Getting the name of this JumpableGround in order to display to user in the menu
     * @return string name
     */
    public abstract String name();

    /**
     * Determines the actions allowed by an Actor on this Wall
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of action
     * @see Ground#allowableActions(Actor, Location, String)
     * @see Status#POWER
     * @see Capability#JUMP
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!location.containsAnActor()) {
            if (actor.hasCapability(Status.POWER)) {
                actionList.add(new DestroyGroundAction(location, direction));
            } else if (actor.hasCapability(Capability.JUMP)) {
                actionList.add(new JumpAction(location, direction, this));
            }
        }
        return actionList;
    }

    /**
     * Determines whether an actor can enter this Ground
     * @param actor the Actor to check
     * @return boolean
     * @see Ground#canActorEnter(Actor)
     * @see Capability#CAN_FLY
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Capability.CAN_FLY);
    }
}
