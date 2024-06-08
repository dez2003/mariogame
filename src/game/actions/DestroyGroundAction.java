package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;
import game.grounds.Dirt;

/**
 * Action that destroys a particular ground
 */
public class DestroyGroundAction extends Action {

    /**
     * constant value for the coin that is dropped when this action is undertaken
     */
    private final static int COIN_DROP_VALUE = 5;

    /**
     * Target location
     */
    protected Location moveToLocation;

    /**
     * One of the 8-d navigation
     */
    protected String direction;

    /**
     * Constructor
     * @param moveToLocation the Location that the actor is trying to move to
     * @param direction the direction in which this Location is from the actor
     */
    public DestroyGroundAction(Location moveToLocation, String direction) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
    }

    /**
     * Executing the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string describing the action to the user
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        moveToLocation.setGround(new Dirt());
        map.moveActor(actor, moveToLocation);
        moveToLocation.addItem(new Coin(COIN_DROP_VALUE));
        return menuDescription(actor);
    }

    /**
     * Providing a menu description for this action
     * @param actor The actor performing the action.
     * @return string describing this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys higher ground at " + direction;
    }
}
