package test;

import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.event.window.*;
import org.lwjgl.glfw.GLFW;

/**
 * Created by KitK4t on 2018-12-16.
 */
public class ListenerTest implements EventListener {

    private TestEngine engine;

    public ListenerTest(TestEngine engine){
        this.engine = engine;
    }

    @EventHandler
    public void onTestKey(EventKey e){
        if(engine.getWindow().isMouseGrabbed() && e.getKey() == GLFW.GLFW_KEY_ESCAPE && e.getAction() == GLFW.GLFW_RELEASE)
            engine.getWindow().setMouseGrabbed(false);
    }

    @EventHandler
    public void onTestMouse(EventMouseButton e){
        if(!engine.getWindow().isMouseGrabbed() && e.getAction() == GLFW.GLFW_RELEASE){
            engine.getWindow().setMouseGrabbed(true);
        }
    }

    @EventHandler
    public void onTestMousePos(EventMouseMovement e){
        if(engine.getWindow().isMouseGrabbed()){
            float addX = (e.getToX()-e.getFromX()) * engine.getCamera().getSensitivity();
            float addY = (e.getToY()-e.getFromY()) * engine.getCamera().getSensitivity();
            engine.getCamera().rotate(0,addX,addY);
        }
    }

    @EventHandler
    public void onTestMouseScroll(EventMouseScroll e){
    }

    @EventHandler
    public void onTestMouseEnter(EventMouseEnter e){
    }

    @EventHandler
    public void onTestWindowIconify(EventWindowIconify e){
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
