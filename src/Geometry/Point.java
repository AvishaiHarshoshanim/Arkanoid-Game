// 207810771 Avishai Harshoshanim
package Geometry;
/**
 * A class that represents a point in 2D space.
 */
public class Point {

    /**
     * The threshold for determining if two doubles are equal.
     */
    static final double THRESHOLD = 0.00001;

    private double x, y;

    /**
     * Checks if two doubles are equal within a certain threshold.
     *
     * @param a the first double to compare
     * @param b the second double to compare
     * @return true if the doubles are equal, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Constructs a new Geometry.Point with the given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (doubleEquals(this.x, other.getX()) && (doubleEquals(this.y, other.getY())));
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }
    /**
     * Sets the x coordinate of this point.
     *
     * @param x the new x coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the y coordinate of this point.
     *
     * @param y the new y coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Geometry.Point{" + "x = " + x + " y = " + y + '}';
    }
}