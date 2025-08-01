// 207810771 Avishai Harshoshanim
package Game;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation class displays a countdown animation on top of a given game screen for a specified duration.
 * The animation counts down from a given number back to 1, with each number appearing on the screen for a specified
 * duration before being replaced with the next number.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private Counter secNum;

    /**
     * Constructs a CountdownAnimation object with the specified parameters.
     *
     * @param numOfSeconds the total duration of the countdown animation in seconds.
     * @param countFrom    the starting number of the countdown.
     * @param gameScreen   the game screen where the countdown animation will be displayed on top of.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
        this.secNum = new Counter();
        this.secNum.setCounter(this.countFrom);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (secNum.getValue() == 0) {
            this.stop = true;
        } else {
            d.setColor(Color.gray.darker());
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, secNum.toString(), 32);
        }
         if (secNum.getValue() == countFrom) {
            secNum.decrease(1);
        } else {
            sleeper.sleepFor((long) (1000 * numOfSeconds));
            secNum.decrease(1);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
