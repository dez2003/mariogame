package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Capability;
import game.actions.ConsumeAction;
import game.actors.Drinker;

import java.util.Stack;

/**
 * Singleton class that represents bottle
 */
public class Bottle extends Item implements Consumable {

    /**
     * Instance of Bottle
     */
    private static Bottle instance;

    /**
     * The Actor that drinks the Bottle
     */
    private Drinker drinker;

    /**
     * The different water types in the Bottle
     */
    private final Stack<Water> waterTypes = new Stack<>();

    /**
     * Constructor
     */
    private Bottle() {
        super("Bottle", 'b', false);
        addConsumeAction();
        this.addCapability(Capability.FILLABLE);
    }

    /**
     * Returns the instance of bottle
     * @return An instance of Bottle
     */
    public static Bottle getInstance() {
        if (instance == null) {
            instance = new Bottle();
        }
        return instance;
    }

    /**
     * Set the drinker for this bottle
     * @param drinker the drinker to set
     */
    public void setDrinker(Drinker drinker) {
        this.drinker = drinker;
    }

    /**
     * Drinker consumes the Bottle
     * @param actor The Actor that consumes the item
     * @see Consumable#consumedBy(Actor)
     */
    @Override
    public void consumedBy(Actor actor) {
        while (!waterTypes.empty()) { waterTypes.pop().addEffect(drinker); }
    }

    /**
     * Override of the toString method
     * @return new string
     * @see Item#toString()
     */
    @Override
    public String toString() {
        return "Bottle" + waterTypes;
    }

    /**
     * Adds water to the Bottle
     * @param waterType Type of water that is added into Bottle
     */
    public void addWater(Water waterType) { waterTypes.push(waterType); }

    /**
     * Adds new consume action
     * @see Consumable#addConsumeAction()
     */
    @Override
    public void addConsumeAction() {
        this.addAction(new ConsumeAction(this));
    }
}
