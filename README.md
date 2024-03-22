# AIBoardGame
A turn based interactive board game, with AI as the opponent Using the following design patters:

Prototype design pattern - used for the AI to make moves and decide on the optimal move without impacting the original Board Object on which the game is played
Builder design patter - used to the class GameInfo, to reduce redundant declaration of constructors.

Liskov Substitution principle - We should not have subclass that does not extend all the behaviours of the parent class.

Momento Design Patter - Storing history of the boards, so that we can undo/replay the game from a point in time.
AI Logic:
Using chain of responsibility through singleton design pattern, hence making the AI logic more readable and extensible so that the chain of rules can be easily modified in isolation.
