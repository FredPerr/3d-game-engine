package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when the window is resized.
 * It collects the last and the new dimension of the window in pixels.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventWindowResize extends Event{

    /**Last and nwe dimensions of the window.*/
    private int width, height, lastWidth, lastHeight;

    /**@param height New height of the window in pixels.
     * @param lastHeight Old height of the window in pixels.
     * @param lastWidth Old width of the window in pixels.
     * @param width New width of the window in pixels.*/
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
