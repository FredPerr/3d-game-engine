package engine.render;

import engine.event.DefaultEvents;
import engine.event.EventSystem;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * A camera is an object with a field of view, a render distance in near and far.
 */
public class Camera {

    private float fov, renderDistanceNear, renderDistanceFar;

    /**Create a camera.
     * @param fov Field of view angle of the camera.
     * @param renderDistanceFar Distance from which it stops rendering in far.
     * @param renderDistanceNear Distance from which the camera starts rendering in near.*/
    public Camera(float fov, float renderDistanceNear, float renderDistanceFar) {
        this.fov = fov;
        this.renderDistanceNear = renderDistanceNear;
        this.renderDistanceFar = renderDistanceFar;
    }

    /**@return Field of view angle of the camera.*/
    public float getFov() {
        return fov;
    }

    /**Set the filed of view angle of the camera.
     * @param fov Filed of view angle on 360.*/
    public void setFov(float fov) {
        //TODO add event here to catch change fov to refresh the matrix projection.
        DefaultEvents.fovChange.setValues(this, getFov(), fov);
        EventSystem.callEvent(DefaultEvents.fovChange);
        this.fov = fov;
    }

    /**@return Render distance from the camera that starts to render.*/
    public float getRenderDistanceNear() {
        return renderDistanceNear;
    }

    /**Set the render near distance.
     * @param renderDistanceNear Distance from the camera that starts to render.*/
    public void setRenderDistanceNear(float renderDistanceNear) {
        DefaultEvents.renderDistanceNear.setValues(getRenderDistanceNear(), renderDistanceNear);
        EventSystem.callEvent(DefaultEvents.renderDistanceNear);
        this.renderDistanceNear = renderDistanceNear;
    }

    /**@return Render distance from the camera that stops to render.*/
    public float getRenderDistanceFar() {
        return renderDistanceFar;
    }

    /**Set the render far distance.
     * @param renderDistanceFar Distance from the camera that stops to render.*/
    public void setRenderDistanceFar(float renderDistanceFar) {
        DefaultEvents.renderDistanceFar.setValues(getRenderDistanceFar(), renderDistanceFar);
        EventSystem.callEvent(DefaultEvents.renderDistanceFar);
        this.renderDistanceFar = renderDistanceFar;
    }
}
