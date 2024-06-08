package game.items;

/**
 * Class managing a wallet's materials
 */
public class WalletManager {
    /**
     * The balance of the wallet
     */
    private int balance;

    private static WalletManager instance;

    /**
     * Constructor
     */
    private WalletManager() {
        this.balance = 0;
    }

    public static WalletManager getInstance() {
        if (instance == null){
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Getter for the balance
     * @return int
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Increases the balance
     * @param amount the amount ot increase by
     */
    public void increaseBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Reduces the balance
     * @param value the value to reduce by
     */
    public void reduceBalance(int value) {
        this.balance -= value;
    }
}
