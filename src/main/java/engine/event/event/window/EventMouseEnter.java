package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when the mouse enter of leave the window
 * area whether or not the window is focus. It collects if the
 * cursor entered or left the window.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventMouseEnter extends Event{

    /**Whether or not the cursor is in the window area.*/
    private boolean entered;

    /**@param entered Whether or not the cursor is in the window area.*/
    public void setValues(boolean entered){
        this.entered = entered;
    }

    /**@return Whether or not the cursor is in the window area.*/
    public boolean isEntered(){
        return this.entered;
    }
}
