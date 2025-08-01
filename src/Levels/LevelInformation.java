// 207810771 Avishai Harshoshanim
package Levels;

import Collision.Block;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import java.util.List;

/**
 * The LevelInformation interface represents the information and configuration for a game level.
 * It provides methods to retrieve various properties of the level, such as the number of balls,
 * the initial ball velocities, paddle speed and width, level name, background sprite, blocks,
 * balls, and the number of blocks to be removed.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * Returns a list of the initial velocities for each ball in the level.
     * The size of the list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite.
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up the level.
     *
     * @return a list of blocks.
     */
    List<Block> blocks();

    /**
     * Returns a list of balls in the level.
     *
     * @return a list of balls.
     */
    List<Ball> balls();

    /**
     * Returns the number of blocks that need to be removed to clear the level.
     * This number should be less than or equal to the total number of blocks in the level.
     *
     * @return the number of blocks to be removed.
     */
    int numberOfBlocksToRemove();
}
