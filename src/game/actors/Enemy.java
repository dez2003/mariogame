package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.Zappable;
import game.actions.AttackAction;
import game.behaviours.*;
import game.reset.Resettable;

import java.util.*;

/**
 * Abstract class representing all NPCs that are considered hostile to the player
 */
public abstract class Enemy extends Actor implements Resettable, Zappable {
    /**
     * A HashMap of all the Enemy's behaviours (priority, behaviour)
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Constructor
     *
     * @param name The name of the Enemy
     * @param displayChar The character that will be displayed on the terminal/console
     * @param hitPoints The health of the Enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        registerInstance();
        behaviours.put(BehaviourPriority.ATTACK_BEHAVIOUR.getValue(), new AttackBehaviour(this));
        this.addCapability(Status.ENEMY);
        this.registerZappable();
    }

    /**
     * Removes the Enemy when the game is resetting
     *
     * @param map The GameMap where the Enemy is located at
     * @see Resettable#resetInstance(GameMap)
     */
    @Override
    public void resetInstance(GameMap map) {
        map.removeActor(this);
    }

    /**
     * Adds a behaviour to the list
     * @param priority the priority of the behaviour
     * @param behaviour the Behaviour to add
     */
    public void addBehaviour(int priority, Behaviour behaviour){
        behaviours.put(priority, behaviour);
    }

    /**
     * Returns ActionList listing all the actions that can done to the Enemy
     *
     * @param otherActor the Actor that might perform an action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction, this));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Map<Integer, Behaviour> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());
        reverseSortedMap.putAll(behaviours);
        for(Behaviour behaviour: reverseSortedMap.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Adds follow behaviour if enemy has been attacked or attacked
     *
     * @param actor Actor that attacked
     * @param target Actor that was attacked
     * @param map GameMap where the attack took place
     * @see Status#HOSTILE_TO_ENEMY
     */
    public void attackOccurred(Actor actor, Actor target, GameMap map){
        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            this.addBehaviour(BehaviourPriority.FOLLOW_BEHAVIOUR.getValue(), new FollowBehaviour(actor));
        }
        else {
            this.addBehaviour(BehaviourPriority.FOLLOW_BEHAVIOUR.getValue(), new FollowBehaviour(target));
        }
    }

    /**
     * Halves the lives of enemy when zapped
     * @see Zappable#isZapped()
     */
    @Override
    public void isZapped() {
        this.heal(this.getMaxHp());
        this.hurt(this.getMaxHp()/2);
    }
}
