package engine.event.event.window;

import engine.event.Event;

/**
 * This event is called when a {@link org.lwjgl.glfw.GLFW} key
 * callback is called. A {@link org.lwjgl.glfw.GLFW} key number id and a
 * {@link org.lwjgl.glfw.GLFW} action are collected and accessible.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class EventKey extends Event {

    /**The key and the action collected.*/
    private int key, action;

     /**@param action {@link org.lwjgl.glfw.GLFW} action of the key.
     *                The possible actions are {@code GLFW.GLFW_RELEASE},
     *                {@code GLFW.GLFW_PRESS}, {@code GLFW_REPEAT}.
     * @param key     {@link org.lwjgl.glfw.GLFW} key number id. The
     *                possible keys start with {@code GLFW.GLFW_KEY_}.
     */
    public void setValues(int key, int action){
        this.key = key;
        this.action = action;
    }

    /**@return The {@link org.lwjgl.glfw.GLFW} key number id.
     * The possible keys start with {@code GLFW.GLFW_KEY_} code.*/
    public int getKey(){
        return this.key;
    }

    /**@return The {@link org.lwjgl.glfw.GLFW} action of the key.
     * The possible actions are {@code GLFW.GLFW_RELEASE},
     * {@code GLFW.GLFW_PRESS}, {@code GLFW_REPEAT}.*/
    public int getAction(){
        return this.action;
    }
}
