package engine.event.event.render;

import engine.event.Event;
import engine.render.Camera;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * This event is called when the field of view of a camera changes.
 */
public class EventCameraSettingChange extends Event{

    private CameraSettings currentSetting;
    private Camera camera;

    /**@param setting Setting currently changing.*/
    public void setValues(Camera camera, CameraSettings setting){
        this.camera = camera;
        this.currentSetting = setting;
    }

    /**@return Camera that the fov changed.*/
    public Camera getCamera() {
        return camera;
    }

    /**@return Setting that had been changed.*/
    public CameraSettings getChangedSetting(){
        return this.currentSetting;
    }
}
