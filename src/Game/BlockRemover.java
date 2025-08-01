// 207810771 Avishai Harshoshanim
package Game;
import Collision.Block;
import Collision.HitListener;
import Sprites.Ball;

/**
 * The BlockRemover class is a HitListener implementation that removes blocks from the game when they are hit by
 * a ball. It also keeps track of the number of blocks remaining in the game.
 */
public class BlockRemover implements HitListener {
    static final int ONE = 1;
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object with the specified game and remaining blocks counter.
     *
     * @param game The Game object representing the game.
     * @param removedBlocks The Counter object representing the count of remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Called when the beingHit block is hit by a ball.
     * Removes the beingHit block from the game, removes this listener from the beingHit block and decreases
     * the count of remaining blocks.
     *
     * @param beingHit The Block object being hit.
     * @param hitter The Ball object doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(ONE);
    }
}