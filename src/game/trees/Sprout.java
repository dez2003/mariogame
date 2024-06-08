package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;
import game.grounds.JumpableGround;

/**
 * Class representing a sprout
 */
public class Sprout extends Tree {
    /**
     * Constant representing the chance that a Goomba will spawn on this Tree
     */
    private static final int GOOMBA_SPAWN_PERCENTAGE = 10;

    /**
     * Constant chance of being able to jump over this Tree
     */
    private static final int JUMP_SUCCESS_RATE = 90;

    /**
     * Constant damage that occurs when failing to jump over this tree
     */
    private static final int FALL_DAMAGE = 10;

    /**
     * Constant name of this Tree
     */
    private static final String NAME = "Sprout";

    /**
     * The age of the Tree in turns
     */
    private int age = 0;

    /**
     * Constructor
     */
    public Sprout(){
        super('+');
    }

    /**
     * Informs this Tree of the passing of time
     * @param location The location of the Ground
     * @see Tree#tick(Location)
     */
    @Override
    public void tick(Location location) {
        age++;
        if (age == GROWING_AGE) {
            location.setGround(new Sapling());
        }
        else {
            if (Math.random()*100 <= GOOMBA_SPAWN_PERCENTAGE && !location.containsAnActor()) {
                location.addActor(new Goomba());
            }
        }
    }

    /**
     * Getter for the jump success rate
     * @return int
     * @see JumpableGround#jumpSuccessRate()
     */
    @Override
    public int jumpSuccessRate() { return JUMP_SUCCESS_RATE; }

    /** Getter for the fall damage
     * @return int
     * @see JumpableGround#fallDamage()
     */
    @Override
    public int fallDamage() { return FALL_DAMAGE; }

    /**
     * Getter for the name of the Tree
     * @return string
     * @see JumpableGround#name()
     */
    @Override
    public String name() { return NAME; }
}
