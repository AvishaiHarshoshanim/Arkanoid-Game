// 207810771 Avishai Harshoshanim
package Collision;
import Geometry.Line;
import Geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment in which the game takes place.
 * It holds a collection of Collidable objects and provides methods for checking collisions
 * between a given trajectory and the objects in the environment.
 */
public class GameEnvironment {

    private List<Collidable> list;

    /**
     * Creates a new GameEnvironment object with an empty list of collidables.
     */
    public GameEnvironment() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to be added to the environment.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }
    /**
     * Removes the given collidable object from the environment.
     *
     * @param c the collidable object to be removed from the environment.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }

    /**
     * Assumes an object moving from the start point of the given line to its end point.
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the line representing the object's trajectory.
     * @return a CollisionInfo object representing the closest collision that is going to occur,
     *         or null if there are no collisions.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.list == null) {
            return null;
        }
        List<Collidable> collidables = new ArrayList<>(list);
        Point closestInter = null;
        Collidable closestCollidable = null;
        for (Collidable collidable : collidables) {
            Point inter = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (inter != null) {
                if (closestInter == null
                        || trajectory.start().distance(inter) < trajectory.start().distance(closestInter)) {
                    closestInter = inter;
                    closestCollidable = collidable;
                }
            }
        }
        if (closestInter == null) {
            return null;
        } else {
            return new CollisionInfo(closestInter, closestCollidable);
        }
    }

    /**
     * Returns the list of collidables in this environment.
     *
     * @return the list of collidables in this environment.
     */
    public List<Collidable> getList() {
        return list;
    }
}