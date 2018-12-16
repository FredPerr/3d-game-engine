package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the mouse enter of leave the window area.
 */
public class EventMouseEnter extends Event{

    private boolean entered;

    public void setValues(boolean entered){
        this.entered = entered;
    }

    /**@return True if the cursor enter the window. False if the cursor left the window.*/
    public boolean isEntered(){
        return this.entered;
    }
}
