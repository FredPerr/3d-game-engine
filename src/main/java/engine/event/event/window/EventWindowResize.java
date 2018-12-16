package engine.event.event.window;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * Called when the window is resized.
 */
public class EventWindowResize extends Event{

    private int width, height, lastWidth, lastHeight;

    public void setValues(int width, int height, int lastHeight, int lastWidth){
        this.height = height;
        this.width = width;
        this.lastHeight = lastHeight;
        this.lastWidth = lastWidth;
    }

    /**@return The width of the window in pixels.*/
    public int getLastWidth(){
        return this.lastWidth;
    }

    /**@return The height of the window in pixels.*/
    public int getLastHeight(){
        return this.lastHeight;
    }

    /**@return The width of the window in pixels.*/
    public int getWidth(){
        return this.width;
    }

    /**@return The height of the window in pixels.*/
    public int getHeight(){
        return this.height;
    }
}
