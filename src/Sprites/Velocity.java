// 207810771 Avishai Harshoshanim
package Sprites;
import Geometry.Point;

/**
 The class represents the velocity of an object, specified by its change in position on the x and y axes.
 */
public class Velocity {
    private double dx, dy;
    static final int MINUS_ONE = -1;

    /**
     * Constructs a new Sprites.Velocity object with the specified changes in position.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the change in position on the x-axis to the specified value.
     *
     * @param dx the new value for the change in position on the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in position on the y-axis to the specified value.
     *
     * @param dy the new value for the change in position on the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point by adding the change in position to the point's coordinates.
     *
     * @param p the point to apply the velocity to
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns a new Velocity object with the specified angle and speed.
     *
     * @param angle the angle of the velocity vector, in degrees
     * @param speed the magnitude of the velocity vector
     * @return a new Velocity object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = MINUS_ONE * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}