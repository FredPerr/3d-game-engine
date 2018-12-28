package engine.event.listener;

import engine.Engine;
import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventPriority;
import engine.event.event.render.EventCameraSettingChanged;
import engine.render.Renderer;

/**
 * Created by KitK4t on 2018-12-27.
 */
public class ListenerCameraChange implements EventListener {

    private Engine engine;

    public ListenerCameraChange(Engine engine){
        this.engine = engine;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCameraSettingsChange(EventCameraSettingChanged e){
        for(Renderer r : engine.getRenderers())
            r.updateMatrixProjection(engine.getWindow().getWidth(), engine.getWindow().getHeight());

    }
}
