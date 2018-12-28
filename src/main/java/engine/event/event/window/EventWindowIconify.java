package engine.event.event.window;

import engine.event.Event;

/**
 * Called when the window is iconified or restored.
 * It stores the current mode of the window (iconified or windowed).
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventWindowIconify extends Event {

    /**Whether or not the window is iconified.*/
    private boolean iconified;

    /**@param iconified Whether or not the window is iconified.*/
    public void setValues(boolean iconified){
        this.iconified = iconified;
    }

    /**@return Whether or not the window is iconified.*/
    public boolean isIconified(){
        return this.iconified;
    }
}
