// 207810771 Avishai Harshoshanim
package Game;
import Collision.Block;
import Collision.HitListener;
import Sprites.Ball;

/**
 * The BallRemover class is a HitListener implementation that removes balls from the game when they hit the
 * death-region block. It keeps track of the game and a counter for the remaining balls.
 */
public class BallRemover implements HitListener {
    static final int ONE = 1;
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover object with the specified game and remaining balls counter.
     *
     * @param game The Game object representing the game.
     * @param remainingBalls The Counter object representing the count of remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Called when the beingHit block (death-region block) is hit by a ball.
     * Removes the hitter ball from the game and decreases the count of remaining balls.
     *
     * @param beingHit The Block object being hit.
     * @param hitter The Ball object doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(ONE);
    }
}
