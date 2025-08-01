// 207810771 Avishai Harshoshanim
package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class is an implementation of the Animation interface that allows
 * the animation to be stopped by a key press.
 */
public class KeyPressStoppableAnimation implements  Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation object with the specified keyboard sensor, key, and animation.
     *
     * @param sensor The KeyboardSensor used to detect key presses.
     * @param key The key that can be pressed to stop the animation.
     * @param animation The animation to be played.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
