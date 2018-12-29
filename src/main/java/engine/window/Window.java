package engine.window;

import engine.IEngine;
import engine.event.EventSystem;
import engine.event.event.window.*;
import engine.util.Image;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The Window class is a class that provides
 * almost all of the GLFW functions to manage
 * a window.
 *
 * Created by KitK4t on 2018-12-15.
 */
public class Window {

    private IEngine engine;

    /**Default events*/
    private EventKey eventKey;
    private EventMouseEnter eventMouseEnter;
    private EventMouseButton eventMouseButton;
    private EventWindowResize eventWindowResize;
    private EventMouseMovement eventMouseMovement;
    private EventMouseScroll eventMouseScroll;
    private EventWindowMaximize eventWindowMaximize;
    private EventWindowIconify eventWindowIconify;
    private EventWindowTryClose eventWindowTryClose;
    private EventWindowFullscreen eventWindowFullscreen;

    /**Video mode currently used.*/
    private GLFWVidMode videoMode;

    /**Cursor GLFW image.*/
    private GLFWImage cursor;

    /**Icon GLFW image buffer.*/
    private GLFWImage.Buffer icon;

    private float[] clearColor;

    /**Title of the window. GLFW does not provide a method to get it so it is stored here.*/
    private String title;

    /**Handle id of the window given by GLFW.
     * This is the way to link this class with GLFW.*/
    private long handle;

    /**Old width and height for fullscreen only.*/
    private int oldWidth, oldHeight;

    /**old pos x and y of the mouse cursor.*/
    private int oldMousePosX, oldMousePosY;

    /**The window is visible or not. GLFW does not provide a method to get it so it is stored here.*/
    private boolean visible;

    /**The window is iconified or not. GLFW does not provide a method to get it so it is stored here.*/
    private boolean iconified;

    /**Create a window for the engine.
     * @param title Title of the window.
     * @param height Height of the window in pixels.
     * @param width Width of the window in pixels.*/
    public Window(IEngine engine, String title, int width, int height){
        this.clearColor = new float[]{0,0,0};
        this.engine = engine;
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

        this.eventKey = new EventKey();
        this.eventMouseButton = new EventMouseButton();
        this.eventMouseEnter = new EventMouseEnter();
        this.eventMouseMovement = new EventMouseMovement();
        this.eventMouseScroll = new EventMouseScroll();
        this.eventWindowIconify = new EventWindowIconify();
        this.eventWindowMaximize = new EventWindowMaximize();
        this.eventWindowResize = new EventWindowResize();
        this.eventWindowTryClose = new EventWindowTryClose();
        this.eventWindowFullscreen = new EventWindowFullscreen();

        glfwSetKeyCallback(getHandle(), (long window, int key, int scancode, int action, int mods) -> callbackInputKey(key, action));
        glfwSetWindowSizeCallback(getHandle(), ((window, w, h) -> callbackResized(w, h)));
        glfwSetMouseButtonCallback(getHandle(), (long window, int button, int action, int mods) -> callbackInputMouse(button, action));
        glfwSetCursorEnterCallback(getHandle(), (long window, boolean entered) -> callbackMouseEnter(entered));
        glfwSetCursorPosCallback(getHandle(), (long window, double x, double y) -> callbackMouseMove((int) x, (int) y));
        glfwSetScrollCallback(getHandle(), (long window, double xoffset, double yoffset) -> callbackScroll((int) xoffset, (int) yoffset));
        glfwSetWindowMaximizeCallback(getHandle(), (long window, boolean maximized) -> callbackMaximize(maximized));
        glfwSetWindowIconifyCallback(getHandle(), (long window, boolean iconify) -> callbackIconify(iconify));
        glfwSetWindowCloseCallback(getHandle(), (long window)-> callbackShouldClose());

        glfwMakeContextCurrent(getHandle());
        glfwSwapInterval(1);
        GL.createCapabilities();

        setPositionCentered();
        setIconified(false);
        setVisible(true);
    }

    /**Refresh the clear color and clear the screen pixels to prepare the next render.*/
    void clearScreen(){
        GL11.glClearColor(getClearColor()[0],getClearColor()[1],getClearColor()[2],0);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
    }

    /**Set the clear background color of the window.
     * @param r Red value from 0f to 1f.
     * @param g Green value from 0f to 1f.
     * @param b Blue value from 0f to 1f.
     *
     * */
    public void setClearColor(float r, float g, float b){
        clearColor[0] = r;
        clearColor[1] = g;
        clearColor[2] = b;
    }

    /**Set the image of the cursor.
     * @param image Engine image of the cursor.*/
    public void setCursor(Image image) {
        cursor = GLFWImage.malloc();
        cursor.set(image.getWidth(), image.getHeight(), image.getData());
        long id = glfwCreateCursor(cursor,0,0);
        glfwSetCursor(getHandle(), id);
    }

    /**Set the icon of the window.
     * @param image Engine image of the window icon.*/
    public void setIcon(Image image) {
        GLFWImage iconImg = GLFWImage.malloc();
        icon = GLFWImage.malloc(1);
        iconImg.set(image.getWidth(), image.getHeight(), image.getData());
        icon.put(0, iconImg);
        glfwSetWindowIcon(getHandle(), icon);
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
            eventWindowFullscreen.setValues(true);
            EventSystem.callEvent(eventWindowFullscreen);
        }else if(!fullscreen && isFullscreen()) {
            glfwSetWindowMonitor(getHandle(), MemoryUtil.NULL, 0, 0, oldWidth, oldHeight, 0);
            setPositionCentered();
            eventWindowFullscreen.setValues(false);
            EventSystem.callEvent(eventWindowFullscreen);
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

    /**@return Clear color of the window.
     * RGB channel order with values from 0f to 1f.*/
    public float[] getClearColor(){
        return this.clearColor;
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

    /**@return True if the window is trying to close.*/
    public boolean isCloseRequest(){
        return glfwWindowShouldClose(getHandle());
    }

    protected void callbackIconify(boolean iconified){
        eventWindowIconify.setValues(iconified);
        EventSystem.callEvent(eventWindowIconify);
    }

    protected void callbackMaximize(boolean maximized){
        eventWindowMaximize.setValues(maximized);
        EventSystem.callEvent(eventWindowMaximize);
    }

    protected void callbackScroll(int addX, int addY){
        eventMouseScroll.setValues(addX, addY);
        EventSystem.callEvent(eventMouseScroll);
    }

    protected void callbackMouseMove(int x, int y){
        eventMouseMovement.setValues(oldMousePosX, oldMousePosY, x, y);
        EventSystem.callEvent(eventMouseMovement);
        this.oldMousePosX = x;
        this.oldMousePosY = y;
    }

    protected void callbackMouseEnter(boolean entered){
        eventMouseEnter.setValues(entered);
        EventSystem.callEvent(eventMouseEnter);
    }

    protected void callbackInputKey(int key, int action){
        eventKey.setValues(key, action);
        EventSystem.callEvent(eventKey);
    }

    protected void callbackInputMouse(int button, int action){
        eventMouseButton.setValues(button, action);
        EventSystem.callEvent(eventMouseButton);
    }

    protected void callbackShouldClose(){
        EventSystem.callEvent(eventWindowTryClose);
        engine.getLoop().stop();
    }

    protected void callbackResized(int width, int height){
        eventWindowResize.setValues(width, height, oldWidth, oldHeight);
        EventSystem.callEvent(eventWindowResize);
    }
}
