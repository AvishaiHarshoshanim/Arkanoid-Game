// 207810771 Avishai Harshoshanim
package Collision;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Velocity;

/**
 * The Collidable interface represents objects that can collide with each other in the game.
 * Objects that implement this interface should have a rectangular collision shape that can be used to detect
 * collisions with other objects.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle shape of this object. This is the shape that should be used to detect
     * collisions with other objects in the game.
     *
     * @return the collision rectangle shape of this object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision has occurred with it at the given collision point and current velocity
     * Returns the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter the ball that hit the object
     * @param collisionPoint the point of collision with this object
     * @param currentVelocity the current velocity of the object before the collision
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}