package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.JumpableGround;
import game.items.Coin;

/**
 * Class representing a sapling
 */
public class Sapling extends Tree {
    /**
     * Constant that represents the chance that a coin will be spawned on this Tree
     */
    private static final int COIN_SPAWN_PERCENTAGE = 10;

    /**
     * Constant representing the value of the Coin that can be dropped on this Tree
     */
    private static final int COIN_DROP_VALUE = 20;

    /**
     * Constant chance of being able to jump over this Tree
     */
    private static final int JUMP_SUCCESS_RATE = 80;

    /**
     * Constant damage that occurs when failing to jump over this tree
     */
    private static final int FALL_DAMAGE = 20;

    /**
     * Constant name of this Tree
     */
    private static final String NAME = "Sapling";

    /**
     * The age of the Tree in turns
     */
    private int age = 0;

    /**
     * Constructor
     */
    public Sapling(){
        super('t');
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
            location.setGround(new Mature());
        }
        else {
            if (Math.random()*100 <= COIN_SPAWN_PERCENTAGE) {
                location.addItem(new Coin(COIN_DROP_VALUE));
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
