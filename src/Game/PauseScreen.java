// 207810771 Avishai Harshoshanim
package Game;

import biuoop.DrawSurface;

/**
 * The PauseScreen class represents a pause screen animation.
 * It displays a "paused" message on the screen and waits for the user to press the space key to continue.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructs a PauseScreen object.
     * Initializes the 'stop' flag as false.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getHeight() / 4, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
