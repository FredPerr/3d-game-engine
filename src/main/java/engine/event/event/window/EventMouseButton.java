package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when a {@link org.lwjgl.glfw.GLFW} mouse button
 * callback is called. A {@link org.lwjgl.glfw.GLFW} mouse button number id and a
 * {@link org.lwjgl.glfw.GLFW} action are collected and accessible.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventMouseButton extends Event{

    /**The button and the action collected.*/
    private int button, action;

    /**@param action  {@link org.lwjgl.glfw.GLFW} action of the button.
     *                The possible actions are {@code GLFW.GLFW_RELEASE},
     *                {@code GLFW.GLFW_PRESS}, {@code GLFW_REPEAT}.
     * @param button  {@link org.lwjgl.glfw.GLFW} button number id. The
     *                possible buttons start with {@code GLFW.GLFW_MOUSE_BUTTON_}.
     */
    public void setValues(int button, int action){
        this.button = button;
        this.action = action;
    }

    /**@return The {@link org.lwjgl.glfw.GLFW} mouse button number id.
     * The possible buttons start with {@code GLFW.GLFW_MOUSE_BUTTON_} code.*/
    public int getButton(){
        return this.button;
    }

    /**@return The GLFW action id.*/
    public int getAction(){
        return this.action;
    }
}
