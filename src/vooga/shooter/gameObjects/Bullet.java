package vooga.shooter.gameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import vooga.shooter.graphics.Canvas;

/**
 * Bullet class will have all the info needed to
 * represent a bullet shot by either a player or enemy.
 * Each bullet will have its own owner Sprite and a damage
 * parameter.
 *
 * @author Jesse Starr
 * (add your own name as you edit)
 */
public class Bullet extends Sprite{
    private int myDamage;
    private Sprite myOwner;

    /**
     * Constructs a bullet to be shown on the screen.
     * @param position the starting position of the bullet
     * @param size the size of the image to use
     * @param image the image of the bullet
     * @param velocity the starting velocity of the bullet
     * @param damage the damage that the bullet will do (to enemy or player)
     */
    public Bullet (Point position, Dimension size, Image image, Point velocity,
            int damage) {
        super(position, size, image, velocity);
        myDamage = damage;
    }

    /**
     * This will be called after the bullet is done moving.
     * Will make the bullet do something extra after motion
     * (e.g. maybe some particle effects, or other graphical
     * stuff, or something else cool).
     */
    public void continueUpdate(Canvas c) {
        
    }

    /**
     * Returns the damage this bullet will do to another sprite.
     * @return myDamage
     */
    public int getDamage() {
        return myDamage;
    }

    /**
     * Sets the owner of this bullet (e.g. the player or
     * an enemy sprite).
     * @param s the owner of this bullet
     */
    public void setOwner(Sprite s) {
        myOwner = s;
    }

    /**
     * Returns the owner of this bullet.
     * (could be player or enemy).
     * @return myOwner
     */
    public Sprite getOwner() {
        return myOwner;
    }

    /**
     * Describes what happens when the bullet
     * collides with a bullet.
     * @param b the bullet that this bullet is
     * colliding with
     */
    public void collide (Bullet b) {
        
    }

    /**
     * Describes what happens when the bullet
     * collides with a player (will only
     * happen in multiplayer).
     * @param p the player that this bullet
     * is colliding with
     */
    public void collide (Player p) {
        
    }

    /**
     * Describes what happens when this bullet
     * collides with an enemy.
     * @param e the enemy that this bullet is
     * colliding with
     */
    public void collide (Enemy e) {
        
    }

    /**
     * Returns the type of this sprite.
     * @return "bullet"
     */
    public String getType() {
        return "bullet";
    }

    /**
     * Bullet has nothing else to paint.
     */
    public void continuePaint (Graphics pen) {}
}
