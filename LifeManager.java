package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import game.actions.DieAction;

/**
 * Class used to manage the lives of an Actor
 */
public class LifeManager {

    /**
     * The instance of the LifeManager
     */
    private static LifeManager instance;

    /**
     * The default amount of lives
     */
    private int lives = 3;

    /**
     * Getting the instance of LifeManager
     * @return the instance
     */
    static public LifeManager getInstance() {
        if (instance == null) {
            instance = new LifeManager();
        }
        return instance;
    }

    /**
     * Gets the DieAction of an Actor who has lives
     * @param actor the actor to get the DieAction for
     * @return the DieAction
     */
    public DieAction getDieAction(Actor actor) {
        lives--;
        if (lives > 0) {
            new Display().println(actor + " loses a life! " + lives + " remaining");
            return new DieAction(false);
        }
        return new DieAction(true);
    }

    /**
     * Add an amount of lives to the tally
     * @param amount the amount of lives to add
     */
    public void addLives(int amount) { lives+=amount; }

    /**
     * Getter for the amount of lives
     * @return the amount of lives
     */
    public int getLives() { return lives; }
}
