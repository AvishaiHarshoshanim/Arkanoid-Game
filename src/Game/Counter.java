// 207810771 Avishai Harshoshanim
package Game;
/**
 * The Counter class represents a simple counter that can be incremented,
 * decremented, and queried for its current value.
 */
public class Counter {
    private int counter;

    /**
     * Increases the counter by a specified number.
     *
     * @param number The number to be added to the counter.
     */
    void increase(int number) {
        this.setCounter(this.getValue() + number);
    }

    /**
     * Decreases the counter by a specified number.
     *
     * @param number The number to be subtracted from the counter.
     */
    void decrease(int number) {
        this.setCounter(this.getValue() - number);
    }

    /**
     * Returns the current value of the counter.
     *
     * @return The current value of the counter.
     */
    int getValue() {
        return counter;
    }

    /**
     * Sets the value of the counter.
     *
     * @param counter The new value for the counter.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Returns a string representation of the counter.
     *
     * @return The string representation of the counter.
     */
    @Override
    public String toString() {
        return Integer.toString(counter);
    }
}