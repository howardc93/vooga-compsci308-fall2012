package vooga.turnbased.gameobject.mapobject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import vooga.turnbased.gamecore.MapMode;


/**
 * items that could be picked up on the map
 * 
 * @author rex
 * 
 */
public class MapItemObject extends MapObject {
	
	private static final double SIZE_RELATIVE_TO_TILE = 0.34;

    /**
     * Creates the MapItemObject that will be used in MapMode.
     * @param id Integer ID associated with the MapItemObject.
     * @param event GameEvent that can be passed to GameManager.
     * @param location Location of object on the map.
     * @param mapImage Image of the object.
     * @param mapMode MapMode in which the object exists.
     */
    public MapItemObject (int id, String event, Point location, Image mapImage,
            MapMode mapMode) {
        super(id, event, location, mapImage, mapMode);

    }

    /**
     * Sets action taken when a player object interacts with this item object.
     * @param target MapObject that interacts with item object.
     */
    public void interact (MapObject target) {
        if (target instanceof MapPlayerObject) {
            setVisible(false);
        }
    }

    @Override
    public void paint (Graphics g) {
        paintInProportion(g, myOffset, myTileDimensions, SIZE_RELATIVE_TO_TILE);
    }
}
