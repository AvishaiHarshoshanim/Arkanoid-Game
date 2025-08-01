// 207810771 Avishai Harshoshanim
package Sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection is a class that holds a collection of Sprites and enables to notify all Sprites of passing time
 * and to draw all Sprites on a given DrawSurface.
 */
public class SpriteCollection {

    private List<Sprite> list;

    /**
     * Creates a new SpriteCollection.
     */
    public SpriteCollection() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a new Sprite to the collection.
     *
     * @param s the Sprite to add
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * Removes a Sprite from the collection.
     *
     * @param s the Sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }

    /**
     *  Notifies all Sprites in the collection that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listToIterate = new ArrayList<>(this.list);
        for (Sprite sprite : listToIterate) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all Sprites in the collection on the given DrawSurface.
     *
     *  @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> listToIterate = new ArrayList<>(this.list);
        for (Sprite sprite : listToIterate) {
            sprite.drawOn(d);
        }
    }

    /**
     * Returns the list of Sprites in the collection.
     *
     * @return the list of Sprites in the collection
     */
    public List<Sprite> getList() {
        return this.list;
    }
}