#Will show how to make a new RPG game.

# Introduction #

Currently, you can make a game by solely utilizing our LevelEditor to create your own levels (maps), and then using our built-in TestMonster.java class for all your enemies. However, you could also create your own class that implements BattleObject.java instead of TestMonster.java.   The default implementation is fairly flexible in its use, but by creating your own, you could completely redesign how the in-game characters interact in BattleMode, creating any sort of Battle you would like.

We are looking into how to load up different maps as different games in the arcade, rather than simply different maps in our single game as it is currently set up.


# Details #

BattleObject.java is an abstract class you can implement to create any sort of battle you like. Implementing BattleObject will ask you to complete several functions. You can implement up to 4 different battle options, which you can write under the appropriate functions.  These will detail exactly how the actions works, and it can heal the character, attack the enemy, or use your player stats of your own invention.  Each BattleObject is constructed with a map of stats that the player will pass in when creating the object in the LevelEditor.  These can be any stats you'd like. Health does not have to be used as the stat that kills the object, it could be stamina, happiness, or whatever you like depending on the theme of your game.

**MapObject**
It could be extended if user wants to create representation of a character on the map. The most important method to override is interact(MapObject target), which is responsible for interacting with other MapObjects