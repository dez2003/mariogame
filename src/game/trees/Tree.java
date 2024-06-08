package game.trees;

import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Dirt;
import game.grounds.JumpableGround;
import game.reset.Resettable;

/**
 * Abstract class representing a tree in the game
 */
public abstract class Tree extends JumpableGround implements Resettable {

    /**
     * Constant age at which a Tree will grow
     */
    protected static final int GROWING_AGE = 10;

    /**
     * Constant percentage change that when reset, a Tree will be removed from the game
     */
    protected static final int RESET_PERCENTAGE = 50;

    /**
     * Constructor
     * @param displayChar the display character of the tree
     */
    public Tree(char displayChar) {
        super(displayChar);
        registerInstance();
    }

    /**
     * Resets the trees on the map
     * @param map the map to reset the trees on
     * @see Resettable#resetInstance(GameMap)
     */
    @Override
    public void resetInstance(GameMap map) {
        if (Math.random()*100 <= RESET_PERCENTAGE){
            boolean breakOuter = false;
            for (int x : map.getXRange()){
                for (int y: map.getYRange()){
                    if (map.at(x, y).getGround() == this) {
                        map.at(x, y).setGround(new Dirt());
                        breakOuter = true;
                        break;
                    }
                }
                if (breakOuter) { break; }
            }
        }
    }
}
