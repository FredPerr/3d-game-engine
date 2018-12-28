package engine.render;

import engine.event.DefaultEvents;
import engine.event.EventSystem;
import engine.event.event.render.CameraSettings;
import engine.util.Location;
import engine.util.Rotation;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * A camera is an object with a field of view, a render distance in near and far.
 */
public class Camera {

    private Location location;
    private Rotation rotation;
    private boolean pitchMinMax;
    private float fov,
            renderDistanceNear,
            renderDistanceFar,
            sensitivity = 0.2f,
            pitchMax = 90,
            pitchMin = -90;

    /**Create a camera.
     * @param fov Field of view angle of the camera.
     * @param renderDistanceFar Distance from which it stops rendering in far.
     * @param renderDistanceNear Distance from which the camera starts rendering in near.*/
    public Camera(Location location, Rotation rotation, float fov, float renderDistanceNear, float renderDistanceFar,
                  boolean pitchMinMax, float sensitivity,
                  float pitchMax, float pitchMin) {
        this.fov = fov;
        this.renderDistanceNear = renderDistanceNear;
        this.renderDistanceFar = renderDistanceFar;
        this.pitchMin = pitchMin;
        this.pitchMax = pitchMax;
        this.pitchMinMax = pitchMinMax;
        this.sensitivity = sensitivity;
        this.location = location;
        this.rotation = rotation;
    }

    /**Rotate the camera. The pitch max and min is taking in account.
     * @param addRoll Add value of the roll.
     * @param addYaw Add value of the yaw.
     * @param addPitch Add value of the pitch.*/
    public void rotate(float addRoll, float addYaw, float addPitch) {
        rotation.add(addRoll, addYaw, addPitch);
        if(pitchMinMax) {
            if(rotation.getPitch()+addPitch > pitchMax) rotation.setPitch(pitchMax);
            if(rotation.getPitch()+addPitch < pitchMin) rotation.setPitch(pitchMin);
        }
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.ROTATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**Create a location to add to the current location of the camera to move.
     * @param distance Distance from the last location.
     * @param considerPitch Pitch is consider and the y axis change proportionally to the angle of the pitch.*/
    public void goForward(float distance, boolean considerPitch) {
        float pitchFactorX = (float) Math.cos(Math.toRadians(rotation.getPitch()));
        float pitchFactorZ = (float) Math.cos(Math.toRadians(rotation.getPitch()));
        float dx = (float)(Math.sin(Math.toRadians(rotation.getYaw())) * distance);
        float dy = (float) Math.sin(Math.toRadians(rotation.getPitch())) * distance;
        float dz = (float)(Math.cos(Math.toRadians(rotation.getYaw())) * distance);

        if(considerPitch) {
            dx*=pitchFactorX;
            dz*=pitchFactorZ;
            getLocation().addY(-dy);
        }
        getLocation().add(dx, 0, -dz);
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**Create a location to add to the current location of the camera to move.
     * @param distance Distance from the last location.
     * @param considerPitch Pitch is consider and the y axis change proportionally to the angle of the pitch.*/
    public void goBackward(float distance, boolean considerPitch) {
        float pitchFactorX = (float) Math.cos(Math.toRadians(rotation.getPitch()));
        float pitchFactorZ = (float) Math.cos(Math.toRadians(rotation.getPitch()));
        float dx = (float)(Math.sin(Math.toRadians(rotation.getYaw())) * distance);
        float dy = (float) Math.sin(Math.toRadians(rotation.getPitch())) * distance;
        float dz = (float)(Math.cos(Math.toRadians(rotation.getYaw())) * distance);

        if(considerPitch) {
            dx*=pitchFactorX;
            dz*=pitchFactorZ;
            getLocation().addY(dy);
        }
        getLocation().add(-dx, 0, dz);
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**Create a location to add to the current location of the camera to move.
     * @param distance Distance from the last location.*/
    public void goLeftward(float distance) {
        float dx = (float) (Math.sin(Math.toRadians(90-rotation.getYaw())) * distance);
        float dz = (float) (Math.cos(Math.toRadians(90-rotation.getYaw())) * distance);
        getLocation().add(-dx, 0, -dz);
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**Create a location to add to the current location of the camera to move.
     * @param distance Distance from the last location.*/
    public void goRightward(float distance) {
        float dx = (float) (Math.sin(Math.toRadians(90-rotation.getYaw())) * distance);
        float dz = (float) (Math.cos(Math.toRadians(90-rotation.getYaw())) * distance);
        getLocation().add(dx, 0, dz);
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**Create a location to add to the current location of the camera to move.
     * @param distance Distance from the last location.*/
    public void goUpward(float distance) {
        getLocation().add(0, distance, 0);
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**Create a location to add to the current location of the camera to move.
     * @param distance Distance from the last location.*/
    public void goDownward(float distance) {
        getLocation().add(0, -distance, 0);
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCATION);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**@return Field of view angle of the camera.*/
    public float getFov() {
        return fov;
    }

    /**Set the filed of view angle of the camera.
     * @param fov Filed of view angle on 360.*/
    public void setFov(float fov) {
        this.fov = fov;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.FOV);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**@return Render distance from the camera that starts to render.*/
    public float getRenderDistanceNear() {
        return renderDistanceNear;
    }

    /**Set the render near distance.
     * @param renderDistanceNear Distance from the camera that starts to render.*/
    public void setRenderDistanceNear(float renderDistanceNear) {
        this.renderDistanceNear = renderDistanceNear;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.RENDER_DISTANCE_NEAR);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**@return Render distance from the camera that stops to render.*/
    public float getRenderDistanceFar() {
        return renderDistanceFar;
    }

    /**Set the render far distance.
     * @param renderDistanceFar Distance from the camera that stops to render.*/
    public void setRenderDistanceFar(float renderDistanceFar) {
        this.renderDistanceFar = renderDistanceFar;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.RENDER_DISTANCE_FAR);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**@return location of the camera.*/
    public Location getLocation(){
        return this.location;
    }

    /**@return Rotation of the camera.*/
    public Rotation getRotation(){
        return this.rotation;
    }

    /**
     * @return True if the pitch is blocked over and under a certain value to prevent upsidedown camera.
     */
    public boolean isPitchMinMax() {
        return pitchMinMax;
    }

    /**
     * @param pitchMinMax the pitch_min_max to set
     */
    public void setPitchMinMax(boolean pitchMinMax) {
        this.pitchMinMax = pitchMinMax;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.LOCK_PITCH);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**
     * @return the maximum pitch the camera can get.
     */
    public float getPitchMax() {
        return pitchMax;
    }

    /**
     * @param pitchMax the pitch maximum the camera can reach.
     */
    public void setPitchMax(float pitchMax) {
        this.pitchMax = pitchMax;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.PITCH_MAX);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**
     * @return the minimum pitch the camera can reach.
     */
    public float getPitchMin() {
        return pitchMin;
    }

    /**
     * @param pitchMin the pitch minimum the camera can reach.
     */
    public void setPitchMin(float pitchMin) {
        this.pitchMin = pitchMin;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.PITCH_MIN);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }

    /**
     * @return the sensitivity of the camera.
     */
    public float getSensitivity() {
        return sensitivity;
    }

    /**
     * @param sensitivity the sensitivity to set.
     */
    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
        DefaultEvents.eventCameraSettingChanged.setValues(this, CameraSettings.SENSITIVITY);
        EventSystem.callEvent(DefaultEvents.eventCameraSettingChanged);
    }
}
