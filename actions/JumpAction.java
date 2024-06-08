package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.JumpableGround;
import game.Status;

/**
 * Action that allows an Actor to jump
 */
public class JumpAction extends Action {
    /**
     * Target location
     */
    protected Location moveToLocation;

    /**
     * One of the 8-d navigation
     */
    protected String direction;

    /**
     * boolean variable to determine if jump was successful
     */
    protected boolean jumpSuccess;

    /**
     * variable to store the ground that is being jumped over
     */
    protected JumpableGround jumpableGround;

    /**
     * Constructor
     * @param moveToLocation Location where the actor would like to move
     * @param direction the direction in which this Location is
     * @param ground the Jumpable ground that is at the Location
     */
    public JumpAction(Location moveToLocation, String direction, JumpableGround ground) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.jumpableGround = ground;
    }

    /**
     * Executing the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string describing the result of the action
     * @see Status#TALL
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.TALL)){
            jumpSuccess = true;
        }
        else {
            jumpSuccess = Math.random()*100 <= jumpableGround.jumpSuccessRate();
        }

        if (jumpSuccess){
            map.moveActor(actor, moveToLocation);
        }
        else {
            actor.hurt(jumpableGround.fallDamage());
        }
        return jumpDescription(actor);
    }

    /**
     * Return a menu description of the action
     * @param actor The actor performing the action.
     * @return a string describing the action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction + " over " + jumpableGround.name() + "(" + moveToLocation.x() + ", " + moveToLocation.y() + ")";
    }

    /**
     * Return a string describing the result of performing this action
     * @param actor the actor performing the action
     * @return a string that described the action
     */
    private String jumpDescription(Actor actor) {
        if (jumpSuccess){
            return actor + " jumps " + direction + " successfully!";
        }
        else {
            return actor + " failed to jump " + direction + " and was hurt by " + jumpableGround.fallDamage() + " hit points.";
        }
    }
}
