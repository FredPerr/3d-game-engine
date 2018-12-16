package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the window is maximizing minimizing.
 */
public class EventWindowMaximize extends Event{

    private boolean maximized;

    public void setValues(boolean maximized){
        this.maximized = maximized;
    }

    /**@return True if the window is maximized and false if not.*/
    public boolean isMaximized(){
        return this.maximized;
    }
}
