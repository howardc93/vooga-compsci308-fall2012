package vooga.platformer.leveleditor;

import java.awt.Rectangle;
import java.awt.geom.Point2D;


/**
 * Classes responsible for sprite placement
 * on a LevelBoard should consider implementing this interface.
 * 
 * @author Paul Dannenberg
 * 
 */
public interface ISpritePlacementManager {

    /**
     * Calls the sprite's getLocation() method
     * to determine if that sprite, at its
     * current location can be placed on the
     * LevelBoard.
     * 
     * @param sprite The sprite to place on the
     *        LevelBoard.
     * @return
     */
    boolean isValidPosition (Sprite sprite);

    /**
     * Selects a sprite which can now be
     * moved by the user.
     * 
     * @param toSelect The sprite that the
     *        user has selected.
     */
    void selectSprite (Sprite toSelect);

    /**
     * Selects the sprite(s) occupying the
     * position on the LevelBoard specified
     * by the parameter, point IF point lies
     * within one of the sprites currently on
     * the LevelBoard.
     * 
     * @param point Sprites whose images
     *        overlap with point will be selected
     *        by the user. The user will then be able
     *        to move them.
     * 
     * @return true if a sprite was successfully
     *         selected.
     */
    boolean selectSprite (Point2D point);

    /**
     * Clears the current selection of sprites.
     */
    void clearSelection ();

    /**
     * Removes a sprite from the LevelBoard.
     * 
     * @param toRemove The sprite to be removed.
     */
    void removeSprite (Sprite toRemove);

    /**
     * Selects any sprites whose outlines
     * overlap with the region determined
     * by the parameter, region.
     * 
     * @param region If any Sprite's outline
     *        intersects with this Rectangle, the
     *        sprite will be selected and can be
     *        controlled by user input.
     */
    void selectRegion (Rectangle region);

    /**
     * Sends all selected sprites coordinates
     * to move to. This causes all
     * SELECTED sprites to move to the
     * coordinates described by x and y
     * regardless of whether or not they are
     * 'valid' placement positions.
     * 
     * @param x x coordinate.
     * @param y y coordinate.
     */
    void follow (int x, int y);

    /**
     * Places all currently selected sprites
     * on the LevelBoard.
     * Calls the sprite's getLocation() method
     * to determine if that sprite, at its
     * current location can be placed on the
     * LevelBoard.
     */
    void positionSprites ();

}
