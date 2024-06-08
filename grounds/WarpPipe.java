package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Capability;
import game.Status;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;
import game.reset.Resettable;

/**
 * Class representing a warping pipe in the game
 */
public class WarpPipe extends JumpableGround implements Resettable {
    /**
     * Constant jump success percentage when attempting to jump the WarpPipe
     */
    private static final int JUMP_SUCCESS_RATE = 100;

    /**
     * Constant fall damage when failing to jump the WarpPipe
     */
    private static final int FALL_DAMAGE = 0;

    /**
     * Constant name for this WarpPipe
     */
    private static final String NAME = "Warp Pipe";

    /**
     * Boolean value to determine if a piranha has been spawned or not in a particular reset
     */
    private boolean piranhaSpawned = false;

    /**
     * Location of this WarpPipe
     */
    private final Location location;

    /**
     * The connecting WarpPipe to this WarpPipe
     */
    private WarpPipe connector;

    /**
     * Constructor for a pipe without a connector yet
     * @param location the location of the WarpPipe
     */
    public WarpPipe(Location location){
        super('C');
        this.location = location;
        registerInstance();
    }

    /**
     * Constructor for a pipe with a connector
     * @param location the location of the WarpPipe
     * @param connector the connecting WarpPipe
     */
    public WarpPipe(Location location, WarpPipe connector){
        super('C');
        this.connector = connector;
        this.location = location;
        registerInstance();
    }

    /**
     * Getter for the jump success rate
     * @return int
     * @see JumpableGround#jumpSuccessRate()
     */
    @Override
    public int jumpSuccessRate() {
        return JUMP_SUCCESS_RATE;
    }

    /**
     * Getter for the fall damage
     * @return int
     * @see JumpableGround#fallDamage()
     */
    @Override
    public int fallDamage() {
        return FALL_DAMAGE;
    }

    /**
     * Getter for the name of the WarpPipe
     * @return string
     * @see JumpableGround#name()
     */
    @Override
    public String name() {
        return NAME;
    }

    /**
     * Gets the allowable actions of the pipe
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of actions
     * @see Ground#allowableActions(Actor, Location, String)
     * @see Status#HOSTILE_TO_ENEMY
     * @see Capability#JUMP
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor()){
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                actions.add(new TeleportAction(connector.getLocation().map(), this, connector));
            }
        }
        else {
            if (actor.hasCapability(Capability.JUMP)){
                actions.add(new JumpAction(location, direction, this));
            }
        }
        return actions;
    }

    /**
     * Informs this WarpPipe of the passing of time
     * @param location The location of the Ground
     * @see Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        if (!piranhaSpawned && !location.containsAnActor()) {
            location.addActor(new PiranhaPlant());
            piranhaSpawned = true;
        }
    }

    /**
     * Setter
      * @param piranhaSpawned boolean
     */
    public void setPiranhaSpawned(boolean piranhaSpawned) {
        this.piranhaSpawned = piranhaSpawned;
    }

    /**
     * Getter
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter for the connector
     * @param connector WarpPipe
     */
    public void setConnector(WarpPipe connector){
        this.connector = connector;
    }

    /**
     * Resets the trees on the map
     * @param map the map to reset the trees on
     * @see Resettable#resetInstance(GameMap)
     * @see Status#ENEMY
     */
    @Override
    public void resetInstance(GameMap map) {
        if (!map.isAnActorAt(location) || !map.getActorAt(location).hasCapability(Status.ENEMY)) {
            piranhaSpawned = false;
        }
    }

}
