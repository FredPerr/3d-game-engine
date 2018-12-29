package engine.event.listener;

import engine.Engine;
import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventPriority;
import engine.event.event.render.CameraSettings;
import engine.event.event.render.EventCameraSettingChanged;
import engine.render.Renderer;

/**
 * The listener camera change class listen the camera changes.
 *
 * Created by KitK4t on 2018-12-27.
 */
public class ListenerCameraChange implements EventListener {

    private Engine engine;

    public ListenerCameraChange(Engine engine){
        this.engine = engine;
    }

    /**Updade the matrix when a setting changes in the camera except for the location and the rotation.*/
    @EventHandler(priority = EventPriority.HIGH)
    public void onCameraSettingsChange(EventCameraSettingChanged e){
        if(e.getEditedSetting() != CameraSettings.LOCATION && e.getEditedSetting() != CameraSettings.LOCATION)
        for(Renderer r : engine.getRenderers())
            r.updateMatrixProjection(engine.getWindow().getWidth(), engine.getWindow().getHeight());

    }
}
