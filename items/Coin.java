package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;

/**
 * Class representing the coin item in the game
 */
public class Coin extends Item implements Resettable {
    /**
     * Integer value of the coin
     */
    private final int value;

    /**
     * Constructor
     * @param value the value of the coin
     */
    public Coin(int value){
        super("Coin", '$', true);
        this.value = value;
        registerInstance();
    }

    /**
     * Method to add this coin to the wallet via WalletManager
     */
    public void addToWallet(){
        WalletManager.getInstance().increaseBalance(this.value);
    }

    /**
     * Gets the PickUpItemAction of this item
     * @param actor the Actor picking the item up
     * @return the PickUpItemAction
     * @see Item#getPickUpAction(Actor)
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    /**
     * Resets any coins on the map
     * @param map the map that the coin is on
     * @see Resettable#resetInstance(GameMap)
     */
    @Override
    public void resetInstance(GameMap map) {
        for (int x : map.getXRange()){
            for (int y: map.getYRange()){
                if (map.at(x, y).getItems().contains(this)){
                    map.at(x, y).removeItem(this);
                    break;
                }
            }
        }
    }
}
