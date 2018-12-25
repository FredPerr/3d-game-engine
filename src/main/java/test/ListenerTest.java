package test;

import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventSystem;
import engine.event.event.window.*;
import engine.window.Window;

/**
 * Created by KitK4t on 2018-12-16.
 */
public class ListenerTest implements EventListener {

    @EventHandler
    public void onTestKey(EventKey e){
        //System.out.println("Key press: "+e.getKey() + " " + e.getAction());
    }

    @EventHandler
    public void onTestMouse(EventMouseButton e){
        //System.out.println("Mouse press: "+e.getButton() + " " + e.getAction());
    }

    @EventHandler
    public void onTestMousePos(EventMouseMovement e){
        //System.out.println("Mouse move: "+ e.getFromX() + " to " + e.getToX());
    }

    @EventHandler
    public void onTestMouseScroll(EventMouseScroll e){
        //System.out.println("Mouse move: "+ e.getAddX() + " & " + e.getAddY());
    }

    @EventHandler
    public void onTestMouseEnter(EventMouseEnter e){
        //System.out.println("Mouse enter: "+ e.isEntered());
    }

    @EventHandler
    public void onTestWindowIconify(EventWindowIconify e){
        //System.out.println("Window iconified: "+ e.isIconified());
    }

    @EventHandler
    public void onTestWindowMaximize(EventWindowMaximize e){
        //System.out.println("Window maximize: "+ e.isMaximized());
    }

    @EventHandler
    public void onTestWindowResize(EventWindowResize e){
        //System.out.println("Window resize: "+ e.getWidth() + " & " + e.getHeight());
    }

    @EventHandler
    public void onTestWindowFullscreen(EventWindowFullscreen e){
        //System.out.println("Window fullscreen: "+ e.isFullscreen());
    }

    @EventHandler
    public void onTestWindowTryClose(EventWindowTryClose e){
        //System.out.println("Window tries to close.");
    }
}
