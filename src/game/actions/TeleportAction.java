package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.WarpPipe;

/**
 * Class representing the action to teleport between maps
 */
public class TeleportAction extends Action {

    /**
     * The GameMap that this teleport will go to
     */
    private final GameMap to;

    /**
     * The pipe at the new map
     */
    private final WarpPipe toPipe;

    /**
     * The pipe used to teleport
     */
    private final WarpPipe fromPipe;

    /**
     * Constructor
     * @param to where to teleport to
     * @param fromPipe pipe teleporting from
     * @param toPipe pipe teleporting to
     */
    public TeleportAction(GameMap to, WarpPipe fromPipe, WarpPipe toPipe){
        this.to = to;
        this.toPipe = toPipe;
        this.fromPipe = fromPipe;
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
        toPipe.setConnector(fromPipe);
        map.removeActor(actor);
        if (to.isAnActorAt(toPipe.getLocation())){
            to.removeActor(to.getActorAt(toPipe.getLocation()));
            toPipe.setPiranhaSpawned(false);
        }
        to.addActor(actor, toPipe.getLocation());
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
        return actor + " teleports";
    }
}
