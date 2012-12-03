package vooga.turnbased.gamecore.gamemodes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

import vooga.turnbased.gamecore.GameManager;
import vooga.turnbased.gui.GameWindow;

/**
 * Mode designed to manage what happens when the game ends. 
 * @author RPG team
 *
 */
public class GameOverMode extends GameMode {

    /**
     * Constructor for GameOverMode.
     * @param gm GameManager to which this mode belongs.
     * @param modeName String name of mode.
     * @param involvedIDs List of IDs of involved sprites.
     */
    public GameOverMode (GameManager gm, String modeName, List<Integer> involvedIDs) {
        super(gm, modeName, involvedIDs);
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
        playModeEntranceSound("GameOverSound");
    }

    @Override
    public void initialize () {
    }

    @Override
    public void paint (Graphics g) {
        Image background = GameWindow.importImage("GameOverImage");
        Dimension paneDim = getGameManager().getPaneDimension();
        g.drawImage(background, 0, 0, paneDim.width, paneDim.width, null);
    }

    @Override
    public void update () {
    }

    @Override
    public void processMouseInput (int mousePressed, Point myMousePosition, int myMouseButton) {
    }

}
