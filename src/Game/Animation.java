// 207810771 Avishai Harshoshanim
package Game;
import biuoop.DrawSurface;
/**
 * The Animation interface represents an animation that can be displayed on a DrawSurface.
 */
public interface Animation {
    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface object where the animation is drawn.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Determines whether the animation should stop or continue.
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}