package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.RegenerateLifeAction;

/**
 * Class representing a life regenerator item
 */
public class LifeRegenerator extends Item {

    /**
     * The amount of lives to regenerate
     */
    private final int amount;

    /***
     * Constructor.
     * @param amount the amount of lives to regenerate
     */
    public LifeRegenerator(int amount) {
        super("Life Regenerator", '♥', true);
        this.amount = amount;
        this.addAction(new RegenerateLifeAction(this));
    }

    /**
     * Getter for the amount of lives to regenerate
     * @return the amount of lives to regenerate
     */
    public int getAmount() { return amount; }
}
