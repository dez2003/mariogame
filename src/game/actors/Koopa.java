package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Capability;
import game.Status;
import game.behaviours.BehaviourPriority;
import game.behaviours.WanderBehaviour;
import game.actions.DamageAction;

/**
 * A reptilian Enemy that have a shell they retreat to when defeated.
 */
public abstract class Koopa extends Enemy {
    /**
     * the damage that the enemy does with its intrinsic weapon
     */
    protected int intrinsic_damage;

    /**
     * the verb describing the intrinsic weapon
     */
    protected String intrinsic_verb;

    /**
     * Constructor.
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        intrinsic_damage = 30;
        intrinsic_verb = "punches";
        this.addBehaviour(BehaviourPriority.WANDER_BEHAVIOUR.getValue(), new WanderBehaviour());
    }

    /**
     * Figures out what to do next
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     * @see Actor#isConscious()
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            return new DoNothingAction();
        }
        else {
            return super.playTurn(actions, lastAction, map, display);
        }
    }

    /**
     * Returns an ActionList of all the actions that can be done to the Koopa
     *
     * @param otherActor the Actor that might perform an action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     * @see Actor#isConscious()
     * @see Capability#CAN_DESTROY_SHELL
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (!isConscious()) {
            if (otherActor.hasCapability(Capability.CAN_DESTROY_SHELL)) {
                actions.add(new DamageAction(this));
            }
        }
        else {
            actions.add(super.allowableActions(otherActor, direction, map));
        }

        return actions;
    }

    /**
     * Returns display character of the Koopa
     * @return displayChar
     * @see Actor#getDisplayChar()
     */
    public char getDisplayChar() {
        return this.isConscious() ? super.getDisplayChar() : 'D';
    }

    /**
     * Returns the Koopa's IntrinsicWeapon
     * @return IntrinsicWeapon
     * @see Actor#getIntrinsicWeapon()
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsic_damage, intrinsic_verb);
    }

    /**
     * Hurts the Koopa when attacked and checks if it is not conscious after
     * @param points number of hitpoints to deduct.
     * @see Actor#hurt(int)
     * @see Actor#isConscious()
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (!isConscious()) {
            this.addCapability(Status.DORMANT);
        }
    }
}
