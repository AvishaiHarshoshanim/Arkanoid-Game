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
import java.util.ArrayList;
import java.util.List;

/**
 * The characteristics of the "Direct Hit" level.
 */
public class DirectHitLevel implements LevelInformation {

    /**
     * The Background class represents the background of the "Direct Hit" level.
     */
    public static class Background implements Sprite {

        @Override
        public void drawOn(DrawSurface d) {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 20, d.getWidth(), d.getHeight());
            int centerX = 400;
            int centerY = 163;
            int outerRadius = 100;
            int innerRadius = 30;
            int numCircles = 6;
            int[] circleRadii = new int[numCircles];
            for (int i = 0; i < numCircles; i++) {
                if (i % 2 == 0) {
                    d.setColor(Color.RED);
                } else {
                    d.setColor(Color.WHITE);
                }
                circleRadii[i] = outerRadius - (i * ((outerRadius - innerRadius) / (numCircles - 1)));
                int circleX = centerX - circleRadii[i];
                int circleY = centerY - circleRadii[i];
                int circleWidth = circleRadii[i] * 2;
                d.fillOval(circleX, circleY, circleWidth, circleWidth);
            }
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
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(0, 10);
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(385, 150), 30, 30, Color.red));
        return blocks;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(new Point(400, 550), 5, Color.white));
        return balls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
