package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actors.Enemy;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * The enemy involved in the attack
	 */
	protected Enemy enemy;

	/**
	 * Constructor
	 *
	 * @param target The Actor to be attacked
	 * @param direction The direction where the attack is happening
	 * @param enemy the enemy involved in the attack
	 */
	public AttackAction(Actor target, String direction, Enemy enemy) {
		this.target = target;
		this.direction = direction;
		this.enemy = enemy;
	}

	/**
	 * Executes the attack on the target by the actor
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return A statement of the action that has been done after execution
	 * @see Status#POWER
	 * @see Status#TALL
	 * @see Status#DORMANT
	 * @see Status#DEAD
	 * @see Status#HAS_LIVES
	 * @see Action#execute(Actor, GameMap)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(Math.random()*100 <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		enemy.attackOccurred(actor, target, map);

		int damage = 0;
		if (actor.hasCapability(Status.POWER)) {
			target.resetMaxHp(0);
		}
		else if (!target.hasCapability(Status.POWER)){
			damage = weapon.damage();
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		target.removeCapability(Status.TALL);

		if (!target.isConscious()) {
			if (target.hasCapability(Status.DORMANT)) {
				result += System.lineSeparator() + target + " has gone into dormant state.";
			} else {
				target.addCapability(Status.DEAD);
				Location targetLocation = map.locationOf(target);
				new DieAction(true).execute(target, map);
				if (target.hasCapability(Status.HAS_LIVES)){
					map.addActor(target, targetLocation);
				}
				result += System.lineSeparator() + target + " is killed.";
			}
		}

	return result;
	}

	/**
	 * Returns a menu description of the attack action
	 *
	 * @param actor The actor performing the action.
	 * @return A description of the attack action
	 * @see Action#menuDescription(Actor)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
