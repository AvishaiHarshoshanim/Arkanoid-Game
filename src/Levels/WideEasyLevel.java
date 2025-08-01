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
 * The characteristics of the "Wide Easy" level.
 */
public class WideEasyLevel implements LevelInformation {

    /**
     * The Background class represents the background of the "Wide Easy" level.
     */
    public static class Background implements Sprite {

        @Override
        public void drawOn(DrawSurface d) {
            Color myBlue = new Color(0, 176, 255);
            d.setColor(myBlue);
            d.fillRectangle(0, 20, d.getWidth(), d.getHeight());
            int centerX = 150;
            int centerY = 150;
            int outerCircleRadius = 60;
            int innerCircleRadius = 30;
            int numRays = 16;
            d.setColor(new Color(243, 227, 56, 255));
            d.setColor(Color.yellow);
            double angleIncrement = Math.PI * 2 / numRays;
            double angle = 0;
            for (int i = 0; i < numRays; i++) {
                int x1 = (int) (centerX + outerCircleRadius * Math.cos(angle));
                int y1 = (int) (centerY + outerCircleRadius * Math.sin(angle));
                int x2 = (int) (centerX + innerCircleRadius * Math.cos(angle));
                int y2 = (int) (centerY + innerCircleRadius * Math.sin(angle));
                d.drawLine(x1, y1, x2, y2);
                angle += angleIncrement;
            }
            d.setColor(new Color(255, 223, 97));
            d.fillCircle(centerX, centerY, innerCircleRadius);
            d.setColor(new Color(255, 238, 150));
            d.fillCircle(centerX, centerY, innerCircleRadius - 5);
            d.setColor(new Color(255, 245, 189));
            d.fillCircle(centerX, centerY, innerCircleRadius - 10);
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
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<>();
        double angle = 300;
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, 10);
            initialBallVelocities.add(velocity);
            angle = angle + 10;
        }
        angle = 60;
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, 10);
            initialBallVelocities.add(velocity);
            angle = angle - 10;
        }
        return initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.RED, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        List<Block> blocks = new ArrayList<>();
        double blockX = 20;
        double blockW = 50.6666667;
        for (int i = 0; i < 7; i++) {
            if (i != 3) {
                for (int j = 0; j < 2; j++) {
                    blocks.add(new Block(new Point(blockX, 220), blockW, 25, colors[i]));
                    blockX = blockX + blockW;
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    blocks.add(new Block(new Point(blockX, 220), blockW, 25, colors[i]));
                    blockX = blockX + blockW;
                }
            }
        }

        return blocks;
    }

    @Override
    public List<Ball> balls() {
        int ballX = 400, ballY = 500;
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            balls.add(new Ball(new Point(ballX, ballY), 5, Color.white));
        }
        return balls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

}
