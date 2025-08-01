// 207810771 Avishai Harshoshanim
package Collision;
import Game.GameLevel;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block in the game, which can be collided with and drawn on a DrawSurface.
 * Blocks can have a specific size and color, and their collisions are determined by their collision rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    static final int MINUS_ONE = -1;
    private Geometry.Point upperLeft;
    private double width, height;
    private Geometry.Rectangle collisionRectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a new Block object with the specified upper left corner point,
     * width, height, and color.
     *
     * @param upperLeft the upper left corner point of the block
     * @param width the width of the block
     * @param height the height of the block
     * @param color the color of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.collisionRectangle = new Geometry.Rectangle(this.getUpperLeft(), this.getWidth(), this.getHeight());
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Removes the block from a given game.
     *
     * @param game the game that the block will be removed from him
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Returns the collision rectangle of this block.
     *
     * @return the collision rectangle of this block
     */
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    /**
     * Calculates and returns the new velocity of an object after a collision with this block, given the collision
     * point and the object's current velocity. The method checks the side of the collision point relative to the
     * block and determine the new direction of the object's velocity. If the collision point is on the top or bottom
     * of the block, the method inverts the object's y-velocity; if the collision point is on the left or right of the
     * block, it inverts the object's x-velocity.
     *
     * @param hitter the ball that hit the block
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity of the object after the collision
     */
    public Velocity hit(Ball hitter, Geometry.Point collisionPoint, Velocity currentVelocity) {
        Line l = new Line(collisionPoint, collisionPoint);
        double newDx = currentVelocity.getDx(), newDy = currentVelocity.getDy();
        if ((collisionRectangle.getUpLine().isIntersecting(l))
                || ((collisionRectangle.getDownLine().isIntersecting(l)))) {
             newDy = newDy * MINUS_ONE;
        }
        if ((collisionRectangle.getRightLine().isIntersecting(l))
                || ((collisionRectangle.getLeftLine().isIntersecting(l)))) {
            newDx = newDx * MINUS_ONE;
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * Returns the height of this block.
     *
     * @return the height of this block
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of this block.
     *
     * @return the width of this block
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the upperLeft of this block.
     *
     * @return the upperLeft of this block
     */
    public Geometry.Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * This method is called by the game environment once every game tick.
     * It currently has no implementation.
     */
    public void timePassed() {
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the block
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }

    /**
     * Adds the block to the specified game, as both a sprite and a collidable object.
     *
     * @param g the game to add the block to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the color of this block.
     *
     * @return the color of this block
     */
    public Color getColor() {
        return color;
    }

    /**
     * Adds the given HitListener object to the hitListeners list.
     *
     * @param hl the HitListener object to be added to the hitListeners list.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes the given HitListener object from the hitListeners list.
     *
     * @param hl the HitListener object to be removed from the hitListeners list.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter The Ball object representing the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}