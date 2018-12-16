package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when a key is pressed, release or still pressed.
 */
public class EventKey extends Event {

    private int key, action;

    public void setValues(int key, int action){
        this.key = key;
        this.action = action;
    }

    /**@return The GLFW key id.*/
    public int getKey(){
        return this.key;
    }

    /**@return The GLFW action id.*/
    public int getAction(){
        return this.action;
    }
}
