package game.trees;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.FlyingKoopa;
import game.actors.NormalKoopa;
import game.grounds.Dirt;
import game.grounds.JumpableGround;

import java.util.ArrayList;

/**
 * Class representing a mature tree
 */
public class Mature extends Tree {
    /**
     * Constant representing the age at which it can start producing new sprouts
     */
    private static final int NEW_SPROUT_AGE = 5;

    /**
     * Constant spawn percentage of a Koopa on this Tree
     */
    private static final int KOOPA_SPAWN_PERCENTAGE = 15;

    /**
     * Constant percentage of the Tree to die
     */
    private static final int DIE_PERCENTAGE = 10;

    /**
     * Constant chance of being able to jump over this Tree
     */
    private static final int JUMP_SUCCESS_RATE = 70;

    /**
     * Constant damage that occurs when failing to jump over this tree
     */
    private static final int FALL_DAMAGE = 30;

    /**
     * Constant name of this Tree
     */
    private static final String NAME = "Mature";

    /**
     * The age of the Tree in turns
     */
    private int age = 0;

    /**
     * Constructor
     */
    public Mature(){
        super('T');
    }

    /**
     * Informs this Tree of the passing of time
     * @param location The location of the Ground
     * @see Tree#tick(Location)
     * @see Status#FERTILE
     */
    @Override
    public void tick(Location location) {
        age++;
        if (age % NEW_SPROUT_AGE == 0){
            GameMap map = location.map();
            ArrayList<Location> fertileLocations = new ArrayList<>();
            for (int x: map.getXRange()){
                for (int y: map.getYRange()){
                    Location possibleFertileLocation = map.at(x, y);
                    if (possibleFertileLocation.getGround().hasCapability(Status.FERTILE)){
                        fertileLocations.add(possibleFertileLocation);
                    }
                }
            }
            int randIndex = (int) (Math.random()*fertileLocations.size());
            fertileLocations.get(randIndex).setGround(new Sprout());
        }

        if (Math.random()*100 <= KOOPA_SPAWN_PERCENTAGE && !location.containsAnActor()){
            //has 50% chance of either spawning a normal or flying Koopa
            if (Math.random()*100 <= 50) {
                location.addActor(new NormalKoopa());
            }
            else {
                location.addActor(new FlyingKoopa());
            }
        }

        if (Math.random()*100 <= DIE_PERCENTAGE){
            location.setGround(new Dirt());
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
