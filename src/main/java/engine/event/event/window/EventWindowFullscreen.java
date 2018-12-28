package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when the window goes to windowed mode to
 * fullscreen or the contrary. It collects the current mode.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventWindowFullscreen extends Event {

    /**Current window mode.*/
    private boolean fullscreen;

    /**@param fullscreen Current window mode (true to fullscreen
     *                    and false to windowed mode).*/
    public void setValues(boolean fullscreen){
        this.fullscreen = fullscreen;
    }

    /**@return True if the window is fullscreen mode and false if it is windowed mode.*/
    public boolean isFullscreen(){
        return this.fullscreen;
    }
}
