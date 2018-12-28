package engine.event.event.render;

import engine.event.Event;
import engine.render.Camera;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * This event is called when the field of view of a camera changes.
 */
public class EventFovChange extends Event{

    private Camera camera;
    private float oldFov, newFov;

    public void setValues(Camera camera, float oldFov, float newFov){
        this.camera = camera;
        this.oldFov = oldFov;
        this.newFov = newFov;
    }


    /**@return Camera that the fov changed.*/
    public Camera getCamera() {
        return camera;
    }

    /**@return Old fov of the camera.*/
    public float getOldFov() {
        return oldFov;
    }

    /**@return New fov of the camera.*/
    public float getNewFov() {
        return newFov;
    }
}
