package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing a Piranha plant
 */
public class PiranhaPlant extends Enemy {

    /**
     * Constructor
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
    }

    /**
     * Returns intrinsic weapon
     * @return Piranha Plant's intrinsic weapon
     * @see edu.monash.fit2099.engine.actors.Actor#getIntrinsicWeapon()
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() { return new IntrinsicWeapon(90, "chomps"); }

    /**
     * Resets Piranha Plant
     * @param map The GameMap where the Enemy is located
     * @see game.reset.Resettable#resetInstance(GameMap)
     */
    public void resetInstance(GameMap map) {
        this.increaseMaxHp(50);
        this.heal(this.getMaxHp());
    }
}
