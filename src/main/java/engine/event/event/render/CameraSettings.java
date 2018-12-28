package engine.event.event.render;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * The camera setting enum class provides the list of the possible
 * modifications of an {@link engine.render.Camera} object.
 * An enum should be returned to get the type of the
 * modification in the {@link engine.render.Camera} object
 * that have been performed. Each of these enum are called on a
 * {@link engine.event.event.render.EventCameraSettingChange} event.
 * event. They are all indicated when they should be used.
 */
public enum CameraSettings {

    /**When the field of view (fov) changes.*/
    FOV,
    /**When the render distance in far plan changes.*/
    RENDER_DISTANCE_FAR,
    /**When the render distance in near plan changes.*/
    RENDER_DISTANCE_NEAR,
    /**When the location changes.*/
    LOCATION,
    /**When the rotation changes.*/
    ROTATION,
    /**When the sensitivity changes.*/
    SENSITIVITY,
    /**When the maximum and minimum pitch is enabled or disabled.*/
    LOCK_PITCH,
    /**When the pitch minimum changes.*/
    PITCH_MIN,
    /**When the pitch maximum changes.*/
    PITCH_MAX
}
