package game.actors;

import game.Capability;

/**
 * Koopa that can fly
 */
public class FlyingKoopa extends Koopa {

    /**
     * Constructor
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Capability.CAN_FLY);
    }
}
