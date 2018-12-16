package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 *  * Called when a mouse button is pressed or released.
 */
public class EventMouseButton extends Event{

    private int button, action;

    public void setValues(int key, int action){
        this.button = key;
        this.action = action;
    }

    /**@return The GLFW button id.*/
    public int getButton(){
        return this.button;
    }

    /**@return The GLFW action id.*/
    public int getAction(){
        return this.action;
    }
}
