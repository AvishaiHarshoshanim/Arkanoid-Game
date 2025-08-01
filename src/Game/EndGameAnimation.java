// 207810771 Avishai Harshoshanim
package Game;

import biuoop.DrawSurface;

/**
 * The EndGameAnimation class implements the Animation interface and represents the animation displayed at the end
 * of the game, showing the game outcome and the final score.
 */
public class EndGameAnimation implements Animation {
    private boolean stop;
    private String isFailed;
    private Counter score;

    /**
     * Constructs an EndGameAnimation object with the specified game outcome and final score.
     *
     * @param isFailed the game outcome, either "failed" for game over or any other value for winning.
     * @param score    the final score achieved in the game.
     */
    public EndGameAnimation(String isFailed, Counter score) {
        this.isFailed = isFailed;
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isFailed.equals("failed")) {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "Game Over. Your score is " + this.score.toString(), 32);
        } else {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2, "You Win! Your score is " + this.score.toString(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
