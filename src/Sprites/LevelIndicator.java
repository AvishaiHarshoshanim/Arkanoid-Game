// 207810771 Avishai Harshoshanim
package Sprites;

import Game.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The LevelIndicator class is a sprite that displays the current score on the game screen.
 */
public class LevelIndicator implements Sprite {
    private int x, y, font;
    private String name;

    /**
     * Creates a new ScoreIndicator with the specified position, score counter, and font size.
     *
     * @param x The x-coordinate of the score indicator position.
     * @param y The y-coordinate of the score indicator position.
     * @param name The name of the level.
     * @param font The font size for displaying the score.
     */
    public LevelIndicator(int x, int y, String name, int font) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.font = font;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the score indicator.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(this.x, this.y, "Level Name: " +  this.name, this.font);
    }

    /**
     * Does nothing in the ScoreIndicator.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds the score indicator to a given game.
     *
     * @param g The game to which the score indicator should be added.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
