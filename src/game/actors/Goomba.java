package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.BehaviourPriority;
import game.behaviours.WanderBehaviour;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {
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
	public Goomba() {
		super("Goomba", 'g', 50);
		intrinsic_damage = 10;
		intrinsic_verb = "kicks";
		this.addBehaviour(BehaviourPriority.WANDER_BEHAVIOUR.getValue(), new WanderBehaviour());
	}

	/**
	 * Figure out what to do next.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (Math.random()*100 <= 10) {
			map.removeActor(this);
			return new DoNothingAction();
		}
		else {
			return super.playTurn(actions, lastAction, map, display);
		}
	}

	/**
	 * Returns the Goomba's IntrinsicWeapon
	 * @return IntrinsicWeapon
	 * @see Actor#getIntrinsicWeapon()
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(intrinsic_damage, intrinsic_verb);
	}

}
