package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

/**
 * Separate action for damaging an unconscious actor
 */
public class DamageAction extends Action {
    /**
     * The Actor that is to be damaged
     */
    private final Actor target;

    /**
     * Constructor
     *
     * @param target The Actor to be damaged
     */
    public DamageAction(Actor target) {
        this.target = target;
    }

    /**
     * Executes the action of damaging the Koopa's shell by the actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A statement of the action that has been done after execution
     * @see Action#execute(Actor, GameMap)
     */
    public String execute(Actor actor, GameMap map) {

        if (Math.random()*100 > actor.getWeapon().chanceToHit()) {
            return actor + " misses " + target + ".";
        }
        map.locationOf(target).addItem(new SuperMushroom());
        map.removeActor(target);

        return menuDescription(actor);
    }

    /**
     * Returns a menu description of the damage action
     *
     * @param actor The actor performing the action.
     * @return A description of the damage action
     * @see Action#menuDescription(Actor)
     */
    public String menuDescription(Actor actor) {
        return actor + " destroys " + target;
    }
}
