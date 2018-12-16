package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the mouse scroll in y (and xwhich is rare);
 */
public class EventMouseScroll extends Event{

    private int addX, addY;

    public void setValues(int addX, int addY) {
        this.addX = addX;
        this.addY = addY;
    }

    /**@return The amount added in x. Can be negative.*/
    public int getAddX(){
        return this.addX;
    }

    /**@return The amount added in y. Can be negative.*/
    public int getAddY(){
        return this.addY;
    }
}
