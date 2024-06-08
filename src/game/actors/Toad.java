package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Capability;
import game.Status;
import game.actions.BuyItemAction;
import game.actions.SpeakAction;
import game.items.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing the character Toad
 */
public class Toad extends Actor{

    /**
     * Hashmap of the items that Toad sells <item to sell, price of item>
     */
    private final HashMap<Item, Integer> itemsSelling = new HashMap<>() {{
        put(new Wrench(), 200);
        put(new SuperMushroom(), 400);
        put(new PowerStar(), 600);
        put(new Lightning(), 800);
    }};

    /**
     * An ArrayList of phrases that Toad can speak
     */
    private final ArrayList<String> phrasesSpoken = new ArrayList<>(){{
        add("You might need a wrench to smash Koopa's hard shells.");
        add("You better get back to finding the Power Stars.");
        add("The Princess is depending on you! You are our only hope.");
        add("Being imprisoned in these walls can drive a fungus crazy :(");
    }};

    /**
     * Constructor
     */
    public Toad(){
        super("Toad", 'O', 999999999);
    }

    /**
     * Returns an ActionList of all the actions that can be done to the Koopa
     *
     * @param otherActor the Actor that might perform an action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     * @see Capability#CAN_BUY
     * @see Status#HAS_LIVES
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        HashMap<Item, Integer> itemsToSell = new HashMap<>(itemsSelling);
        if (otherActor.hasCapability(Status.HAS_LIVES)) {
            itemsToSell.put(new LifeRegenerator(1), 400);
        }
        if (otherActor.hasCapability(Capability.CAN_BUY)) {
            itemsToSell.forEach((item ,price) -> actions.add(new BuyItemAction(item, price)));
            actions.add(new BuyItemAction(new LuckyDrop(itemsToSell.keySet()), 200));
        }
        actions.add(new SpeakAction(getStatement(otherActor), this));
        return actions;
    }

    /**
     * Figures out what to do next
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action on what to do next
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) { return new DoNothingAction(); }

    /**
     * Figures out which statement Toad will speak if spoken to
     * @param actor the Actor that is speaking to Toad
     * @return the statement that Toad will speak
     * @see Status#POWER
     * @see Capability#CAN_DESTROY_SHELL
     */
    private String getStatement(Actor actor) {
        ArrayList<String> phrasesCopy = new ArrayList<>(phrasesSpoken);
        if (actor.hasCapability(Status.POWER)){
            phrasesCopy.remove(1);
        }
        if (actor.hasCapability(Capability.CAN_DESTROY_SHELL)){
            phrasesCopy.remove(0);
        }
        int index = (int) (Math.random()*phrasesCopy.size());
        return phrasesCopy.get(index);
    }
}