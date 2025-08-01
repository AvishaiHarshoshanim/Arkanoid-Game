// 207810771 Avishai Harshoshanim
package Sprites;
import Game.GameLevel;
import Collision.Collidable;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 *  The Paddle class represents the player's paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    static final int SURFACE_WIDTH = 800;
    static final int WALL_WIDTH = 20;
    static final int MINUS_ONE = -1;
    static final int PADDLE_PARTS = 5;
    static final int TWO = 2;
    static final int THREE = 3;
    static final int FOUR = 4;
    static final int REGION_ONE_ANGLE = 300;
    static final int REGION_TWO_ANGLE = 330;
    static final int REGION_THREE_ANGLE = 30;
    static final int REGION_FOUR_ANGLE = 60;

    private biuoop.KeyboardSensor keyboard;
    private Point upperLeft;
    private double width, height, speed;

    /**
     * Constructor.
     *
     * @param upperLeft The upper left point of the paddle.
     * @param width The width of the paddle.
     * @param height The height of the paddle.
     * @param speed The speed of the paddle.
     * @param keyboard The keyboard sensor used to control the paddle's movement.
     */
    public Paddle(Point upperLeft, double width, double height, double speed, biuoop.KeyboardSensor keyboard) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left, as long as the new position is within the game screen.
     */
    public void moveLeft() {
        double newX = this.getUpperLeft().getX() - this.speed;
        if (newX > WALL_WIDTH) {
            this.getUpperLeft().setX(newX);
        }
    }
    /**
     * Moves the paddle to the right, as long as the new position is within the game screen.
     */
    public void moveRight() {
        double newX = this.getUpperLeft().getX() + this.speed;
        if ((newX + this.width) < (SURFACE_WIDTH - WALL_WIDTH)) {
            this.getUpperLeft().setX(newX);
        }
    }

    /**
     * Implements the Sprite interface. The timePassed method checks if the left or right arrow keys are pressed
     * and moves the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * Implements the Sprite interface. Draws the paddle on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }

    /**
     * Implements the Collidable interface. Returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.getUpperLeft(), this.getWidth(), this.getHeight());
    }
    /**
     * Implements the Collidable interface. Calculates the new velocity of the ball after colliding with the paddle.
     *
     * @param hitter the ball that hit the paddle
     * @param collisionPoint The point where the collision occurred.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWeight = this.getWidth() / PADDLE_PARTS;
        double regionOne = this.getUpperLeft().getX() + regionWeight;
        double regionTwo = this.getUpperLeft().getX() + (TWO * regionWeight);
        double regionThree = this.getUpperLeft().getX() + (THREE * regionWeight);
        double regionFour = this.getUpperLeft().getX() + (FOUR * regionWeight);

        Line l = new Line(collisionPoint, collisionPoint);
        double newDx = currentVelocity.getDx(), newDy = currentVelocity.getDy();
        if ((this.getCollisionRectangle().getUpLine().isIntersecting(l))
                || ((this.getCollisionRectangle().getDownLine().isIntersecting(l)))) {
            newDy = newDy * MINUS_ONE;
        }
        if ((this.getCollisionRectangle().getRightLine().isIntersecting(l))
                || ((this.getCollisionRectangle().getLeftLine().isIntersecting(l)))) {
            newDx = newDx * MINUS_ONE;
        }
        double speed = Math.sqrt((newDx * newDx) + (newDy * newDy));
        if (collisionPoint.getX() < regionOne) {
            return Velocity.fromAngleAndSpeed(REGION_ONE_ANGLE, speed);
        } else if (collisionPoint.getX() < regionTwo) {
            return Velocity.fromAngleAndSpeed(REGION_TWO_ANGLE, speed);
        } else if (collisionPoint.getX() < regionThree) {
            return new Velocity(newDx, newDy);
        } else if (collisionPoint.getX() < regionFour) {
            return Velocity.fromAngleAndSpeed(REGION_THREE_ANGLE, speed);
        } else {
            return Velocity.fromAngleAndSpeed(REGION_FOUR_ANGLE, speed);
        }
    }

    /**
     * Adds the paddle to the game, both as a sprite and as a collidable object.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the height of the paddle.
     *
     * @return the height of the paddle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the paddle.
     *
     * @return the width of the paddle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the upperLeft of the paddle.
     *
     * @return the upperLeft of the paddle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    @Override
    public String toString() {
        return "Sprites.Paddle{" + "upperLeft=" + upperLeft + '}';
    }
}