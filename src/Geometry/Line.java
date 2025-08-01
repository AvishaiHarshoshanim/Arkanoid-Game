// 207810771 Avishai Harshoshanim
package Geometry;
import java.util.List;
/**
 * A class that represents a line in 2D space.
 */
public class Line {
    /**
     * The threshold for determining if two doubles are equal.
     */
    static final double THRESHOLD = 0.00001;
    static final double ZERO = 0;
    static final double TWO = 2;

    private Point start, end;

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
     * Constructs a Geometry.Line object with the given start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Constructs a Geometry.Line object with the given coordinates of the start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
    }
    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(end);
    }
    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / TWO, (this.start.getY() + this.end.getY()) / TWO);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if this line segment intersects with the other line segment.
     *
     * @param other the other line segment to check for intersection with this one
     * @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // If the lines are the same line
        if (this.equals(other)) {
            return true;
        }
        double thisDy = this.start.getY() - this.end.getY();
        double thisDx = this.start.getX() - this.end.getX();
        double otherDy = other.start().getY() - other.end().getY();
        double otherDx = other.start().getX() - other.end().getX();
        // If one of the lines is just a point
        if ((this.start.equals(this.end)) || (other.start().equals(other.end()))) {
            // If both lines are just a point
            if ((this.start.equals(this.end)) && (other.start().equals(other.end()))) {
                // If the points are the same
                if (this.start.equals(other.start())) {
                    return true;
                }
                // If the points are different
                return false;
            }
        }
        // If one of the lines is vertical
        if ((doubleEquals(thisDx, ZERO)) || (doubleEquals(otherDx, ZERO))) {
            double startThisY = Math.min(this.start.getY(), this.end.getY());
            double endThisY = Math.max(this.start.getY(), this.end.getY());
            double startOtherY = Math.min(other.start().getY(), other.end().getY());
            double endOtherY = Math.max(other.start().getY(), other.end().getY());
            // If both lines are vertical
            if ((doubleEquals(thisDx, ZERO)) && (doubleEquals(otherDx, ZERO))) {
                // If both lines have the same x value
                if (doubleEquals(this.start.getX(), other.start().getX())) {
                    // If the intersection between the lines is infinite
                    if (((startOtherY >  startThisY) && (startOtherY <  endThisY))
                            || ((endOtherY >  startThisY) && (endOtherY < endThisY))
                            || ((startThisY >  startOtherY) && (startThisY <  endOtherY))
                            || ((endThisY >  startOtherY) && (endThisY < endOtherY))) {
                        return true;
                    }
                    // For the two cases where there is one point of intersection between the two lines
                    if (doubleEquals(startThisY, endOtherY)) {
                        return true;
                    }
                    if (doubleEquals(startOtherY, endThisY)) {
                        return true;
                    }
                    // If there is no intersection point
                    return false;
                }
                // Two vertical lines that do not have the same x value
                return false;
            }
            // Just the other line is vertical
            if (doubleEquals(otherDx, ZERO)) {
                double thisSlope = thisDy / thisDx;
                double thisIntercept = this.start.getY() - (thisSlope * this.start.getX());
                double crossY = (thisSlope * other.start().getX()) + thisIntercept;
                double startThisX = Math.min(this.start.getX(), this.end.getX());
                double endThisX = Math.max(this.start.getX(), this.end.getX());
                // If the intersection point is within the y-range of both lines, they intersect
                if ((crossY >= startThisY) && (crossY <= endThisY)
                        && (crossY >= startOtherY) && (crossY <= endOtherY) && (other.start.getX() >= startThisX)
                        && (other.start.getX() <= endThisX)) {
                    return true;
                }
                // If the intersection point is not within the y-range of both lines, they don't intersect
                return false;
            }
            // Just this line is vertical
            if (doubleEquals(thisDx, ZERO)) {
                double otherSlope = otherDy / otherDx;
                double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
                double crossY = (otherSlope * this.start.getX()) + otherIntercept;
                double startOtherX = Math.min(other.start().getX(), other.end().getX());
                double endOtherX = Math.max(other.start().getX(), other.end().getX());
                // If the intersection point is within the y-range of both lines, they intersect
                if ((crossY >= startOtherY) && (crossY <= endOtherY)
                        && (crossY >= startThisY) && (crossY <= endThisY) && (this.start.getX() >= startOtherX)
                        && (this.start.getX() <= endOtherX)) {
                    return true;
                }
                // If the intersection point is not within the y-range of both lines, they don't intersect
                return false;
            }
        }
        double mySlope = thisDy / thisDx;
        double otherSlope = otherDy / otherDx;
        double myIntercept = this.start.getY() - (mySlope * this.start.getX());
        double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
        // The slopes of the two lines are different
        if (!doubleEquals(mySlope, otherSlope)) {
            double crossX = (otherIntercept - myIntercept) / (mySlope - otherSlope);
            double crossY = (mySlope * crossX) + myIntercept;
            double startThisX = Math.min(this.start.getX(), this.end.getX());
            double endThisX = Math.max(this.start.getX(), this.end.getX());
            double startOtherX = Math.min(other.start().getX(), other.end().getX());
            double endOtherX = Math.max(other.start().getX(), other.end().getX());
            double startThisY = Math.min(this.start.getY(), this.end.getY());
            double endThisY = Math.max(this.start.getY(), this.end.getY());
            double startOtherY = Math.min(other.start().getY(), other.end().getY());
            double endOtherY = Math.max(other.start().getY(), other.end().getY());
            // If the intersection point is within both lines' ranges, they intersect
            return ((crossX >= startThisX) && (crossX <= endThisX) && (crossY >= startThisY) && (crossY <= endThisY)
                    && (crossX >= startOtherX) && (crossX <= endOtherX) && (crossY >= startOtherY)
                    && (crossY <= endOtherY));
        }
        // The slopes of the two lines are equal and both have the same point of intersection with the y-axis
        if (doubleEquals(myIntercept, otherIntercept)) {
            double startThisX = Math.min(this.start.getX(), this.end.getX());
            double endThisX = Math.max(this.start.getX(), this.end.getX());
            double startOtherX = Math.min(other.start().getX(), other.end().getX());
            double endOtherX = Math.max(other.start().getX(), other.end().getX());
            // If the intersection between the lines is infinite
            if (((startOtherX >  startThisX) && (startOtherX <  endThisX))
                    || ((endOtherX >  startThisX) && (endOtherX < endThisX))
                    || ((startThisX >  startOtherX) && (startThisX <  endOtherX))
                    || ((endThisX >  startOtherX) && (endThisX < endOtherX))) {
                return true;
            }
            // For the two cases where there is one point of intersection between the two lines
            if (doubleEquals(startThisX, endOtherX)) {
                return true;
            }
            if (doubleEquals(startOtherX, endThisX)) {
                return true;
            }
            // If there is no intersection point
            return false;
        }
        // The slopes of the two lines are equal but neither has the same point of intersection with the y-axis
        return false;
    }

    /**
     * Returns the intersection point of this line with another line, or null if they don't intersect or coincide.
     *
     * @param other the line to intersect with
     * @return the intersection point of this line with another line, or null if they don't intersect or coincide
     */
    public Point intersectionWith(Line other) {
        // If the lines are the same "line"
        if (this.equals(other)) {
            // If both lines are just the same point
            if (this.start.equals(this.end)) {
                return this.start;
            }
            // If the lines are actually the same line
            return null;
        }
        double thisDy = this.start.getY() - this.end.getY();
        double thisDx = this.start.getX() - this.end.getX();
        double otherDy = other.start().getY() - other.end().getY();
        double otherDx = other.start().getX() - other.end().getX();
        // If one of the lines is vertical
        if ((doubleEquals(thisDx, ZERO)) || (doubleEquals(otherDx, ZERO))) {
            double startThisY = Math.min(this.start.getY(), this.end.getY());
            double endThisY = Math.max(this.start.getY(), this.end.getY());
            double startOtherY = Math.min(other.start().getY(), other.end().getY());
            double endOtherY = Math.max(other.start().getY(), other.end().getY());
            // If both lines are vertical
            if ((doubleEquals(thisDx, ZERO)) && (doubleEquals(otherDx, ZERO))) {
                // If both lines have the same x value
                if (doubleEquals(this.start.getX(), other.start().getX())) {
                    // If the intersection between the lines is infinite or the case of a line and a point
                    if (((startOtherY >  startThisY) && (startOtherY <  endThisY))
                            || ((endOtherY >  startThisY) && (endOtherY < endThisY))
                            || ((startThisY >  startOtherY) && (startThisY <  endOtherY))
                            || ((endThisY >  startOtherY) && (endThisY < endOtherY))) {
                        // For the two cases of a line and a point
                        if (this.start.equals(this.end)) {
                            return new Point(this.start.getX(), this.start.getY());
                        }
                        if (other.start().equals(other.end())) {
                            return new Point(other.start().getX(), other.start().getY());
                        }
                        // If the intersection between the lines is actually infinite
                        return null;
                    }
                    // For the two cases where there is one point of intersection between the two lines
                    if (doubleEquals(startThisY, endOtherY)) {
                        double startThisX = Math.min(this.start.getX(), this.end.getX());
                        return new Point(startThisX, startThisY);
                    }
                    if (doubleEquals(startOtherY, endThisY)) {
                        double endThisX = Math.max(this.start.getX(), this.end.getX());
                        return new Point(endThisX, endThisY);
                    }
                    // If there is no intersection point
                    return null;
                }
                // Two vertical lines that do not have the same x value
                return null;
            }
            // Just the other line is vertical
            if (doubleEquals(otherDx, ZERO)) {
                double thisSlope = thisDy / thisDx;
                double thisIntercept = this.start.getY() - (thisSlope * this.start.getX());
                double crossY = (thisSlope * other.start().getX()) + thisIntercept;
                double startThisX = Math.min(this.start.getX(), this.end.getX());
                double endThisX = Math.max(this.start.getX(), this.end.getX());
                // If the intersection point is within the y-range of both lines, they intersect
                if ((crossY >= startOtherY) && (crossY <= endOtherY)
                        && (crossY >= startThisY) && (crossY <= endThisY) && (other.start.getX() >= startThisX)
                        && (other.start.getX() <= endThisX)) {
                    return new Point(other.start().getX(), crossY);
                }
                // If the intersection point is not within the y-range of both lines, they don't intersect
                return null;
            }
            // Just this line is vertical
            if (doubleEquals(thisDx, ZERO)) {
                double otherSlope = otherDy / otherDx;
                double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
                double crossY = (otherSlope * this.start.getX()) + otherIntercept;
                double startOtherX = Math.min(other.start().getX(), other.end().getX());
                double endOtherX = Math.max(other.start().getX(), other.end().getX());
                // If the intersection point is within the y-range of both lines, they intersect
                if ((crossY >= startOtherY) && (crossY <= endOtherY)
                        && (crossY >= startThisY) && (crossY <= endThisY) && (this.start.getX() >= startOtherX)
                        && (this.start.getX() <= endOtherX)) {
                    return new Point(this.start.getX(), crossY);
                }
                // If the intersection point is not within the y-range of both lines, they don't intersect
                return null;
            }
        }
        double mySlope = thisDy / thisDx;
        double otherSlope = otherDy / otherDx;
        double myIntercept = this.start.getY() - (mySlope * this.start.getX());
        double otherIntercept = other.start().getY() - (otherSlope * other.start().getX());
        // The slopes of the two lines are different
        if (!doubleEquals(mySlope, otherSlope)) {
            double crossX = (otherIntercept - myIntercept) / (mySlope - otherSlope);
            double crossY = (mySlope * crossX) + myIntercept;
            double startThisX = Math.min(this.start.getX(), this.end.getX());
            double endThisX = Math.max(this.start.getX(), this.end.getX());
            double startOtherX = Math.min(other.start().getX(), other.end().getX());
            double endOtherX = Math.max(other.start().getX(), other.end().getX());
            double startThisY = Math.min(this.start.getY(), this.end.getY());
            double endThisY = Math.max(this.start.getY(), this.end.getY());
            double startOtherY = Math.min(other.start().getY(), other.end().getY());
            double endOtherY = Math.max(other.start().getY(), other.end().getY());
            // If the intersection point is within both lines' ranges, they intersect
            if ((crossX >= startThisX) && (crossX <= endThisX) && (crossY >= startThisY) && (crossY <= endThisY)
                    && (crossX >= startOtherX) && (crossX <= endOtherX) && (crossY >= startOtherY)
                    && (crossY <= endOtherY)) {
                return new Point(crossX, crossY);
            }
            return null;
        }
        // The slopes of the two lines are equal and both have the same point of intersection with the y-axis
        if (doubleEquals(myIntercept, otherIntercept)) {
            double startThisX = Math.min(this.start.getX(), this.end.getX());
            double endThisX = Math.max(this.start.getX(), this.end.getX());
            double startOtherX = Math.min(other.start().getX(), other.end().getX());
            double endOtherX = Math.max(other.start().getX(), other.end().getX());
            // If the intersection between the lines is infinite
            if (((startOtherX >  startThisX) && (startOtherX <  endThisX))
                    || ((endOtherX >  startThisX) && (endOtherX < endThisX))
                    || ((startThisX >  startOtherX) && (startThisX <  endOtherX))
                    || ((endThisX >  startOtherX) && (endThisX < endOtherX))) {
                return null;
            }
            // For the two cases where there is one point of intersection between the two lines
            if (doubleEquals(startThisX, endOtherX)) {
                double startThisY = Math.min(this.start.getY(), this.end.getY());
                return new Point(startThisX, startThisY);
            }
            if (doubleEquals(startOtherX, endThisX)) {
                double endThisY = Math.max(this.start.getY(), this.end.getY());
                return new Point(endThisX, endThisY);
            }
            // If there is no intersection point
            return null;
        }
        // The slopes of the two lines are equal but neither has the same point of intersection with the y-axis
        return null;
    }

    /**
     * Returns true if this line segment is equal to the other line segment.
     *
     * @param other the other line segment to compare to this one
     * @return true if the line segments are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start()));
    }

    /**
     * Finds the closest intersection point between this line and the specified rectangle, as measured
     * from the start point of this line.
     *
     * @param rect the rectangle to find intersection points with
     * @return the closest intersection point to the start of this line, or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        Point closestInter = null;
        double closestDist = Double.POSITIVE_INFINITY;
        for (Point point : list) {
            if (point != null) {
                double dist = this.start().distance(point);
                if (dist < closestDist) {
                    closestInter = point;
                    closestDist = dist;
                }
            }
        }
        return closestInter;
    }
}
