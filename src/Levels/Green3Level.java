// 207810771 Avishai Harshoshanim
package Levels;

import Collision.Block;
import Game.GameLevel;
import Geometry.Point;
import Levels.LevelInformation;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 * The characteristics of the "Green 3" level.
 */
public class Green3Level implements LevelInformation {
    static final int ZERO = 0;
    static final int BLOCKS_START_X = 280;
    static final int BLOCKS_START_Y = 140;
    static final int BLOCKS_HEIGHT = 25;
    static final int BLOCKS_WIDTH = 50;
    static final int BLOCKS_ROWS = 5;
    static final int BLOCKS_COLUMNS = 10;

    /**
     * The Background class represents the background of the "Green 3" level.
     */
    public static class Background implements Sprite {

        @Override
        public void drawOn(DrawSurface d) {
            Color[] colors = {java.awt.Color.RED.darker(), java.awt.Color.GREEN.darker(), java.awt.Color.BLUE.darker(),
                    java.awt.Color.ORANGE.darker(), Color.white.darker(), java.awt.Color.MAGENTA.darker()};
            Color myGreen = new Color(42, 130, 21);
            d.setColor(myGreen);
            d.fillRectangle(0, 20, d.getWidth(), d.getHeight());
            int buildingWidth = 200;
            int buildingHeight = 400;
            int buildingX = 100;
            int buildingY = d.getHeight() - buildingHeight;
            d.setColor(Color.BLACK);
            d.fillRectangle(buildingX, buildingY, buildingWidth, buildingHeight);
            int windowWidth = 40;
            int windowHeight = 40;
            int windowSpacing = 20;
            int numWindows = 5;
            int windowX = buildingX + windowSpacing;
            int windowY = buildingY + windowSpacing;
            for (int i = 0; i < numWindows; i++) {
                for (int j = 0; j < 3; j++) {
                    d.setColor(colors[i]);
                    d.fillRectangle(windowX, windowY, windowWidth, windowHeight);
                    windowX += windowWidth + windowSpacing;
                }
                windowX = buildingX + windowSpacing;
                windowY += windowHeight + windowSpacing;
            }
            int doorWidth = 50;
            int doorHeight = 85;
            int doorX = (buildingWidth - doorWidth) / 2 + buildingX;
            int doorY = buildingY + buildingHeight - doorHeight;
            d.setColor(Color.orange);
            d.fillRectangle(doorX, doorY, doorWidth, doorHeight);
            d.setColor(Color.black);
            int radHand = 3;
            d.fillCircle(doorX + doorWidth - radHand - 1, doorY + (doorHeight / 2), radHand);
            Polygon t = new Polygon();
            t.addPoint(buildingX, buildingY);
            t.addPoint(buildingX + buildingWidth, buildingY);
            t.addPoint(buildingX + (buildingWidth / 2), buildingY - 60);
            d.drawPolygon(t);
            d.setColor(Color.gray);
            d.fillPolygon(t);
        }

        @Override
        public void timePassed() {
        }

        @Override
        public void addToGame(GameLevel g) {
            g.addSprite(this);
        }
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(300, 7);
        initialBallVelocities.add(velocity);
        velocity = Velocity.fromAngleAndSpeed(60, 7);
        initialBallVelocities.add(velocity);
        return initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {java.awt.Color.RED.darker(), java.awt.Color.GREEN.darker(), java.awt.Color.BLUE.darker(),
                java.awt.Color.ORANGE.darker(), Color.white.darker(), java.awt.Color.MAGENTA.darker()};
        List<Block> blocks = new ArrayList<>();
        for (int i = ZERO; i < BLOCKS_ROWS; i++) {
            Color color = colors[i];
            for (int j = ZERO; j < BLOCKS_COLUMNS - i; j++) {
                int x = BLOCKS_START_X + j * (BLOCKS_WIDTH) + i * (BLOCKS_WIDTH);
                int y = BLOCKS_START_Y + i * (BLOCKS_HEIGHT);
                blocks.add(new Block(new Point(x, y), BLOCKS_WIDTH, BLOCKS_HEIGHT, color));
            }
        }
        return blocks;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(new Point(400, 500), 5, Color.white));
        balls.add(new Ball(new Point(400, 500), 5, Color.white));
        return balls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

}
