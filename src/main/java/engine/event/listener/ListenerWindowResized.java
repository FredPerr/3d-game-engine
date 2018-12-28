package engine.event.listener;

import engine.Engine;
import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventPriority;
import engine.event.event.window.EventKey;
import engine.event.event.window.EventWindowResize;
import engine.render.DefaultRenderer;
import engine.render.Renderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by KitK4t on 2018-12-27.
 */
public class ListenerWindowResized implements EventListener {

    private Engine engine;

    public ListenerWindowResized(Engine engine){
        this.engine = engine;
    }

    /**This method update the aspect ratio of the  projection matrix
     * when the window is resized.*/
    @EventHandler(priority = EventPriority.HIGH)
    public void onWindowResized(EventWindowResize e){
        GL11.glViewport(0,0,e.getWidth(), e.getHeight());
        //This code seem useless.
        //for(Renderer r : engine.getRenderers())
        //    r.updateMatrixProjection(e.getWidth(), e.getHeight());
    }
}
