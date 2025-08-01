// 207810771 Avishai Harshoshanim
package Sprites;
import Game.GameLevel;
import Collision.CollisionInfo;
import Collision.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Ball class represents a ball on a 2D plane.
 */
public class Ball implements Sprite {
    /**
     * The threshold for determining if two doubles are equal.
     */
    static final double THRESHOLD = 0.00001;
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int MINUS_ONE = -1;

    private Point point;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private double borderXStart, borderYStart, borderXEnd, borderYEnd;
    private GameEnvironment environment;

    /**
     * Checks if two double values are equal, within the threshold.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if the values are equal, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Checks if the first double value is less than the second value, within the threshold.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if the first value is less than the second value, false otherwise
     */
    public static boolean doubleEqualsLess(double a, double b) {
        return  (a - b) < THRESHOLD;
    }

    /**
     * Creates a new Sprites.Ball with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Creates a new Sprites.Ball with the given x and y coordinates, radius, and color.
     *
     * @param x the x coordinate of the center point of the ball
     * @param y the y coordinate of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Sets the boundaries of the surface.
     *
     * @param borderXStart the starting x coordinate of the surface
     * @param borderYStart the starting y coordinate of the surface
     * @param borderXEnd the ending x coordinate of the surface
     * @param borderYEnd the ending y coordinate of the surface
     */
    public void setBorders(double borderXStart, double borderYStart, double borderXEnd, double borderYEnd) {
        this.borderXStart = borderXStart;
        this.borderYStart = borderYStart;
        this.borderXEnd = borderXEnd;
        this.borderYEnd = borderYEnd;
    }

