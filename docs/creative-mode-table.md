# Requirement 4

**Title**:
Adding Lives

**Description**:
Mario will start with 3 lives and when Mario dies, his lives will be reduced by one and Mario will drop all his items in the location he died. If Mario goes back to that location, he can pick up the dropped items. The dropping of item isolated to a new DieAction class. Mario's Lives will be managed via a Life Manager Class.

**Explanation why it adheres to SOLID principles** (WHY):

-S: This will conform to the Single-responsibility Principle as a class will be created called DieAction which will only be responsible for implementing what happens when an Actor dies, as well as having LifeManager deal with the lives and losing them.

-O: This will conform to the Open-Closed Principle as this class will not change any of the existing code, instead adding DieAction that extends Action to make it conform to the current system. This enables this new feature of adding lives without modifying any parts of the already built system.

-L: This will conform to the Liskov-Substitution Principle as the DieAction class can be treated directly as an Action, due to the overriding of the menuDescription and execute methods. 

-I: This will conform to the Interface Segregation Principle as it will not implement any methods that are not required for the use of this class.

-D: This will conform to the Dependency Inversion Principle as DieAction is created to rely on the abstracted Actor class rather than specifically for the Player. 

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                        |
|-------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | This feature will use three classes from the engine package: Actor, Action and Item. DieAction will extend Action and will use Actor and Item. |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | This feature will re-use the reset game feature from Assignment 2.                                                                             |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | This feature uses the Resettable interface in order to reset the game once the player loses a life.                                            |
| Must use existing or create new capabilities                                                                            | Uses existing Capability 'CAN_RESET' and adds new Status 'DEAD' and 'HAS_LIVES'.                                                               |

---

# Requirement 5

**Title**:
Toad Store Upgrade

**Description**:
Toad will be given 3 new items he sells. One will be a LifeRegenerator which gives Mario an extra life, the second will be a lightning item which zaps all objects that can be zapped, and the last item will be a lucky drop which when used gives the Actor a random one of the other items Toad sells (or nothing).

- LifeRegenerator: LifeRegenerator adds a new RegenerateLifeAction which when executed will add a life to the Actor using the LifeManager.
- LuckyDrop: This item will contain a Collection of Items, when the item is consumed, using math.random, an item from the list (or nothing) will be added to the Actor's inventory.
- LightningItem: a new interface Zappable will be implemented for all objects in the game that can be zapped. An instance of any object that can be zapped will then be added to a list in ZapManager. The LightningItem creates a new action ZapAction, that when executed will call the ZapManager to zap all the Zappable objects.

**Explanation why it adheres to SOLID principles** (WHY):

-S: This will conform to the single-responsibility principle as each new item will be in charge of its own features/functionality rather than having a different class have the functionality for these items embedded in them. E.g. the LuckyDrop is responsible for picking the random item to provide the Actor rather than having that functionality in Toad.

-O: This conforms to the Open-Closed principle as the new items are added as children of Item, and the new actions are added of children of Action, therefore no existing code needs to be modified as these new classes are able to work in the system.

-L: This conforms to the Liskov-Substitution Principle as the LuckyDrop can be swapped with any other Consumable and not break the system, as well as the LightningItem or LifeRegenerator can be swapped with any other item without the system breaking.

-I: This conforms to interface Segregation Principle as no redundant methods from implemented interface will be present. As the LuckyDrop uses both methods provided by the Consumable interface.

-D: This conforms to the Dependency Inversion Principle as the ZapManager is reliant on the Zappable interface, rather than the concrete classes that implement the interface.

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                                                                                                    |
|-------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | This feature will use 3 classes from the engine package: Item, Action and Actor. LifeRegenerator, LightingItem and LuckyDrop will extend Item and interact with Actor, these items also have associated new (and existing) Action classes. |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | This feature will re-use Toad and the selling of items from Assignment 2.                                                                                                                                                                  |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | LuckyDrop utilises the Consumable interface, whilst a new Zappable interface is created.                                                                                                                                                   |
| Must use existing or create new capabilities                                                                            | This feature will use existing Capability 'CAN_BUY' as well as new Status 'HAS_LIVES'                                                                                                                                                      |

