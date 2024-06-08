package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    ENEMY, // used to tell if an actor is an enemy
    TALL, // use this status to tell that current instance has "grown".
    FERTILE, // this status is for Ground that is fertile (new sprouts can spawn)
    POWER, // this status is used to tell if an actor is invincible (used PowerStar)
    DORMANT, // use this status for an actor who is not conscious but still on the map
    DEAD, // use this status to check if an actor is dead
    HAS_LIVES, // use this status to check if an actor has lives
}