    /**
     * Checks the validity of the circle object by ensuring that it is within the bounds of the given borders
     * and that its radius is greater than zero. If it is not, the circle object is adjusted accordingly.
     */
    public void checkValidity() {
        if ((this.point.getX() + this.r) > borderXEnd) {
            this.point.setX(borderXEnd - this.r);
        }
        if ((this.point.getX() - this.r) < borderXStart) {
            this.point.setX(borderXEnd + this.r);
        }
        if ((this.point.getY() + this.r) > borderYEnd) {
            this.point.setY(borderYEnd - this.r);
        }
        if ((this.point.getY() - this.r) < borderYStart) {
            this.point.setY(borderYEnd + this.r);
        }
        if (r <= ZERO) {
            this.r = ONE;
        }
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball to the given values.
     *
     * @param dx the velocity on the x-axis
     * @param dy the velocity on the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the current velocity of the ball.
     *
     * @return the current velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step according to its velocity, and handles collisions
     * with the edges of the given surface.
     */
    public void oldMoveOneStep() {
        double surfaceStartX = this.borderXStart;
        double surfaceStartY = this.borderYStart;
        double surfaceEndX = this.borderXEnd;
        double surfaceEndY = this.borderYEnd;
        boolean regularMove = true, xMoved = false, yMoved = false;
        // Handle collision with both right edge and left edge of surface
        if (doubleEqualsLess(surfaceEndX, (this.point.getX() + r))
                && (doubleEquals((this.point.getX() - r), surfaceStartX))) {
            this.getVelocity().setDx(ZERO);
        }
        // Handle collision with both top edge and bottom edge of surface
        if (doubleEqualsLess(surfaceEndY, (this.point.getY() + r))
                && (doubleEquals((this.point.getY() - r), surfaceStartY))) {
            this.getVelocity().setDy(ZERO);
        }
        // Handle collision with right edge of surface
        if (surfaceEndX >= (this.point.getX() + r)) {
            // If the ball is touching the right border
            if (doubleEqualsLess(surfaceEndX, (this.point.getX() + r))) {
                this.getVelocity().setDx(this.getVelocity().getDx() * MINUS_ONE);
                this.point.setX(this.point.getX() + this.getVelocity().getDx());
                xMoved = true;
                regularMove = false;
            } else {
                // If the ball move beyond the right border after the next move
                if ((this.point.getX() + r + this.getVelocity().getDx()) > surfaceEndX) {
                    this.point.setX(surfaceEndX - r);
                    xMoved = true;
                    regularMove = false;
                }
            }
        }
        // Handle collision with left edge of surface
        // If the ball is touching the border
        if ((doubleEquals((this.point.getX() - r), surfaceStartX))) {
            this.getVelocity().setDx(this.getVelocity().getDx() * MINUS_ONE);
            this.point.setX(this.point.getX() + this.getVelocity().getDx());
            xMoved = true;
            regularMove = false;
        } else {
            // If the ball move beyond the border after the next move
            if ((this.point.getX() - r + this.getVelocity().getDx()) < surfaceStartX) {
                this.point.setX(surfaceStartX + r);
                xMoved = true;
                regularMove = false;
            }
        }
        // Handle collision with bottom edge of surface
        if (surfaceEndY >= (this.point.getY() + r)) {
            // If the ball is touching the border
            if (doubleEqualsLess(surfaceEndY, (this.point.getY() + r))) {
                this.getVelocity().setDy(this.getVelocity().getDy() * MINUS_ONE);
                this.point.setY(this.point.getY() + this.getVelocity().getDy());
                yMoved = true;
                regularMove = false;
            } else {
                // If the ball move beyond the border after the next move
                if ((this.point.getY() + r + this.getVelocity().getDy()) > surfaceEndY) {
                    this.point.setY(surfaceEndY - r);
                    yMoved = true;
                    regularMove = false;
                }
            }
        }
        // Handle collision with top edge of surface
        // If the ball is touching the border
        if (doubleEquals((this.point.getY() - r), surfaceStartY)) {
            this.getVelocity().setDy(this.getVelocity().getDy() * MINUS_ONE);
            this.point.setY(this.point.getY() + this.getVelocity().getDy());
            yMoved = true;
            regularMove = false;
        } else {
            // If the ball move beyond the border after the next move
            if ((this.point.getY() - r + this.getVelocity().getDy()) < surfaceStartY) {
                this.point.setY(surfaceStartY + r);
                yMoved = true;
                regularMove = false;
            }
        }
        // Move the ball if no collisions occurred
        if (regularMove) {
            this.point = this.getVelocity().applyToPoint(this.point);
        } else {
            // Move the ball if one collision occurred or both
            if (!xMoved) {
                this.point.setX(this.point.getX() + this.getVelocity().getDx());
            }
            if (!yMoved) {
                this.point.setY(this.point.getY() + this.getVelocity().getDy());
            }
        }
    }

    /**
     * This method is called by the animation loop every time unit and moves the object one step.
     * It calls the moveOneStep() method to move the object one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Moves the ball one step according to its current velocity.
     * If the ball does not collide with any collidable object in its environment, it will move to its new position
     * according to its current velocity. Otherwise, the ball will move slightly before the collision point and
     * update its velocity based on the collision information.
     */
    public void moveOneStep() {
        Point trajectoryEnd = this.getVelocity().applyToPoint(point);
        Line trajectory = new Line(this.point, trajectoryEnd);
        CollisionInfo closestCollision = this.getEnvironment().getClosestCollision(trajectory);
        if (closestCollision == null) {
            this.point.setX(trajectoryEnd.getX());
            this.point.setY(trajectoryEnd.getY());
        } else {
            // Move the ball slightly before the collision point
            double newX = closestCollision.collisionPoint().getX() - Math.signum(this.getVelocity().getDx());
            double newY = closestCollision.collisionPoint().getY() - Math.signum(this.getVelocity().getDy());
            this.point.setX(newX);
            this.point.setY(newY);
            // Notify the collision object and update the ball's velocity
            Velocity newVelocity = closestCollision.collisionObject().hit(this, closestCollision.collisionPoint(),
                    this.getVelocity());
            this.setVelocity(newVelocity);
        }
    }

    /**
     * Gets the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * Gets the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), r);
    }

    /**
     * Sets the environment of the ball.
     *
     * @param environment the new environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Gets the environment of the ball.
     *
     * @return the environment of the ball
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     *  Add this ball to the game by adding it as a sprite.
     *
     *  @param g the game to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from a given game.
     *
     * @param game the game that the ball will be removed from him
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}