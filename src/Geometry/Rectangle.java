// 207810771 Avishai Harshoshanim
package Geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * A class representing a rectangle with sides parallel to the x- and y-axes.
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;
    private Line upLine, downLine, rightLine, leftLine;

    /**
     * Constructs a new Rectangle object with the specified upper left corner point,
     * width, and height.
     *
     * @param upperLeft the upper left corner point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point downLeft = new Point(upperLeft.getX(), (upperLeft.getY() + this.getHeight()));
        Point upperRight = new Point((upperLeft.getX() + this.getWidth()), upperLeft.getY());
        Point downRight = new Point((upperLeft.getX() + this.getWidth()), (upperLeft.getY() + this.getHeight()));
        this.upLine = new Line(upperLeft, upperRight);
        this.downLine = new Line(downLeft, downRight);
        this.leftLine = new Line(upperLeft, downLeft);
        this.rightLine = new Line(upperRight, downRight);
    }

    /**
     * Computes the intersection points between this rectangle and the specified line.
     *
     * @param line the line to compute intersection points with
     * @return a list of intersection points, which may be empty if there are no intersections
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        list.add(this.getUpLine().intersectionWith(line));
        list.add(this.getDownLine().intersectionWith(line));
        list.add(this.getRightLine().intersectionWith(line));
        list.add(this.getLeftLine().intersectionWith(line));
        return list;
    }

    /**
     * Returns the width of this rectangle.
     *
     * @return the width of this rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Returns the height of this rectangle.
     *
     * @return the height of this rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upperLeft of this rectangle.
     *
     * @return the upperLeft of this rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the upLine of this rectangle.
     *
     * @return the upLine of this rectangle
     */
    public Line getUpLine() {
        return upLine;
    }
    /**
     * Returns the downLine of this rectangle.
     *
     * @return the downLine of this rectangle
     */
    public Line getDownLine() {
        return downLine;
    }
    /**
     * Returns the leftLine of this rectangle.
     *
     * @return the leftLine of this rectangle
     */
    public Line getLeftLine() {
        return leftLine;
    }
    /**
     * Returns the rightLine of this rectangle.
     *
     * @return the rightLine of this rectangle
     */
    public Line getRightLine() {
        return rightLine;
    }
}