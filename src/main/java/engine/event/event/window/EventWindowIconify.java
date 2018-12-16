package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the window is iconified or restored.
 */
public class EventWindowIconify extends Event {

    private boolean iconified;

    public void setValues(boolean iconified){
        this.iconified = iconified;
    }

    /**@return True when the window is iconified and false when it is restored.*/
    public boolean isIconified(){
        return this.iconified;
    }
}
