package engine.window;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by KitK4t on 2018-12-15.
 * The EWindow class is a class that provides almost all of the GLFW functions to
 * manage a window.
 */
public class EWindow {

    /**Video mode currently used.*/
    private GLFWVidMode videoMode;

    /**Title of the window. GLFW does not provide a method to get it so it is stored here.*/
    private String title;

    /**Handle id of the window given by GLFW.
     * This is the way to link this class with GLFW.*/
    private long handle;

    /**Old width and height for fullscreen only.*/
    private int oldWidth, oldHeight;

    /**The window is visible or not. GLFW does not provide a method to get it so it is stored here.*/
    private boolean visible;

    /**The window is iconified or not. GLFW does not provide a method to get it so it is stored here.*/
    private boolean iconified;

    /**Create a window for the engine.
     * @param title Title of the window.
     * @param height Height of the window in pixels.
     * @param width Width of the window in pixels.*/
    public EWindow(String title, int width, int height){
        this.title = title;
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            System.err.println("Could not initialize GLFW.");
            System.exit(-1);
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        this.videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        this.handle = glfwCreateWindow(width, height, title, 0,0);

        if(getHandle() == 0){
            System.err.println("Could not initialize the window.");
            System.exit(-1);
        }

        glfwMakeContextCurrent(getHandle());
        glfwSwapInterval(1);
        GL.createCapabilities();
        GL11.glClearColor(0,0,0,0);

        GL11.glEnable(GL11.GL_DEPTH_TEST | GL11.GL_DEPTH_BUFFER_BIT);

        setPositionCentered();
        setIconified(false);
        setVisible(true);
    }

    /**Set the size of the window.
     * @param height Height of the window.
     * @param width Width of the window.*/
    public void setSize(int width, int height) {
        glfwSetWindowSize(getHandle(), width, height);
    }

    /**Set the mouse grabbed in the window or not.
     * @param grabbed Grab it or ungrab it.*/
    public void setMouseGrabbed(boolean grabbed) {
        if(!isMouseGrabbed() && grabbed)
            glfwSetInputMode(getHandle(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        else if (isMouseGrabbed() && !grabbed)
            glfwSetInputMode(getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public void setFullscreen(boolean fullscreen) {

        boolean wasGrabbed = isMouseGrabbed();
        setMouseGrabbed(false);
        if(fullscreen && !isFullscreen()) {
            this.oldWidth = getWidth();
            this.oldHeight = getHeight();
            glfwSetWindowMonitor(getHandle(), glfwGetPrimaryMonitor(), 0, 0, getVideoMode().width(), getVideoMode().height(), getVideoMode().refreshRate());
        }else if(!fullscreen && isFullscreen()) {
            System.out.println(oldWidth);
            glfwSetWindowMonitor(getHandle(), MemoryUtil.NULL, 0, 0, oldWidth, oldHeight, 0);
            setPositionCentered();
        }

        if(wasGrabbed)
            setMouseGrabbed(true);
    }

    /**Set the window iconified or not.
     * @param iconified True to iconify. False to show again.*/
    public void setIconified(boolean iconified) {
        if(iconified && !isIconified()) {
            glfwIconifyWindow(getHandle());
            this.iconified = true;
        }else if(!iconified && isIconified()){
            glfwRestoreWindow(getHandle());
            this.iconified = false;
        }
    }

    /**Set the window visible or not.
     * @param visible True to set visible. False to hide.*/
    public void setVisible(boolean visible){
        this.visible = visible;
        if(visible)
            glfwShowWindow(getHandle());
        else glfwHideWindow(getHandle());
    }

    /**Close and destroy the window.*/
    public void destroy(){
        Callbacks.glfwFreeCallbacks(getHandle());
        glfwDestroyWindow(getHandle());
    }

    /**Center the window in the middle of the current video mode.*/
    public void setPositionCentered(){
        glfwSetWindowPos(getHandle(),
                (getVideoMode().width() - getWidth()) / 2,
                (getVideoMode().height() - getHeight()) / 2);
    }

    /**Set the title of the window.
     * @param title Title of the window.*/
    public void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(getHandle(), title);
    }

    /**@return Width of the window in pixels.*/
    public int getWidth(){
        IntBuffer buff = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(getHandle(), buff, null);
        return buff.get(0);
    }

    /**@return Height of the window in pixels.*/
    public int getHeight(){
        IntBuffer buff = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(getHandle(), null, buff);
        return buff.get(0);
    }

    /**@return The GLFW video mode currently used.*/
    public GLFWVidMode getVideoMode(){
        return this.videoMode;
    }

    /**@return Title of the window.*/
    public String getTitle(){
        return this.title;
    }

    /**@return The handle of the window.*/
    public long getHandle(){
        return this.handle;
    }

    /**@return X position of the window.*/
    public int getPositionX(){
        IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(getHandle(), buffer, null);
        return buffer.get(0);
    }

    /**@return Y position of the window.*/
    public int getPositionY(){
        IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(getHandle(), null, buffer);
        return buffer.get(0);
    }

    /**@return True if the window is fullscreen mode.*/
    public boolean isFullscreen() {
        return glfwGetWindowMonitor(getHandle()) != 0;
    }

    /**@return True if the mouse is grabbed into the window.*/
    public boolean isMouseGrabbed() {
        return glfwGetInputMode(getHandle(), GLFW_CURSOR) == GLFW_CURSOR_NORMAL ? false : true;
    }

    /**@return True if the window is visible.*/
    public boolean isVisible() {
        return this.visible;
    }

    /**@return True if the window is iconified.*/
    public boolean isIconified() {
        return this.iconified;
    }

    /**@return Mouse x actual coordinate.*/
    public int getMouseX() {
        DoubleBuffer buff = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(getHandle(), buff, null);
        return (int) buff.get(0);
    }

    /**@return Mouse y actual coordinate.*/
    public int getMouseY() {
        DoubleBuffer buff = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(getHandle(), null, buff);
        return (int) buff.get(0);
    }
}
