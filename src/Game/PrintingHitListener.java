// 207810771 Avishai Harshoshanim
package Game;

import Collision.Block;
import Collision.HitListener;
import Sprites.Ball;

/**
 * The PrintingHitListener class is a HitListener implementation that prints a message when a block is hit by a ball.
 */
public class PrintingHitListener implements HitListener {
    /**
     * Called when the beingHit block is hit by a ball.
     * Prints a message indicating that a block was hit.
     *
     * @param beingHit The Block object being hit.
     * @param hitter The Ball object doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}