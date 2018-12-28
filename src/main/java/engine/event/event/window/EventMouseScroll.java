package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when the mouse scrolls.
 * It collects the y amount added and the x one
 * too. Only few mouses can scroll in the x axis
 * so it is really rare to have a different
 * value than 0.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventMouseScroll extends Event{

    /**Amount added in the mouse scroll.*/
    private int addX, addY;

    /**@param addY Amount added in the y axis of the scroll.
     * @param addX Amount added in the x axis of the scroll. (This parameter is rarely different than 0).*/
    public void setValues(int addX, int addY) {
        this.addX = addX;
        this.addY = addY;
    }

    /**@return The amount added in x axis. It can be negative.
     * This parameter is rarely different than 0*/
    public int getAddX(){
        return this.addX;
    }

    /**@return The amount added in y axis. It can be negative.*/
    public int getAddY(){
        return this.addY;
    }
}
