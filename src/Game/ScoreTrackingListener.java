// 207810771 Avishai Harshoshanim
package Game;

import Collision.Block;
import Collision.HitListener;
import Sprites.Ball;

/**
 * The ScoreTrackingListener class is a HitListener implementation that tracks and updates
 * the score when a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    static final int SIMPLE_HIT = 5;
    private Counter currentScore;

    /**
     * Creates a new ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter The counter object to track the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Called when the beingHit block is hit by a ball.
     * Increases the current score by the value of a simple hit.
     *
     * @param beingHit The Block object being hit.
     * @param hitter The Ball object doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SIMPLE_HIT);
    }
}