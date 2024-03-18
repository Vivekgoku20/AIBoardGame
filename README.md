# AIBoardGame
A turn based interactive board game, with AI as the opponent Using the following design patters:

Prototype design pattern - used for the AI to make moves and decide on the optimal move without impacting the original Board Object on which the game is palyed
Builder design patter - used to the class GameInfo, to reduce redundant declaration of constructors.

Liskov Substitution principle - We should not have subclass that does not extend all the behaviours of the parent class. 