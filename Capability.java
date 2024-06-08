package game;

/**
 * This enum is used to give capabilities to objects
 */
public enum Capability {
    JUMP, // used for the ability for the actor to be able to jump
    CAN_RESET, // used for the ability to reset the edu.monash.fit2099.game
    CAN_DESTROY_SHELL, // used for the ability to destroy the Koopa's shell
    CAN_BUY, // used for the ability to buy items from Toad
    CAN_FLY, // used for the ability to fly (flying Koopa)
    FILLABLE, // use this status to check if the ground is a fountain
}
