package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.ZapAction;

/**
 * Class representing the lightning item
 */
public class Lightning extends Item {
    /**
     * Constructor.
     */
    public Lightning() {
        super("Lightning", '⚡', true);
        this.addAction(new ZapAction(this));
    }
}
