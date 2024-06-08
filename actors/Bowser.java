package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.grounds.Fire;
import game.items.Key;

/**
 * The ultimate villain that takes Princess Peach hostage
 */
public class Bowser extends Enemy {
    /**
     * The original location at the start of the game
     */
    private final Location originalLocation;

    /**
     * Constructor
     * @param originalLocation Bowser's initial location
     */
    public Bowser(Location originalLocation) {
        super("Bowser", 'B', 500);
        this.originalLocation = originalLocation;
        this.addItemToInventory(Key.getInstance());
    }

    /**
     * Resets Bowser character
     * @param map The GameMap where the Enemy is located at
     * @see game.reset.Resettable#resetInstance(GameMap)
     */
    @Override
    public void resetInstance(GameMap map) {
        if (map.locationOf(this) != originalLocation) {
            if (map.isAnActorAt(originalLocation)) {
                map.removeActor(map.getActorAt(originalLocation));
            }
            map.moveActor(this, originalLocation);
        }
        this.resetMaxHp(500);
    }

    /**
     * Returns Bowser's intrinsic weapon
     * @return intrinsic weapon
     * @see Actor#getIntrinsicWeapon()
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punch");
    }


    /**
     * Adds follow behaviour and fire whenever Bowser attacks
     *
     * @param actor Actor that attacked
     * @param target Actor that was attacked
     * @param map GameMap where the attack took place
     * @see Enemy#attackOccurred(Actor, Actor, GameMap)
     */
    @Override
    public void attackOccurred(Actor actor, Actor target, GameMap map) {
        super.attackOccurred(actor, target, map);
        map.locationOf(actor).setGround(new Fire());
    }
}
