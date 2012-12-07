package vooga.turnbased.gameobject.mapobject;

import java.awt.Image;
import java.awt.Point;
import java.util.Set;
import vooga.turnbased.gui.GameWindow;

public class MapTeleportObject extends MapObject {
    // Point myDestination; // would need to complicate xml for this

    public MapTeleportObject (Set<String> allowableModes, String condition, Point location,
                              Image mapImage) {
        super(allowableModes, condition, location, mapImage);
        // myDestination = destination;
    }

    /**
     * Sets action taken when a player object interacts with this item object.
     * 
     * @param target MapObject that interacts with item object.
     */
    @Override
    public void interact (MapObject target) {
        super.interact(target);
        // target.setLocation(myDestination); 
        GameWindow.playSound("TeleportSound");
    }
}
