// 207810771 Avishai Harshoshanim
package Sprites;
import Game.GameLevel;
import biuoop.DrawSurface;

/**
 * The Sprite interface represents a game object that can be drawn to the screen and can be updated over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that one frame of the game has passed.
     */
    void timePassed();
    /**
     * Adds the sprite to the given game.
     *
     * @param g the game to which to add the sprite
     */
    void addToGame(GameLevel g);
}