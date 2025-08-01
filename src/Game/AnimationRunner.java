// 207810771 Avishai Harshoshanim
package Game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running an animation by updating frames and displaying them on a GUI.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    static final int THOUSAND = 1000;
    static final int ZERO = 0;

    /**
     * Constructs an AnimationRunner with the specified frames per second.
     *
     * @param framesPerSecond the desired number of frames per second for the animation.
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * Returns the GUI associated with this AnimationRunner.
     *
     * @return the GUI object.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Sets the GUI for this AnimationRunner.
     *
     * @param gui the GUI object to be set.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * Runs the specified animation.
     *
     * @param animation the Animation object to be run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = THOUSAND / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > ZERO) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}