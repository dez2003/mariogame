package game.behaviours;

/**
 * Associates the different types of behaviours in the order of priority
 */
public enum BehaviourPriority {
    WANDER_BEHAVIOUR(10),
    FOLLOW_BEHAVIOUR(11),
    ATTACK_BEHAVIOUR(12);

    /**
     * The priority of the behaviour
     */
    private final int priority;

    /**
     * Sets the priority of a behaviour
     * @param newPriority The priority of the behaviour
     */
    BehaviourPriority(final int newPriority){ priority = newPriority; }

    /**
     * Returns the value of the priority
     * @return The priority of behaviour
     */
    public int getValue(){ return priority; }
}
