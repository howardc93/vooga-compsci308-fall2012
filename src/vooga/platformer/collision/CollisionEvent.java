package vooga.platformer.collision;

import java.awt.geom.Rectangle2D;
import vooga.platformer.gameobject.GameObject;
import vooga.platformer.level.Level;
import vooga.platformer.util.enums.CollisionDirection;


/**
 * For collisions happen between any pair of game objects there would be a
 * specific collision event to deal with that. This CollisionEvent is a super
 * class for all the other collision events.</br><code>CollisionEvent</code>
 * contains the two colliding <code>GameObject</code>s and the direction of the
 * collision. The subclasses will implement <code>applyCollision</code> which
 * handles the logic for resolving the collision.
 * 
 * @author Mark Govea, Bruce Fu
 * 
 */
public abstract class CollisionEvent {
    private GameObject a;
    private GameObject b;
    private CollisionDirection myDirection;

    public CollisionEvent (GameObject a, GameObject b) {
        this.a = a;
        this.b = b;
        computeDirection();
    }

    public abstract void applyCollision (Level level);

    protected GameObject a () {
        return a;
    }

    protected GameObject b () {
        return b;
    }

    protected CollisionDirection direction () {
        return myDirection;
    }

    private void computeDirection () {
        Rectangle2D intersection = a.getShape().createIntersection(b.getShape());

        // lateral collision ?
        if (intersection.getHeight() > intersection.getWidth()) {
            if (a.getX() > b.getX()) { // determine collision direction
                myDirection = CollisionDirection.RIGHT;
            }
            else {
                myDirection = CollisionDirection.LEFT;
            }
        }
        // vertical collision
        else {
            if (a.getY() > b.getY()) { // determine collision direction
                myDirection = CollisionDirection.DOWN;
            }
            else {
                myDirection = CollisionDirection.UP;
            }
        }
    }
}
