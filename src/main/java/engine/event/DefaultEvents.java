package engine.event;

import engine.event.event.render.EventCameraSettingChanged;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * The default events class stores all the events of the engine ny default (not window events).
 */
public class DefaultEvents {

    public static EventCameraSettingChanged eventCameraSettingChanged = new EventCameraSettingChanged();}
