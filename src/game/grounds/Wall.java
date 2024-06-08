package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * Class representing the walls in the game
 */
public class Wall extends JumpableGround {

	/**
	 * Constant jump success percentage when attempting to jump the Wall
	 */
	private static final int JUMP_SUCCESS_RATE = 80;

	/**
	 * Constant fall damage when failing to jump the Wall
	 */
	private static final int FALL_DAMAGE = 20;

	/**
	 * Constant name for this Wall
	 */
	private static final String NAME = "Wall";

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Determines whether this Ground will block a thrown object
	 * @return boolean
	 * @see Ground#blocksThrownObjects()
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Getter for the jump success rate
	 * @return int
	 * @see JumpableGround#jumpSuccessRate()
	 */
	@Override
	public int jumpSuccessRate() { return JUMP_SUCCESS_RATE; }

	/**
	 * Getter for the fall damage
	 * @return int
	 * @see JumpableGround#fallDamage()
	 */
	@Override
	public int fallDamage() { return FALL_DAMAGE; }

	/**
	 * Getter for the name of this Wall
	 * @return string
	 * @see JumpableGround#name()
	 */
	@Override
	public String name() { return NAME; }
}
