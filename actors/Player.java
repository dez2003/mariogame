package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Capability;
import game.LifeManager;
import game.Status;
import game.actions.ResetAction;
import game.items.Bottle;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, Drinker {

	/**
	 * Menu item for the player
	 */
	private final Menu menu = new Menu();

	/**
	 * Damage of intrinsic weapon
	 */
	protected int intrinsicDamage = 5;

	/**
	 * Original location player was placed
	 */
	private final Location originalLocation;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location originalLocation) {
		super(name, displayChar, hitPoints);
		Bottle.getInstance().setDrinker(this);
		addItemToInventory(Bottle.getInstance());
		this.originalLocation = originalLocation;
		this.addCapability(Capability.CAN_RESET);
		addInitialCapabilities();
		registerInstance();

	}

	/**
	 * Adds the initial (always available capabilities) to the player
	 */
	private void addInitialCapabilities(){
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Capability.JUMP);
		this.addCapability(Capability.CAN_BUY);
		this.addCapability(Status.HAS_LIVES);
	}

	/**
	 * Figures out what to do next
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return an action on what to do next
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 * @see Status#DEAD
	 * @see Capability#CAN_RESET
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (this.hasCapability(Capability.CAN_RESET)) {
			actions.add(new ResetAction());
		}

		if (this.hasCapability(Status.DEAD)) {
			this.removeCapability(Status.DEAD);
			originalLocation.map().moveActor(this, originalLocation);
			ResetManager.getInstance().run(originalLocation.map());
			this.addCapability(Capability.CAN_RESET);
			return LifeManager.getInstance().getDieAction(this);
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Gets the display character of the player
	 * @return the display character
	 * @see Actor#getDisplayChar()
	 * @see Status#TALL
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Resets the Player
	 * @param map the GameMap the Player is located
	 * @see Resettable#resetInstance(GameMap)
	 */
	@Override
	public void resetInstance(GameMap map) {
		this.resetMaxHp(100);
		for (Enum<?> capability: capabilitiesList()){
			removeCapability(capability);
		}
		addInitialCapabilities();
	}

	/**
	 * Returns intrinsic weapon
	 * @return Player's intrinsic weapon
	 * @see Actor#getIntrinsicWeapon()
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(intrinsicDamage, "punches");
	}

	/**
	 * Increases intrinsic damage
	 * @see Drinker#increaseIntrinsicDamage()
	 */
	@Override
	public void increaseIntrinsicDamage() {
		intrinsicDamage += 15;
	}

	/**
	 * Increases hit points
	 * @see Drinker#increaseHitPoints()
	 */
	@Override
	public void increaseHitPoints() {
		heal(50);
	}
}
