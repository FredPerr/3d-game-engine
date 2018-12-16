package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the mouse changes of location on the window.
 */
public class EventMouseMovement extends Event{

    private int fromX, fromY, toX, toY;

    public void setValues(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    /**@return The x position of the last position of the cursor.*/
    public int getFromX() {
        return fromX;
    }

    /**@return The y position of the last position of the cursor.*/
    public int getFromY() {
        return fromY;
    }

    /**@return The x position of the new position of the cursor.*/
    public int getToX() {
        return toX;
    }

    /**@return The y position of the new position of the cursor.*/
    public int getToY() {
        return toY;
    }
}
