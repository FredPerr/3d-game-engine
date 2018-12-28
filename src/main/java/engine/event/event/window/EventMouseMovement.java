package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when the cursor moves on the window area.
 * It collects the old location of the mouse and the new one. The
 * locations starts from the top-left corner of the window.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventMouseMovement extends Event{

    /**Locations of the mouse on the window.*/
    private int fromX, fromY, toX, toY;

    /**@param fromX Old x location of the mouse position on the screen.
     * @param fromY Old y location of the mouse position on the screen.
     * @param toX New x location of the mouse position on the screen.
     * @param toY New y location of the mouse position on the screen.*/
    public void setValues(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    /**@return The x position of the last position of the cursor on the window.*/
    public int getFromX() {
        return fromX;
    }

    /**@return The y position of the last position of the cursor on the window.*/
    public int getFromY() {
        return fromY;
    }

    /**@return The x position of the new position of the cursor on the window.*/
    public int getToX() {
        return toX;
    }

    /**@return The y position of the new position of the cursor on the window.*/
    public int getToY() {
        return toY;
    }
}
