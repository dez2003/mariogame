package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.actors.Enemy;

/**
 * Class representing the NPCs behaviour to attack
 */
public class AttackBehaviour implements Behaviour {
    /**
     * The enemy that implements this behaviour
     */
    private final Enemy enemy;

    /**
     * Constructor
     * @param enemy the enemy that is implementing this behaviour
     */
    public AttackBehaviour(Enemy enemy){
        this.enemy = enemy;
    }

    /**
     * returns the Action of the actor attacking
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return AttackAction if the actor is contained in the map, null otherwise
     * @see Behaviour#getAction(Actor, GameMap)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.contains(actor)){
            for (Exit exit: map.locationOf(actor).getExits()){
                if (exit.getDestination().containsAnActor()){
                    Actor target = exit.getDestination().getActor();
                    if (target.hasCapability(Status.HOSTILE_TO_ENEMY)){
                        return new AttackAction(target, exit.getName(), enemy);
                    }
                }
            }
        }
        return null;
    }
}
