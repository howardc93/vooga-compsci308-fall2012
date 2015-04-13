# Introduction #

Using these steps, you can make a platformer game using our engine.


# Details #


1. Design your level in the level editor. Start the level editor by running the PlatformerFrame class in the vooga.platformer.leveleditor package. Then follow these steps:

a) Place all of the objects you want to put in the level (bricks, enemies, and a player) by clicking the appropriate item on the bottom of the screen

b) Add win/loss conditions. For each of the available conditions, a JFileChooser will come up asking you to select another level. This is the level that will load after the condition is satisfied.

--If you are working on level 1, and you want the game to load level 2 when the player satisfies the condition, just select level 2 (this means you must create levels in reverse order, i.e. have level 2 done before level 1).

--To make a condition represent "winning the game," you could have it point to a level that just has a congratulatory message on the screen, or to make a condition represent "game over," you could have it point to a level with a game over screen.

c) Add LevelPlugins. Add a BackgroundPainter if you want a background, or a FixedMessagePainter if you want to paint a multi-line message in a static spot on the screen.

2.Write a main class, implementing IArcadeGame, to turn your level into a real game. In the runGame() method of this class, do the following:

a) Instantiate a new implementation of InputInitializer. The InputInitializer is an interface that is responsible for setting up the input of the game. You can use our default implementation, KeyboardControllerOnePlayerInputInitializer, which uses the input team's work to set up a basic keyboard controller: use left and right to move, use up to jump, and use spacebar to shoot.

b) Instantiate a new PlatformerController. The PlatformerController implements JPanel and is responsible for running the game and painting it to the screen. Give its constructor the name of the first level of your game and the InputInitializer you instantiated in step a) above.

c) Stick that PlatformerController in a JFrame and you're good to go! Don't forget to pack your JFrame and set it visible.

Here is some sample code that takes care of all three of these steps:
```
JFrame frame = new JFrame("Demo Game");
PlatformerController controller = new PlatformerController("src/vooga/platformer/data/level1.xml", new KeyControllerOnePlayerInputInitializer());

frame.getContentPane().add(controller);
frame.pack();
frame.setVisible(true);
```