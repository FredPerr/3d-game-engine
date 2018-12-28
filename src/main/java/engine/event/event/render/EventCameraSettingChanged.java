package engine.event.event.render;

import engine.event.Event;
import engine.render.Camera;

/**
 * This event is called when the a camera setting changes.
 * These possible settings are in the enum class
 * {@link engine.event.event.render.CameraSettings}. The
 * {@link engine.render.Camera} object and the
 * {@link engine.event.event.render.CameraSettings} edited
 * are accessible.
 *
 * Created by KitK4t on 2018-12-27.
 */
public class EventCameraSettingChanged extends Event{

    /**The current setting edited.*/
    private CameraSettings editedSetting;
    /**The camera that had a value edited.*/
    private Camera camera;

    /**@param camera Camera that a value has been edited.
     * @param editedSetting The setting that have been edited in the camera.*/
    public void setValues(Camera camera, CameraSettings editedSetting){
        this.camera = camera;
        this.editedSetting = editedSetting;
    }

    /**@return Camera that a value has been changed.*/
    public Camera getCamera() {
        return this.camera;
    }

    /**@return Setting that had been edited in the camera.*/
    public CameraSettings getEditedSetting(){
        return this.editedSetting;
    }
}
