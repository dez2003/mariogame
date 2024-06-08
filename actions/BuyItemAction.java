package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import game.items.WalletManager;

/**
 * Action for buying items in the game
 */
public class BuyItemAction extends Action{
    /**
     * The Item that this action will buy
     */
    private final Item itemToBuy;

    /**
     * The price of the Item to be bought
     */
    private final int price;

    /**
     * Constructor
     *
     * @param itemToBuy the Item in which can be bought
     * @param price the price of the item as an int
     */
    public BuyItemAction (Item itemToBuy, int price){
        this.itemToBuy = itemToBuy;
        this.price = price;
    }

    /**
     * Executes the action of buying the Item by the actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string which informs of the actions and whether it is successful
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (WalletManager.getInstance().getBalance() >= price) {
            WalletManager.getInstance().reduceBalance(price);
            actor.addItemToInventory(itemToBuy);
            return actor + " bought " + itemToBuy;
        }
        return "You don't have enough coins! Your Balance is $" + WalletManager.getInstance().getBalance();
    }

    /**
     * A menu description of this action
     *
     * @param actor The actor performing the action.
     * @return string describing the action the actor can perform
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + itemToBuy + " ($" + price + ")";
    }
}
