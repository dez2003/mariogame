package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Capability;

/**
 * Class representing the wrench weapon
 */
public class Wrench extends WeaponItem {

    /**
     * Constructor.
     * @see Capability#CAN_DESTROY_SHELL
     */
    public Wrench() {
        super("Wrench", 'w', 50, "whacks", 80);
        this.addCapability(Capability.CAN_DESTROY_SHELL);
    }
}
