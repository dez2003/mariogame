package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Coin;
import game.items.WalletManager;

/**
 * Class for picking up a coin
 */
public class PickUpCoinAction extends PickUpItemAction {
    /**
     * Private attribute to store the coin being picked up
     */
    private final Coin coin;

    /**
     * Constructor
     * @param coin the coin that is being picked up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    /**
     * Add the coin to the actor's wallet.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        coin.addToWallet();
        return menuDescription(actor) + ", balance = $" + WalletManager.getInstance().getBalance();
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the rock"
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + coin;
    }
}
