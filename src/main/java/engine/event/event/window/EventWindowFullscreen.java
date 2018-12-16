package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the window goes to windowed mode to fullscreen or the contrary.
 */
public class EventWindowFullscreen extends Event {

    private boolean fullscreen;

    public void setValues(boolean fullscreen){
        this.fullscreen = fullscreen;
    }

    /**@return True if the window is fullscreen. False if it is windowed mode.*/
    public boolean isFullscreen(){
        return this.fullscreen;
    }
}
