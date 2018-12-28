package engine.event.event.window;

import engine.event.Event;

/**
 * Called when the window is maximized minimized.
 * It collects whether of not the window is maximized.
 *
 * Created by KitK4t on 2018-12-16.
 *
 *
 */
public class EventWindowMaximize extends Event{

    /**Whether of not the window is maximized.*/
    private boolean maximized;

    /**@param maximized Whether of not the window is maximized.*/
    public void setValues(boolean maximized){
        this.maximized = maximized;
    }

    /**@return Whether of not the window is maximized.*/
    public boolean isMaximized(){
        return this.maximized;
    }
}
