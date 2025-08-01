// 207810771 Avishai Harshoshanim
package Collision;
/**
 * The HitNotifier interface represents an object that can notify listeners of hit events.
 * Implementing classes can manage a list of HitListeners and provide methods to add and remove them.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners for hit events.
     * The specified HitListener will be notified when hit events occur.
     *
     * @param hl The HitListener to be added.
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a HitListener from the list of listeners for hit events.
     * The specified HitListener will no longer be notified when hit events occur.
     *
     * @param hl The HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}