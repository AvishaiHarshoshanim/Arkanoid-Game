// 207810771 Avishai Harshoshanim
package Collision;
import Sprites.Ball;

/**
 * The HitListener interface represents a listener for hit events.
 * Implementing classes can be notified when a specific object is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit The Block object that is being hit.
     * @param hitter The Ball object that is doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}